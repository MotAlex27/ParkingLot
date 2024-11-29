package com.parking.demo.ejb;


import com.parking.demo.common.CarsDto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CarsBean {
    @PersistenceContext
    private EntityManager em;

    public List<CarsDto> findAllCars() {

        return em.createQuery("SELECT new com.parking.demo.common.CarsDto" +
                "(c.licensePlate, c.brand, c.model, c.color, c.parkingSpot)" +
                " FROM Car c", CarsDto.class).getResultList();
    }
}
