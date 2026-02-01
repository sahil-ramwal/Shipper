package com.example.Shipper.Repository;

import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    List<Shipment> findByShipperId(String shipperId);

    List<Shipment> findByCarrierId(String carrierId);

    List<Shipment> findByStatus(ShipmentStatus status);

    boolean existsByTrackingNumber(String trackingNumber);
}
