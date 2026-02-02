package com.example.Shipper.services;

import com.example.Shipper.Repository.CarrierRepository;
import com.example.Shipper.entity.Carrier;
import com.example.Shipper.enums.OperationalStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarrierService {

    private final CarrierRepository carrierRepository;

    public CarrierService(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }


    public Carrier createCarrier(Carrier carrier) {
        if (carrierRepository.existsByCarrierCode(carrier.getCarrierCode())) {
            throw new IllegalArgumentException("Carrier code already exists");
        }
        carrier.deactivate();
        return carrierRepository.save(carrier);
    }


    public Carrier getById(Long carrierId) {
        return carrierRepository.findById(carrierId)
                .orElseThrow(() -> new EntityNotFoundException("Carrier not found"));
    }

    public Carrier getByCarrierCode(String carrierCode) {
        return carrierRepository.findByCarrierCode(carrierCode)
                .orElseThrow(() -> new EntityNotFoundException("Carrier not found"));
    }

    public List<Carrier> getAll() {
        return carrierRepository.findAll();
    }

    public List<Carrier> getByStatus(OperationalStatus status) {
        return carrierRepository.findByOperationalStatus(status);
    }


    public Carrier updateCarrier(Long carrierId, Carrier updated) {
        Carrier carrier = getById(carrierId);

        carrier.setName(updated.getName());
        carrier.setContactEmail(updated.getContactEmail());
        carrier.setContactPhone(updated.getContactPhone());

        return carrierRepository.save(carrier);
    }


    public Carrier activate(Long carrierId) {
        Carrier carrier = getById(carrierId);
        carrier.activate();
        return carrierRepository.save(carrier);
    }

    public Carrier deactivate(Long carrierId) {
        Carrier carrier = getById(carrierId);
        carrier.deactivate();
        return carrierRepository.save(carrier);
    }


    public void delete(Long carrierId) {
        if (!carrierRepository.existsById(carrierId)) {
            throw new EntityNotFoundException("Carrier not found");
        }
        carrierRepository.deleteById(carrierId);
    }
}
