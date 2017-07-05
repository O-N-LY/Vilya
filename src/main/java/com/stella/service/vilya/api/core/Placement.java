package com.stella.service.vilya.api.core;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class Placement {

    public List<Integer> execute(Map<Integer, Integer> dMin, Map<Integer, Integer> dMax, Map<Integer, Boolean> afterTo, Integer m) {
        Boolean done = false;
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            p.add(0);
        }
        while (!done) {
            done = true;
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < m; ++k) {
                    int index = j * m + k;
                    int pj = p.get(j);
                    int pk = p.get(k);
                    /**
                     * walls j and k are coincident
                     */
                    if (dMin.get(index) == 0 && dMax.get(index) == 0) {
                        if (!Objects.equals(pj, pk)) {
                            done = false;
                            if (pj > pk) {
                                p.set(k, pj);
                            } else {
                                p.set(j, pk);
                            }
                        }
                    } else {
                        if (!afterTo.get(index)) {
                            /**
                             * j -> k
                             */
                            if (pk < pj + dMin.get(index)) {
                                done = false;
                                p.set(k, pj + dMin.get(index));
                            } else if (pj < pk - dMax.get(index)) {
                                done = false;
                                p.set(j, pk - dMax.get(index));
                            }
                        } else {
                            /**
                             * k -> j
                             */
                            if (pj < pk + dMin.get(index)) {
                                done = false;
                                p.set(j, pk + dMin.get(index));
                            } else if (pk < pj - dMax.get(index)) {
                                done = false;
                                p.set(k, pj - dMax.get(index));
                            }
                        }
                    }
                }
            }
        }

        return p;
    }

    public Boolean checkHasGaps(List<Integer> px, List<Integer> py, Integer m) {
        Integer gaps = (px.get(m * 2 - 1) - px.get(m * 2 - 2)) * (py.get(m * 2 - 1) - py.get(m * 2 - 2));
        for (int i = 0; i < m - 1; ++i) {
            gaps -= (px.get(i * 2 + 1) - px.get(i * 2)) * (py.get(i * 2 + 1) - py.get(i * 2));
        }
        return gaps > 0;
    }
}
