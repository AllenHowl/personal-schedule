package com.fjy.personalschedule.vo.request.aotu;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 凹凸租车最外层请求协议
 */
@Component
@Data
public class AoTuRequestVo {

    private String AppVersion = "116";

    private String mem_no = "527694867";

    private String pageSize = "20";

    private String publicCityCode = "010";

    private String deviceName = "iPhone10,3";

    private String sceneCode = "U003";

    private String publicToken = "622e8ca4f26b43cb90687e3c5bf56814";

    private String PublicLongitude = "116.481051";

    private String IMEI = "EC793A03-3F6B-4F16-A740-44711F43B1DD";

    private AotuFilterCondition filterCondition = new AotuFilterCondition();

    private String schema = "B";

    private String requestId = "EC793A03-3F6B-4F16-A740-44711F43B1DD4789379523462689969";

    private String OS = "IOS";

    private String pageNum = "1";

    private String cityCode = "110100";

    private String searchType = "1";

    private String ModuleName = "v62/623/search/car/list";

    private String OsVersion = "14.0";

    private String PublicLatitude = "39.995983";

    private String isFirst = "0";
}
