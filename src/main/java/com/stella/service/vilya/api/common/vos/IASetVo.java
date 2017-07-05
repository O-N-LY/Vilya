package com.stella.service.vilya.api.common.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
public class IASetVo {

    private Set<Integer> value;

    public IASetVo() {
        value = new TreeSet<>();
    }

    public IASetVo(IASetVo data) {
        value = new TreeSet<>();
        value.addAll(data.getValue());
    }

    public Boolean isEmpty() {
        return CollectionUtils.isEmpty(value);
    }

    public Integer size() {
        return value.size();
    }
}
