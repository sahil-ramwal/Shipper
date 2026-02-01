package com.example.Shipper.controller;

import com.example.Shipper.entity.ShipperProfile;
import com.example.Shipper.services.ShipperService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippers")
public class ShipperController {

    private final ShipperService shipperService;

    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @PostMapping
    public ShipperProfile create(@RequestBody ShipperProfile shipper) {
        return shipperService.create(shipper);
    }

    @PutMapping("/{id}")
    public ShipperProfile update(
            @PathVariable Long id,
            @RequestBody ShipperProfile shipper) {
        return shipperService.update(id, shipper);
    }

    @PostMapping("/{id}/activate")
    public ShipperProfile activate(@PathVariable Long id) {
        return shipperService.activate(id);
    }

    @PostMapping("/{id}/deactivate")
    public ShipperProfile deactivate(@PathVariable Long id) {
        return shipperService.deactivate(id);
    }

    @GetMapping("/{id}")
    public ShipperProfile get(@PathVariable Long id) {
        return shipperService.get(id);
    }
}
