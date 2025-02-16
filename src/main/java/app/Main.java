package app;

import jakarta.persistence.EntityManagerFactory;

import app.dao.ShipmentDao;
import app.entities.Shipment;
import app.config.HibernateConfig;
import app.dao.ParcelDao;
import app.entities.Parcel;
import app.enums.Status;
import app.dao.LocationDao;
import app.entities.Location;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        ParcelDao parcelDao = ParcelDao.getInstance(emf);
        LocationDao locationDao = LocationDao.getInstance(emf);
        ShipmentDao shipmentDao = ShipmentDao.getInstance(emf);

        Parcel p1 = new Parcel("1234", "Peter", "Rolf");
        parcelDao.create(p1);


//        Parcel parcel1 = Parcel.builder()
//                .trackingNumber("5678")
//                .receiverName("Rolf")
//                .senderName("Peter")
//                .build();

//        parcelDao.createParcel(parcel);
//        parcelDao.createParcel(parcel1);

//        System.out.println(parcelDao.readByTrackingNumber("1234"));

//        parcelDao.readAllParcels().forEach(System.out::println);

//        parcelDao.updateParcelStatus("1234", Status.IN_TRANSIT);
//        System.out.println(parcelDao.readByTrackingNumber("1234"));
//
//        parcelDao.deleteParcel("findes ikke");


//        Dao<Location> locationDao = LocationDao.getInstance(emf);
//        Dao locationDao = (LocationDao) LocationDao.getInstance(emf);

//        locationDao.create(new Location(50.722, 17.562, "Byvej 1, 2100 København Ø"));


        Location copenhagenSource = locationDao.create(new Location(55.24, 11.21, "Mågevej 1, 2400 København NV"));
        Location slagelsePackageCenter = locationDao.create(new Location(55.24, 11.21, "Bjergbygade 1, 4200 Slagelse"));
        Location odensePackageCenter = locationDao.create(new Location(57.99, 10.34, "Grønlandsgade 1, 5000 Odense"));
        Location koldingPackageCenter = locationDao.create(new Location(50.31, 18.18, "Skovvangen 1, 6000 Kolding"));
        Location aarhusDestination = locationDao.create(new Location(45.99, 17.52, "Vestebrogade 1, 8000 Aarhus C"));

        Shipment s1 = new Shipment(p1, slagelsePackageCenter, odensePackageCenter);
        Shipment s2 = new Shipment(p1, slagelsePackageCenter, aarhusDestination);

        shipmentDao.create(s1);
        shipmentDao.create(s2);


//        System.out.println(locationDao.readById(1));
//        locationDao.readById(1);

        emf.close();


    }
}