package com.stella.service.vilya.api.core;

import com.stella.service.vilya.api.common.ops.IAOp;
import com.stella.service.vilya.api.common.vos.BASetVo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AfterTo {

    public Map<Integer, Integer> execute(Map<Integer, BASetVo> n, Integer m, Boolean isXDirt) {
        Map<Integer, Integer> afterTo = new HashMap<>();
        for (int i = 0 ; i < m - 1; ++i) {
            for (int j = i + 1; j < m; ++j) {
                Integer ba = n.get(i * m + j).get(0);
                Integer ia = ba % 13;
                if (isXDirt) {
                    ia = ba / 13;
                }
                Map<Integer, Integer> tmpAfterTo = IAOp.afterTo(ia);
                /**
                 * 0: pi1   1: pi2
                 * 2: pj1   3: pj2
                 */
                for (int px = 0; px < 4; ++px) {
                    for (int py = 0; py < 4; ++py) {
                        int x = 0;
                        switch (px) {
                            case 0: x = i * 2; break;
                            case 1: x = i * 2 + 1; break;
                            case 2: x = j * 2; break;
                            case 3: x = j * 2 + 1; break;
                        }
                        int y = 0;
                        switch (py) {
                            case 0: y = i * 2; break;
                            case 1: y = i * 2 + 1; break;
                            case 2: y = j * 2; break;
                            case 3: y = j * 2 + 1; break;
                        }
                        afterTo.put(x * 2 * m + y, tmpAfterTo.get(px * 2 + py));
                    }
                }
            }
        }
        return afterTo;
    }
}
