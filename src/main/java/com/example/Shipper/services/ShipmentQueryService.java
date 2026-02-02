package com.example.Shipper.services;

import com.example.Shipper.Repository.ShipmentRepository;
import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ShipmentQueryService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentQueryService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Shipment getById(Long shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found"));
    }

    public Shipment getByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() ->
                        new EntityNotFoundException("Shipment not found for tracking number: " + trackingNumber));
    }

    public List<Shipment> getByShipperId(String shipperId) {
        return shipmentRepository.findByShipperId(shipperId);
    }

    public List<Shipment> getByCarrierId(String carrierId) {
        return shipmentRepository.findByCarrierId(carrierId);
    }

    public List<Shipment> getByStatus(ShipmentStatus status) {
        return shipmentRepository.findByStatus(status);
    }

    public boolean trackingNumberExists(String trackingNumber) {
        return shipmentRepository.existsByTrackingNumber(trackingNumber);
    }
}
