package com.iflytek.common.utils;

import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.exception.UnsafeRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * @author zgzhao
 * 安全过滤的字符
 */
public class SecurityFilterUtils {
    private static List<String> list;
    static {
        list = Arrays.asList(
                "script", "select", "insert", "document", "window", "function",
                "delete", "update", "prompt", "alert", "create", "alter",
                "drop", "iframe", "link", "where", "replace", "function", "onabort",
                "onactivate", "onafterprint", "onafterupdate", "onbeforeactivate",
                "onbeforecopy", "onbeforecut", "onbeforedeactivateonfocus",
                "onkeydown", "onkeypress", "onkeyup", "onload",
                "expression", "applet", "layer", "ilayeditfocus", "onbeforepaste",
                "onbeforeprint", "onbeforeunload", "onbeforeupdate",
                "onblur", "onbounce", "oncellchange", "oncontextmenu",
                "oncontrolselect", "oncopy", "oncut", "ondataavailable",
                "ondatasetchanged", "ondatasetcomplete", "ondeactivate",
                "ondrag", "ondrop", "onerror", "onfilterchange", "onfinish", "onhelp",
                "onlayoutcomplete", "onlosecapture", "onmouse", "ote",
                "onpropertychange", "onreadystatechange", "onreset", "onresize",
                "onresizeend", "onresizestart", "onrow", "onscroll",
                "onselect", "onstaronsubmit", "onunload", "IMgsrc", "infarction");
    }

    private static String[] invalidCharacter = new String[]{

    };

    /**
     * @param request
     * @param response
     * 是否含有非法字符
     */
    public static void isHasInvalidCharacter(HttpServletRequest request, HttpServletResponse response) {
        String parameterName;
        String parameterValue;
        // 获取请求的参数
        Enumeration allParameter = request.getParameterNames();
        while (allParameter.hasMoreElements()) {
            parameterName = (String) allParameter.nextElement();
            parameterValue = request.getParameter(parameterName);
            if (null != parameterValue && list.contains(parameterValue)) {
                throw new UnsafeRequestException(ResultCode.UnSafeRequest.getCode(),ResultCode.UnSafeRequest.getMessage());
            }
            if (null != parameterName && list.contains(parameterName)) {
                throw new UnsafeRequestException(ResultCode.UnSafeRequest.getCode(),ResultCode.UnSafeRequest.getMessage());
            }
        }
    }
}
