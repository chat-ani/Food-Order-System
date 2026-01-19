package org.anirban.foodordersystem.strategy;

import org.anirban.foodordersystem.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component("LOWEST_COST")
public class LowestCostStrategy implements SelectionStrategy{
    @Override
    public Restaurant select(List<Restaurant> restaurants, Map<String, Integer> items) {
        return restaurants.stream()
                .min(Comparator.comparingInt(r -> r.calculateBill(items)))
                .orElseThrow();    }
}
