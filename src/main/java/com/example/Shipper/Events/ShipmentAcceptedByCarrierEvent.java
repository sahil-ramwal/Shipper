package com.example.Shipper.Events;

public record ShipmentAcceptedByCarrierEvent(
        Long shipmentId,
        Long carrierId
) {}
