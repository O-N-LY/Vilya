package com.stella.service.vilya.api.common;

import com.stella.service.vilya.api.BaseApiTest;
import com.stella.service.vilya.api.common.enums.IAEnum;
import com.stella.service.vilya.api.common.ops.IAOp;
import com.stella.service.vilya.api.common.vos.DistanceVo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IAOpsTest extends BaseApiTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void iAOpsTest() {
        DistanceVo distanceVo = IAOp.getDistance(IAEnum.OI.getCode(), 70, Integer.MAX_VALUE / 2);
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < 4; ++i) {
            output.append("\n");
            for (int j = 0; j < 4; ++j) {
                output.append(distanceVo.getDMin().get(i * 4 + j)).append("\t");
            }
        }

        logger.info("dmin: " + output.toString());

        output = new StringBuilder();

        for (int i = 0; i < 4; ++i) {
            output.append("\n");
            for (int j = 0; j < 4; ++j) {
                output.append(distanceVo.getDMax().get(i * 4 + j)).append("\t");
            }
        }

        logger.info("dmax: " + output.toString());
    }
}
