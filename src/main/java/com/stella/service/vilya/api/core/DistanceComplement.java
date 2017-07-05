package com.stella.service.vilya.api.core;

import com.stella.service.vilya.api.common.ops.IAOp;
import com.stella.service.vilya.api.common.vos.BASetVo;
import com.stella.service.vilya.api.common.vos.DistanceVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DistanceComplement {

    private static Integer LMIN = 0;

    private static Integer LMAX = Integer.MAX_VALUE / 2;

    public void dMinComplement(Map<Integer, Integer> dMin, Integer m) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                if (dMin.get(i * m + j) == null) {
                    if (dMin.get(j * m + i) != null) {
                        dMin.put(i * m + j, dMin.get(j * m + i));
                    } else {
                        dMin.put(i * m + j, LMIN);
                    }
                }
            }
        }
    }

    public void dMaxComplement(Map<Integer, Integer> dMax, Integer m) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                if (dMax.get(i * m + j) == null) {
                    if (dMax.get(j * m + i) != null) {
                        dMax.put(i * m + j, dMax.get(j * m + i));
                    } else {
                        dMax.put(i * m + j, LMAX);
                    }
                }
            }
        }
    }

    private DistanceVo getPixelDistance(Map<Integer, Integer> r,
                                  Map<Integer, Integer> dMin,
                                  Map<Integer, Integer> dMax,
                                  Integer m) {
        Map<Integer, Integer> pMin = new HashMap<>();
        Map<Integer, Integer> pMax = new HashMap<>();
        Map<Integer, Boolean> pAfterTo = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = i; j < m; ++j) {
                int idx = i * m + j;
                DistanceVo distanceVo = IAOp.getDistance(r.get(idx), dMin.get(idx), dMax.get(idx));

                for (int pi = 0; pi < 4; ++pi) {
                    for (int pj = 0; pj < 4; ++pj) {
                        int ti = i * 2 + pi;
                        if (pi >= 2) ti = j * 2 + pi - 2;
                        int tj = i * 2 + pj;
                        if (pj >= 2) tj = j * 2 + pj - 2;

                        int tidx = ti * m * 2 + tj;

                        if (pMin.get(tidx) == null
                                || pMin.get(tidx) < distanceVo.getDMin().get(pi * 4 + pj)) {
                            pMin.put(tidx, distanceVo.getDMin().get(pi * 4 + pj));
                        }

                        if (pMax.get(tidx) == null
                                || pMax.get(tidx) > distanceVo.getDMax().get(pi * 4 + pj)) {
                            pMax.put(tidx, distanceVo.getDMax().get(pi * 4 + pj));
                        }

                        pAfterTo.putIfAbsent(tidx, distanceVo.getAfterTo().get(pi * 4 + pj));
                    }
                }
            }
        }

        DistanceVo result = new DistanceVo(m * 2);
        result.setDMin(pMin);
        result.setDMax(pMax);
        result.setAfterTo(pAfterTo);
        return result;
    }

    public List<DistanceVo> generatePixelDistance(Map<Integer, BASetVo> scenario,
                                                  Map<Integer, Integer> xDMin,
                                                  Map<Integer, Integer> xDMax,
                                                  Map<Integer, Integer> yDMin,
                                                  Map<Integer, Integer> yDMax,
                                                  Integer m) {
        Map<Integer, Integer> xr = new HashMap<>();
        Map<Integer, Integer> yr = new HashMap<>();
        for (Map.Entry<Integer, BASetVo> entry : scenario.entrySet()) {
            xr.put(entry.getKey(), entry.getValue().get(0) / 13);
            yr.put(entry.getKey(), entry.getValue().get(0) % 13);
        }

        DistanceVo xDistance = getPixelDistance(xr, xDMin, xDMax, m);
        DistanceVo yDistance = getPixelDistance(yr, yDMin, yDMax, m);

        List<DistanceVo> result = new ArrayList<>();
        result.add(xDistance);
        result.add(yDistance);
        return result;
    }
}
