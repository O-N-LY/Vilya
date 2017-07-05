package com.stella.service.vilya.api.common.ops;

import com.stella.service.vilya.api.common.vos.DistanceVo;
import com.stella.service.vilya.api.common.vos.IASetVo;
import com.stella.service.vilya.api.common.vos.IAVo;

import java.util.*;

public class IAOp {

    private static Map<Integer, IASetVo> table;

    private static Integer LMIN = -1;
    private static Integer LMAX = Integer.MAX_VALUE / 2;

    private static Set<Integer> NO_INFO = new HashSet<Integer>() {{
        add(0); add(1); add(2); add(3); add(4); add(5);
        add(6); add(7); add(8); add(9); add(10); add(11); add(12);
    }};

    public static IASetVo inverse(IASetVo s) {
        Set<Integer> result = new TreeSet<>();
        for (int ia : s.getValue()) {
            result.add(12 - ia);
        }
        return new IASetVo(result);
    }

    public static IASetVo union(IASetVo s1, IASetVo s2) {
        s1.getValue().addAll(s2.getValue());
        return s1;
    }

    public static IASetVo compose(int r1, int r2) {
        if (table == null) {
            constructTable();
        }
        IASetVo data = table.get(r1 * 13 + r2);
        return new IASetVo(data);
    }

    public static IASetVo compose(IAVo r1, IAVo r2) {
        return compose(r1.getValue(), r2.getValue());
    }

    public static IASetVo compose(IASetVo s1, IASetVo s2) {
        IASetVo result = new IASetVo();
        for (int r1 : s1.getValue()) {
            for (int r2 : s2.getValue()) {
                result = union(result, compose(r1, r2));
            }
        }
        return result;
    }

    private static void constructTable() {
        table = new HashMap<>();
        for (int r1 = 0; r1 < 13; ++r1) {
            for (int r2 = 0; r2 < 13; ++r2) {
                table.put(r1 * 13 + r2, internalCompose(r1, r2));
            }
        }
    }

