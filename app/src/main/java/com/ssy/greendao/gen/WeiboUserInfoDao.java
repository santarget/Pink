package com.ssy.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ssy.pink.bean.weibo.WeiboUserInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WEIBO_USER_INFO".
*/
public class WeiboUserInfoDao extends AbstractDao<WeiboUserInfo, Long> {

    public static final String TABLENAME = "WEIBO_USER_INFO";

    /**
     * Properties of entity WeiboUserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Screen_name = new Property(1, String.class, "screen_name", false, "SCREEN_NAME");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Description = new Property(3, String.class, "description", false, "DESCRIPTION");
        public final static Property Url = new Property(4, String.class, "url", false, "URL");
        public final static Property Profile_image_url = new Property(5, String.class, "profile_image_url", false, "PROFILE_IMAGE_URL");
        public final static Property Domain = new Property(6, String.class, "domain", false, "DOMAIN");
        public final static Property Gender = new Property(7, String.class, "gender", false, "GENDER");
        public final static Property Followers_count = new Property(8, int.class, "followers_count", false, "FOLLOWERS_COUNT");
        public final static Property Friends_count = new Property(9, int.class, "friends_count", false, "FRIENDS_COUNT");
        public final static Property Statuses_count = new Property(10, int.class, "statuses_count", false, "STATUSES_COUNT");
        public final static Property Favourites_count = new Property(11, int.class, "favourites_count", false, "FAVOURITES_COUNT");
        public final static Property Created_at = new Property(12, String.class, "created_at", false, "CREATED_AT");
        public final static Property Verified = new Property(13, boolean.class, "verified", false, "VERIFIED");
        public final static Property Avatar_large = new Property(14, String.class, "avatar_large", false, "AVATAR_LARGE");
        public final static Property Avatar_hd = new Property(15, String.class, "avatar_hd", false, "AVATAR_HD");
    }


    public WeiboUserInfoDao(DaoConfig config) {
        super(config);
    }
    
    public WeiboUserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WEIBO_USER_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL UNIQUE ," + // 0: id
                "\"SCREEN_NAME\" TEXT," + // 1: screen_name
                "\"NAME\" TEXT," + // 2: name
                "\"DESCRIPTION\" TEXT," + // 3: description
                "\"URL\" TEXT," + // 4: url
                "\"PROFILE_IMAGE_URL\" TEXT," + // 5: profile_image_url
                "\"DOMAIN\" TEXT," + // 6: domain
                "\"GENDER\" TEXT," + // 7: gender
                "\"FOLLOWERS_COUNT\" INTEGER NOT NULL ," + // 8: followers_count
                "\"FRIENDS_COUNT\" INTEGER NOT NULL ," + // 9: friends_count
                "\"STATUSES_COUNT\" INTEGER NOT NULL ," + // 10: statuses_count
                "\"FAVOURITES_COUNT\" INTEGER NOT NULL ," + // 11: favourites_count
                "\"CREATED_AT\" TEXT," + // 12: created_at
                "\"VERIFIED\" INTEGER NOT NULL ," + // 13: verified
                "\"AVATAR_LARGE\" TEXT," + // 14: avatar_large
                "\"AVATAR_HD\" TEXT);"); // 15: avatar_hd
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WEIBO_USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WeiboUserInfo entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String screen_name = entity.getScreen_name();
        if (screen_name != null) {
            stmt.bindString(2, screen_name);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
 
        String profile_image_url = entity.getProfile_image_url();
        if (profile_image_url != null) {
            stmt.bindString(6, profile_image_url);
        }
 
        String domain = entity.getDomain();
        if (domain != null) {
            stmt.bindString(7, domain);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(8, gender);
        }
        stmt.bindLong(9, entity.getFollowers_count());
        stmt.bindLong(10, entity.getFriends_count());
        stmt.bindLong(11, entity.getStatuses_count());
        stmt.bindLong(12, entity.getFavourites_count());
 
        String created_at = entity.getCreated_at();
        if (created_at != null) {
            stmt.bindString(13, created_at);
        }
        stmt.bindLong(14, entity.getVerified() ? 1L: 0L);
 
        String avatar_large = entity.getAvatar_large();
        if (avatar_large != null) {
            stmt.bindString(15, avatar_large);
        }
 
        String avatar_hd = entity.getAvatar_hd();
        if (avatar_hd != null) {
            stmt.bindString(16, avatar_hd);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WeiboUserInfo entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String screen_name = entity.getScreen_name();
        if (screen_name != null) {
            stmt.bindString(2, screen_name);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
 
        String profile_image_url = entity.getProfile_image_url();
        if (profile_image_url != null) {
            stmt.bindString(6, profile_image_url);
        }
 
        String domain = entity.getDomain();
        if (domain != null) {
            stmt.bindString(7, domain);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(8, gender);
        }
        stmt.bindLong(9, entity.getFollowers_count());
        stmt.bindLong(10, entity.getFriends_count());
        stmt.bindLong(11, entity.getStatuses_count());
        stmt.bindLong(12, entity.getFavourites_count());
 
        String created_at = entity.getCreated_at();
        if (created_at != null) {
            stmt.bindString(13, created_at);
        }
        stmt.bindLong(14, entity.getVerified() ? 1L: 0L);
 
        String avatar_large = entity.getAvatar_large();
        if (avatar_large != null) {
            stmt.bindString(15, avatar_large);
        }
 
        String avatar_hd = entity.getAvatar_hd();
        if (avatar_hd != null) {
            stmt.bindString(16, avatar_hd);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public WeiboUserInfo readEntity(Cursor cursor, int offset) {
        WeiboUserInfo entity = new WeiboUserInfo( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // screen_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // description
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // url
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // profile_image_url
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // domain
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // gender
            cursor.getInt(offset + 8), // followers_count
            cursor.getInt(offset + 9), // friends_count
            cursor.getInt(offset + 10), // statuses_count
            cursor.getInt(offset + 11), // favourites_count
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // created_at
            cursor.getShort(offset + 13) != 0, // verified
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // avatar_large
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // avatar_hd
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WeiboUserInfo entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setScreen_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDescription(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProfile_image_url(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDomain(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGender(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFollowers_count(cursor.getInt(offset + 8));
        entity.setFriends_count(cursor.getInt(offset + 9));
        entity.setStatuses_count(cursor.getInt(offset + 10));
        entity.setFavourites_count(cursor.getInt(offset + 11));
        entity.setCreated_at(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setVerified(cursor.getShort(offset + 13) != 0);
        entity.setAvatar_large(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setAvatar_hd(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(WeiboUserInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(WeiboUserInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(WeiboUserInfo entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
