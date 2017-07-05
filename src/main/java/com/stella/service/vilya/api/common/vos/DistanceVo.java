package com.stella.service.vilya.api.common.vos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class DistanceVo {

    private static Integer LMIN = 0;
    private static Integer LMAX = Integer.MAX_VALUE / 2;

    private Map<Integer, Integer> dMin;

    private Map<Integer, Integer> dMax;

    private Map<Integer, Boolean> afterTo;

    public DistanceVo(Integer m) {
        dMin = new HashMap<>();
        dMax = new HashMap<>();
        afterTo = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i == j) {
                    dMin.put(i * m + j, LMIN);
                    dMax.put(i * m + j, LMIN);
                }
                afterTo.put(i * m + j, false);
            }
        }
    }
}
