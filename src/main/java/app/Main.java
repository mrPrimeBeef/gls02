package app;

import app.config.HibernateConfig;
import app.dao.LocationDao;
import app.dao.ParcelDao;
import app.dao.ShipmentDao;
import app.entities.Location;
import app.entities.Parcel;
import app.entities.Shipment;
import app.enums.Status;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        LocationDao locationDao = LocationDao.getInstance(emf);
        ShipmentDao shipmentDao = ShipmentDao.getInstance(emf);
        ParcelDao parcelDao = ParcelDao.getInstance(emf);

        Parcel parcel = Parcel.builder()
                .trackingNumber("5678")
                .receiverName("Rolf")
                .senderName("Peter")
                .status(Status.IN_TRANSIT)
                .build();

        Location location1 = Location.builder()
                .address("Pakkecentral")
                .latitude(123456.12345)
                .latitude(1234.12345)
                .build();

        Location location2 = Location.builder()
                .address("Pakkecentral2")
                .latitude(123456.12345)
                .latitude(1234.12345)
                .build();

        Shipment shipment1 = Shipment.builder()
                .sourceLocation(location1)
                .desinationLocation(location2)
                .shipmentTimeStamp(LocalDateTime.now())
                .parcel(parcel)
                .build();

        locationDao.createLocation(location1);
        locationDao.createLocation(location2);

        parcel.addShipment(shipment1);
        parcelDao.createParcel(parcel);



    }
}