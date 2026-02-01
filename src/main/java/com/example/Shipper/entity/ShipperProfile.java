package com.example.Shipper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shippers")
@Getter
@Setter
public class ShipperProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipperId;

    @Column(nullable = false, unique = true)
    private String shipperCode;

    @Column(nullable = false)
    private String companyName;

    private String contactName;
    private String email;
    private String phone;

    private boolean active = true;

}
