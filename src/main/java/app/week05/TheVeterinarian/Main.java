package app.week05.TheVeterinarian;

import io.javalin.Javalin;
import app.week05.TheVeterinarian.handlers.AppointmentHandler;
import app.week05.TheVeterinarian.handlers.PatientHandler;
import app.week05.TheVeterinarian.dtos.AppointmentDTO;
import app.week05.TheVeterinarian.dtos.PatientDTO;

public class Main {
    public static void main(String[] args) {
        AppointmentHandler appointmentHandler = new AppointmentHandler();
        PatientHandler patientHandler = new PatientHandler();

        Javalin app = Javalin.create().start(7007);
            // ingorer fejl fra favicon 204: “Intet indhold.”
        app.get("/favicon.ico", ctx -> ctx.status(204));

        app.before(ctx -> {
            System.out.println("Received a request to " + ctx.path());
        });

        app.after(ctx -> {
            System.out.println("Sent a response with status " + ctx.status());
        });

        app.get("/api/vet/appointments", ctx -> ctx.json(appointmentHandler.getAllAppointments()));
        app.get("/api/vet/appointments/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            AppointmentDTO appointment = appointmentHandler.getAppointmentById(id);
            if (appointment != null) {
                ctx.json(appointment);
            } else {
                ctx.status(404);
            }
        });

        app.get("/api/vet/patients", ctx -> ctx.json(patientHandler.getAllPatients()));
        app.get("/api/vet/patients/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PatientDTO patient = patientHandler.getPatientById(id);
            if (patient != null) {
                ctx.json(patient);
            } else {
                ctx.status(404);
            }
        });
    }
}
