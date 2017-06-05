package com.SketchApp.services;

import com.SketchApp.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ericweidman on 6/4/17.
 */
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findAllByDrawingId(int id);

}