    private static IASetVo internalCompose(int r1, int r2) {
        Set<Integer> result = new TreeSet<>();
        switch (r1) {
            case 0:
                switch (r2) {
                    //B
                    case 0: result.add(0); break;
                    //BI
                    case 12: break;
                    //D
                    case 4: result.addAll(Arrays.asList(0, 2, 1, 4, 3)); break;
                    //DI
                    case 8: result.add(0); break;
                    //O
                    case 2: result.add(0); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(0, 2, 1, 4, 3)); break;
                    //M
                    case 1: result.add(0); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(0, 2, 1, 4, 3)); break;
                    //S
                    case 3: result.add(0); break;
                    //SI
                    case 9: result.add(0); break;
                    //F
                    case 5: result.addAll(Arrays.asList(0, 2, 1, 4, 3)); break;
                    //FI
                    case 7: result.add(0); break;
                    //EQ
                    case 6: result.add(0); break;
                }
                break;
            case 12:
                switch (r2) {
                    //B
                    case 0: break;
                    //BI
                    case 12: result.add(12); break;
                    //D
                    case 4: result.addAll(Arrays.asList(12, 10, 11, 4, 5)); break;
                    //DI
                    case 8: result.add(12); break;
                    //O
                    case 2: result.addAll(Arrays.asList(12, 10, 11, 4, 5)); break;
                    //OI
                    case 10: result.add(12); break;
                    //M
                    case 1: result.addAll(Arrays.asList(12, 10, 11, 4, 5)); break;
                    //MI
                    case 11: result.add(12); break;
                    //S
                    case 3: result.addAll(Arrays.asList(12, 10, 11, 4, 5)); break;
                    //SI
                    case 9: result.add(12); break;
                    //F
                    case 5: result.add(12); break;
                    //FI
                    case 7: result.add(12); break;
                    //EQ
                    case 6: result.add(12); break;
                }
                break;
            case 4:
                switch (r2) {
                    //B
                    case 0: result.add(0); break;
                    //BI
                    case 12: result.add(12); break;
                    //D
                    case 4: result.add(4); break;
                    //DI
                    case 8: break;
                    //O
                    case 2: result.addAll(Arrays.asList(0, 2, 1, 4, 3)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(12, 10, 11, 4, 5)); break;
                    //M
                    case 1: result.add(0); break;
                    //MI
                    case 11: result.add(12); break;
                    //S
                    case 3: result.add(4); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(12, 10, 11, 4, 5)); break;
                    //F
                    case 5: result.add(4); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(0, 2, 1, 4, 3)); break;
                    //EQ
                    case 6: result.add(4); break;
                }
                break;
            case 8:
                switch (r2) {
                    //B
                    case 0: result.addAll(Arrays.asList(0, 2, 1, 8, 7)); break;
                    //BI
                    case 12: result.addAll(Arrays.asList(12, 10, 11, 8, 9)); break;
                    //D
                    case 4: result.addAll(Arrays.asList(2, 10, 4, 8, 6)); break;
                    //DI
                    case 8: result.add(8); break;
                    //O
                    case 2: result.addAll(Arrays.asList(2, 8, 7)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(10, 8, 9)); break;
                    //M
                    case 1: result.addAll(Arrays.asList(2, 8, 7)); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(10, 8, 9)); break;
                    //S
                    case 3: result.addAll(Arrays.asList(8, 7, 2)); break;
                    //SI
                    case 9: result.add(8); break;
                    //F
                    case 5: result.addAll(Arrays.asList(8, 9, 10)); break;
                    //FI
                    case 7: result.add(8); break;
                    //EQ
                    case 6: result.add(8); break;
                }
                break;
            case 2:
                switch (r2) {
                    //B
                    case 0: result.add(0); break;
                    //BI
                    case 12: result.addAll(Arrays.asList(12, 10, 8, 11, 9)); break;
                    //D
                    case 4: result.addAll(Arrays.asList(2, 4, 3)); break;
                    //DI
                    case 8: result.addAll(Arrays.asList(0, 2, 1, 8, 7)); break;
                    //O
                    case 2: result.addAll(Arrays.asList(0, 2, 1)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(2, 10, 4, 8, 6)); break;
                    //M
                    case 1: result.add(0); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(10, 8, 9)); break;
                    //S
                    case 3: result.add(2); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(8, 7, 2)); break;
                    //F
                    case 5: result.addAll(Arrays.asList(4, 3, 2)); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(0, 2, 1)); break;
                    //EQ
                    case 6: result.add(2); break;
                }
                break;
            case 10:
                switch (r2) {
                    //B
                    case 0: result.addAll(Arrays.asList(0, 2, 1, 8, 7)); break;
                    //BI
                    case 12: result.add(12); break;
                    //D
                    case 4: result.addAll(Arrays.asList(10, 4, 5)); break;
                    //DI
                    case 8: result.addAll(Arrays.asList(12, 10, 11, 8, 9)); break;
                    //O
                    case 2: result.addAll(Arrays.asList(2, 10, 4, 8, 6)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(12, 10, 11)); break;
                    //M
                    case 1: result.addAll(Arrays.asList(2, 8, 7)); break;
                    //MI
                    case 11: result.add(12); break;
                    //S
                    case 3: result.addAll(Arrays.asList(10, 4, 5)); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(10, 12, 11)); break;
                    //F
                    case 5: result.add(10); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(10, 8, 9)); break;
                    //EQ
                    case 6: result.add(10); break;
                }
                break;
            case 1:
                switch (r2) {
                    //B
                    case 0: result.addAll(Arrays.asList(0)); break;
                    //BI
                    case 12: result.addAll(Arrays.asList(12, 10, 11, 8, 9)); break;
                    //D
                    case 4: result.addAll(Arrays.asList(2, 4, 3)); break;
                    //DI
                    case 8: result.addAll(Arrays.asList(0)); break;
                    //O
                    case 2: result.addAll(Arrays.asList(0)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(2, 4, 3)); break;
                    //M
                    case 1: result.addAll(Arrays.asList(0)); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(5, 7, 6)); break;
                    //S
                    case 3: result.addAll(Arrays.asList(1)); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(1)); break;
                    //F
                    case 5: result.addAll(Arrays.asList(4, 3, 2)); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(0)); break;
                    //EQ
                    case 6: result.add(1); break;
                }
                break;
            case 11:
                switch (r2) {
                    //B
                    case 0: result.addAll(Arrays.asList(0, 2, 1, 8, 7)); break;
                    //BI
                    case 12: result.addAll(Arrays.asList(12)); break;
                    //D
                    case 4: result.addAll(Arrays.asList(10, 4, 5)); break;
                    //DI
                    case 8: result.addAll(Arrays.asList(12)); break;
                    //O
                    case 2: result.addAll(Arrays.asList(10, 4, 5)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(12)); break;
                    //M
                    case 1: result.addAll(Arrays.asList(3, 9, 6)); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(12)); break;
                    //S
                    case 3: result.addAll(Arrays.asList(4, 5, 10)); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(12)); break;
                    //F
                    case 5: result.addAll(Arrays.asList(11)); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(11)); break;
                    //EQ
                    case 6: result.add(11); break;
                }
                break;
            case 3:
                switch (r2) {
                    //B
                    case 0: result.addAll(Arrays.asList(0)); break;
                    //BI
                    case 12: result.addAll(Arrays.asList(12)); break;
                    //D
                    case 4: result.addAll(Arrays.asList(4)); break;
                    //DI
                    case 8: result.addAll(Arrays.asList(0, 2, 1, 8, 7)); break;
                    //O
                    case 2: result.addAll(Arrays.asList(0, 2, 1)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(10, 4, 5)); break;
                    //M
                    case 1: result.addAll(Arrays.asList(0)); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(11)); break;
                    //S
                    case 3: result.addAll(Arrays.asList(3)); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(3, 9, 6)); break;
                    //F
                    case 5: result.addAll(Arrays.asList(4)); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(0, 1, 2)); break;
                    //EQ
                    case 6: result.add(3); break;
                }
                break;
            case 9:
                switch (r2) {
                    //B
                    case 0: result.addAll(Arrays.asList(0, 2, 1, 8, 7)); break;
                    //BI
                    case 12: result.addAll(Arrays.asList(12)); break;
                    //D
                    case 4: result.addAll(Arrays.asList(10, 4, 5)); break;
                    //DI
                    case 8: result.addAll(Arrays.asList(8)); break;
                    //O
                    case 2: result.addAll(Arrays.asList(2, 8, 7)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(10)); break;
                    //M
                    case 1: result.addAll(Arrays.asList(2, 8, 7)); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(11)); break;
                    //S
                    case 3: result.addAll(Arrays.asList(3, 9, 6)); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(9)); break;
                    //F
                    case 5: result.addAll(Arrays.asList(10)); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(8)); break;
                    //EQ
                    case 6: result.add(9); break;
                }
                break;
            case 5:
                switch (r2) {
                    //B
                    case 0: result.addAll(Arrays.asList(0)); break;
                    //BI
                    case 12: result.addAll(Arrays.asList(12)); break;
                    //D
                    case 4: result.addAll(Arrays.asList(4)); break;
                    //DI
                    case 8: result.addAll(Arrays.asList(12, 10, 11, 8, 9)); break;
                    //O
                    case 2: result.addAll(Arrays.asList(2, 4, 3)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(12, 10, 11)); break;
                    //M
                    case 1: result.addAll(Arrays.asList(1)); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(12)); break;
                    //S
                    case 3: result.addAll(Arrays.asList(4)); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(12, 10, 11)); break;
                    //F
                    case 5: result.addAll(Arrays.asList(5)); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(5, 7, 6)); break;
                    //EQ
                    case 6: result.add(5); break;
                }
                break;
            case 7:
                switch (r2) {
                    //B
                    case 0: result.addAll(Arrays.asList(0)); break;
                    //BI
                    case 12: result.addAll(Arrays.asList(12, 10, 11, 8, 9)); break;
                    //D
                    case 4: result.addAll(Arrays.asList(2, 4, 3)); break;
                    //DI
                    case 8: result.addAll(Arrays.asList(8)); break;
                    //O
                    case 2: result.addAll(Arrays.asList(2)); break;
                    //OI
                    case 10: result.addAll(Arrays.asList(10, 8, 9)); break;
                    //M
                    case 1: result.addAll(Arrays.asList(1)); break;
                    //MI
                    case 11: result.addAll(Arrays.asList(9, 10, 8)); break;
                    //S
                    case 3: result.addAll(Arrays.asList(2)); break;
                    //SI
                    case 9: result.addAll(Arrays.asList(8)); break;
                    //F
                    case 5: result.addAll(Arrays.asList(5, 7, 6)); break;
                    //FI
                    case 7: result.addAll(Arrays.asList(7)); break;
                    //EQ
                    case 6: result.add(7); break;
                }
                break;
            case 6:
                switch (r2) {
                    //B
                    case 0: result.add(0); break;
                    //BI
                    case 12: result.add(12); break;
                    //D
                    case 4: result.add(4); break;
                    //DI
                    case 8: result.add(8); break;
                    //O
                    case 2: result.add(2); break;
                    //OI
                    case 10: result.add(10); break;
                    //M
                    case 1: result.add(1); break;
                    //MI
                    case 11: result.add(11); break;
                    //S
                    case 3: result.add(3); break;
                    //SI
                    case 9: result.add(9); break;
                    //F
                    case 5: result.add(5); break;
                    //FI
                    case 7: result.add(7); break;
                    //EQ
                    case 6: result.add(6); break;
                }
                break;
        }
        return new IASetVo(result);
    }

    public static DistanceVo getDistance(Integer r, Integer dl, Integer du) {
        int m = 4;
        DistanceVo result = new DistanceVo(m);
        Map<Integer, Integer> min = result.getDMin();
        Map<Integer, Integer> max = result.getDMax();
        Map<Integer, Boolean> afterTo = result.getAfterTo();
        int i1 = 0;
        int i2 = 1;
        int j1 = 2;
        int j2 = 3;
        if (r <= 6) {
            switch (r) {
                case 0:
                    min.put(j1 * m + i1, LMIN + 1);
                    min.put(j2 * m + i1, LMIN + 1);
                    min.put(j1 * m + i2, dl);
                    max.put(j1 * m + i2, du);
                    min.put(j2 * m + i2, LMIN + 1);
                    min.put(i2 * m + i1, LMIN + 1);
                    min.put(j2 * m + j1, LMIN + 1);

                    afterTo.put(i2 * m + i1, true);
                    afterTo.put(j1 * m + i1, true);
                    afterTo.put(j1 * m + i2, true);
                    afterTo.put(j2 * m + i1, true);
                    afterTo.put(j2 * m + i2, true);
                    afterTo.put(j2 * m + j1, true);
                    break;
                case 1:
                    min.put(j1 * m + i1, LMIN + 1);
                    min.put(j2 * m + i1, LMIN + 1);
                    min.put(i2 * m + j1, 0);
                    max.put(i2 * m + j1, 0);
                    min.put(j2 * m + i2, LMIN + 1);
                    min.put(i2 * m + i1, LMIN + 1);
                    min.put(j2 * m + j1, LMIN + 1);

                    afterTo.put(j1 * m + i1, true);
                    afterTo.put(j2 * m + i1, true);
                    afterTo.put(j2 * m + i2, true);
                    afterTo.put(i2 * m + i1, true);
                    afterTo.put(j2 * m + j1, true);
                    break;
                case 2:
                    min.put(j1 * m + i1, LMIN + 1);
                    min.put(i2 * m + j1, dl);
                    max.put(i2 * m + j1, du);
                    min.put(j2 * m + i1, LMIN + 1);
                    min.put(j2 * m + i2, LMIN + 1);
                    min.put(j2 * m + j1, dl);
                    min.put(i2 * m + i1, dl);

                    afterTo.put(j1 * m + i1, true);
                    afterTo.put(i2 * m + j1, true);
                    afterTo.put(j2 * m + i1, true);
                    afterTo.put(j2 * m + i2, true);
                    afterTo.put(j2 * m + j1, true);
                    afterTo.put(i2 * m + i1, true);
                    break;
                case 3:
                    min.put(j1 * m + i1, 0);
                    max.put(j1 * m + i1, 0);
                    min.put(j2 * m + i1, LMIN + 1);
                    min.put(i2 * m + j1, LMIN + 1);
                    min.put(j2 * m + i2, LMIN + 1);
                    min.put(i2 * m + i1, dl);
                    max.put(i2 * m + i1, du);
                    min.put(j2 * m + j1, dl);

                    afterTo.put(j2 * m + i1, true);
                    afterTo.put(i2 * m + j1, true);
                    afterTo.put(j2 * m + i2, true);
                    afterTo.put(i2 * m + i1, true);
                    afterTo.put(j2 * m + j1, true);
                    break;
                case 4:
                    min.put(i1 * m + j1, LMIN + 1);
                    min.put(j2 * m + i1, LMIN + 1);
                    min.put(i2 * m + j1, LMIN + 1);
                    min.put(j2 * m + i2, LMIN + 1);
                    min.put(i2 * m + i1, dl);
                    max.put(i2 * m + i1, du);
                    min.put(j2 * m + j1, dl);

                    afterTo.put(i1 * m + j1, true);
                    afterTo.put(j2 * m + i1, true);
                    afterTo.put(i2 * m + j1, true);
                    afterTo.put(j2 * m + i2, true);
                    afterTo.put(i2 * m + i1, true);
                    afterTo.put(j2 * m + j1, true);
                    break;
                case 5:
                    min.put(i1 * m + j1, LMIN + 1);
                    min.put(j2 * m + i1, LMIN + 1);
                    min.put(i2 * m + j1, LMIN + 1);
                    min.put(j2 * m + i2, 0);
                    max.put(j2 * m + i2, 0);
                    min.put(i2 * m + i1, dl);
                    max.put(i2 * m + i1, du);
                    min.put(j2 * m + j1, dl);

                    afterTo.put(i1 * m + j1, true);
                    afterTo.put(j2 * m + i1, true);
                    afterTo.put(i2 * m + j1, true);
                    afterTo.put(i2 * m + i1, true);
                    afterTo.put(j2 * m + j1, true);
                    break;
                case 6:
                    min.put(j1 * m + i1, 0);
                    max.put(j1 * m + i1, 0);
                    min.put(j2 * m + i1, LMIN + 1);
                    min.put(i2 * m + j1, LMIN + 1);
                    min.put(j2 * m + i2, 0);
                    max.put(j2 * m + i2, 0);
                    min.put(i2 * m + i1, dl);
                    max.put(i2 * m + i1, du);
                    min.put(j2 * m + j1, dl);
                    max.put(j2 * m + j1, du);

                    afterTo.put(i2 * m + i1, true);
                    afterTo.put(j2 * m + i1, true);
                    afterTo.put(i2 * m + j1, true);
                    afterTo.put(j2 * m + j1, true);
                    break;
            }
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (min.get(i * m + j) == null) {
                        if (min.get(j * m + i) != null) {
                            min.put(i * m + j, min.get(j * m + i));
                        } else {
                            min.put(i * m + j, 0);
                        }
                    }

                    if (max.get(i * m + j) == null) {
                        if (max.get(j * m + i) != null) {
                            max.put(i * m + j, max.get(j * m + i));
                        } else {
                            max.put(i * m + j, LMAX);
                        }
                    }
                }
            }
        } else {
            DistanceVo rev = getDistance(12 - r, dl, du);
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < m; ++j) {
                    int ti = (i + 2) % 4;
                    int tj = (j + 2) % 4;
                    min.put(ti * m + tj, rev.getDMin().get(i * m + j));
                    max.put(ti * m + tj, rev.getDMax().get(i * m + j));
                    afterTo.put(ti * m + tj, rev.getAfterTo().get(i * m + j));
                }
            }
        }
        return result;
    }

    public static Map<Integer, Integer> afterTo(Integer r) {
        Map<Integer, Integer> result = new HashMap<>();
        /**
         * 0: pi1   1: pi2
         * 2: pj1   3: pj2
         */
        int pi1 = 0;
        int pi2 = 1;
        int pj1 = 2;
        int pj2 = 3;
        if (r > 6) {
            r = 12 - r;
            pi1 = 2;
            pi2 = 3;
            pj1 = 0;
            pj2 = 1;
        }
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (i == j) {
                    result.put(i * 4 + j, 0);
                } else {
                    result.put(i * 4 + j, -1);
                }
            }
        }
        result.put(pi2 * 4 + pi1, 1);
        result.put(pj2 * 4 + pj1, 1);
        switch (r) {
            case 0:
                result.put(pj1 * 4 + pi1, 1);
                result.put(pj1 * 4 + pi2, 1);
                result.put(pj2 * 4 + pi1, 1);
                result.put(pj2 * 4 + pi2, 1);
                break;
            case 1:
                result.put(pj1 * 4 + pi1, 1);
                result.put(pj2 * 4 + pi1, 1);
                result.put(pj2 * 4 + pi2, 1);
                result.put(pi2 * 4 + pj1, 0);
                result.put(pj1 * 4 + pi2, 0);
                break;
            case 2:
                result.put(pj1 * 4 + pi1, 1);
                result.put(pi2 * 4 + pj1, 1);
                result.put(pj2 * 4 + pi1, 1);
                result.put(pj2 * 4 + pi2, 1);
                break;
            case 3:
                result.put(pi2 * 4 + pj1, 1);
                result.put(pj2 * 4 + pi1, 1);
                result.put(pj2 * 4 + pi2, 1);
                result.put(pi1 * 4 + pj1, 0);
                result.put(pj1 * 4 + pi1, 0);
                break;
            case 4:
                result.put(pi1 * 4 + pj1, 1);
                result.put(pi2 * 4 + pj1, 1);
                result.put(pj2 * 4 + pi1, 1);
                result.put(pj2 * 4 + pi2, 1);
                break;
            case 5:
                result.put(pi1 * 4 + pj1, 1);
                result.put(pi2 * 4 + pj1, 1);
                result.put(pj2 * 4 + pi1, 1);
                result.put(pi2 * 4 + pj2, 0);
                result.put(pj2 * 4 + pi2, 0);
                break;
            case 6:
                result.put(pi2 * 4 + pj1, 1);
                result.put(pj2 * 4 + pi1, 1);
                result.put(pi1 * 4 + pj1, 0);
                result.put(pj1 * 4 + pi1, 0);
                result.put(pi2 * 4 + pj2, 0);
                result.put(pj2 * 4 + pi2, 0);
                break;
        }
        return result;
    }
}
