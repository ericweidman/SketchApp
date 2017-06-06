package com.SketchApp.controllers;

import com.SketchApp.services.CommentRepository;
import com.SketchApp.services.DrawingRepository;
import com.SketchApp.services.FriendRepository;
import com.SketchApp.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ericweidman on 6/6/17.
 */
public class DrawingController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrawingRepository drawingRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FriendRepository friendRepository;

}
