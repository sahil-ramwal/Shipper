package com.example.Shipper.entity;

import com.example.Shipper.enums.OperationalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Carrier {

    @Id
    @GeneratedValue
    private Long carrierId;

    @Column(unique = true, nullable = false)
    private String carrierCode;

    private String name;
    private String contactEmail;
    private String contactPhone;

    @Enumerated(EnumType.STRING)
    private OperationalStatus operationalStatus;

    public void activate() {
        this.operationalStatus = OperationalStatus.ACTIVE;
    }

    public void deactivate() {
        this.operationalStatus = OperationalStatus.INACTIVE;
    }
}
