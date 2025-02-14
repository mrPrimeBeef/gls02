package app.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import app.enums.Status;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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


    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Shipment> shipments = new HashSet<>();

    public void addShipment(Shipment shipment){
        if (shipment != null){
            this.shipments.add(shipment);
            shipment.setParcel(this);
        }
    }

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }
}