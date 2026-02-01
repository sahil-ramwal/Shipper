package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class AcceptedState implements ShipmentState {

    @Override
    public ShipmentStatus getStatus() {
        return ShipmentStatus.ACCEPTED;
    }

    @Override
    public void pickup(Shipment shipment) {
        shipment.setState(ShipmentStatus.IN_TRANSIT);
    }
}

