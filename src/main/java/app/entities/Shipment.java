package app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Setter
    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Parcel parcel;

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Location sourceLocation;

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Location desinationLocation;

    private LocalDateTime shipmentTimeStamp;

    public Shipment() {
    }
}