package com.example.Shipper.controller;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.services.ShipmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/{id}/tender")
    public Shipment tender(@PathVariable Long id) {
        return shipmentService.tender(id);
    }

    @PostMapping("/{id}/accept")
    public Shipment accept(
            @PathVariable Long id,
            @RequestParam String carrierId) {
        return shipmentService.accept(id, carrierId);
    }

    @PostMapping("/{id}/pickup")
    public Shipment pickup(@PathVariable Long id) {
        return shipmentService.pickup(id);
    }

    @PostMapping("/{id}/deliver")
    public Shipment deliver(@PathVariable Long id) {
        return shipmentService.deliver(id);
    }

    @PostMapping("/{id}/cancel")
    public Shipment cancel(@PathVariable Long id) {
        return shipmentService.cancel(id);
    }
}
