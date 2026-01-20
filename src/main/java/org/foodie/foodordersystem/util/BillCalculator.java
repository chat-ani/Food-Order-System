package org.foodie.foodordersystem.util;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public final class BillCalculator {

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