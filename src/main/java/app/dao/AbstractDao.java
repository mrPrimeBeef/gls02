package app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public abstract class AbstractDao<Entity> {

    protected static EntityManagerFactory emf;

    protected AbstractDao(EntityManagerFactory entityManagerFactory) {
        emf = entityManagerFactory;
    }

    protected abstract Class<Entity> getEntityClass();

    public Entity create(Entity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        }
    }

    public Entity readById(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(getEntityClass(), id);
        }

    }

    public List<Entity> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Entity> query = em.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass());
            return query.getResultList();
        }
    }

    public Entity update(Entity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        }
    }

    public void delete(Entity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }
    }

}