package com.ssy.pink.common;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class ResponseCode {
    public static final String CODE_SUCCESS = "success";
    public static final String CODE_001 = "001";//请求参数非法或者为空
    public static final String CODE_002 = "002";//参数校验不通过，用户的会员编号信息不能为空
    public static final String CODE_003 = "003";//用户信息不存在
    public static final String CODE_004 = "004";//参数校验不通过，小组名称不能为空
    public static final String CODE_0041 = "0041";//参数校验不通过，小组名称重名了
    public static final String CODE_999 = "999";//用户小组信息添加失败

}
