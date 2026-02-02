package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class AcceptedState implements ShipmentState {

    @Override
    public ShipmentStatus supports() {
        return ShipmentStatus.ACCEPTED;
    }

    @Override
    public ShipmentStatus pickup(Shipment shipment) {

        return ShipmentStatus.IN_TRANSIT;
    }
}

