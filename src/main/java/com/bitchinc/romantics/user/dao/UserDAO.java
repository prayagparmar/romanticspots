package com.bitchinc.romantics.user.dao;

import com.bitchinc.romantics.user.model.User;

/**
 * User: prayagparmar
 * Date: 7/30/14
 * Time: 7:03 PM
 */


public interface UserDAO {
    User findByUserName(String username);

    User createUser(String username, String password, String email);
}
