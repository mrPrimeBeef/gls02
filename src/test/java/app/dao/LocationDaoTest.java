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

class LocationDaoTest {
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static final ParcelDao parcelDao = ParcelDao.getInstance(emf);
    private static final LocationDao locationDao = LocationDao.getInstance(emf);

    @BeforeEach
    void setUp() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Shipment").executeUpdate();
            em.createQuery("DELETE FROM Location ").executeUpdate();
            em.createQuery("DELETE FROM Parcel").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE parcel_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE shipment_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE location_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();
            app.populators.ParcelPopulator.populate(parcelDao, locationDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("EntityManagerFactory closed.");
        }
    }

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

        assertEquals(2,location.getId());
        assertEquals("Pakkecentral2",location.getAddress());
        locationDao.getLocationById(40);
//        assertThrows(NoResultException.class, () -> );
    }

    @Test
    void getAllLocations() {
    }

    @Test
    void updateLocation() {
    }

    @Test
    void deleteLocation() {
    }
}