package app.week06.Persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private int number;
    private int price;

    public Room(int number, int price) {
        this.number = number;
        this.price = price;
    }
}
