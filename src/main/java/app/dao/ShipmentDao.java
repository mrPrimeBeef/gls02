package app.dao;

import jakarta.persistence.EntityManagerFactory;

import app.entities.Shipment;

public class ShipmentDao extends AbstractDao<Shipment> {

    private static ShipmentDao instance;

    private ShipmentDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public static ShipmentDao getInstance(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            instance = new ShipmentDao(entityManagerFactory);
        }
        return instance;
    }

    @Override
    protected Class<Shipment> getEntityClass() {
        return Shipment.class;
    }


}