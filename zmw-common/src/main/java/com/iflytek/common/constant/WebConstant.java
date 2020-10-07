package com.iflytek.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zgzhao
 * 应用web级配置
 */
public class WebConstant {
    public static final Map<String, String> contextType = new HashMap<String, String>() {
        private static final long serialVersionUID = -6801187685007783309L;
        {    // image
            put("png", "image/png");
            put("gif", "image/gif");
            put("bmp", "image/bmp");
            put("ico", "image/x-ico");
            put("jpeg", "image/jpeg");
            put("jpg", "image/jpeg");
            // 压缩文件
            put("zip", "application/zip");
            put("rar", "application/x-rar");
            // doc
            put("pdf", "application/pdf");
            put("ppt", "application/vnd.ms-powerpoint");
            put("xls", "application/vnd.ms-excel");
            put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
            put("doc", "application/wps-office.doc");
            put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            put("txt", "text/plain");
            // 音频
            put("mp4", "video/mp4");
            put("flv", "video/x-flv");
        }
    };
}
