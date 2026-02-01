package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class TenderedState implements ShipmentState {

    @Override
    public ShipmentStatus getStatus() {
        return ShipmentStatus.TENDERED;
    }

    @Override
    public void accept(Shipment shipment, String carrierId) {
        shipment.setCarrierId(carrierId);
        shipment.setState(ShipmentStatus.ACCEPTED);
    }

    @Override
    public void cancel(Shipment shipment) {
        shipment.setState(ShipmentStatus.CANCELLED);
    }
}
