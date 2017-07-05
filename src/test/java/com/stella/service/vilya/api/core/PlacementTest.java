package com.stella.service.vilya.api.core;

import com.stella.service.vilya.api.BaseApiTest;
import com.stella.service.vilya.api.common.vos.BASetVo;
import com.stella.service.vilya.api.common.vos.DistanceVo;
import com.stella.service.vilya.api.service.FloorPlanDesignService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;

public class PlacementTest extends BaseApiTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static Integer ROOM = 0;
    private static Integer KITCHEN = 1;
    private static Integer HALL = 2;
    private static Integer BATH = 3;
    private static Integer ENVELOP = 4;

    @Autowired
    private DistanceComplement distanceComplement;

    @Autowired
    private QualityConsistency qualityConsistency;

    @Autowired
    private Placement placement;

    @Test
    public void case1Test() throws IOException {
        Integer m = 5;
        List<Map<Integer, BASetVo>> scenarios = loadScenarios(m);
        logger.info("scenarios: " + scenarios.size());

        Map<Integer, Integer> wMin = new HashMap<>();
        Map<Integer, Integer> wMax = new HashMap<>();
        Map<Integer, Integer> hMin = new HashMap<>();
        Map<Integer, Integer> hMax = new HashMap<>();

        /**
         * Table 7
         */
        wMin.put(ROOM * m + ROOM, 360);
        hMin.put(ROOM * m + ROOM, 360);
        wMin.put(KITCHEN * m + KITCHEN, 180);
        hMin.put(KITCHEN * m + KITCHEN, 180);
        wMin.put(HALL * m + HALL, 120);
        wMax.put(HALL * m + HALL, 600);
        hMin.put(HALL * m + HALL, 600);
        wMin.put(BATH * m + BATH, 180);
        hMin.put(BATH * m + BATH, 180);
        wMax.put(ENVELOP * m + ENVELOP, 700);

        /**
         * Table 8
         */
        hMin.put(HALL * m + ENVELOP, 120);
        wMin.put(ROOM * m + HALL, 90);
        hMin.put(ROOM * m + HALL, 90);
        wMin.put(HALL * m + BATH, 70);
        hMin.put(HALL * m + BATH, 70);
        wMin.put(ROOM * m + ENVELOP, 360);
        hMin.put(ROOM * m + ENVELOP, 360);
        wMin.put(ROOM * m + KITCHEN, 120);
        hMin.put(ROOM * m + KITCHEN, 120);

        distanceComplement.dMinComplement(wMin, m);
        distanceComplement.dMinComplement(hMin, m);
        distanceComplement.dMaxComplement(wMax, m);
        distanceComplement.dMaxComplement(hMax, m);

        List<List<Integer>> xPlacement = new ArrayList<>();
        List<List<Integer>> yPlacement = new ArrayList<>();
        Integer count = 0;
        for (Map<Integer, BASetVo> scenario : scenarios) {
            List<DistanceVo> distanceVos = distanceComplement.generatePixelDistance(scenario, wMin, wMax, hMin, hMax, m);
            DistanceVo xDistance = distanceVos.get(0);
            DistanceVo yDistance = distanceVos.get(1);
            Boolean result = qualityConsistency.execute(xDistance.getDMin(), xDistance.getDMax(), xDistance.getAfterTo(), m * 2);
            result = result && qualityConsistency.execute(yDistance.getDMin(), yDistance.getDMax(), yDistance.getAfterTo(), m * 2);
            if (result) {
                count++;
                List<Integer> xRes = placement.execute(xDistance.getDMin(), xDistance.getDMax(), xDistance.getAfterTo(), m * 2);
                List<Integer> yRes = placement.execute(yDistance.getDMin(), yDistance.getDMax(), yDistance.getAfterTo(), m * 2);

                Boolean isExist = false;
                for (int idx = 0; idx < xPlacement.size(); ++idx) {
                    List<Integer> xPlace = xPlacement.get(idx);
                    List<Integer> yPlace = yPlacement.get(idx);
                    isExist = isExist || (compareList(xPlace, xRes) && compareList(yPlace, yRes));
                }
                isExist = isExist || placement.checkHasGaps(xRes, yRes, m);
                if (!isExist) {
                    xPlacement.add(xRes);
                    yPlacement.add(yRes);
                    StringBuilder output = new StringBuilder();
                    output.append("\n");
                    for (int i = 0; i < m * 2; ++i) {
                        output.append(xRes.get(i)).append("\t");
                    }
                    output.append("\n");
                    for (int i = 0; i < m * 2; ++i) {
                        output.append(yRes.get(i)).append("\t");
                    }
                    logger.info("placement: " + output.toString());
                }
            }
        }

//        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < m; ++i) {
//            output.append("\n");
//            for (int j = 0; j < m; ++j) {
//                output.append(wMax.get(i * m + j)).append("\t");
//            }
//        }
        logger.info("count: " + xPlacement.size());
    }

    private List<Map<Integer, BASetVo>> loadScenarios(Integer m) throws IOException {
        List<Map<Integer, BASetVo>> scenarios = new ArrayList<>();
        File file = new File("quality_scenarios_1.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        while (true) {
            String temp = buffer.readLine();
            if (temp == null) {
                break;
            }
            String[] rs = temp.split(" ");
            Map<Integer, BASetVo> scenario = new HashMap<>();
            int i = 0;
            for (String r : rs) {
                BASetVo baSetVo = new BASetVo();
                baSetVo.getValue().add(Integer.parseInt(r));
                scenario.put(i, baSetVo);
                i++;
                if (i >= m * m) {
                    break;
                }
            }
            scenarios.add(scenario);
        }
        buffer.close();

        return scenarios;
    }

    private Boolean compareList(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int idx = 0; idx < a.size(); ++idx) {
            if (!Objects.equals(a.get(idx), b.get(idx))) {
                return false;
            }
        }
        return true;
    }
}
