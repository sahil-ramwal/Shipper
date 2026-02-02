package com.example.Shipper.services;

import com.example.Shipper.Repository.CarrierShipmentReadRepository;
import com.example.Shipper.entity.CarrierShipmentRead;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarrierQueryService {

    private final CarrierShipmentReadRepository repository;

    public CarrierQueryService(CarrierShipmentReadRepository repository) {
        this.repository = repository;
    }

    public List<CarrierShipmentRead> getShipmentsForCarrier(Long carrierId) {
        return repository.findByCarrierId(carrierId);
    }
}
