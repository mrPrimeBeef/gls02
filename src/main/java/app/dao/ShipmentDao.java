package app.dao;

import app.entities.Shipment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class ShipmentDao {
    private static ShipmentDao instance;
    private final EntityManagerFactory emf;

    private ShipmentDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static ShipmentDao getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new ShipmentDao(emf);
        }
        return instance;
    }

    public Shipment createShipment(Shipment shipment) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(shipment);
            em.getTransaction().commit();
            return shipment;
        } finally {
            em.close();
        }
    }

    public Shipment getShipmentById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Shipment.class, id);
        } finally {
            em.close();
        }
    }

    public List<Shipment> getAllShipments() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Shipment s", Shipment.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Shipment updateShipment(Shipment shipment) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shipment updatedShipment = em.merge(shipment);
            em.getTransaction().commit();
            return updatedShipment;
        } finally {
            em.close();
        }
    }

    public void deleteShipment(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shipment shipment = em.find(Shipment.class, id);
            if (shipment != null) {
                em.remove(shipment);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}