package dao.daoImpl;

import dao.DepartmentDao;
import entity.Department;
import entity.Lector;
import entity.LectorDegree;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {
    private Session currentSession;
    private static final String QUERY_DEGREE =
            "SELECT count(L) FROM Lector L join L.departments D WHERE D.department_name = :name group by L.degree having  L.degree = :var1";

    public Session openSessionTransaction() {
        currentSession = HibernateUtils.getSessionFactory().openSession();
        currentSession.beginTransaction();
        return currentSession;

    }

    public Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public Optional<Department> get(int departmentId) {
        return Optional.ofNullable(getCurrentSession().get(Department.class, departmentId));
    }

    @Override
    public List<Department> getAll() {
        return (List<Department>) getCurrentSession().createQuery("from Department").list();
    }

    @Override
    public void save(Department department) {
        getCurrentSession().persist(department);
    }


    @Override
    public void addDepartmentToLector(Department department, Lector lector) {
        department.addLector(lector);
        getCurrentSession().persist(department);
    }

    @Override
    public List<Lector> getAllLectorsByDepartmentId(int departmentId) {
        Query query = getCurrentSession().createQuery
                ("select L from Lector L  join L.departments D where D.id_department = :id");
        query.setParameter("id", departmentId);
        return (List<Lector>) query.getResultList();
    }

    @Override
    public void update(Department department) {
        getCurrentSession().update(department);
    }

    @Override
    public void delete(Department department) {
        getCurrentSession().delete(department);
    }

    @Override
    public void deleteAll() {
        List<Department> departments = getAll();
        for (Department d : departments) {
            getCurrentSession().delete(d);
        }
    }

    @Override
    public List<String> getHeadOfTheDepartmentName(String departmentName) {
        Query query = getCurrentSession().createQuery
                ("SELECT D.head_of_department_name FROM Department D WHERE D.department_name LIKE :name");
        query.setParameter("name", "%" + departmentName + "%");
        return query.getResultList();
    }

    @Override
    public Optional<Long> getAssociateProfessorsGroupedByDepartmentName(String departmentName) {
        Query query = getCurrentSession().createQuery
                (QUERY_DEGREE);
        query.setParameter("name", departmentName);
        query.setParameter("var1", LectorDegree.ASSOCIATE_PROFESSOR);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<Long> getProfessorsGroupedByDepartmentName(String departmentName) {
        Query query = getCurrentSession().createQuery
                (QUERY_DEGREE);
        query.setParameter("name", departmentName);
        query.setParameter("var1", LectorDegree.PROFESSOR);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<Long> getAssistanceGroupedByDepartmentName(String departmentName) {
        Query query = getCurrentSession().createQuery
                (QUERY_DEGREE);
        query.setParameter("name", departmentName);
        query.setParameter("var1", LectorDegree.ASSISTANT);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<Double> getAverageSalaryForDepartmentName(String departmentName) {
        Query query = getCurrentSession().createQuery
                ("select AVG(L.salary) from Lector L  join L.departments D where D.department_name = :name");
        query.setParameter("name", departmentName);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<Long> getCountOfEmployeesForDepartmentName(String departmentName) {
        Query query = getCurrentSession().createQuery
                ("select count(L) from Lector L  join L.departments D where D.department_name = :name");
        query.setParameter("name", departmentName);
        return query.uniqueResultOptional();
    }
}
