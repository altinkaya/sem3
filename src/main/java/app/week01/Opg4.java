package app.week01;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Opg4 {

    static class Employee {
        String name;
        LocalDate birthDate;

        Employee(String name, LocalDate birthDate) {
            this.name = name;
            this.birthDate = birthDate;
        }

        public String getName() {
            return name;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public int getAge() {
            return Period.between(birthDate, LocalDate.now()).getYears();
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", birthDate=" + birthDate +
                    ", age=" + getAge() +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");

        Supplier<Employee> employeesSupplier = () -> {
            Random random = new Random();
            int randomIndex = random.nextInt(names.size());
            String randomName = names.get(randomIndex);
            LocalDate randomBirthDate = LocalDate.now().minusYears(20 + random.nextInt(10)).minusMonths(random.nextInt(12));
            return new Employee(randomName, randomBirthDate);
        };

        List<Employee> employees = createEmployees(10, employeesSupplier);
        employees.forEach(System.out::println);

        double averageAge = employees.stream()
                .mapToInt(Employee::getAge)
                .average()
                .orElse(0.0);
        System.out.println("Average age: " + averageAge);

        int month = 5;
        List<Employee> mayBirthdays = employees.stream()
                .filter(e -> e.getBirthDate().getMonthValue() == month)
                .collect(Collectors.toList());
        System.out.println("Employees with birthdays in May: " + mayBirthdays);

        Map<Integer, Long> employeesByBirthMonth = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getBirthDate().getMonthValue(), Collectors.counting()));
        System.out.println("Employees by birth month: " + employeesByBirthMonth);

        List<Employee> currentMonthBirthdays = employees.stream()
                .filter(e -> e.getBirthDate().getMonthValue() == LocalDate.now().getMonthValue())
                .collect(Collectors.toList());
        System.out.println("Employees with birthdays this month: " + currentMonthBirthdays);
    }

    public static List<Employee> createEmployees(int numEmployees, Supplier<Employee> supplier) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < numEmployees; i++) {
            employees.add(supplier.get());
        }
        return employees;
    }
}
