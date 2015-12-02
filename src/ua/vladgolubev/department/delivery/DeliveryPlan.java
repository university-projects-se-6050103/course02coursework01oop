package ua.vladgolubev.department.delivery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * План поставки, що містить список поставок, які об’єднані однаковою датою.
 */
public class DeliveryPlan {
    private List<Delivery> plannedDeliveries = new ArrayList<>();
    private LocalDate lastPossibleDate = LocalDate.MIN;

    public DeliveryPlan planDelivery(Delivery delivery) {
        plannedDeliveries.add(delivery);
        if (delivery.getEstimatedDate().compareTo(lastPossibleDate) > 0) {
            lastPossibleDate = delivery.getEstimatedDate();
        }
        return this;
    }

    public List<Delivery> getPlannedDeliveries() {
        return plannedDeliveries;
    }

    public LocalDate getLastPossibleDate() {
        return lastPossibleDate;
    }

    @Override
    public String toString() {
        return "DeliveryPlan{" +
                "plannedDeliveries=" + plannedDeliveries +
                ", lastPossibleDate=" + lastPossibleDate +
                '}';
    }
}
