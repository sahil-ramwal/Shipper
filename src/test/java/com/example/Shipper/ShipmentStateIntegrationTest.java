package com.example.Shipper.IntegrationTests;

import com.example.Shipper.Repository.ShipmentRepository;
import com.example.Shipper.ShipperApplication;
import com.example.Shipper.StatePattern.AcceptedState;
import com.example.Shipper.StatePattern.ShipmentStateRegistry;
import com.example.Shipper.entity.Shipment;
import com.example.Shipper.enums.ShipmentStatus;
import com.example.Shipper.services.ShipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ShipperApplication.class)
@Transactional
@ActiveProfiles("test")
class ShipmentStateIntegrationTest {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentRepository shipmentRepository;

//    @Autowired
//    private EntityManager entityManager;

    private Shipment shipment;

    @BeforeEach
    void setUp() {
        Shipment s = new Shipment();
        s.setTrackingNumber("TRK-1001");
        s.setShipperId("SHIPPER-1");
        s.setOriginAddress("Hyderabad");
        s.setDestinationAddress("Bangalore");
        ShipmentStateRegistry shipmentStateRegistry=new ShipmentStateRegistry(List.of(AcceptedState));
                s.setRegistry(shipmentStateRegistry);
        shipment = shipmentRepository.save(s);
    }

    @Test
    void shouldCompleteFullShipmentLifecycle() {

        Shipment tendered = shipmentService.tender(shipment.getShipmentId());
        assertEquals(ShipmentStatus.TENDERED, tendered.getStatus());

        Shipment accepted = shipmentService.accept(
                shipment.getShipmentId(), "CARRIER-123");
        assertEquals(ShipmentStatus.ACCEPTED, accepted.getStatus());
        assertEquals("CARRIER-123", accepted.getCarrierId());

        Shipment inTransit = shipmentService.pickup(shipment.getShipmentId());
        assertEquals(ShipmentStatus.IN_TRANSIT, inTransit.getStatus());

        Shipment delivered = shipmentService.deliver(shipment.getShipmentId());
        assertEquals(ShipmentStatus.DELIVERED, delivered.getStatus());
    }

    @Test
    void shouldFailWhenDeliveringBeforePickup() {

        shipmentService.tender(shipment.getShipmentId());
        shipmentService.accept(shipment.getShipmentId(), "CARRIER-123");

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> shipmentService.deliver(shipment.getShipmentId())
        );

        assertTrue(ex.getMessage().toLowerCase().contains("deliver"));
    }

    @Test
    void shouldAllowCancelFromTendered() {

        shipmentService.tender(shipment.getShipmentId());

        Shipment cancelled = shipmentService.cancel(shipment.getShipmentId());

        assertEquals(ShipmentStatus.CANCELLED, cancelled.getStatus());
    }

    @Test
    void shouldNotAllowCancelAfterDelivery() {

        shipmentService.tender(shipment.getShipmentId());
        shipmentService.accept(shipment.getShipmentId(), "CARRIER-123");
        shipmentService.pickup(shipment.getShipmentId());
        shipmentService.deliver(shipment.getShipmentId());

        assertThrows(IllegalStateException.class,
                () -> shipmentService.cancel(shipment.getShipmentId()));
    }

    @Test
    void shouldRestoreCorrectStateAfterReload() {

        shipmentService.tender(shipment.getShipmentId());
//
//        entityManager.flush();
//        entityManager.clear();

        Shipment reloaded = shipmentRepository
                .findById(shipment.getShipmentId())
                .orElseThrow();

        assertEquals(ShipmentStatus.TENDERED, reloaded.getStatus());
    }
}
