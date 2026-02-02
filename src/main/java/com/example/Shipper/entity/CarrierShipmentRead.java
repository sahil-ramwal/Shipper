package com.example.Shipper.entity;

import com.example.Shipper.enums.ShipmentExecutionStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "carrier_shipments_read")
public class CarrierShipmentRead {

    @Id
    private Long shipmentId;

    private Long carrierId;

    @Enumerated(EnumType.STRING)
    private ShipmentExecutionStatus status;
}
