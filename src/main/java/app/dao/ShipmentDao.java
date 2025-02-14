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
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(shipment);
            em.getTransaction().commit();
            return shipment;
        }
    }

    public Shipment getShipmentById(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Shipment.class, id);
        }
    }

    public List<Shipment> getAllShipments() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT s FROM Shipment s", Shipment.class).getResultList();
        }
    }

    public Shipment updateShipment(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Shipment updatedShipment = em.merge(shipment);
            em.getTransaction().commit();
            return updatedShipment;
        }
    }

    public void deleteShipment(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "DELETE FROM Shipment s WHERE s.id = :id";
            em.getTransaction().begin();
            em.createQuery(jpql)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }
}