package com.stella.service.vilya.api.common.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
public class BASetVo {

    private Set<Integer> value;

    public BASetVo() {
        value = new TreeSet<>();
    }

    public BASetVo(BASetVo data) {
        value = new TreeSet<>();
        value.addAll(data.getValue());
    }

    public Boolean isEmpty() {
        return CollectionUtils.isEmpty(value);
    }

    public Integer size() {
        return value.size();
    }

    public Integer get(int index) {
        return new ArrayList<>(value).get(index);
    }
}
