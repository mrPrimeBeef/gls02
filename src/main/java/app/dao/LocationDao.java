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
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
            return location;
        } finally {
            em.close();
        }
    }

    public Location getLocationById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Location.class, id);
        } finally {
            em.close();
        }
    }

    public List<Location> getAllLocations() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Location updateLocation(Location location) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Location updatedLocation = em.merge(location);
            em.getTransaction().commit();
            return updatedLocation;
        } finally {
            em.close();
        }
    }

    public void deleteLocation(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Location location = em.find(Location.class, id);
            if (location != null) {
                em.remove(location);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}