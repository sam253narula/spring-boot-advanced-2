package com.example.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDetails {

    @Id
    private Integer id;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "NUMBER_OF_ITEMS_AVAILABLE")
    private int numberOfItemsAvailable;

    @Column(name = "PRICE")
    private int price;

}
