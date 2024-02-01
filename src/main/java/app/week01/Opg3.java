package app.week01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Opg3 {

    public static class Employee {
        private String name;
        private int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 7, 14, 21, 28, 35, 42);
        Predicate<Integer> divisibleBy7 = number -> number % 7 == 0;
        List<Integer> filteredNumbers = numbers.stream().filter(divisibleBy7).collect(Collectors.toList());
        System.out.println(filteredNumbers);


        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        Supplier<List<Employee>> employeesSupplier = () -> names.stream().map(name -> new Employee(name, 20)).collect(Collectors.toList());
        List<Employee> employees = employeesSupplier.get();


        Consumer<Employee> printEmployee = employee -> System.out.println(employee.toString());
        employees.forEach(printEmployee);


        Function<Employee, String> employeeToName = Employee::getName;
        List<String> employeeNames = employees.stream().map(employeeToName).collect(Collectors.toList());
        System.out.println(employeeNames);


        Predicate<Employee> isOlderThan18 = employee -> employee.getAge() > 18;
        boolean isAnyEmployeeOlderThan18 = employees.stream().anyMatch(isOlderThan18);
        System.out.println(isAnyEmployeeOlderThan18);
    }
}
