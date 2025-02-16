package app.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

import app.enums.Status;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Builder
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

    public Parcel(String trackingNumber, String senderName, String receiverName,Status status) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.status = status;
    }

}
