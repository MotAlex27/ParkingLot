package com.parking.demo.ejb;

import com.parking.demo.common.UserDto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class UserBean {
    @PersistenceContext
    private EntityManager em;

    public List<UserDto> findAllUsers() {
        try {
            return em.createQuery("SELECT new com.parking.demo.common.UserDto" +
                    "(u.username, u.password, u.email)" +
                    " FROM User u", UserDto.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
