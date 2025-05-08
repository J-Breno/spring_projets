package com.github.Jbreno.dscatalog.util;

import com.github.Jbreno.dscatalog.entities.Product;
import com.github.Jbreno.dscatalog.projections.ProductProjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static List<Product> replace(List<ProductProjection> ordered, List<Product> unordered) {
        Map<Long, Product> map = new HashMap<>();
        for(Product p : unordered) {
            map.put(p.getId(), p);
        }

        List<Product> result = new ArrayList<>();
        for(ProductProjection pj : ordered) {
            result.add(map.get(pj.getId()));
        }
        return result;
    }
}
