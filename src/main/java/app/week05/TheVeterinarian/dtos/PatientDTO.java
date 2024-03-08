package app.week05.TheVeterinarian.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private int id;
    private String name;
    private String species;
    private String breed;
    private String medicalHistory;
}
