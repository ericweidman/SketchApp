package com.SketchApp.entities;

import javax.persistence.*;

/**
 * Created by ericweidman on 6/4/17.
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String commenter;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    private Drawing drawing;

    public Comment() {
    }

    public Comment(String comment, Drawing drawing) {
        this.comment = comment;
        this.drawing = drawing;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }
}
