package com.fjy.personalschedule.component;

import com.fjy.personalschedule.vo.request.dingding.DingMessage;
import com.fjy.personalschedule.vo.request.dingding.MarkDownVo;
import com.fjy.personalschedule.vo.request.dingding.TextVo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 钉钉消息创建工厂
 */
public class DingMessageFactory {

    /**
     * @desc 创建钉钉text类型消息
     * @param content-文本内容
     * @param phoneList-@手机号码列表
     * @param atAll-是否@全体
     * @return
     */
    public static DingMessage buildTextVo(String content, List<String> phoneList, Boolean atAll){
        TextVo textVo = new TextVo();
        if (!CollectionUtils.isEmpty(phoneList)) {
            textVo.getAt().setAtMobiles(phoneList);
        }
        if (atAll != null) {
            textVo.getAt().setIsAtAll(atAll);
        }
        textVo.getText().setContent(content);
        return textVo;
    }


    /**
     * @desc 创建钉钉markdown类型消息
     * @param title-文本标题
     * @param content-文本内容
     * @param phoneList-@手机号列表
     * @param atAll-是否@全体
     * @return
     */
    public static DingMessage buildMarkDownVo(String title, String content,List<String> phoneList, Boolean atAll ){
        MarkDownVo markDownVo = new MarkDownVo();
        if (!CollectionUtils.isEmpty(phoneList)) {
            markDownVo.getAt().setAtMobiles(phoneList);
        }
        if (atAll != null) {
            markDownVo.getAt().setIsAtAll(atAll);
        }
        markDownVo.getMarkdown().setTitle(title);
        markDownVo.getMarkdown().setText(content);
        return markDownVo;
    }
}
