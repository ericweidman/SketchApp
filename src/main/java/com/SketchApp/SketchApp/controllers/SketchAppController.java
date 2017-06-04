package com.SketchApp.SketchApp.controllers;

import com.SketchApp.SketchApp.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ericweidman on 6/4/17.
 */
@RestController
public class SketchAppController {

    @Autowired
    private UserRepository userRepository;




}
