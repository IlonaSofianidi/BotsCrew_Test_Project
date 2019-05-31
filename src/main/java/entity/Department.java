package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "departments", schema = "botscrew_test_db")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_department", nullable = false)
    private int id_department;

    @Column(name = "department_name", nullable = false, length = 45)
    private String department_name;

    @Column(name = "head_of_department_name", nullable = false, length = 45)
    private String head_of_department_name;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "departments_lectors",
            joinColumns = @JoinColumn(name = "id_department"),
            inverseJoinColumns = @JoinColumn(name = "id_lector")
    )
    private Set<Lector> lectors = new HashSet<>();

    public void addLector(Lector lector) {
        this.lectors.add(lector);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id_department == that.id_department &&
                department_name.equals(that.department_name) &&
                head_of_department_name.equals(that.head_of_department_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_department, department_name, head_of_department_name);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id_department=" + id_department +
                ", department_name='" + department_name + '\'' +
                ", head_of_department_name='" + head_of_department_name + '\'' +
                '}';
    }
}
