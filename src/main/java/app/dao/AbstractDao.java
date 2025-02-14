package app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public abstract class AbstractDao<Entity> {

    private static EntityManagerFactory emf;

    protected AbstractDao(EntityManagerFactory entityManagerFactory) {
        emf = entityManagerFactory;
    }

    public Entity create(Entity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        }
    }

}