package com.bitchinc.romantics.controller;

import com.bitchinc.romantics.enums.Status;
import com.bitchinc.romantics.pojo.AuthenticationResponse;
import com.bitchinc.romantics.user.dao.UserDAO;
import com.bitchinc.romantics.user.domain.UserJSON;
import com.bitchinc.romantics.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: prayagparmar
 * Date: 7/24/14
 * Time: 9:57 PM
 */
@Controller
@RequestMapping("/user")
public class UserService {
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity register(@RequestBody UserJSON userJSON) {
        //TODO 1. data validation
        //TODO 2. try catch and throw appropriate exceptions
        //TODO 3. Redirect to login after signup

        userDAO.createUser(userJSON.getUsername(), userJSON.getPassword(), userJSON.getEmail());

        return new ResponseEntity(userJSON, HttpStatus.OK);
    }

    @RequestMapping(value = "/login/success", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity loginSuccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return new ResponseEntity(new AuthenticationResponse(userDAO.findByUserName(username).getUserId(), Status.SUCCESS), HttpStatus.OK);
    }

    //TODO sending default login form
    @RequestMapping(value = "/login/failure", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity loginFailure() {
        return new ResponseEntity(new AuthenticationResponse(Status.BAD_USERNAME_PASSWORD), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserJSON userJSON = new UserJSON();

        User user = userDAO.findByUserName(username);
        userJSON.setUsername(user.getUsername());
        userJSON.setEmail(user.getEmail());
        userJSON.setImage(user.getImage());

        return new ResponseEntity(new AuthenticationResponse(userJSON, Status.SUCCESS), HttpStatus.OK);
    }
}
