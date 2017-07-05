package com.stella.service.vilya.api.core;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QualityConsistency {

    public Boolean execute(Map<Integer, Integer> min,
                        Map<Integer, Integer> max,
                        Map<Integer, Boolean> afterTo,
                        Integer m) {
        Boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < m; ++j) {
                    for (int k = 0; k < m; ++k) {
                        if (i == j || i == k || j == k) {
                            continue;
                        }
                        if (afterTo.get(i * m + k) == afterTo.get(k * m + j)) {
                            if (min.get(i * m + j) < min.get(i * m + k) + min.get(k * m + j)) {
                                min.put(i * m + j, min.get(i * m + k) + min.get(k * m + j));
                                done = false;
                            }
                            if (max.get(i * m + j) > max.get(i * m + k) + max.get(k * m + j)) {
                                max.put(i * m + j, max.get(i * m + k) + max.get(k * m + j));
                                done = false;
                            }
                        }
                    }
                    if (min.get(i * m + j) > max.get(i * m + j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
