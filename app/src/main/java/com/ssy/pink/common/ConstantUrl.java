package com.ssy.pink.common;

public class ConstantUrl {
    /* weibo1 https://api.weibo.com*/
    public static final String WEIBO_LOGIN = "oauth2/authorize";
    public static final String WEIBO_USER_INFO = "2/users/show.json";
    public static final String WEIBO_EMOTIONS = "2/emotions.json";
    public static final String WEIBO_LATEST_STATUS = "2/statuses/user_timeline.json";//此接口最多只返回最新的5条微博
    public static final String WEIBO_SHARE = "2/statuses/share.json";
//    public static final String WEIBO_RANK = "2/users/show_rank.json";
    /* weibo2 http://api.t.sina.com.cn/ */
    public static final String WEIBO_REPOST = "2/statuses/repost.json";
    public static final String WEIBO_UPLOAD = "statuses/upload.json";


    /* pink */
    public static final String LIST_FANS_Org = "adminservice/queryFansOrgInfoList";
    public static final String LIST_PRODUCT = "adminservice/queryProductList";
    public static final String SYNC_CUSTOMER = "adminservice/synCustomerInfo";
    public static final String SYNC_RECHARGE_RECORD = "adminservice/synUserAddMoneyInfo";
    public static final String SYNC_SPEND_RECORD = "adminservice/synUserSpendInfo";
    public static final String ORDER_ONE_PRODUCE = "adminservice/orderOneProduct";
    public static final String GET_ORDER_INFO = "adminservice/queryUserOrderInfo";
    public static final String GET_USER_MONEY_INFO = "adminservice/queryUserAcountInfo";
    public static final String GET_VERSION = "adminservice/queryAppInfo";

    //分组和小号
    public static final String LIST_GROUP_INFO = "adminservice/queryCustomerGroupinfoList";
    public static final String LIST_SMALL_INFO = "adminservice/querySmallCustomerDetailList";
    public static final String GET_SMALL_STATUS = "adminservice/queryCustomerStatusGroupNum";
    public static final String ADD_GROUP = "adminservice/addSelfOneGroup";
    public static final String DELETE_GROUP = "adminservice/deleteSelfOneGroup";
    public static final String UPDATE_GROUP = "adminservice/updateSelfOneGroup";

    public static final String BIND_SMALL = "adminservice/synUserSmallInfo";
    public static final String DELETE_SMALL = "adminservice/deleteOneUserSmallInfo";
    public static final String MOVE_SMALL = "adminservice/bindOneGroupForSmallNum";//移动小号到另一分组
    public static final String CHECK_SMALL = "adminservice/judgeWeiboNumExist";//绑定之前校验账号是否被占用

}
