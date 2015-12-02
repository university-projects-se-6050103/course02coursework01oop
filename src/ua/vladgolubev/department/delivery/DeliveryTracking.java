package ua.vladgolubev.department.delivery;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public final class DeliveryTracking {
    public static List<Delivery> getOverdueDeliveries(DeliveryPlan deliveryPlan) {
        return deliveryPlan.getPlannedDeliveries()
                .stream()
                .filter(delivery -> delivery.getEstimatedDate().compareTo(LocalDate.now()) > 0)
                .collect(Collectors.toList());
    }
}
