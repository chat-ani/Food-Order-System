package org.foodie.foodordersystem.strategy;

import org.foodie.foodordersystem.entity.Restaurant;

import java.util.List;
import java.util.Map;

public interface SelectionStrategy {
    Restaurant select(List<Restaurant> restaurants, Map<String, Integer> items);
}
