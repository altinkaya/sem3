package app.week05.TheVeterinarian.handlers;

import app.week05.TheVeterinarian.dtos.AppointmentDTO;
import java.util.HashMap;
import java.util.Map;

public class AppointmentHandler {
    private Map<Integer, AppointmentDTO> appointments = new HashMap<>();

    public AppointmentHandler() {
        appointments.put(1, new AppointmentDTO(1, "2021-10-01", "10:00", 1, "Annual checkup"));
        appointments.put(2, new AppointmentDTO(2, "2021-10-01", "11:00", 2, "Vaccination"));
        appointments.put(3, new AppointmentDTO(3, "2021-10-01", "12:00", 3, "Dental cleaning"));
        appointments.put(4, new AppointmentDTO(4, "2021-10-01", "13:00", 4, "Surgery"));
        appointments.put(5, new AppointmentDTO(5, "2021-10-01", "14:00", 5, "X-ray"));
    }

    public Map<Integer, AppointmentDTO> getAllAppointments() {
        return appointments;
    }

    public AppointmentDTO getAppointmentById(int id) {
        return appointments.get(id);
    }
}
