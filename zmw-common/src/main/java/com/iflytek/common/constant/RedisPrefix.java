package com.iflytek.common.constant;

/**
 * @author zgzhao
 * redis 前缀
 */
public class RedisPrefix {
    /**
     * 评论量
     */
    public static final String DISCUSS_NUMBER = "DISCUSS_NUMBER";
    /**
     * 文章 点赞量
     */
    public static final String ARTICLE_PRAISE_NUMBER = "praise_count";

    /**
     * 评论者 点赞量
     */
    public static final String DISCUSS_PRAISE_NUMBER ="discuss_praise_number";

    /**
     * 回复评论者 点赞量
     */
    public static final String REPLY_PRAISE_NUMBER ="reply_praise_number";

    /**
     * 验证码
     */
    public static final String VERIFICATION_CODE = "verification_code";

    /**
     * 美文号
     */
    public static final String MW_ID = "mw_id";


    /**
     * 共享 shiro session
     */
    public static final String SHIRO_SESSION = "shiro_session";

    /**
     * shiro cache
     */
    public static final String SHIRO_CACHE = "shiro_cache";

    /**
     * 访问量限制
     */
    public static final String REQUEST_LIMIT = "request_limit";

    /**
     * 统计方法 一年的 信息
     */
    public static final String STATISTICS_ONE_YEAR = "statistics_one_year";


    /**
     * 当天请求次数
     */
    public static final String REQUEST_COUNT = "request_count";

    /**
     * 应用的当前周
     */
    public static final String CURRENT_WEEK = "current_week";

    /**
     * 应用当前周的天数
     */
    public static final String CURRENT_WEEK_DAY = "current_week_day";

    /**
     * 新增用户
     */
    public static final String ADD_USER = "add_user";


    /**
     * 当天的活跃用户
     */
    public static final String ACTIVE_USER = "active_user";
    /**
     * 用户总量
     */
    public static final String USER_SUM = "user_sum";

    /**
     * spring redis 注解 key
     */
    public static final String SPRING_REDIS = "spring_redis";


    /**
     * 文章缓存的mwId
     */
    public static final String ARTICLE_MW_ID = "article_mw_id";

    /**
     * 文章方法缓存组
     */
    public static final String ARTICLE_GROUP = "article_group";

    /**
     * 粉丝的数量
     */
    public static final String FANS_NUMBER = "fans_number";
    /**
     * 收藏的数量
     */
    public static final String COLLECT_NUMBER = "collect_number";

    /**
     * 文章的阅读量
     */
    public static final String ARTICLE_READING_NUMBER = "article_reading_number";

    /**
     * 文章
     */
    public static final String ARTICLE_ID = "article_id";


    /**
     * 在线人数
     */
    public static final String ONLINE_COUNT = "online_count";

    /**
     * 当前小时请求次数
     */
    public static final String REQUEST_COUNT_HOUR = "request_count_hour";


    /**
     * 当前天的请求量
     */
    public static final String REQUEST_COUNT_DAY = "request_count_day";

    /**
     * session 剔除 队列
     */
    public static final String KICK_OUT_QUEUE = "kick_out_queue";
}
