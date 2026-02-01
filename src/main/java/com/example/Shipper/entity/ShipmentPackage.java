package com.example.Shipper.entity;


import com.example.Shipper.enums.ContentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long packageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id",nullable = false)
    private Shipment shipment;
   @Column(nullable = false)
    private double weight;
   @Column(nullable = false)
   private String dimensions;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;
}
