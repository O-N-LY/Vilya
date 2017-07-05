package com.stella.service.vilya.api.common.ops;

import com.stella.service.vilya.api.common.enums.IAEnum;
import com.stella.service.vilya.api.common.vos.BASetVo;

import java.util.Set;
import java.util.TreeSet;

public class BASetConstruct {

    private static Integer genBa(IAEnum x, IAEnum y) {
        return x.getCode() * 13 + y.getCode();
    }

    public static BASetVo equal() {
        Set<Integer> result = new TreeSet<>();
        result.add(genBa(IAEnum.EQ, IAEnum.EQ));
        return new BASetVo(result);
    }

    public static BASetVo inclusionAdjN() {
        Set<Integer> result = new TreeSet<>();
        result.add(genBa(IAEnum.S, IAEnum.EQ));
        result.add(genBa(IAEnum.D, IAEnum.EQ));
        result.add(genBa(IAEnum.F, IAEnum.EQ));
        result.add(genBa(IAEnum.EQ, IAEnum.EQ));
        result.add(genBa(IAEnum.S, IAEnum.F));
        result.add(genBa(IAEnum.D, IAEnum.F));
        result.add(genBa(IAEnum.EQ, IAEnum.F));
        result.add(genBa(IAEnum.F, IAEnum.F));
        return new BASetVo(result);
    }

    public static BASetVo inclusionAdjE() {
        Set<Integer> result = new TreeSet<>();
        result.add(genBa(IAEnum.EQ, IAEnum.S));
        result.add(genBa(IAEnum.F, IAEnum.F));
        result.add(genBa(IAEnum.F, IAEnum.D));
        result.add(genBa(IAEnum.EQ, IAEnum.D));
        result.add(genBa(IAEnum.EQ, IAEnum.F));
        result.add(genBa(IAEnum.F, IAEnum.EQ));
        result.add(genBa(IAEnum.EQ, IAEnum.EQ));
        result.add(genBa(IAEnum.F, IAEnum.S));
        return new BASetVo(result);
    }

    public static BASetVo inclusionAdjW() {
        Set<Integer> result = new TreeSet<>();
        result.add(genBa(IAEnum.EQ, IAEnum.S));
        result.add(genBa(IAEnum.EQ, IAEnum.D));
        result.add(genBa(IAEnum.EQ, IAEnum.F));
        result.add(genBa(IAEnum.EQ, IAEnum.EQ));
        result.add(genBa(IAEnum.S, IAEnum.S));
        result.add(genBa(IAEnum.S, IAEnum.D));
        result.add(genBa(IAEnum.S, IAEnum.EQ));
        result.add(genBa(IAEnum.S, IAEnum.F));
        return new BASetVo(result);
    }

    public static BASetVo inclusionAdjS() {
        Set<Integer> result = new TreeSet<>();
        result.add(genBa(IAEnum.D, IAEnum.S));
        result.add(genBa(IAEnum.D, IAEnum.EQ));
        result.add(genBa(IAEnum.S, IAEnum.S));
        result.add(genBa(IAEnum.F, IAEnum.S));
        result.add(genBa(IAEnum.S, IAEnum.EQ));
        result.add(genBa(IAEnum.F, IAEnum.EQ));
        result.add(genBa(IAEnum.EQ, IAEnum.S));
        result.add(genBa(IAEnum.EQ, IAEnum.EQ));
        return new BASetVo(result);
    }

    public static BASetVo inclusionAdj() {
        BASetVo baSetVo = new BASetVo();
        BAOp.union(baSetVo, inclusionAdjN());
        BAOp.union(baSetVo, inclusionAdjE());
        BAOp.union(baSetVo, inclusionAdjW());
        BAOp.union(baSetVo, inclusionAdjS());
        return baSetVo;
    }

    public static BASetVo inclusion() {
        BASetVo baSetVo = new BASetVo();
        BAOp.union(baSetVo, inclusionAdj());
        baSetVo.getValue().add(genBa(IAEnum.D, IAEnum.D));
        return baSetVo;
    }

    public static BASetVo inclusionSW() {
        Set<Integer> result = new TreeSet<>();
        result.add(genBa(IAEnum.S, IAEnum.EQ));
        result.add(genBa(IAEnum.S, IAEnum.S));
        result.add(genBa(IAEnum.EQ, IAEnum.S));
        result.add(genBa(IAEnum.EQ, IAEnum.EQ));
        return new BASetVo(result);
    }

