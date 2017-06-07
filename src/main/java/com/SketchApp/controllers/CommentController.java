package com.SketchApp.controllers;

import com.SketchApp.entities.Comment;
import com.SketchApp.entities.Drawing;
import com.SketchApp.services.CommentRepository;
import com.SketchApp.services.DrawingRepository;
import com.SketchApp.services.FriendRepository;
import com.SketchApp.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by ericweidman on 6/6/17.
 */
@RestController
public class CommentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrawingRepository drawingRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FriendRepository friendRepository;


    @RequestMapping(path = "/add-comment/{id}")
    public String saveComment(@PathVariable("id") int drawingId, Comment userComment, HttpSession userSession) throws Exception {

        //Checks for a valid id.
        if (drawingId == 0) {
            throw new Exception("No drawing id given!");
        }
        //Checks for a user session.
        if (userSession == null) {
            throw new Exception("No user logged in!");
        }
        //Checks for valid comment.
        if (userComment == null) {
            throw new Exception("No comment sent!");
        }

        String username = (String) userSession.getAttribute("username");

        //Saves comment to specific drawing.
        Drawing drawing = drawingRepository.findOne(drawingId);
        Comment comment = new Comment();
        comment.setComment(userComment.getComment());
        comment.setCommenter(username);
        comment.setDrawing(drawing);

        System.out.println("Comment " + "\"" + userComment.getComment() + "\"" + " added to image " + drawing.getTitle());
        return "Comment saved!";
    }

    @RequestMapping(path = "/delete-comment/{id}")
    public String deleteComment(@PathVariable("id") int commentId) throws Exception {

        //Checks for comment id.
        if (commentId == 0) {
            throw new Exception("No id given!");
        }
        //Deletes comment.
        commentRepository.delete(commentId);
        System.out.println("Comment deleted!");
        return "Comment deleted!";
    }
}
