package com.SketchApp.controllers;

import com.SketchApp.entities.Comment;
import com.SketchApp.entities.Drawing;
import com.SketchApp.entities.User;
import com.SketchApp.services.CommentRepository;
import com.SketchApp.services.DrawingRepository;
import com.SketchApp.services.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by ericweidman on 6/6/17.
 */

@RestController
public class DrawingController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrawingRepository drawingRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(path = "/save-drawing")
    public String saveDrawing(@RequestBody Drawing newUserDrawing, HttpSession userSession) throws Exception {

        //Checks to see if a user is logged in.
        if (userSession == null) {
            throw new Exception("No user logged in!");
        }

        //Checks for a title
        if (newUserDrawing.getTitle() == null) {
            throw new Exception("The drawing was not given a title!");
        }

        //Checks for base64 image.
        if (newUserDrawing.getBase64result() == null) {
            throw new Exception("No image given!");
        }

        //Establishes the user.
        String username = (String) userSession.getAttribute("username");
        User loggedInUser = userRepository.findByusername(username);

        //Saves image to the public folder with the convention public/username_title.png
        byte[] userDrawing = Base64.decodeBase64(newUserDrawing.getBase64result());
        String saveLocation = "public/" + loggedInUser.getUsername() + "_" + newUserDrawing.getTitle() + ".png";
        new FileOutputStream(saveLocation).write(userDrawing);

        //Saves the image to the database.
        Drawing saveDrawing = new Drawing();
        saveDrawing.setTitle(newUserDrawing.getTitle());
        saveDrawing.setBase64result(newUserDrawing.getBase64result());
        saveDrawing.setFileLocation(saveLocation);
        saveDrawing.setUser(loggedInUser);
        drawingRepository.save(saveDrawing);

        System.out.println("Drawing saved to " + saveLocation);
        return "Drawing saved to " + saveLocation;
    }

    @RequestMapping(path = "/delete-drawing/{id}")
    public String saveDrawing(@PathVariable("id") int drawingId, HttpSession userSession) throws Exception {

        //Checks for drawing id.
        if (drawingId == 0) {
            throw new Exception("Drawing id not given!");
        }
        //Checks session for valid user.
        if (userSession == null) {
            throw new Exception("No user logged in!");
        }
        //If username on drawing does not match username from session it cannot be deleted.
        Drawing drawingToBeDeleted = drawingRepository.findOne(drawingId);
        String currentUserName = (String) userSession.getAttribute("username");
        if (!drawingToBeDeleted.getUser().getUsername().equals(currentUserName)) {
            throw new Exception("User cannot delete drawings of others!");
        }

        drawingRepository.delete(drawingId);
        System.out.println("Drawing removed!");
        return "Drawing removed!";
    }

    @RequestMapping(path = "/all-user-drawings")
    public List<Drawing> allUserDrawings(HttpSession userSession) throws Exception {

        if (userSession == null) {
            throw new Exception("No user logged in!");
        }
        //Gets the current user by session username.
        User currentUser = userRepository.findByusername((String) userSession.getAttribute("username"));
        //Returns a list with all current users drawings.
        return drawingRepository.findAllByUserId(currentUser.getId());
    }

    @RequestMapping(path = "/all-drawings")
    public List<Drawing> allDrawings() {

        //Returns all drawings in DB.
        return (List<Drawing>) drawingRepository.findAll();
    }
}