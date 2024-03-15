package app.week06.Controller;


import app.week06.DAO.HotelDAO;
import app.week06.DAO.RoomDAO;
import app.week06.DTO.HotelDTO;
import app.week06.DTO.RoomDTO;
import app.week06.Persistence.Hotel;
import app.week06.Persistence.Room;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

public class HotelController {
    public static Handler getAll(HotelDAO dao) {
        return ctx -> {
            if (dao.getAll().isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND).result("No hotels were found.");
            } else {
                ctx.status(HttpStatus.OK).json(dao.getAll());
            }
        };
    }

    public static Handler delete(HotelDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Hotel foundHotel = dao.getById(id);
            if (foundHotel != null) {
                HotelDTO hotelDTO = HotelDTO.builder()
                        .id(foundHotel.getId())
                        .name(foundHotel.getName())
                        .address(foundHotel.getAddress())
                        .rooms(foundHotel.getRooms())
                        .build();

                dao.delete(foundHotel.getId());

                ctx.status(HttpStatus.OK).json(hotelDTO);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Hotel was not found.");
            }
        };
    }


    public static Handler getHotelById(HotelDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (dao.getById(id) != null) {
                Hotel foundHotel = dao.getById(id);
                HotelDTO hotelDTO = HotelDTO.builder()
                        .id(foundHotel.getId())
                        .name(foundHotel.getName())
                        .address(foundHotel.getAddress())
                        .rooms(foundHotel.getRooms())
                        .build();
                ctx.status(HttpStatus.OK).json(hotelDTO);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("The hotel id you are looking for does not exist.");
            }
        };
    }

    public static Handler getHotelWithRooms(HotelDAO hotelDAO, RoomDAO roomDAO) {
        return ctx -> {
            int hotelId = Integer.parseInt(ctx.pathParam("id"));

            Hotel foundHotel = hotelDAO.getById(hotelId);

            if (foundHotel != null) {
                List<Room> rooms = roomDAO.getAllRoomsByHotelId(hotelId);

                // Map Room entities to RoomDTO objects
                List<RoomDTO> roomDTOs = rooms.stream()
                        .map(room -> RoomDTO.builder()
                                .id(room.getId())
                                .hotelId(room.getHotel().getId())
                                .number(room.getNumber())
                                .price(room.getPrice())
                                .build())
                        .collect(Collectors.toList());

                ctx.json(roomDTOs);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("The hotel with the provided id does not exist.");
            }
        };
    }

    public static Handler create(HotelDAO dao) {
        return ctx -> {
            Hotel hotel = ctx.bodyAsClass(Hotel.class);
            if (hotel != null) {
                dao.create(hotel);
                ctx.status(HttpStatus.OK).json(hotel);
            } else {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Couldn't create the hotel with the given data.");
            }
        };
    }

    public static Handler update(HotelDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HotelDTO updatedHotelDTO = ctx.bodyAsClass(HotelDTO.class);
            Hotel foundHotel = dao.getById(id);
            updatedHotelDTO.setRooms(foundHotel.getRooms());

            if (foundHotel != null) {
                foundHotel.setName(updatedHotelDTO.getName());
                foundHotel.setAddress(updatedHotelDTO.getAddress());
                dao.update(foundHotel);
                updatedHotelDTO.setId(id);
                ctx.json(updatedHotelDTO);
            } else {

                ctx.status(HttpStatus.NOT_FOUND).result("Hotel not found.");
            }
        };
    }
}
