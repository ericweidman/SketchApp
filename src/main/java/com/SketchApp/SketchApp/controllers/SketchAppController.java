package com.SketchApp.SketchApp.controllers;

import com.SketchApp.SketchApp.entities.User;
import com.SketchApp.SketchApp.services.UserRepository;
import com.SketchApp.SketchApp.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by ericweidman on 6/4/17.
 */
@RestController
public class SketchAppController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(path = "/newuser")
    private String newUser(@RequestBody User userSubmittedViaForm, HttpSession userSession) throws Exception {

        User saveUser = new User();
        User checkIfExsists = userRepository.findByusername(userSubmittedViaForm.getUsername());

        if (userSubmittedViaForm.getUsername() == null || userSubmittedViaForm.getUsername().trim().length() == 0) {
            throw new Exception("Username not valid.");

        } else if (userSubmittedViaForm.getPassword() == null || userSubmittedViaForm.getPassword().trim().length() == 0) {
            throw new Exception("Password not valid.");
        } else if (checkIfExsists != null) {
            throw new Exception("Username already in use.");
        } else {
            saveUser.setUsername(userSubmittedViaForm.getUsername().toLowerCase());
            saveUser.setPassword(PasswordStorage.createHash(userSubmittedViaForm.getPassword()));
            userRepository.save(saveUser);
            userSession.setAttribute("username", saveUser.getUsername());
            System.out.println("User created and added to database!");
            return "User created and added to database!";
        }
    }

    @RequestMapping(path = "/login")
    public void loginUser(@RequestBody User userSubmittedViaForm, HttpSession userSession) throws Exception {

        //Finds the user in database via the submitted username.
        User checkUserValidity = userRepository.findByusername(userSubmittedViaForm.getUsername().toLowerCase());

        //Checks for username submission for null or empty string.
        if (userSubmittedViaForm.getUsername() == null || userSubmittedViaForm.getUsername().trim().length() == 0) {
            throw new Exception("Invalid username input");
        }
        //Checks for password submission for null or empty string.
        else if (userSubmittedViaForm.getPassword() == null || userSubmittedViaForm.getPassword().trim().length() == 0) {
            throw new Exception("Invalid password input");
        }
        //Checks if uses exists.
        else if (checkUserValidity == null) {
            throw new Exception("User does not exist");
        }
        //Checks for password equality.
        else if (!PasswordStorage.verifyPassword(userSubmittedViaForm.getPassword(), checkUserValidity.getPassword())) {
            throw new Exception("Incorrect Password");
        } else {
            //Saves session by valid username.
            userSession.setAttribute("userName", checkUserValidity.getUsername());
        }
    }

    @RequestMapping(path = "/logout")
    public void logout(HttpSession userSession) {
        userSession.invalidate();
    }
}
