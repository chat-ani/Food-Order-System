package org.foodie.foodordersystem.dto;

import org.foodie.foodordersystem.entity.SelectionType;

import java.util.Map;

public class OrderRequest {
    public String orderId;
    public String user;
    public Map<String, Integer> items;
    public SelectionType selectionType;
}
