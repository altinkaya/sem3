package app.week03;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;

public class LombokExercise {
    public static void main(String[] args) {
        Person person = new Person("John", "Doe", 25);
        System.out.println(person); // This should print something like "Person(firstName=John, lastName=Doe, age=25)"

        person.setAge(26);
        System.out.println(person.getAge()); // This should print "26"
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Person {
        private String firstName;
        private String lastName;
        private int age;
    }
}
