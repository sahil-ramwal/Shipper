package com.example.Shipper.services;

import com.example.Shipper.Repository.ShipmentRepository;
import com.example.Shipper.StatePattern.ShipmentState;
import com.example.Shipper.StatePattern.ShipmentStateRegistry;
import com.example.Shipper.entity.Shipment;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentStateRegistry stateRegistry;

    public ShipmentService(ShipmentRepository shipmentRepository,
                           ShipmentStateRegistry stateRegistry) {
        this.shipmentRepository = shipmentRepository;
        this.stateRegistry = stateRegistry;
    }

    private Shipment loadShipment(Long shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found"));
    }

    public Shipment tender(Long shipmentId) {
        Shipment shipment = loadShipment(shipmentId);
        ShipmentState state = stateRegistry.getState(shipment.getStatus());

        shipment.setStatus(state.tender(shipment));
        return shipment;
    }

    public Shipment accept(Long shipmentId, String carrierId) {
        Shipment shipment = loadShipment(shipmentId);
        ShipmentState state = stateRegistry.getState(shipment.getStatus());

        shipment.setStatus(state.accept(shipment, carrierId));
        return shipment;
    }

    public Shipment pickup(Long shipmentId) {
        Shipment shipment = loadShipment(shipmentId);
        ShipmentState state = stateRegistry.getState(shipment.getStatus());

        shipment.setStatus(state.pickup(shipment));
        return shipment;
    }

    public Shipment deliver(Long shipmentId) {
        Shipment shipment = loadShipment(shipmentId);
        ShipmentState state = stateRegistry.getState(shipment.getStatus());

        shipment.setStatus(state.deliver(shipment));
        return shipment;
    }

    public Shipment cancel(Long shipmentId) {
        Shipment shipment = loadShipment(shipmentId);
        ShipmentState state = stateRegistry.getState(shipment.getStatus());

        shipment.setStatus(state.cancel(shipment));
        return shipment;
    }
}
