package com.example.Shipper.StatePattern;

import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class DeliveredState implements ShipmentState {
    public ShipmentStatus supports() {
        return ShipmentStatus.DELIVERED;
    }
}
