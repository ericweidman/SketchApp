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
        User checkIfExsists = userRepository.findByUserName(userSubmittedViaForm.getUserName());

        if (userSubmittedViaForm.getUserName() == null || userSubmittedViaForm.getUserName().trim().length() == 0) {
            throw new Exception("Username not valid.");

        } else if (userSubmittedViaForm.getPassword() == null || userSubmittedViaForm.getPassword().trim().length() == 0) {
            throw new Exception("Password not valid.");
        }else if (checkIfExsists != null){
            throw new Exception("Username already in use.");
        }else{
            saveUser.setUserName(userSubmittedViaForm.getUserName().toLowerCase());
            saveUser.setPassword(PasswordStorage.createHash(userSubmittedViaForm.getPassword()));
            userRepository.save(saveUser);
            userSession.setAttribute("usernName", saveUser.getUserName());
            System.out.println("User created and added to database!");
            return "User created and added to database!";
        }
    }
}
