package com.example.Shipper.services;

import com.example.Shipper.Events.ShipmentAcceptedByCarrierEvent;
import com.example.Shipper.Repository.CarrierShipmentRepository;
import com.example.Shipper.entity.CarrierShipment;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarrierCommandService {

    private final CarrierShipmentRepository shipmentRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CarrierCommandService(CarrierShipmentRepository shipmentRepository,
                                 ApplicationEventPublisher eventPublisher) {
        this.shipmentRepository = shipmentRepository;
        this.eventPublisher = eventPublisher;
    }

    public void acceptShipment(Long shipmentId, Long carrierId) {
        CarrierShipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        shipment.accept();

        eventPublisher.publishEvent(
                new ShipmentAcceptedByCarrierEvent(shipmentId, carrierId)
        );
    }

    public void pickupShipment(Long shipmentId) {
        CarrierShipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow();
        shipment.pickup();
    }

    public void markInTransit(Long shipmentId) {
        CarrierShipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow();
        shipment.markInTransit();
    }

    public void deliverShipment(Long shipmentId) {
        CarrierShipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow();
        shipment.deliver();
    }
}
