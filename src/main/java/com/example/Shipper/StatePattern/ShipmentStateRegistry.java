package com.example.Shipper.StatePattern;

import com.example.Shipper.enums.ShipmentStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ShipmentStateRegistry {

    private final Map<ShipmentStatus, ShipmentState> stateMap;

    public ShipmentStateRegistry(List<ShipmentState> states) {
        this.stateMap = states.stream()
                .collect(Collectors.toMap(
                        ShipmentState::getStatus,
                        Function.identity()
                ));
    }

    public ShipmentState getState(ShipmentStatus status) {
        return stateMap.get(status);
    }
}

