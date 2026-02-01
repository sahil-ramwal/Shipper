package com.example.Shipper.Repository;

import com.example.Shipper.entity.ShipmentPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentPackageRepository
        extends JpaRepository<ShipmentPackage, Long> {

    List<ShipmentPackage> findByShipment_ShipmentId(Long shipmentId);
}
