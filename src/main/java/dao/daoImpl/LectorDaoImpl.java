package dao.daoImpl;

import dao.LectorDao;
import entity.Department;
import entity.Lector;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;
import java.util.Optional;

public class LectorDaoImpl implements LectorDao {
    private Session currentSession;

    public Session openSessionTransaction() {
        currentSession = HibernateUtils.getSessionFactory().openSession();
        currentSession.beginTransaction();
        return currentSession;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public Optional<Lector> get(int lectorId) {
        return Optional.ofNullable(getCurrentSession().get(Lector.class, lectorId));

    }

    @Override
    public List<Lector> getAll() {
        return getCurrentSession().createQuery("from Lector").list();
    }

    @Override
    public void save(Lector lector) {

        getCurrentSession().persist(lector);
    }

    @Override
    public void update(Lector lector) {

        getCurrentSession().update(lector);
    }

    @Override
    public void delete(Lector lector) {

        getCurrentSession().delete(lector);
    }

    @Override
    public void deleteAll() {
        List<Lector> lectors = getAll();
        for (Lector l : lectors) {
            getCurrentSession().delete(l);

        }
    }

    @Override
    public void addLectorToDepartment(Lector lector, Department department) {
        lector.addDepartment(department);
        getCurrentSession().persist(lector);

    }

    @Override
    public List<Department> getAllDepartmentsByLectorId(int lectorId) {
        Query query = getCurrentSession().createQuery
                ("select D from Department D inner join D.lectors L where L.id_lector = :id");
        query.setParameter("id", lectorId);
        List<Department> list = query.getResultList();
        return list;
    }
}

