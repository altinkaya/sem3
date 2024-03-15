package app.week06.DTO;

import app.week06.Persistence.Hotel;
import app.week06.Persistence.Room;

public class DTOConverter {
    public static HotelDTO convertToHotelDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        return hotelDTO;
    }

    public static RoomDTO convertToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setHotelId(room.getHotel().getId());
        roomDTO.setNumber(room.getNumber());
        roomDTO.setPrice(room.getPrice());
        return roomDTO;
    }
}
