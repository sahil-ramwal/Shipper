package com.example.Shipper.entity;

import com.example.Shipper.StatePattern.ShipmentState;
import com.example.Shipper.StatePattern.ShipmentStateRegistry;
import com.example.Shipper.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    @Column(unique = true, nullable = false,length = 50)
    private String trackingNumber;
    @Column(nullable = false,length = 50)
    private String shipperId;

    // Nullable - carrier is assigned later
    private String carrierId;

    // Nullable - may be reference to address entity or just free text
    private String originId;

    @Column(nullable = false)
    private String originAddress;

    @Column(nullable = false)
    private String destinationAddress;

    private LocalDate estimatedDeliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status = ShipmentStatus.PENDING;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipmentPackage> shipmentPackages = new ArrayList<>();

    @Transient
    private ShipmentState state;
    @Transient
    private ShipmentStateRegistry registry;

    public void setRegistry(ShipmentStateRegistry registry) {
        this.registry = registry;
    }

    @PostLoad
    @PostPersist
    private void initState() {
        this.state = registry.getState(this.status);
    }


    public void setState(ShipmentStatus newStatus) {
        this.status = newStatus;
        this.state=registry.getState(newStatus);
    }
    public void tender() {
        state.tender(this);
    }

    public void accept(String carrierId) {
        state.accept(this, carrierId);
    }

    public void pickup() {
        state.pickup(this);
    }

    public void deliver() {
        state.deliver(this);
    }

    public void cancel() {
        state.cancel(this);
    }


//    public void addPackage(ShipmentPackage pkg) {
//        shipmentPackages.add(pkg);
//        pkg.setShipment(this);
//    }
}