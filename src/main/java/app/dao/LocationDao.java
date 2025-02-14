package app.dao;

import app.entities.Location;
import jakarta.persistence.EntityManagerFactory;

public class LocationDao extends Dao<Location> {

    private LocationDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public LocationDao getInstance(EntityManagerFactory entityManagerFactory){

    }



}
