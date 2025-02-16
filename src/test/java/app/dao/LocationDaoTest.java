package app.dao;

import app.config.HibernateConfig;
import app.entities.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationDaoTest extends TestSetUp {
    @Test
    void getInstance() {
        assertNotNull(emf);
    }

    @Test
    void createLocation() {
        Location locationNew = Location.builder()
                .address("Ny")
                .latitude(789.456)
                .longitude(123.456)
                .build();

        locationDao.create(locationNew);

        List<Location> listOfAllLocations = locationDao.findAll();

        assertEquals(3, listOfAllLocations.size());
        assertEquals(3, listOfAllLocations.get(2).getId());
        assertThrows(IllegalArgumentException.class, () -> locationDao.create(null));
    }

    @Test
    void getLocationById() {
        Location location = locationDao.findById(2);

        assertEquals(2, location.getId());
        assertEquals("Pakkecentral2", location.getAddress());
        assertNull(locationDao.findById(40));
    }

    @Test
    void getAllLocations() {
        List<Location> list = locationDao.findAll();

        assertEquals("Pakkecentral", list.get(0).getAddress());
        assertEquals(123456.12345, list.get(0).getLatitude());
        assertEquals(2, list.size());
    }

    @Test
    void updateLocation() {
        Location locationToUpdate = locationDao.findById(1);
        locationToUpdate.setAddress("newAdresse");
        Location updatedeLocation = locationDao.update(locationToUpdate);

        assertEquals("newAdresse",locationDao.findById(1).getAddress());
    }

    @Test
    void deleteLocation() {
        shipmentDao.deleteShipment(1);
        locationDao.delete(1);

        assertEquals(1,locationDao.findAll().size());
    }
}