package app;

import jakarta.persistence.EntityManagerFactory;

import app.config.HibernateConfig;
import app.dao.ParcelDao;
import app.entities.Parcel;
import app.enums.Status;
import app.dao.Dao;
import app.dao.LocationDao;
import app.entities.Location;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        ParcelDao parcelDao = ParcelDao.getInstance(emf);

        Parcel parcel = Parcel.builder()
                .trackingNumber("1234")
                .build();
        Parcel parcel1 = Parcel.builder()
                .trackingNumber("5678")
                .receiverName("Rolf")
                .senderName("Peter")
                .build();

        parcelDao.createParcel(parcel);
        parcelDao.createParcel(parcel1);

//        System.out.println(parcelDao.readByTrackingNumber("1234"));

//        parcelDao.readAllParcels().forEach(System.out::println);

        parcelDao.updateParcelStatus("1234", Status.IN_TRANSIT);
        System.out.println(parcelDao.readByTrackingNumber("1234"));

        parcelDao.deleteParcel("findes ikke");


//        Dao<Location> locationDao = LocationDao.getInstance(emf);
//        Dao locationDao = (LocationDao) LocationDao.getInstance(emf);

//        locationDao.create(new Location(55.722, 12.562, "Ryparken 192 1.th, 2100 København Ø"));


        emf.close();


    }
}