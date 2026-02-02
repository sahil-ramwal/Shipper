package com.example.Shipper.Repository;

import com.example.Shipper.entity.Carrier;
import com.example.Shipper.enums.OperationalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long> {


    Optional<Carrier> findByCarrierCode(String carrierCode);

    boolean existsByCarrierCode(String carrierCode);


    List<Carrier> findByOperationalStatus(OperationalStatus status);

    List<Carrier> findByNameContainingIgnoreCase(String name);


    Optional<Carrier> findByCarrierCodeAndOperationalStatus(
            String carrierCode,
            OperationalStatus operationalStatus
    );
}

