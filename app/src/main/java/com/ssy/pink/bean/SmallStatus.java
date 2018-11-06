package com.ssy.pink.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.Objects;

import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ssy
 * @date 2018/11/6
 */
@Entity
public class SmallStatus implements Serializable {
    private static final long serialVersionUID = -3919580145263785793L;
    @Id
    @Unique
    String uid;
    boolean normal;

    @Generated(hash = 30960280)
    public SmallStatus(String uid, boolean normal) {
        this.uid = uid;
        this.normal = normal;
    }

    @Generated(hash = 282576332)
    public SmallStatus() {
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean getNormal() {
        return this.normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmallStatus)) return false;
        SmallStatus that = (SmallStatus) o;
        return Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "SmallStatus{" +
                "uid='" + uid + '\'' +
                ", normal=" + normal +
                '}';
    }
}
