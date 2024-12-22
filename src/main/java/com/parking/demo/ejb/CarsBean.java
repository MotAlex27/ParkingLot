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
                "(c.id,c.licensePlate, c.brand, c.model, c.color, c.parkingSpot)" +
                " FROM Car c", CarsDto.class).getResultList();
    }

    public void createCar(Long id, String licensePlate, String brand, String model, String color, String parkingSpot, Long userId) {
        Car car = new Car();
        car.setId(id);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        car.setBrand(brand);
        car.setModel(model);
        car.setColor(color);

        User user = em.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);
        em.persist(user);
    }

    public List<CarsDto> findCarsByOwnerId(Long ownerId) {
        return em.createQuery("SELECT new com.parking.demo.common.CarsDto" +
                        "(c.licensePlate, c.brand, c.model, c.color, c.parkingSpot)" +
                        " FROM Car c WHERE c.owner.id = :ownerId", CarsDto.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
    }

    public void deleteCarsById(Long carId) {
        Car car = em.find(Car.class, carId);
        em.remove(car);
    }

    public void updateCar(Long carId, String licensePlate, String brand, String model, String color, String parkingSpot) {
        Car car = em.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setBrand(brand);
        car.setModel(model);
        car.setColor(color);
        car.setParkingSpot(parkingSpot);

        User oldOwner = car.getOwner();
        oldOwner.getCars().remove(car);

        User user = em.find(User.class, oldOwner.getId());
        user.getCars().add(car);
        car.setOwner(user);
       // em.merge(car);
    }

    public CarsDto findCarById(Long carId) {
        return em.createQuery("SELECT new com.parking.demo.common.CarsDto" +
                        "(c.licensePlate, c.brand, c.model, c.color, c.parkingSpot)" +
                        " FROM Car c WHERE c.id = :carId", CarsDto.class)
                .setParameter("carId", carId)
                .getSingleResult();
    }
}
