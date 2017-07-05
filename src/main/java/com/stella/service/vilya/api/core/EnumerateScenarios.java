package com.stella.service.vilya.api.core;

import com.stella.service.vilya.api.common.vos.PathConsistencyResponse;
import com.stella.service.vilya.api.common.enums.IAEnum;
import com.stella.service.vilya.api.common.ops.BAOp;
import com.stella.service.vilya.api.common.vos.BASetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EnumerateScenarios {

    @Autowired
    private PathConsistency pathConsistency;

    public List<Map<Integer, BASetVo>> execute(Map<Integer, BASetVo> c, Integer m, Integer st) {
        List<Integer> topoOrder = getTopoOrder(c, m, st);
        Map<Integer, BASetVo> tmpN = expandN(null, 0);
        List<Map<Integer, BASetVo>> l = dfs(c, m, topoOrder, tmpN, 1);
        return l;
    }

    private List<Integer> getTopoOrder(Map<Integer, BASetVo> c, Integer m, Integer st) {
        List<Integer> topoOrder = new ArrayList<>();
        topoOrder.add(st);
        while (topoOrder.size() < m) {
            Integer idx = -1;
            Integer score = 1;
            for (int i = 0; i < m; ++i) {
                if (!topoOrder.contains(i)) {
                    Integer tScore = 1;
                    for (Integer j : topoOrder) {
                        tScore *= c.get(i * m + j).size();
                    }
                    if (idx == -1 || tScore < score) {
                        idx = i;
                        score = tScore;
                    }
                }
            }
            topoOrder.add(idx);
        }
        return topoOrder;
    }

    private List<Map<Integer, BASetVo>> dfs(Map<Integer, BASetVo> c,
                                            Integer m,
                                            List<Integer> topoOrder,
                                            Map<Integer, BASetVo> n,
                                            Integer size) {
        List<Map<Integer, BASetVo>> l = new ArrayList<>();
        PathConsistencyResponse pathConsistencyResponse = pathConsistency.execute(n, size);
        if (!pathConsistencyResponse.getSuccess()) {
            return l;
        }
        if (Objects.equals(size, m)) {
            l.add(translateN(n, topoOrder, m));
            return l;
        }
        Integer k = topoOrder.get(size);
        Map<Integer, BASetVo> tmpN = expandN(n, size);
        Integer count = 1;
        for (int idx = 0; idx < size; ++idx) {
            Integer i = topoOrder.get(idx);
            count *= c.get(i * m + k).size();
        }
        /**
         * enum tmpN
         */
        for (int cnt = 0; cnt < count; ++cnt) {
            Integer tmpCnt = cnt;
            for (int idx = 0; idx < size; ++idx) {
                Integer i = topoOrder.get(idx);
                Integer rIdx = tmpCnt % c.get(i * m + k).size();
                tmpCnt = tmpCnt / c.get(i * m + k).size();
                Integer r = c.get(i * m + k).get(rIdx);

                Set<Integer> value = new TreeSet<>();
                value.add(r);
                BASetVo baSet = new BASetVo(value);
                tmpN.put(idx * (size + 1) + size, baSet);
                tmpN.put(size * (size + 1) + idx, BAOp.inverse(baSet));
            }

            /**
             * recursive
             */
            List<Map<Integer, BASetVo>> tmpL = dfs(c, m, topoOrder, tmpN, size + 1);
            l.addAll(tmpL);
        }
        return l;
    }

    private Map<Integer, BASetVo> expandN(Map<Integer, BASetVo> n, Integer size) {
        Map<Integer, BASetVo> tmpN = new HashMap<>();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                tmpN.put(i * (size + 1) + j, n.get(i * size + j));
            }
        }
        Set<Integer> value = new TreeSet<>();
        value.add(IAEnum.EQ.getCode() * 13 + IAEnum.EQ.getCode());
        tmpN.put((size + 1) * (size + 1) - 1, new BASetVo(value));
        return tmpN;
    }

    private Map<Integer, BASetVo> translateN(Map<Integer, BASetVo> n, List<Integer> topoOrder, Integer m) {
        Map<Integer, BASetVo> tmpN = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                Integer x = topoOrder.get(i);
                Integer y = topoOrder.get(j);
                tmpN.put(x * m + y, n.get(i * m + j));
            }
        }
        return tmpN;
    }
}
