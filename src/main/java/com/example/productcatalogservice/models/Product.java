package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{

    private String description;
    private String name;
    private double price;
    private Category category;
    private String imageUrl;

}
