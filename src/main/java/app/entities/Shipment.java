package app.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
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