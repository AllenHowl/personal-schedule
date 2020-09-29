package com.fjy.personalschedule.vo.request.dingding;

import lombok.Data;

/**
 * 钉钉的markdown消息体
 *
 * {
 *      "msgtype": "markdown",
 *      "markdown": {
 *          "title":"杭州天气",
 *          "text": "#### 杭州天气 @150XXXXXXXX \n> 9度，西北风1级，空气良89，相对温度73%\n> ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n> ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"
 *      },
 *       "at": {
 *           "atMobiles": [
 *               "150XXXXXXXX"
 *           ],
 *           "isAtAll": false
 *       }
 *  }
 *
 *  msgtype：此消息类型为固定markdown
 *  title：首屏会话透出的展示内容
 *  text：markdown格式的消息
 *  atMobiles：被@人的手机号（在text内容里要有@手机号）
 *  isAtAll：是否@所有人
 */

@Data
public class MarkDownVo implements DingMessage {

    private String msgtype;

    private MarkDown markdown;

    private AtVo at;

    public MarkDownVo() {
        this.msgtype = "markdown";

        this.markdown = new MarkDown();
        // 填充at
        this.at = new AtVo();
    }

    @Data
    public class MarkDown {

        private String title;

        private String text;

    }
}
