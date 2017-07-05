package com.stella.service.vilya.api.core;

import com.stella.service.vilya.api.BaseApiTest;
import com.stella.service.vilya.api.common.ops.BAOp;
import com.stella.service.vilya.api.common.ops.BASetConstruct;
import com.stella.service.vilya.api.common.vos.BASetVo;
import com.stella.service.vilya.api.common.vos.PathConsistencyResponse;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathConsistencyTest extends BaseApiTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PathConsistency pathConsistency;

    @Autowired
    private EnumerateScenarios enumerateScenarios;

    @Test
    public void case1Test() throws IOException {
        Map<Integer, BASetVo> c = new HashMap<>();
        Integer m = 5;
        c.put(0, BASetConstruct.equal());
        c.put(1, BASetConstruct.adjacency());
        c.put(2, BASetConstruct.adjacency());
        c.put(3, BASetConstruct.noOverlap());
        c.put(4, BASetConstruct.inclusionSW());
        c.put(6, BASetConstruct.equal());
        c.put(7, BASetConstruct.noOverlap());
        c.put(8, BASetConstruct.noOverlap());
        c.put(9, BASetConstruct.inclusion());
        c.put(12, BASetConstruct.equal());
        c.put(13, BASetConstruct.adjacency());
        c.put(14, BASetConstruct.inclusionAdjE());
        c.put(18, BASetConstruct.equal());
        c.put(19, BASetConstruct.inclusion());
        c.put(24, BASetConstruct.equal());
        for (int i = 0; i < m - 1; ++i) {
            for (int j = i + 1; j < m; ++j) {
                c.put(j * m + i, BAOp.inverse(c.get(i * m + j)));
            }
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            output.append("\n");
            for (int j = 0; j < m; ++j) {
                output.append(c.get(i * m + j).size()).append("\t");
            }
        }
        logger.info("input: " + output.toString());
        PathConsistencyResponse response = pathConsistency.execute(c, m);
        Assert.assertTrue(response.getSuccess());
        output = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            output.append("\n");
            for (int j = 0; j < m; ++j) {
                output.append(c.get(i * m + j).size()).append("\t");
            }
        }
        logger.info("output: " + output.toString());

        List<Map<Integer, BASetVo>> result = enumerateScenarios.execute(c, m, 4);
        saveResult(result);
        logger.info("result: " + result.size());
    }

    private void saveResult(List<Map<Integer, BASetVo>> result) throws IOException {
        File file = new File("quality_scenarios_1.txt");
        BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
        for (Map<Integer, BASetVo> scenario : result) {
            StringBuilder line = new StringBuilder();
            for (BASetVo r : scenario.values()) {
                line.append(r.get(0)).append(" ");
            }
            line.append("\n");
            buffer.write(line.toString());
        }
        buffer.close();
    }
}
