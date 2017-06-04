package com.SketchApp.SketchApp.services;

import com.SketchApp.SketchApp.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ericweidman on 6/4/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserName(String username);
}
