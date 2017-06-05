package com.SketchApp.services;

import com.SketchApp.entities.Drawing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ericweidman on 6/4/17.
 */
public interface DrawingRepository extends CrudRepository<Drawing, Integer> {

    List<Drawing> findAllByUserId(int id);

    Drawing findByTitle(String title);

}
