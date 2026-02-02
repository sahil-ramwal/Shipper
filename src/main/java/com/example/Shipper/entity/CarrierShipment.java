package com.example.Shipper.entity;

import com.example.Shipper.enums.ShipmentExecutionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class CarrierShipment {

    @Id
    private Long shipmentId; // SAME ID from Shipper

    private Long carrierId;

    @Enumerated(EnumType.STRING)
    private ShipmentExecutionStatus status;

    public void accept() {
        if (status != ShipmentExecutionStatus.TENDERED)
            throw new IllegalStateException("Cannot accept");
        status = ShipmentExecutionStatus.ACCEPTED;
    }

    public void pickup() {
        if (status != ShipmentExecutionStatus.ACCEPTED)
            throw new IllegalStateException("Cannot pickup");
        status = ShipmentExecutionStatus.PICKED_UP;
    }

    public void deliver() {
        if (status != ShipmentExecutionStatus.IN_TRANSIT)
            throw new IllegalStateException("Cannot deliver");
        status = ShipmentExecutionStatus.DELIVERED;
    }
    public  void markInTransit(){
        if(status!=ShipmentExecutionStatus.ACCEPTED)
            throw  new IllegalStateException("Not accepted");
        status=ShipmentExecutionStatus.IN_TRANSIT;
    }
}
