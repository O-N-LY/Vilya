package com.stella.service.vilya.api.service.impl;

import com.stella.service.vilya.api.common.vos.BASetVo;
import com.stella.service.vilya.api.common.vos.PathConsistencyResponse;
import com.stella.service.vilya.api.core.AfterTo;
import com.stella.service.vilya.api.core.EnumerateScenarios;
import com.stella.service.vilya.api.core.PathConsistency;
import com.stella.service.vilya.api.core.Placement;
import com.stella.service.vilya.api.service.FloorPlanDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FloorPlanDesignServiceImpl implements FloorPlanDesignService {

    @Autowired
    private PathConsistency pathConsistency;

    @Autowired
    private EnumerateScenarios enumerateScenarios;

    @Autowired
    private AfterTo afterTo;

    @Autowired
    private Placement placement;

    public void getFloorPlanDesign(Map<Integer, BASetVo> c,
                                   Map<Integer, Integer> xDMin,
                                   Map<Integer, Integer> xDMax,
                                   Map<Integer, Integer> yDMin,
                                   Map<Integer, Integer> yDMax,
                                   Integer m) {

        PathConsistencyResponse pathConsistencyResponse = pathConsistency.execute(c, m);
        if (!pathConsistencyResponse.getSuccess()) {
            System.out.println("C is inconsistent!");
        }

        /**
         * Assume that envelop is indexed to m-1
         */
        List<Map<Integer, BASetVo>> scenarios = enumerateScenarios.execute(c, m, m - 1);

        for (Map<Integer, BASetVo> scenario : scenarios) {
            Map<Integer, Integer> xAfterTo = afterTo.execute(scenario, m, true);
            List<Integer> xPlacement = placement.execute(xDMin, xDMax, null, m * 2);

            Map<Integer, Integer> yAfterTo = afterTo.execute(scenario, m, false);
            List<Integer> yPlacement = placement.execute(yDMin, yDMax, null, m * 2);
        }
    }
}
