package com.iflytek.common.exception;
/**
 * @author AZhao
 */
public class RequestLimitException extends BaseException {
    /**
     * 当前方法的最大次数
     */
    private long count;

    /**
     * 下一次请求间隔的时间 单位s
     */
    private long time;
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public RequestLimitException(int code, String msg) {
        super(code, msg);
    }

    public RequestLimitException(int code, String msg,long time, long count) {
        super(code, msg);
        this.count = count;
        this.time =time;
    }

    public RequestLimitException() {
    }

    private static final long serialVersionUID = -4275699170673810522L;

}
