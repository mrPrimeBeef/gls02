package app.dao;

import app.entities.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class LocationDao {
    private static LocationDao instance;
    private final EntityManagerFactory emf;

    private LocationDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static LocationDao getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new LocationDao(emf);
        }
        return instance;
    }

    public Location createLocation(Location location) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
            return location;
        }
    }

    public Location getLocationById(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Location.class, id);
        }
    }

    public List<Location> getAllLocations() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
        }
    }

    public Location updateLocation(Location location) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Location updatedLocation = em.merge(location);
            em.getTransaction().commit();
            return updatedLocation;
        }
    }

    public void deleteLocation(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "DELETE FROM Location l WHERE l.id = :id";
            em.getTransaction().begin();
            em.createQuery(jpql)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }
}