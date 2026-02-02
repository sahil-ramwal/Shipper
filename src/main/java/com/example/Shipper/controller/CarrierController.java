package com.example.Shipper.controller;

import com.example.Shipper.entity.Carrier;
import com.example.Shipper.enums.OperationalStatus;
import com.example.Shipper.services.CarrierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carriers")
public class CarrierController {

    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }


    @PostMapping
    public ResponseEntity<Carrier> create(@RequestBody Carrier carrier) {
        return new ResponseEntity<>(
                carrierService.createCarrier(carrier),
                HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public Carrier getById(@PathVariable Long id) {
        return carrierService.getById(id);
    }

    @GetMapping("/code/{carrierCode}")
    public Carrier getByCode(@PathVariable String carrierCode) {
        return carrierService.getByCarrierCode(carrierCode);
    }

    @GetMapping
    public List<Carrier> getAll() {
        return carrierService.getAll();
    }

    @GetMapping("/status/{status}")
    public List<Carrier> getByStatus(@PathVariable OperationalStatus status) {
        return carrierService.getByStatus(status);
    }


    @PutMapping("/{id}")
    public Carrier update(
            @PathVariable Long id,
            @RequestBody Carrier carrier) {
        return carrierService.updateCarrier(id, carrier);
    }


    @PutMapping("/{id}/activate")
    public Carrier activate(@PathVariable Long id) {
        return carrierService.activate(id);
    }

    @PutMapping("/{id}/deactivate")
    public Carrier deactivate(@PathVariable Long id) {
        return carrierService.deactivate(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        carrierService.delete(id);
    }
}
