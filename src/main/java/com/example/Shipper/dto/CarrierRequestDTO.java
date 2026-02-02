package com.example.Shipper.dto;

import com.example.Shipper.enums.OperationalStatus;
import lombok.Data;

@Data
public class CarrierRequestDTO {
    private Long carrierId;
    private String carrierCode;
    private String name;
    private String contactEmail;
    private String contactPhone;
    private OperationalStatus operationalStatus;
}
