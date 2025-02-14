package app.dao;

import jakarta.persistence.EntityManagerFactory;

import app.entities.Location;

public class LocationDao extends AbstractDao<Location> {

    private static LocationDao instance;

    private LocationDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public static LocationDao getInstance(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            instance = new LocationDao(entityManagerFactory);
        }
        return instance;
    }


}