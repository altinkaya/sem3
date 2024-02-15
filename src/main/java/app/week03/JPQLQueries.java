package app.week03;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

public class JPQLQueries {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("employeePU");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();


            TypedQuery<Employee> queryAll = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> employees = queryAll.getResultList();


            TypedQuery<Employee> querySalary = em.createQuery("SELECT e FROM Employee e WHERE e.salary > :salary", Employee.class);
            querySalary.setParameter("salary", 60000.0);
            List<Employee> employeesSalary = querySalary.getResultList();


            TypedQuery<Employee> queryDepartment = em.createQuery("SELECT e FROM Employee e WHERE e.department = :department", Employee.class);
            queryDepartment.setParameter("department", "IT");
            List<Employee> employeesDepartment = queryDepartment.getResultList();


            TypedQuery<Employee> queryName = em.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE :letter", Employee.class);
            queryName.setParameter("letter", "J%");
            List<Employee> employeesName = queryName.getResultList();


            Query queryUpdateSalary = em.createQuery("UPDATE Employee e SET e.salary = :newSalary WHERE e.id = :id");
            queryUpdateSalary.setParameter("newSalary", 75000.0);
            queryUpdateSalary.setParameter("id", 1);
            int rowsUpdatedSalary = queryUpdateSalary.executeUpdate();


            Query queryUpdateDepartment = em.createQuery("UPDATE Employee e SET e.department = ?1 WHERE e.id = ?2");
            queryUpdateDepartment.setParameter(1, "Marketing");
            queryUpdateDepartment.setParameter(2, 1);
            int rowsUpdatedDepartment = queryUpdateDepartment.executeUpdate();


            Query queryAvgSalary = em.createQuery("SELECT AVG(e.salary) FROM Employee e");
            Double avgSalary = (Double) queryAvgSalary.getSingleResult();


            Query queryTotalSalary = em.createQuery("SELECT SUM(e.salary) FROM Employee e");
            Double totalSalary = (Double) queryTotalSalary.getSingleResult();

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    @Table(name = "employees")
    public static class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String firstName;
        private String lastName;
        @Column(unique = true)
        private String email;
        private double salary;
        private String department;
    }
}
