package app.dao;

import java.util.List;

import app.entities.Shipment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import app.entities.Parcel;
import app.enums.Status;
import app.exceptions.DaoExeception;


public class ParcelDao extends AbstractDao<Parcel> {

    private static ParcelDao instance;

    private ParcelDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public static ParcelDao getInstance(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            instance = new ParcelDao(entityManagerFactory);
        }
        return instance;
    }

    @Override
    protected Class<Parcel> getEntityClass() {
        return Parcel.class;
    }

//    public Parcel createParcel(Parcel p) {
//        try (EntityManager em = emf.createEntityManager()) {
//            em.getTransaction().begin();
//            em.persist(p);
//            em.getTransaction().commit();
//            return p;
//        }
//    }

    public Parcel readByTrackingNumber(String trackingNumber) {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT p FROM Parcel p WHERE p.trackingNumber=:trackingNumber";
            TypedQuery<Parcel> query = em.createQuery(jpql, Parcel.class);
            query.setParameter("trackingNumber", trackingNumber);
            return query.getSingleResult();
        }
    }

//    public List<Parcel> readAllParcels() {
//        try (EntityManager em = emf.createEntityManager()) {
//            String jpql = "SELECT p FROM Parcel p";
//            TypedQuery<Parcel> query = em.createQuery(jpql, Parcel.class);
//            return query.getResultList();
//        }
//    }

//    public void updateParcelStatus(String trackingNumber, Status status) {
//        try (EntityManager em = emf.createEntityManager()) {
//            String jpql = "UPDATE Parcel p SET p.status = :status WHERE p.trackingNumber = :trackingNumber";
//            em.getTransaction().begin();
//            em.createQuery(jpql)
//                    .setParameter("status", status)
//                    .setParameter("trackingNumber", trackingNumber)
//                    .executeUpdate();
//            em.getTransaction().commit();
//        }
//    }

    public void updateParcelStatus(String trackingNumber, Status status) {
        try (EntityManager em = emf.createEntityManager()) {
            Parcel parcel = readByTrackingNumber(trackingNumber);
            parcel.setStatus(status);
            em.getTransaction().begin();
            em.merge(parcel);
            em.getTransaction().commit();
        }
    }

    public void deleteParcel(String trackingNumber) throws DaoExeception {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "DELETE FROM Parcel p WHERE p.trackingNumber = :trackingNumber";
            em.getTransaction().begin();
            em.createQuery(jpql)
                    .setParameter("trackingNumber", trackingNumber)
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

}