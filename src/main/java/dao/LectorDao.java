package dao;

import entity.Department;
import entity.Lector;

import java.util.List;
import java.util.Optional;

public interface LectorDao {
    Optional<Lector> get(int lectorId);

    List<Lector> getAll();

    void save(Lector lector);

    void update(Lector lector);

    void delete(Lector lector);

    void deleteAll();

    void addLectorToDepartment(Lector lector,Department department);

    List<Department> getAllDepartmentsByLectorId(int  lectorId);

    List<Lector> getLectorsByGlobalSearch(String template);
}
