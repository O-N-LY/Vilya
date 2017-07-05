package com.stella.service.vilya.api.common.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PathConsistencyResponse {

    private Boolean success;

    private Integer i;

    private Integer j;

    private Integer k;

    private Integer nPathsChecked;

    private Integer nChanges;
}
