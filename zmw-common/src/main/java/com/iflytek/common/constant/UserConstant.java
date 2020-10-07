package com.iflytek.common.constant;

/**
 * @author zgzhao
 * 用户级配置
 */
public class UserConstant {
    /**
     * 匹配mwId
     * mw11011
     */
    public static final String MWID_REGEXP = "^mw[0-9]{5,}$";

    /**
     * 匹配手机号
     * 11位通用
     */
    public static final String PHONE_REGEXP = "1[34578]\\d{9}";
    /**
     * MD5密码的盐
     */
    public static final String USER_PWD_SALT = "21c2f32f297a57a5a743894a0e4a801fc3";

    /**
     * 验证码的MD5盐
     */
    public static final String VERIFICATION_CODE_SALT = "28f3d6b72f34f2134862609c29aa161f";
    /**
     * 回复评论者默认显示的最多条数
     */
    public static final Integer REPLY_Discuss_PAGE_SIZE=4;



}
