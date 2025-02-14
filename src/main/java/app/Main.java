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
                .receiverName("person1")
                .senderName("person2")
                .status(Status.IN_TRANSIT)
                .build();

        Location location1 = Location.builder()
                .address("Pakkecentral")
                .latitude(123456.12345)
                .longitude(1234.12345)
                .build();

        Location location2 = Location.builder()
                .address("Pakkecentral2")
                .latitude(123456.12345)
                .longitude(1234.12345)
                .build();

        Shipment shipment1 = Shipment.builder()
                .sourceLocation(location1)
                .desinationLocation(location2)
                    .shipmentTimeStamp(LocalDateTime.of(2025,02,12,8,50))
                .parcel(parcel)
                .build();

        locationDao.createLocation(location1);
        locationDao.createLocation(location2);

        parcel.addShipment(shipment1);
        parcelDao.createParcel(parcel);



        // location crud
//        locationDao.getAllLocations().forEach(System.out::println);
//        System.out.println(locationDao.getLocationById(1));
//        location1.setAddress("Havnen1");
//        locationDao.updateLocation(location1);
//        System.out.println(locationDao.getLocationById(1));

        // shipment crud
//      shipmentDao.getAllShipments().forEach(System.out::println);
        System.out.println(shipmentDao.getShipmentById(1));
        shipment1.setShipmentTimeStamp(LocalDateTime.now());
        shipmentDao.updateShipment(shipment1);
        System.out.println(shipment1);
        shipmentDao.deleteShipment(shipment1.getId());

        locationDao.deleteLocation(1);
        locationDao.deleteLocation(2);


    }
}