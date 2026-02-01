package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class InTransitState implements ShipmentState {

    @Override
    public ShipmentStatus getStatus() {
        return ShipmentStatus.IN_TRANSIT;
    }

    @Override
    public void deliver(Shipment shipment) {
        shipment.setState(ShipmentStatus.DELIVERED);
    }
}

