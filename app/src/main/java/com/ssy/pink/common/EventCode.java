package com.ssy.pink.common;

/**
 * Created by ssy on 2017/8/30.
 */

public class EventCode {
    /*登录*/
    public static final int LOGIN_SUCCESS = 10;
    public static final int LOGIN_FAIL = 11;
    public static final int LOGIN_CHOOSE_ORG = 12;
    public static final int LOGIN_OUT = 13;

    /* 用户数据更新 */
    public static final int GET_MONEY_INFO = 20;//获取用户金额信息(金额，爱豆，小号) MoneyInfo

    /* 分组和小号*/
    public static final int ADD_GROUP = 30;//添加分组
    public static final int UPDATE_GROUPS = 31;//刷新分组信息
    public static final int EDIT_GROUP = 32;//编辑分组信息
    public static final int MOVE_SMALL = 33;//移动小号

    /* 抡博 */
    public static final int WORK_FINISH = 40;//抡博完成
    public static final int WORK_UPDATE_LOG = 41;//更新抡博日志
    public static final int WORK_WAITING = 43;//每轮之间等待
    public static final int WORK_NEXT = 44;//下一个账号


}
