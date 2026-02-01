package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class PendingState implements ShipmentState {

    @Override
    public ShipmentStatus getStatus() {
        return ShipmentStatus.PENDING;
    }

    @Override
    public void tender(Shipment shipment) {
        shipment.setState(ShipmentStatus.TENDERED);
    }

    @Override
    public void cancel(Shipment shipment) {
        shipment.setState(ShipmentStatus.CANCELLED);
    }
}
