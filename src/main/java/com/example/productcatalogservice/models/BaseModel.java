package com.example.productcatalogservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private Date createdAt;
//
//    private Date updatedAt;
//

    private State state = State.ACTIVE;
}
