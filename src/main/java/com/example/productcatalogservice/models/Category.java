package com.example.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {

    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference // to avoid cyclic calls , should be added to parent side
    private List<Product> products;

}
