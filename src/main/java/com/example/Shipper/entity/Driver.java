package com.example.Shipper.entity;

import jakarta.persistence.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue
    private Long driverId;

    private String name;
    private String licenseNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Carrier carrier;
}
