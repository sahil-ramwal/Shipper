package com.example.Shipper.services;

import com.example.Shipper.Repository.ShipmentRepository;
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
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found"));

        shipment.setRegistry(stateRegistry);
        return shipment;
    }

    public Shipment tender(Long shipmentId) {
        Shipment shipment = loadShipment(shipmentId);
        shipment.tender();
        return shipmentRepository.save(shipment);
    }

    public Shipment accept(Long shipmentId, String carrierId) {
        Shipment shipment = loadShipment(shipmentId);
        shipment.accept(carrierId);
        return shipmentRepository.save(shipment);
    }

    public Shipment pickup(Long shipmentId) {
        Shipment shipment = loadShipment(shipmentId);
        shipment.pickup();
        return shipmentRepository.save(shipment);
    }

    public Shipment deliver(Long shipmentId) {
        Shipment shipment = loadShipment(shipmentId);
        shipment.deliver();
        return shipmentRepository.save(shipment);
    }

    public Shipment cancel(Long shipmentId) {
        Shipment shipment = loadShipment(shipmentId);
        shipment.cancel();
        return shipmentRepository.save(shipment);
    }
}

