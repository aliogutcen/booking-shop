package com.ogutcenali.repository.entity;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashMap;
import java.util.Map;

@RedisHash("Cart")
public class Cart {

    @Id
    private String id;
    private Map<Long, Integer> products;

    public Cart(String id) {
        this.id = id;
        this.products = new HashMap<>();
    }

    public String getId() {
        return id;
    }


    public Map<Long, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Integer> products) {
        this.products = products;
    }

    public void addProduct(Long productId, int quantity) {
        products.put(productId, quantity);
    }

    public void removeProduct(Long productId) {
        products.remove(productId);
    }

    public void clear() {
        products.clear();
    }


}
