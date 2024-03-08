package app.week05.Dogexercise.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DogDTO {
    private int id;
    private String name;
    private String breeds;
    private int age;
}
