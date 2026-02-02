package com.example.Shipper.Repository;


import com.example.Shipper.entity.CarrierShipmentRead;import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrierShipmentReadRepository
        extends JpaRepository<CarrierShipmentRead, Long> {

    List<CarrierShipmentRead> findByCarrierId(Long carrierId);
}
