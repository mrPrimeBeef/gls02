package app.populators;

import app.dao.LocationDao;
import app.dao.ParcelDao;
import app.entities.Location;
import app.entities.Parcel;
import app.entities.Shipment;
import app.enums.Status;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParcelPopulator {
    public static void populate(ParcelDao parcelDao, LocationDao locationDao){
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
    }
}