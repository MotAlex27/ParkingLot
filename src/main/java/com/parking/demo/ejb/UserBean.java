package com.parking.demo.ejb;

import com.parking.demo.common.UserDto;
import com.parking.demo.entities.User;
import com.parking.demo.entities.UserGroup;
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
                    "(u.username, u.password, u.email,u.id)" +
                    " FROM User u", UserDto.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createUser(String username, String email, String password, List<String> roles) {
        try {
            // Create and persist the user entity
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(hashPassword(password)); // Password should be hashed
            em.persist(user);

            // Create and persist associated user groups
            for (String role : roles) {
                UserGroup userGroup = new UserGroup();
                userGroup.setUser(user); // Set the User object
                userGroup.setUserGroup(role);
                em.persist(userGroup);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) {
        // Use SHA-256 for password hashing
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
