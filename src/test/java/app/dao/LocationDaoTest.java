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
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static final ParcelDao parcelDao = ParcelDao.getInstance(emf);
    private static final LocationDao locationDao = LocationDao.getInstance(emf);
    private static final ShipmentDao shipmentDao = ShipmentDao.getInstance(emf);

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

        locationDao.createLocation(locationNew);

        List<Location> listOfAllLocations = locationDao.getAllLocations();

        assertEquals(3, listOfAllLocations.size());
        assertEquals(3, listOfAllLocations.get(2).getId());
        assertThrows(IllegalArgumentException.class, () -> locationDao.createLocation(null));
    }

    @Test
    void getLocationById() {
        Location location = locationDao.getLocationById(2);

        assertEquals(2, location.getId());
        assertEquals("Pakkecentral2", location.getAddress());
        assertNull(locationDao.getLocationById(40));
    }

    @Test
    void getAllLocations() {
        List<Location> list = locationDao.getAllLocations();

        assertEquals("Pakkecentral", list.get(0).getAddress());
        assertEquals(123456.12345, list.get(0).getLatitude());
        assertEquals(2, list.size());
    }

    @Test
    void updateLocation() {
        Location locationToUpdate = locationDao.getLocationById(1);
        locationToUpdate.setAddress("newAdresse");
        Location updatedeLocation = locationDao.updateLocation(locationToUpdate);

        assertEquals("newAdresse",locationDao.getLocationById(1).getAddress());
    }

    @Test
    void deleteLocation() {
        shipmentDao.deleteShipment(1);
        locationDao.deleteLocation(1);

        assertEquals(1,locationDao.getAllLocations().size());
    }
}