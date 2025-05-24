package com.example.productcatalogservice.models;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private Date createdAt;
//
//    private Date updatedAt;
//
//    private State state;
}
