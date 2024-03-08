package app.week05.TheVeterinarian.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private int id;
    private String date;
    private String time;
    private int patientId;
    private String description;
}
