package app.dao;

import app.config.HibernateConfig;
import app.entities.Parcel;
import app.enums.Status;
import app.exceptions.DaoExeception;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelDaoTest {
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static final ParcelDao parcelDao = ParcelDao.getInstance(emf);
    private static final LocationDao locationDao = LocationDao.getInstance(emf);

    @Test
    void getInstance() {
        assertNotNull(emf);
    }

    @Test
    void createParcel() {
        Parcel parcel = Parcel.builder()
                .senderName("Test")
                .receiverName("Test")
                .status(Status.PENDING)
                .trackingNumber("7899")
                .build();

        parcelDao.createParcel(parcel);
        assertEquals(2, parcel.getId());

        assertThrows(IllegalArgumentException.class, () -> parcelDao.createParcel(null));

        List<Parcel> parcels = parcelDao.readAllParcels();
        assertEquals(2, parcels.size());
    }

    @Test
    void readByTrackingNumber() {
        Parcel parcel = parcelDao.readByTrackingNumber("5678");

        assertEquals("person1", parcel.getReceiverName());
        assertEquals("person2", parcel.getSenderName());
        assertEquals(1, parcel.getId());

        assertNotEquals(2, parcel.getId());

        assertThrows(NoResultException.class, () -> parcelDao.readByTrackingNumber("findesIkke"));
    }

    @Test
    void readAllParcels() {
        List<Parcel> parcels = parcelDao.readAllParcels();
        assertEquals(1, parcels.size());
        assertEquals(1, parcels.get(0).getId());
        assertEquals("5678", parcels.get(0).getTrackingNumber());
        assertEquals("person2", parcels.get(0).getSenderName());
        assertEquals("person1", parcels.get(0).getReceiverName());
        assertEquals(Status.IN_TRANSIT, parcels.get(0).getStatus());
        assertNull(parcels.get(0).getUpdated());
    }

    @Test
    void updateParcelStatus() {
        LocalDateTime now = LocalDateTime.now();

        parcelDao.updateParcelStatus("5678", Status.DELIVERED);
        Parcel parcel = parcelDao.readByTrackingNumber("5678");
        assertEquals(Status.DELIVERED, parcel.getStatus());
        assertNotNull(parcel.getUpdated());
        assertTrue(now.isBefore(parcel.getUpdated()));
    }

    @Test
    void deleteParcel() {
        parcelDao.deleteParcel("5678");
        assertThrows(NoResultException.class, () -> parcelDao.deleteParcel("findesIkke"));
        assertThrows(NoResultException.class, () -> parcelDao.readByTrackingNumber("5678"));

        List<Parcel> parcels = parcelDao.readAllParcels();
        assertEquals(0, parcels.size());
    }
}