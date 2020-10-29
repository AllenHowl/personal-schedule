package com.fjy.personalschedule.task.tasks;

import com.fjy.personalschedule.component.ConstantField;
import com.fjy.personalschedule.utils.HttpPostUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AFunTask
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/10/16
 * @Version: 1.0.0
 **/
@Slf4j
@Component
public class AFunTask implements TaskInc {
    private static Map<String, Object> aFunHeader = new HashMap<>();
    private static Map<String, Object> aFunParam = new HashMap<>();

    static {
        headerNotification();
        paramNotification();
    }

    private static void headerNotification() {
        aFunHeader.put("Cookie", "_fw_crm_v=c258b196-db73-42fe-8a32-e2d8895275f5; _ga=GA1.2.1939751776.1597299124; ajs_anonymous_id=%229acab4b6-336b-4da8-941a-c28232dffb03%22; __utmz=171347184.1597299126.1.1.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); remember_web_59ba36addc2b2f9401580f014c7f58ea4e30989d=eyJpdiI6ImpcL2R4M0o2aExwOWNBbFhwdHVBdk9BPT0iLCJ2YWx1ZSI6InY5dVp1VFk2YVJTV2l2bVVBK1Q0ZnQ5NXdQelB1cEtcL3AzbUVCQTNJb3UzNXhhekJQZ3Z4V21uM1JkT3I0TG1XcVMzb2w0SVwvcW5FalBMR2xiRWNNSEZwVm0xZFBoelpId3ZkUTlhYTMySFhuRHJ6S0tUMlRyc0g1VzVtRWFTOHk1MVFLa0x6dzdXZkhkQkxOTVV4bGluYmxMeVFSMGJMWUJrakJcLzFFNWhnQT0iLCJtYWMiOiJjY2ExNjVmMGMwMDJjN2UxYmExZWY2NjQzOTBjZWVhYWRiNjJlMDJhZWI3NzgxYjY2YWJjNTMwZjhhM2Q3ODgyIn0%3D; __cfduid=d5f7ec60538acd2c5cd9633c0facdbd4d1601359315; _gid=GA1.2.1612649215.1602675631; __utmc=171347184; __utma=171347184.1939751776.1597299124.1602763771.1602814994.14; __utmt=1; __utmb=171347184.1.10.1602814994; XSRF-TOKEN=eyJpdiI6IjlpMmVhM1RGNWNQeWdjNkZZZmdsSEE9PSIsInZhbHVlIjoiVTJERE9ERWs3c0poaHN1aThJVVNnNlJjaWF0RW1ZZ1U1eWVuZUlieHBtU21PQjNlUFBqK2RRUStRQ3B2ZmcxUiIsIm1hYyI6IjAwOGNkNmRjZTQ5YWYwYjRlMzU0OWE3Yjg0Y2YxNGM4NTUzNGUzMTQzZGIzMGY3ZGY1NTZjZGFmZTBkNmQyYjcifQ%3D%3D; proxypanel_session=eyJpdiI6InJ5RFwvWmU5elwvakhQeHVnMGR5UUtiQT09IiwidmFsdWUiOiJEXC91a3E3VXYzSktLVGFTZFd2YzNDSmVUMHdmcU5ZazVJYXBzd1htNEJlcGh4N0xRc2Vmb2oyeWh2N3E1d3BcL2YiLCJtYWMiOiJmMzE5MGI1MGFlZmZlM2U0MTQwM2UwOWI4NDgzYTI4MmJlMmMwMWQzM2MyOWQxN2ZlM2U4MDJhODczNTUwNGUxIn0%3D");
        aFunHeader.put("x-requested-with", "XMLHttpRequest");
        /*aFunHeader.put();
        aFunHeader.put();
        aFunHeader.put();*/
    }

    private static void paramNotification() {
        aFunParam.put("_token", "BSxPgWrTupRWQWjLv5N5YfnoTlqwkYdMpY05JhFl");
    }


    @Override
    public void taskProcess() {
        log.info("AFun task process....");
        String result = HttpPostUtils.requestByPost(ConstantField.aFunUrl, aFunHeader, aFunParam);
        log.info("aFun check in , result = {}", result);
    }


    /**
     * unicode 转字符串
     * @param unicode 全为 Unicode 的字符串
     * 考虑情况：没有，一段，多段
     * @return
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        // 不存在unicode的情况
        if(unicode==null|| !unicode.contains("\\\\u")){
            return unicode;
        }
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

}
