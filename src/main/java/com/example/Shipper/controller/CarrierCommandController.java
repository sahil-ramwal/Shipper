package com.example.Shipper.controller;

import com.example.Shipper.services.CarrierCommandService;import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrier/commands/shipments")
public class CarrierCommandController {

    private final CarrierCommandService service;

    public CarrierCommandController(CarrierCommandService service) {
        this.service = service;
    }

    @PostMapping("/{shipmentId}/accept")
    public void acceptShipment(@PathVariable Long shipmentId,
                               @RequestParam Long carrierId) {
        service.acceptShipment(shipmentId, carrierId);
    }

    @PostMapping("/{shipmentId}/pickup")
    public void pickup(@PathVariable Long shipmentId) {
        service.pickupShipment(shipmentId);
    }

    @PostMapping("/{shipmentId}/in-transit")
    public void inTransit(@PathVariable Long shipmentId) {
        service.markInTransit(shipmentId);
    }

    @PostMapping("/{shipmentId}/deliver")
    public void deliver(@PathVariable Long shipmentId) {
        service.deliverShipment(shipmentId);
    }
}
