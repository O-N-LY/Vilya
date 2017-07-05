package com.stella.service.vilya.api.core;

import com.stella.service.vilya.api.common.vos.PathConsistencyResponse;
import com.stella.service.vilya.api.common.ops.BAOp;
import com.stella.service.vilya.api.common.vos.BASetVo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@Component
public class PathConsistency {

    public PathConsistencyResponse execute(Map<Integer, BASetVo> c, Integer m) {
        Queue<Integer> lPathsToVisit = new PriorityQueue<>();
        Integer nPathsChecked = 0;
        Integer nChanges = 0;

        /**
         * first loop
         */
        for (int i = 0; i < m - 1; ++i) {
            for (int j = i + 1; j < m; ++j) {
                for (int k = 0; k < m; ++k) {
                    if (i == k || j == k) {
                        continue;
                    }
                    nPathsChecked++;
                    queueCheck(lPathsToVisit, i, k, j, m);
                    BASetVo cik = c.get(i * m + k);
                    BASetVo ckj = c.get(k * m + j);
                    BASetVo cij = c.get(i * m + j);
                    BASetVo tij = BAOp.intersect(cij, BAOp.compose(cik, ckj));
                    if (tij.isEmpty()) {
                        return new PathConsistencyResponse(false, i, j, k, nPathsChecked, nChanges);
                    }
                    if (tij.size() < cij.size()) {
                        nChanges++;
                        c.put(i * m + j, tij);
                        c.put(j * m + i, BAOp.inverse(tij));
                        queueAdd(lPathsToVisit, i, j, m);
                    }
                }
            }
        }

        /**
         * second loop
         */
        while (lPathsToVisit.size() > 0) {
            nPathsChecked++;
            Integer lPath = lPathsToVisit.poll();
            int i = lPath / m / m;
            int k = lPath / m % m;
            int j = lPath % m;
            BASetVo cik = c.get(i * m + k);
            BASetVo ckj = c.get(k * m + j);
            BASetVo cij = c.get(i * m + j);
            BASetVo tij = BAOp.intersect(cij, BAOp.compose(cik, ckj));
            if (tij.isEmpty()) {
                return new PathConsistencyResponse(false, i, j, k, nPathsChecked, nChanges);
            }
            if (tij.size() < cij.size()) {
                nChanges++;
                c.put(i * m + j, tij);
                c.put(j * m + i, BAOp.inverse(tij));
                queueAdd(lPathsToVisit, i, j, m);
            }
        }

        return new PathConsistencyResponse(true, 0, 0, 0, nPathsChecked, nChanges);
    }

    private void queueAdd(Queue<Integer> queue, int i, int j, int m) {
        for (int k = 0; k < m; ++k) {
            if (i == k || j == k) {
                continue;
            }
            /**
             * Path: k -> i -> j
             */
            Integer lPath = (k * m + i) * m + j;
            if (!queue.contains(lPath)) {
                queue.add(lPath);
            }
            /**
             * Path: i -> j -> k
             */
            lPath = (i * m + j) * m + k;
            if (!queue.contains(lPath)) {
                queue.add(lPath);
            }
        }
    }

    private void queueCheck(Queue<Integer> queue, int i, int k, int j, int m) {
        /**
         * Path: i -> k -> j
         */
        Integer lPath = (i * m + k) * m + j;
        if (queue.contains(lPath)) {
            queue.remove(lPath);
        }
    }

}
