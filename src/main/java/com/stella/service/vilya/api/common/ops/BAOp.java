package com.stella.service.vilya.api.common.ops;

import com.stella.service.vilya.api.common.vos.BASetVo;
import com.stella.service.vilya.api.common.vos.BAVo;
import com.stella.service.vilya.api.common.vos.IASetVo;

import java.util.Set;
import java.util.TreeSet;

public class BAOp {

    public static BASetVo inverse(BASetVo s) {
        Set<Integer> result = new TreeSet<>();
        for (int ba : s.getValue()) {
            int x = 12 - ba / 13;
            int y = 12 - ba % 13;
            result.add(x * 13 + y);
        }
        return new BASetVo(result);
    }

    public static BASetVo intersect(BASetVo s1, BASetVo s2) {
        Set<Integer> result = new TreeSet<>();
        for (int ba : s1.getValue()) {
            if (s2.getValue().contains(ba)) {
                result.add(ba);
            }
        }
        return new BASetVo(result);
    }

    public static BASetVo union(BASetVo s1, BASetVo s2) {
        s1.getValue().addAll(s2.getValue());
        return s1;
    }

    public static BASetVo compose(int b1, int b2) {
        IASetVo xs = IAOp.compose(b1 / 13, b2 / 13);
        IASetVo ys = IAOp.compose(b1 % 13, b2 % 13);
        Set<Integer> data = new TreeSet<>();
        for (Integer x : xs.getValue()) {
            for (Integer y : ys.getValue()) {
                data.add(x * 13 + y);
            }
        }
        return new BASetVo(data);
    }

    public static BASetVo compose(BAVo b1, BAVo b2) {
        return compose(b1.getValue(), b2.getValue());
    }

    public static BASetVo compose(BASetVo s1, BASetVo s2) {
        BASetVo result = new BASetVo();
        for (int b1 : s1.getValue()) {
            for (int b2 : s2.getValue()) {
                result = union(result, compose(b1, b2));
            }
        }
        return result;
    }
}
