package com.SketchApp.services;

import com.SketchApp.entities.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ericweidman on 6/4/17.
 */
public interface FriendRepository extends CrudRepository<Friend, Integer> {

    List<Friend> findAllByUserId(int id);

}
