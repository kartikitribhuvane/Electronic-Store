package com.ElectronicStore.ElectronicStore.dtos;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
<<<<<<< HEAD
=======
@ToString
>>>>>>> origin/master
public class ProductDto {

    private String productId;

    private String title;

    private String description;

    private int price;

    private int discountedPrice;

    private int quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;
}
