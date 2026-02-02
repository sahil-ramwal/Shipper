package com.example.Shipper.Repository;


import com.example.Shipper.entity.CarrierShipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierShipmentRepository
        extends JpaRepository<CarrierShipment, Long> {
}
