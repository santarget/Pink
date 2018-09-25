package com.ssy.greendao.helper;

import com.ssy.greendao.gen.EmotionInfoDao;
import com.ssy.pink.bean.weibo.EmotionInfo;

import java.util.List;

/**
 * @author ssy
 * @date 2018/9/20
 */
public class EmotionDbHelper extends BaseDbHelper<EmotionInfoDao, EmotionInfo> {

    public EmotionDbHelper(EmotionInfoDao dao) {
        super(dao);
    }

    public List<EmotionInfo> queryAll() {
        return super.query("and", null, null, null, null);
    }
}
