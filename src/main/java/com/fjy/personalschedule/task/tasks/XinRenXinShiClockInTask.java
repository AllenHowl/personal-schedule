package com.fjy.personalschedule.task.tasks;

import com.fjy.personalschedule.utils.HttpPostUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class XinRenXinShiClockInTask implements TaskInc {

    @Override
    public  void taskProcess(){
        log.info("xrxs start send request....");
        log.debug("xrxs start send request....");
        String url = "https://mapi.xinrenxinshi.com/v3/attendance/sign?aMap=39.996007%3A116.481003%3A65.000000&appKey=app_ios&bMap=&deviceId=835667B6-FAA4-43A4-9FF0-FBD0211C2B7D&faceImg=&mac=f0%3A3e%3A90%3Acc%3Aee%3Abc&nonce=EW4IX94ZDQ69&sign=I%2BZT4f8noI265CxJj6FKRWPRGBA%3D&timestamp=1600398860945&token=7c530c7c9f554568b8a16608a82d9825&ver=2.1.2";
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("Cookie","QJYDSID=7c530c7c9f554568b8a16608a82d9825; WAVESSID=7c530c7c9f554568b8a16608a82d9825");

        Map<String,Object> paramMap = new HashMap<>();
//        paramMap.put("aMap","39.996007%3A116.481003%3A65.000000");
//        paramMap.put("appKey","app_ios");
//        paramMap.put("deviceId","835667B6-FAA4-43A4-9FF0-FBD0211C2B7D");
//        paramMap.put("mac","f0%3A3e%3A90%3Acc%3Aee%3Abc");
//        paramMap.put("nonce","EW4IX94ZDQ69");
//        paramMap.put("sign","I%2BZT4f8noI265CxJj6FKRWPRGBA%3D");
//        paramMap.put("timestamp","1600398860945");
//        paramMap.put("token","7c530c7c9f554568b8a16608a82d9825");
//        paramMap.put("ver","2.1.2");
//        paramMap.put("bMap","");
//        paramMap.put("faceImg","");
        String s = HttpPostUtils.requestByPost(url, headerMap, paramMap);
        System.out.println(s);
    }
}
