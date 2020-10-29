package com.fjy.personalschedule.component;

import com.fjy.personalschedule.vo.response.aotu.CarVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用常量字段
 */
public class ConstantField {

    /**
     * 凹凸租车url
     */
    public static final String aotuUrl = "https://app30.aotuzuche.com:7065/v62/623/search/car/list";

    /**
     * 凹凸租车正常返回码
     */
    public static final String aotuCode = "000000";

    /**
     * 价格低于200的车map
     */
    public static List<CarVo> carVoList = new ArrayList<>();

    /**
     * 价格高于200的最便宜的车
     */
    public static CarVo carVo;

    /**
     * 上次发送消息的时间
     */
    public static Long latestTimeStamp;

    /**
     * 钉钉发送消息时间间隔
     */
    public static long dingInterval = 600000;

    /**
     * 钉钉请求地址
     */
    public static String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=d5e0bff30217149ae1bdcd15d7b9739c7dadc7615cca4bcf8388a15d2109677c";

    /**
     * 钉钉校验关键词
     */
    public static String dingKeyWorld = "哦呦～新消息来了呦～";

    /**
     * 阿凡vpn签到接口
     */
    public static String aFunUrl = "https://www.afunv.com/checkIn";
}
