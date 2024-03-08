package app.week05.HotelOpg.DTO;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private int id;
    private int hotelId;
    private int number;
    private double price;
}
