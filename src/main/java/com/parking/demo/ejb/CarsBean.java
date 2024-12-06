package com.parking.demo.ejb;


import com.parking.demo.common.CarsDto;
import com.parking.demo.entities.Car;
import com.parking.demo.entities.User;
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
    public void createCar(String licensePlate, String brand, String model, String color, String parkingSpot, Long userId) {
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        car.setBrand(brand);
        car.setModel(model);
        car.setColor(color);

        User user = em.find(User.class,userId);
        user.getCars().add(car);
        car.setOwner(user);
        em.persist(user);
    }
}
