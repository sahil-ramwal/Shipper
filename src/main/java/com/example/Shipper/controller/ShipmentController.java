package com.example.Shipper.controller;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import com.example.Shipper.services.ShipmentCommandService;
import com.example.Shipper.services.ShipmentQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentCommandService commandService;
    private final ShipmentQueryService queryService;

    public ShipmentController(ShipmentCommandService commandService,
                              ShipmentQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    /* ---------------- CREATE ---------------- */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment create(@RequestBody Shipment shipment) {
        return commandService.createShipment(shipment);
    }

    /* ---------------- READ ---------------- */

    @GetMapping("/{id}")
    public Shipment getById(@PathVariable Long id) {
        return queryService.getById(id);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public Shipment getByTracking(@PathVariable String trackingNumber) {
        return queryService.getByTrackingNumber(trackingNumber);
    }

    @GetMapping("/shipper/{shipperId}")
    public List<Shipment> getByShipper(@PathVariable String shipperId) {
        return queryService.getByShipperId(shipperId);
    }

    @GetMapping("/carrier/{carrierId}")
    public List<Shipment> getByCarrier(@PathVariable String carrierId) {
        return queryService.getByCarrierId(carrierId);
    }

    @GetMapping
    public List<Shipment> getByStatus(@RequestParam ShipmentStatus status) {
        return queryService.getByStatus(status);
    }

    /* ---------------- STATE TRANSITIONS ---------------- */

    @PostMapping("/{id}/tender")
    public Shipment tender(@PathVariable Long id) {
        return commandService.tender(id);
    }

    @PostMapping("/{id}/accept")
    public Shipment accept(@PathVariable Long id,
                           @RequestParam String carrierId) {
        return commandService.accept(id, carrierId);
    }

    @PostMapping("/{id}/pickup")
    public Shipment pickup(@PathVariable Long id) {
        return commandService.pickup(id);
    }

    @PostMapping("/{id}/deliver")
    public Shipment deliver(@PathVariable Long id) {
        return commandService.deliver(id);
    }

    @PostMapping("/{id}/cancel")
    public Shipment cancel(@PathVariable Long id) {
        return commandService.cancel(id);
    }
}
