package app.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Location sourceLocation;
    @ManyToOne
    private Location destinationLocation;

    public Shipment(Location sourceLocation, Location destinationLocation) {
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
    }
}