package app.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

import app.enums.Status;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String trackingNumber;
    private String senderName;
    private String receiverName;
    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(updatable = false)
    private LocalDateTime created;
    private LocalDateTime updated;
//    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "parcel",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Shipment> shipments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }

    public Parcel(String trackingNumber, String senderName, String receiverName) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.status = Status.PENDING;
    }

    public Parcel(String trackingNumber, String senderName, String receiverName, Status status) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.status = status;
    }


//    public void addShipment(Shipment shipment) {
//        if (shipment != null) {
//            this.shipments.add(shipment);
//            shipment.setParcel(this);
//        }
//    }
//
//    public Location getSource() {
//        return shipments.get(0).getDestinationLocation();
//    }
//
//    public Location getDestination() {
//        return shipments.get(shipments.size() - 1).getDestinationLocation();
//    }

}