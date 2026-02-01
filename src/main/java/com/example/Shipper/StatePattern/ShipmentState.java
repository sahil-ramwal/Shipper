package com.example.Shipper.StatePattern;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;

public interface ShipmentState {

    ShipmentStatus getStatus();

    default void tender(Shipment shipment) {
        throw new IllegalStateException("Cannot tender from " + getStatus());
    }

    default void accept(Shipment shipment, String carrierId) {
        throw new IllegalStateException("Cannot accept from " + getStatus());
    }

    default void pickup(Shipment shipment) {
        throw new IllegalStateException("Cannot pickup from " + getStatus());
    }

    default void deliver(Shipment shipment) {
        throw new IllegalStateException("Cannot deliver from " + getStatus());
    }

    default void cancel(Shipment shipment) {
        throw new IllegalStateException("Cannot cancel from " + getStatus());
    }
}
