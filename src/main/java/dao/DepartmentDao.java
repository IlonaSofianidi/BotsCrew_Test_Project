package dao;

import entity.Department;
import entity.Lector;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {
    Optional<Department> get(int departmentId);

    List<Department> getAll();

    void save(Department department);

    void update(Department department);

    void delete(Department department);

    void deleteAll();

    void addDepartmentToLector(Department department, Lector lector);

    List<Lector> getAllLectorsByDepartmentId(int departmentId);

    List<String> getHeadOfTheDepartmentName(String departmentName);

    Optional<Long>getAssociateProfessorsGroupedByDepartmentName(String departmentName);

    Optional<Long> getProfessorsGroupedByDepartmentName(String departmentName);

    Optional<Long> getAssistanceGroupedByDepartmentName(String departmentName);

    Optional<Double> getAverageSalaryForDepartmentName(String departmentName);

    Optional<Long> getCountOfEmployeesForDepartmentName(String departmentName);

}
