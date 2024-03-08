package app.week05.HotelOpg.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private int id;
    private String name;
    private String address;
    private List<RoomDTO> rooms;
}

