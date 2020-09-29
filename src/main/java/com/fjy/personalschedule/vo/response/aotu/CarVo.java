package com.fjy.personalschedule.vo.response.aotu;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class CarVo {
    /**
     * 名称
     */
    private String brandInfo;
    /**
     * 动力
     */
    private String sweptVolum;

    private String carNo;

    private String coverPic;
    /**
     * 原价
     */
    private String dayPrice;
    /**
     * 优惠价
     */
    private String preferentialPrice;

    private String distance;

    private String gbType;

    /**
     * 车牌
     */
    private String plateNum;
    /**
     * 已租次数
     */
    private String transCount;
    /**
     * 里程限制
     */
    private String dayMileageTxt;

    private String holidayPrice;

    private String supportGetReturn;

    private String confirmation;

    private String excellentCar;

    private List<String> tag;

    private String carScore;

    private String score;

    private String carAddrIndex;

    private String lat;

    private String lon;

    private int memNo;

    private int brand;

    private int type;

    private String typeTxt;

    private int evalScore;

    private int guidePurchasePrice;

    private String cylinderCapacityTxt;

    /**
     * 年份
     */
    private int year;

    private int ownerType;

    private int isServiceRange;

    private int isAcceptGetReturn;

    private String ccUnit;

    private String getCarAddr;

    private JSONObject carAddressBO;

    private int isAcceptSetPrice;

    private List<JSONObject> carServiceTagList;

    private List<JSONObject> carFavorableTagList;

    private String fave;
}
