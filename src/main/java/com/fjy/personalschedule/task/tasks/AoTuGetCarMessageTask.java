package com.fjy.personalschedule.task.tasks;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fjy.personalschedule.component.ConstantField;
import com.fjy.personalschedule.component.DingMessageFactory;
import com.fjy.personalschedule.utils.HttpPostUtils;
import com.fjy.personalschedule.vo.request.aotu.AoTuRequestVo;
import com.fjy.personalschedule.vo.request.dingding.DingMessage;
import com.fjy.personalschedule.vo.response.aotu.CarVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AoTuGetCarMessageTask implements TaskInc {

    @Autowired
    private AoTuRequestVo aoTuRequestVo;

    private static Map<String,Object> header;

    static{
        header = new HashMap<>();
        header.put("Cookie","autoCookieToken=818b7a18537d4d6abd48cdb785535f42; gr_user_id=a0fc536f-76be-49fc-a8d6-43cf7a9706ca; grwng_uid=37f2fc06-813b-492b-9fe7-d4fa4ab8fd7f");
        header.put("X-Tingyun-Id","YfYbInNBhKA;c=2;r=313942634");
        header.put("Accept","application/json;version=3.0;compress=false");
        header.put("User-Agent","Autoyol_116:iPhone_14.0|C8EFD6F379E587DE68FF778803FA6F193E3EE833A669D3BF587CE77214D2A1C5DEDD5AA2D74A00");
    }

    @Override
    public void taskProcess() {

        try {
            String response = HttpPostUtils.postWithJson(ConstantField.aotuUrl, header, JSON.toJSONString(aoTuRequestVo));

            parseResponse(response);
        } catch (Exception e) {
            log.error("get aotu message error , e = ",e);
        }

    }

    private void parseResponse(String response) {

        if (StringUtils.isBlank(response)) {
            System.out.println("get aotu message error");
            return;
        }

        JSONObject jsonObject = JSON.parseObject(response);

        if (!ConstantField.aotuCode.equals(jsonObject.getString("resCode"))) {
            System.out.println("get aotu message error , response = "+response);
            return;
        }
        JSONObject data = jsonObject.getJSONObject("data");

        if (!ObjectUtils.allNotNull(data)) {
            return;
        }

        JSONArray carList = data.getJSONArray("carList");

        if (CollectionUtils.isEmpty(carList)) {
            return;
        }

        List<CarVo> carVos = carList.toJavaList(CarVo.class);

        ConstantField.carVo = carVos.get(0);

        Iterator<CarVo> carIterator = carVos.iterator();

        List<CarVo> carVoList = new ArrayList<>();

        while (carIterator.hasNext()) {
            CarVo next = carIterator.next();
            if (Integer.valueOf(next.getDayPrice())<=200||Integer.valueOf(next.getPreferentialPrice())<=200) {
                carVoList.add(next);
            }else {
                break;
            }
        }

        sendDingMessage(carVoList);
    }


    /**
     * @desc 钉钉发送消息
     * @param carVoList
     */
    private void sendDingMessage(List<CarVo> carVoList){

        if (CollectionUtils.isEmpty(carVoList)) {
            return;
        }
        // 每30分钟发一次
        if (ConstantField.latestTimeStamp!=null && (System.currentTimeMillis()-ConstantField.latestTimeStamp) < ConstantField.dingInterval ) {
            return;
        }

        StringBuilder message = new StringBuilder("### 有符合预期的车辆信息(200)").append("\n")
                .append("开始：2020-10-01 09：30").append("\n").append("\n")
                .append("结束：2020-10-07 20：00").append("\n");

        carVoList.stream().forEach(carVo -> {
            convertMessage(message,carVo);
        });
        // 补充时间信息
        message.append("***").append("\n")
                .append("时间：")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n").append("\n");

        message.append("@15076960772 @13202380184");

        DingMessage dingMessage = DingMessageFactory.buildMarkDownVo(ConstantField.dingKeyWorld,message.toString(),Arrays.asList("15076960772","13202380184"),null);

        String result = HttpPostUtils.postWithJson(ConstantField.dingUrl, null, JSON.toJSONString(dingMessage));

        if (result != null && JSON.parseObject(result).getInteger("errcode") == 0) {
            // 标记请求时间
            ConstantField.latestTimeStamp = System.currentTimeMillis();
        }

        System.out.println(result);

    }

    private void convertMessage(StringBuilder stringBuilder, CarVo carVo){
        if (null == carVo) {
            return;
        }
        if (null == stringBuilder) {
            stringBuilder = new StringBuilder();
        }

        stringBuilder.append("***").append("\n")
                .append("```").append("\n")
                .append("名称：").append(carVo.getBrandInfo()).append("\n")
                .append("动力：").append(carVo.getSweptVolum()).append("\n")
                .append("原价：").append(carVo.getDayPrice()).append("\n")
                .append("优惠价：").append(carVo.getPreferentialPrice()).append("\n")
                .append("车牌：").append(carVo.getPlateNum()).append("\n")
                .append("已租次数：").append(carVo.getTransCount()).append("\n")
                .append("里程限制：").append(carVo.getDayMileageTxt()).append("\n")
                .append("车龄：").append(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()))-carVo.getYear()).append("\n")
                .append("```").append("\n");
    }


    /**
     * 当前价格最低的信息
     */
    public void sendPoorMessage(){
        if(ConstantField.carVo == null){
            return;
        }

        StringBuilder message = new StringBuilder("### 当前价格最低信息").append("\n")
                .append("开始：2020-10-01 09：30").append("\n").append("\n")
                .append("结束：2020-10-07 20：00").append("\n");

        convertMessage(message,ConstantField.carVo);

        // 补充时间信息
        message.append("***").append("\n")
                .append("时间：")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n").append("\n");

        DingMessage dingMessage = DingMessageFactory.buildMarkDownVo(ConstantField.dingKeyWorld,message.toString(),Arrays.asList("15076960772","13202380184"),null);

        String result = HttpPostUtils.postWithJson(ConstantField.dingUrl, null, JSON.toJSONString(dingMessage));

        System.out.println(result);
    }

    @Test
    public void test(){

        /*AoTuRequestVo aoTuRequestVo = new AoTuRequestVo();
        String response = HttpPostUtils.postWithJson(ConstantField.aotuUrl, header, JSON.toJSONString(aoTuRequestVo));
        System.out.println(response);*/
        CarVo carVo = new CarVo();
        carVo.setBrandInfo("海马 V70");
        carVo.setSweptVolum("1.5T");
        carVo.setDayPrice("260");
        carVo.setPreferentialPrice("232");
        carVo.setPlateNum("京Q***T7");
        carVo.setTransCount("4");
        carVo.setDayMileageTxt("日均限300km");
        carVo.setYear(2016);


        String url = "https://oapi.dingtalk.com/robot/send?access_token=d5e0bff30217149ae1bdcd15d7b9739c7dadc7615cca4bcf8388a15d2109677c";
        StringBuilder message = new StringBuilder("### 当前价格最低信息").append("\n");

        convertMessage(message,carVo);

        // 补充时间信息
        message.append("***").append("\n")
                .append("时间：")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n").append("\n");

        message.append("@15076960772 @13202380184");

        DingMessage dingMessage = DingMessageFactory.buildMarkDownVo(ConstantField.dingKeyWorld,message.toString(), Arrays.asList("15076960772","13202380184"),false);
        String s = HttpPostUtils.postWithJson(url, null, JSON.toJSONString(dingMessage));
        System.out.println(s);

    }


}
