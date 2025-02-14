package app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public abstract class Dao<Entity> {

    private static EntityManagerFactory emf;

    public Dao(EntityManagerFactory entityManagerFactory) {
        emf = entityManagerFactory;
    }

    public abstract Dao<Entity> getInstance(EntityManagerFactory entityManagerFactory);

    public Entity create(Entity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        }
    }


}