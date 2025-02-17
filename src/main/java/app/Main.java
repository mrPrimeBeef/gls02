package app;

import app.dao.Dao;
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
        Dao<Location> locationDao = new Dao<>(Location.class, emf);
        ShipmentDao shipmentDao = ShipmentDao.getInstance(emf);

        Parcel p1 = parcelDao.create(new Parcel("1234", "Peter", "Rolf"));

        Location locationA = locationDao.create(new Location(55.24, 11.21, "Mågevej 1, 2400 København NV"));
        Location locationB = locationDao.create(new Location(55.24, 11.21, "Bjergbygade 1, 4200 Slagelse"));
        Location locationC = locationDao.create(new Location(57.99, 10.34, "Grønlandsgade 1, 5000 Odense"));
        Location locationD = locationDao.create(new Location(50.31, 18.18, "Skovvangen 1, 6000 Kolding"));
        Location locationE = locationDao.create(new Location(45.99, 17.52, "Vestebrogade 1, 8000 Aarhus C"));

        shipmentDao.create(new Shipment(p1, locationA, locationB));
        shipmentDao.create(new Shipment(p1, locationB, locationC));
        shipmentDao.create(new Shipment(p1, locationC, locationD));
        shipmentDao.create(new Shipment(p1, locationD, locationE));

        Parcel p = parcelDao.readById(1);
        p.getShipments().forEach(System.out::println);


        System.out.println(locationDao.readById(1));

        locationDao.readAll().forEach(System.out::println);

        //        System.out.println(locationDao);


        emf.close();

    }
}