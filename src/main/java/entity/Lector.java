package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lectors", schema = "botscrew_test_db")
@Data
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_lector", nullable = false)
    private int id_lector;
    @Column(name = "lector_name", nullable = false, length = 45)
    private String name;
    @Column(name = "salary")
    private double salary;
    @Enumerated(EnumType.STRING)
    private LectorDegree degree;

    @ManyToMany(mappedBy = "lectors", fetch = FetchType.LAZY)

    private Set<Department> departments = new HashSet<>();

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lector lector = (Lector) o;
        return id_lector == lector.id_lector &&
                Double.compare(lector.salary, salary) == 0 &&
                name.equals(lector.name) &&
                degree == lector.degree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_lector, name, salary, degree);
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id_lector=" + id_lector +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", degree=" + degree +
                '}';
    }
}
