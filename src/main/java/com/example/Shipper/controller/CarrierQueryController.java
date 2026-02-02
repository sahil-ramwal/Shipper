package com.example.Shipper.controller;



import com.example.Shipper.entity.CarrierShipmentRead;
import com.example.Shipper.services.CarrierQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrier/queries/shipments")
public class CarrierQueryController {

    private final CarrierQueryService service;

    public CarrierQueryController(CarrierQueryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarrierShipmentRead> getShipments(
            @RequestParam Long carrierId) {
        return service.getShipmentsForCarrier(carrierId);
    }
}
