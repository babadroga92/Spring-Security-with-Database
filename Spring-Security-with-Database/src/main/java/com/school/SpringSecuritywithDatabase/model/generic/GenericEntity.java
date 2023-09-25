package com.school.SpringSecuritywithDatabase.model.generic;
import com.fasterxml.jackson.annotation.JsonView;
import com.school.SpringSecuritywithDatabase.view.View;

import javax.persistence.*;

@MappedSuperclass //this class wont be represented in database
public abstract class GenericEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.ShowMinimal.class)
    private int id;

    public GenericEntity() {
    }

    public GenericEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
