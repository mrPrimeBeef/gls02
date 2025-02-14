package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double latitude;
    private double longitude;
    private String address;

    @ToString.Exclude
    @OneToMany(mappedBy = "sourceLocation", cascade = CascadeType.ALL)
    private List<Shipment> shipmentsFromHere;

    @ToString.Exclude
    @OneToMany(mappedBy = "desinationLocation", cascade = CascadeType.ALL)
    private List<Shipment> shipmentsToHere;

    public Location() {
    }

    public Location(double latitude, double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
}