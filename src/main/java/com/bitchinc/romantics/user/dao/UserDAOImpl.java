package com.bitchinc.romantics.user.dao;

import com.bitchinc.romantics.user.model.User;
import com.bitchinc.romantics.user.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: prayagparmar
 * Date: 7/30/14
 * Time: 7:04 PM
 */
@Repository
public class UserDAOImpl extends AJpaDAO implements UserDAO {
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public User findByUserName(String username) {
        List<User> users = entityManager
                .createQuery("from User where username= :username", User.class)
                .setParameter("username", username)
                .getResultList();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

//    @Override
//    public void createUser(User user) {
//        sessionFactory.getCurrentSession().save(user);
//    }

    @Override
    @Transactional
    public User createUser(String username, String password, String email) {
        User user = new User(username, passwordEncoder.encode(password), email);
        save(user);

        return user;
    }

//    @Override
//    public User createUser(String username, String password, String email, String userRole, byte[] image) {
//        User user = new User(username, password, email, image);
//        save(user);
//
//        return user;
//    }
}
