package com.SketchApp.controllers;

import com.SketchApp.entities.User;
import com.SketchApp.services.CommentRepository;
import com.SketchApp.services.DrawingRepository;
import com.SketchApp.services.FriendRepository;
import com.SketchApp.services.UserRepository;
import com.SketchApp.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by ericweidman on 6/4/17.
 */
@RestController
public class UserAuthController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/newuser")
    private String newUser(@RequestBody User userSubmittedViaForm) throws Exception {

        User saveUser = new User();

        //Checks to see if the user already exists.
        User checkIfExists = userRepository.findByusername(userSubmittedViaForm.getUsername().toLowerCase());

        //Checks username for validity. -- This will need more functionality one we've decided on naming conventions.
        if (userSubmittedViaForm.getUsername() == null || userSubmittedViaForm.getUsername().trim().length() == 0) {
            throw new Exception("Username not valid.");
        }
        //Checks password for validity -- This will need more functionality too.
        else if (userSubmittedViaForm.getPassword() == null || userSubmittedViaForm.getPassword().trim().length() == 0) {
            throw new Exception("Password not valid.");
        }
        //Simple check to see if the user already exists -- Should be complete.
        else if (checkIfExists != null) {
            throw new Exception("Username already in use.");
        }
        //I'm not sure that this being an "else" is a great idea. Will ponder later.
        //Adds the username and password to the database.
        else {
            saveUser.setUsername(userSubmittedViaForm.getUsername().toLowerCase());
            saveUser.setPassword(PasswordStorage.createHash(userSubmittedViaForm.getPassword()));
            userRepository.save(saveUser);
            System.out.println("User " + saveUser.getUsername() + " created!");
            return "User created and added to database!";
        }
    }

    @RequestMapping(path = "/login")
    public String loginUser(@RequestBody User userSubmittedViaForm, HttpSession userSession) throws Exception {

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
        }
        //If all checks are passed will log user to session, again maybe shouldn't be an "else".
        else {
            //Saves session by valid username.
            userSession.setAttribute("username", checkUserValidity.getUsername());
            System.out.println("User " + checkUserValidity.getUsername() + " authenticated!");
            return "User authenticated!";
        }
    }

    @RequestMapping(path = "/logout")
    public String logout(HttpSession userSession) throws Exception {

        //Simple check to see if there is even a user that needs to be logged out.
        if (userSession == null) {
            throw new Exception("There is not a user currently logged in");
        }
        //Destroys the session.
        String username = (String) userSession.getAttribute("username");
        System.out.printf("User " + username + " logged out!");
        userSession.invalidate();
        return "User logged out!";
    }
}