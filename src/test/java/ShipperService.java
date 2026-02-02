package com.example.Shipper.services;

import com.example.Shipper.Repository.ShipperRepository;
import com.example.Shipper.entity.ShipperProfile;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShipperService {

    private final ShipperRepository shipperRepository;

    public ShipperService(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
    }

    public ShipperProfile create(ShipperProfile shipper) {
        return shipperRepository.save(shipper);
    }

    public ShipperProfile update(Long id, ShipperProfile updated) {
        ShipperProfile shipper = shipperRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shipper not found"));

        shipper.setCompanyName(updated.getCompanyName());
        shipper.setContactName(updated.getContactName());
        shipper.setEmail(updated.getEmail());
        shipper.setPhone(updated.getPhone());

        return shipperRepository.save(shipper);
    }

    public ShipperProfile activate(Long id) {
        ShipperProfile shipper = shipperRepository.findById(id)
                .orElseThrow();
        shipper.setActive(true);
        return shipper;
    }

    public ShipperProfile deactivate(Long id) {
        ShipperProfile shipper = shipperRepository.findById(id)
                .orElseThrow();
        shipper.setActive(false);
        return shipper;
    }

    public ShipperProfile get(Long id) {
        return shipperRepository.findById(id)
                .orElseThrow();
    }
}
