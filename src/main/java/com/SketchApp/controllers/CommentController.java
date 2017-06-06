package com.SketchApp.controllers;

import com.SketchApp.services.CommentRepository;
import com.SketchApp.services.DrawingRepository;
import com.SketchApp.services.FriendRepository;
import com.SketchApp.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

}
