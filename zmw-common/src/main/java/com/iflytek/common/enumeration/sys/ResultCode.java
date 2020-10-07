package com.iflytek.common.enumeration.sys;
/**
 * @author AZhao
 */
public enum ResultCode {
    Success(1, "成功"),
    //1xxxx用户页面错误
    Unknown_Exception(-1, "未知异常"),
    Locked_Account(10001, "账号被禁用不能登录"),
    Unknown_Account(10002, "错误的帐号"),
    Incorrect_Credentials(10003, "账号或密码错误"),
    Expired_Credentials(10004, "过期的凭证"),
    No_Permission(10005, "未授权"),
    Verification_Code_Error(10006, "验证码错误"),

    Register_Failure(10007, "注册失败"),
    Phone_Has_Register(10008, "该手机号已经注册过了"),
    Phone_Type_Error(10009, "手机格式错误"),
    UnLogin(10010, "用户未登录"),
    Tag_Has_Exits(10011, "该标签已经存在"),
    Account_Pwd_Not_Null(10012, "账号或密码不能为空"),
    Pwd_Error(10013, "密码错误"),
    Unknown_User(10014, "该用户不存在"),
    Invalid_Time(10015, "无效时间"),
    Modify_Failure(10016, "修改失败"),
    Verification_Code_Expire(10017, "验证码过期"),
    Upload_Failure(10018, "上传失败"),
    Delete_File_Failure(10019, "删除失败"),
    Key_Has_Exist(10020, "key已经存在"),
    DownLoad_File_Failure(10021, "下载失败"),
    Article_Not_Exist(10022, "文章不存在"),
    Article_Not_Open(10023, "该文章作者不对外公开"),
    Article_Encrypt_Open(10024, "该文章输入密码之后才能看"),
    Article_Pwd_Error(10025, "文章密码错误"),
    //2xxxxweb系统错误
    Not_Readable_JSON(20001, "不能读取json"),
    Argument_Error(20002, "参数验证异常"),
    Not_Found(20003, "资源没有找到"),
    Request_Method_Error(20004, "不合法的请求方法"),
    Media_Type_Not_Supported(20005, "内容类型不支持"),
    Service_Error(20006, "服务器内部错误"),
    UnSafeRequest(20007,"请求失败，请求不安全"),
    Validation_Exception(20008,"校验失败"),

    //3xxxxx数据错误
    Has_Permission(30001, "该用户已经有了该权限"),
    Has_Role(30002, "该用户已经拥有该角色"),
    Start_Num_Is_Null(30003, "开始页码不能为空"),
    Page_Size_Is_Null(30004, "页大小不能为空"),
    Request_Limit(30005, "访问次数受限"),


    //4xxxx 数据库错误
    Data_Duplicate(40001, "数据重复");
    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
