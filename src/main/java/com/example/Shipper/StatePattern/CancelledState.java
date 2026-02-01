package com.example.Shipper.StatePattern;

import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

@Component
public class CancelledState implements ShipmentState {
    public ShipmentStatus getStatus() {
        return ShipmentStatus.CANCELLED;
    }
}
