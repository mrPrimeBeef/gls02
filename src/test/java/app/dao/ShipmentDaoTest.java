package app.dao;

import app.config.HibernateConfig;
import app.entities.Location;
import app.entities.Parcel;
import app.entities.Shipment;
import app.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentDaoTest extends TestSetUp {
    @Test
    void createShipment() {
        Parcel parcel = Parcel.builder()
                .trackingNumber("1")
                .receiverName("test")
                .senderName("test")
                .status(Status.DELIVERED)
                .build();

        Location location1 = Location.builder()
                .address("test")
                .latitude(1.1)
                .longitude(1.1)
                .build();

        Location location2 = Location.builder()
                .address("test2")
                .latitude(2.2)
                .longitude(2.2)
                .build();

        Shipment shipment1 = Shipment.builder()
                .sourceLocation(location1)
                .desinationLocation(location2)
                .shipmentTimeStamp(LocalDateTime.of(2025, 03, 3, 3, 33))
                .parcel(parcel)
                .build();

        locationDao.createLocation(location1);
        locationDao.createLocation(location2);
        parcelDao.createParcel(parcel);
        Shipment shipment = shipmentDao.createShipment(shipment1);

        assertEquals(2, shipmentDao.getAllShipments().size());
        assertEquals(2,shipment.getId());
    }

    @Test
    void getShipmentById() {
        Shipment shipment = shipmentDao.getShipmentById(1);

        assertEquals("person1",shipment.getParcel().getReceiverName());
        assertEquals("Pakkecentral",shipment.getSourceLocation().getAddress());
        assertEquals("Pakkecentral2",shipment.getDesinationLocation().getAddress());
    }

    @Test
    void getAllShipments() {
        List<Shipment> list = shipmentDao.getAllShipments();

        assertEquals(1,list.size());
        assertEquals("person1",list.get(0).getParcel().getReceiverName());
        assertEquals("Pakkecentral",list.get(0).getSourceLocation().getAddress());
        assertEquals("Pakkecentral2",list.get(0).getDesinationLocation().getAddress());
    }

    @Test
    void updateShipment() {
        Shipment shipment = shipmentDao.getShipmentById(1);
        shipment.setShipmentTimeStamp(LocalDateTime.of(2000,1,1,0,0));
        shipmentDao.updateShipment(shipment);

        assertEquals(LocalDateTime.of(2000,1,1,0,0), shipmentDao.getShipmentById(1).getShipmentTimeStamp());
    }

    @Test
    void deleteShipment() {
        shipmentDao.deleteShipment(1);

       assertTrue(shipmentDao.getAllShipments().isEmpty());
    }
}