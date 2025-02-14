package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @ManyToOne
    @JoinColumn(name = "sourceLocation")
    private Location sourceLocation;

    @ManyToOne
    @JoinColumn(name = "desinationLocation")
    private Location desinationLocation;

    private LocalDateTime shipmentTimeStamp;

    public Shipment() {
    }
}