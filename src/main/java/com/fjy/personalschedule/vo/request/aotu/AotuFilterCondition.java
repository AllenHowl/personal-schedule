package com.fjy.personalschedule.vo.request.aotu;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class AotuFilterCondition {

    private String gbType = "2";

    private JSONObject carConfigVo = new JSONObject();

    private AotuRentTimeVo rentTimeVo = new AotuRentTimeVo();

    private AotuRentAddressVo rentAddressVo = new AotuRentAddressVo();

    private JSONObject mapVo = new JSONObject();

    private String engineType = "2,3";

    private JSONObject carPriceVo = new JSONObject();

    private JSONObject carTagVo = new JSONObject();

    private JSONObject specialTagVo = new JSONObject();

    private String seq = "3";
}
