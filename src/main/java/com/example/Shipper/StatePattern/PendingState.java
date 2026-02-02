package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class PendingState implements ShipmentState {

    @Override
    public ShipmentStatus supports() {
        return ShipmentStatus.PENDING;
    }

    @Override
    public ShipmentStatus tender(Shipment shipment) {
        return ShipmentStatus.TENDERED;
    }

    @Override
    public ShipmentStatus cancel(Shipment shipment) {
        return ShipmentStatus.CANCELLED;
    }
}