    public static BASetVo separation() {
        Set<Integer> result = new TreeSet<>();
        result.add(genBa(IAEnum.O, IAEnum.B));
        result.add(genBa(IAEnum.B, IAEnum.OI));
        result.add(genBa(IAEnum.B, IAEnum.BI));
        result.add(genBa(IAEnum.D, IAEnum.B));
        result.add(genBa(IAEnum.OI, IAEnum.BI));
        result.add(genBa(IAEnum.OI, IAEnum.B));
        result.add(genBa(IAEnum.DI, IAEnum.BI));
        result.add(genBa(IAEnum.D, IAEnum.BI));
        result.add(genBa(IAEnum.BI, IAEnum.D));
        result.add(genBa(IAEnum.BI, IAEnum.B));
        result.add(genBa(IAEnum.BI, IAEnum.BI));
        result.add(genBa(IAEnum.BI, IAEnum.DI));
        result.add(genBa(IAEnum.DI, IAEnum.B));
        result.add(genBa(IAEnum.B, IAEnum.B));
        result.add(genBa(IAEnum.B, IAEnum.D));
        result.add(genBa(IAEnum.BI, IAEnum.OI));
        result.add(genBa(IAEnum.B, IAEnum.DI));
        result.add(genBa(IAEnum.BI, IAEnum.O));
        result.add(genBa(IAEnum.O, IAEnum.BI));
        result.add(genBa(IAEnum.B, IAEnum.O));
        result.add(genBa(IAEnum.B, IAEnum.M));
        result.add(genBa(IAEnum.B, IAEnum.S));
        result.add(genBa(IAEnum.B, IAEnum.FI));
        result.add(genBa(IAEnum.B, IAEnum.EQ));
        result.add(genBa(IAEnum.B, IAEnum.F));
        result.add(genBa(IAEnum.B, IAEnum.SI));
        result.add(genBa(IAEnum.B, IAEnum.MI));
        result.add(genBa(IAEnum.M, IAEnum.BI));
        result.add(genBa(IAEnum.S, IAEnum.BI));
        result.add(genBa(IAEnum.FI, IAEnum.BI));
        result.add(genBa(IAEnum.EQ, IAEnum.BI));
        result.add(genBa(IAEnum.F, IAEnum.BI));
        result.add(genBa(IAEnum.SI, IAEnum.BI));
        result.add(genBa(IAEnum.MI, IAEnum.BI));
        result.add(genBa(IAEnum.BI, IAEnum.MI));
        result.add(genBa(IAEnum.BI, IAEnum.SI));
        result.add(genBa(IAEnum.BI, IAEnum.F));
        result.add(genBa(IAEnum.BI, IAEnum.EQ));
        result.add(genBa(IAEnum.BI, IAEnum.FI));
        result.add(genBa(IAEnum.BI, IAEnum.S));
        result.add(genBa(IAEnum.BI, IAEnum.M));
        result.add(genBa(IAEnum.MI, IAEnum.B));
        result.add(genBa(IAEnum.SI, IAEnum.B));
        result.add(genBa(IAEnum.F, IAEnum.B));
        result.add(genBa(IAEnum.EQ, IAEnum.B));
        result.add(genBa(IAEnum.FI, IAEnum.B));
        result.add(genBa(IAEnum.S, IAEnum.B));
        result.add(genBa(IAEnum.M, IAEnum.B));
        return new BASetVo(result);
    }

    public static BASetVo adjacency() {
        Set<Integer> result = new TreeSet<>();
        result.add(genBa(IAEnum.O, IAEnum.M));
        result.add(genBa(IAEnum.EQ, IAEnum.MI));
        result.add(genBa(IAEnum.FI, IAEnum.MI));
        result.add(genBa(IAEnum.MI, IAEnum.D));
        result.add(genBa(IAEnum.D, IAEnum.MI));
        result.add(genBa(IAEnum.OI, IAEnum.M));
        result.add(genBa(IAEnum.F, IAEnum.MI));
        result.add(genBa(IAEnum.MI, IAEnum.O));
        result.add(genBa(IAEnum.MI, IAEnum.S));
        result.add(genBa(IAEnum.MI, IAEnum.FI));
        result.add(genBa(IAEnum.M, IAEnum.O));
        result.add(genBa(IAEnum.M, IAEnum.S));
        result.add(genBa(IAEnum.MI, IAEnum.DI));
        result.add(genBa(IAEnum.SI, IAEnum.MI));
        result.add(genBa(IAEnum.S, IAEnum.M));
        result.add(genBa(IAEnum.DI, IAEnum.MI));
        result.add(genBa(IAEnum.FI, IAEnum.M));
        result.add(genBa(IAEnum.M, IAEnum.OI));
        result.add(genBa(IAEnum.OI, IAEnum.MI));
        result.add(genBa(IAEnum.F, IAEnum.M));
        result.add(genBa(IAEnum.D, IAEnum.M));
        result.add(genBa(IAEnum.DI, IAEnum.M));
        result.add(genBa(IAEnum.M, IAEnum.SI));
        result.add(genBa(IAEnum.M, IAEnum.D));
        result.add(genBa(IAEnum.S, IAEnum.MI));
        result.add(genBa(IAEnum.M, IAEnum.FI));
        result.add(genBa(IAEnum.M, IAEnum.EQ));
        result.add(genBa(IAEnum.SI, IAEnum.M));
        result.add(genBa(IAEnum.MI, IAEnum.F));
        result.add(genBa(IAEnum.MI, IAEnum.OI));
        result.add(genBa(IAEnum.EQ, IAEnum.M));
        result.add(genBa(IAEnum.M, IAEnum.DI));
        result.add(genBa(IAEnum.O, IAEnum.MI));
        result.add(genBa(IAEnum.M, IAEnum.F));
        result.add(genBa(IAEnum.MI, IAEnum.SI));
        result.add(genBa(IAEnum.MI, IAEnum.EQ));
        return new BASetVo(result);
    }

    public static BASetVo noOverlap() {
        BASetVo baSetVo = new BASetVo();
        BAOp.union(baSetVo, separation());
        BAOp.union(baSetVo, adjacency());
        return baSetVo;
    }
}
