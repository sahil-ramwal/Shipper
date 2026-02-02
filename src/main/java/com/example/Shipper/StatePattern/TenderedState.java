package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class TenderedState implements ShipmentState {

    @Override
    public ShipmentStatus supports() {
        return ShipmentStatus.TENDERED;
    }

    @Override
    public ShipmentStatus accept(Shipment shipment, String carrierId) {
        shipment.setCarrierId(carrierId);
        return ShipmentStatus.ACCEPTED;
    }

    @Override
    public ShipmentStatus cancel(Shipment shipment) {
        return ShipmentStatus.CANCELLED;
    }
}
