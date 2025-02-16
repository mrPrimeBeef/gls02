package app.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Parcel parcel;
    @ManyToOne
    private Location sourceLocation;
    @ManyToOne
    private Location destinationLocation;
    private LocalDateTime shipDateTime;

    public Shipment(Parcel parcel, Location sourceLocation, Location destinationLocation) {
        this.parcel = parcel;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
    }
}