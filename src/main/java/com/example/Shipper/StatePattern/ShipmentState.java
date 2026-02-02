package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
public interface ShipmentState {

    ShipmentStatus supports();

    default ShipmentStatus tender(Shipment shipment) {
        throw new IllegalStateException("Cannot tender from " + supports());
    }

    default ShipmentStatus accept(Shipment shipment, String carrierId) {
        throw new IllegalStateException("Cannot accept from " + supports());
    }

    default ShipmentStatus pickup(Shipment shipment) {
        throw new IllegalStateException("Cannot pickup from " + supports());
    }

    default ShipmentStatus deliver(Shipment shipment) {
        throw new IllegalStateException("Cannot deliver from " + supports());
    }

    default ShipmentStatus cancel(Shipment shipment) {
        throw new IllegalStateException("Cannot cancel from " + supports());
    }
}

