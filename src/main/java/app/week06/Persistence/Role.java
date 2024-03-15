package app.week06.Persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    Set<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }
}
