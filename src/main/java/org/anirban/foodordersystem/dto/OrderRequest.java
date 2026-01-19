package org.anirban.foodordersystem.dto;

import org.anirban.foodordersystem.entity.SelectionType;

import java.util.Map;

public class OrderRequest {
    public String orderId;
    public String user;
    public Map<String, Integer> items;
    public SelectionType selectionType;
}
