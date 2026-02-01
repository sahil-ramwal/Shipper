package com.example.Shipper.Repository;

import com.example.Shipper.entity.ShipperProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipperRepository
        extends JpaRepository<ShipperProfile, Long> {

    Optional<ShipperProfile> findByShipperCode(String shipperCode);

    boolean existsByShipperCode(String shipperCode);

    List<ShipperProfile> findByActiveTrue();
}
