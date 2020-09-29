package com.fjy.personalschedule.vo.request.dingding;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AtVo {

    private List<String> atMobiles;

    private Boolean isAtAll;

    public AtVo() {
        this.atMobiles = new ArrayList<>();
        this.isAtAll = false;
    }
}
