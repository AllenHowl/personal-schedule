package com.fjy.personalschedule.vo.request.dingding;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 钉钉的text消息体
 *
 * {
 *     "msgtype": "text",
 *     "text": {
 *         "content": "我就是我, 是不一样的烟火@156xxxx8827"
 *     },
 *     "at": {
 *         "atMobiles": [
 *             "156xxxx8827",
 *             "189xxxx8325"
 *         ],
 *         "isAtAll": false
 *     }
 * }
 *
 * msgtype：消息类型，此时固定为：text
 * content：消息内容
 * atMobiles：被@人的手机号（在content里添加@人的手机号）
 * isAtAll：是否@所有人
 *
 */

@Data
public class TextVo implements DingMessage{

    private String msgtype;

    private Text text;

    private AtVo at;

    public TextVo() {
        this.msgtype = "text";
        this.text = new Text();
        // 填充at
        this.at = new AtVo();
    }

    @Data
    public class Text{

        private String content;

    }
}
