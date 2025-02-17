package app;

import jakarta.persistence.EntityManagerFactory;

import app.config.HibernateConfig;
import app.dao.Dao;
import app.dao.ParcelDao;
import app.entities.Location;
import app.entities.Parcel;
import app.entities.Shipment;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        ParcelDao parcelDao = new ParcelDao(emf);
        Dao<Location> locationDao = new Dao<>(Location.class, emf);
        Dao<Shipment> shipmentDao = new Dao<>(Shipment.class, emf);

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

        parcelDao.readById(1).getShipments().forEach(System.out::println);

        locationDao.readAll().forEach(System.out::println);

        parcelDao.readAll().forEach(System.out::println);

        emf.close();

    }
}