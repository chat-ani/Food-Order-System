package org.anirban.foodordersystem.util;

import java.util.Map;

public final class BillCalculator {

    private BillCalculator() {
    }

    public static int calculateBill(
            Map<String, Integer> menu,
            Map<String, Integer> orderItems) {

        return orderItems.entrySet().stream()
                .mapToInt(entry ->
                        menu.get(entry.getKey()) * entry.getValue()
                )
                .sum();
    }
}