package com.wj.dao.master.entity;

import javax.persistence.*;

/**
 * @author jun.wang
 * @title: Authority
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/23 15:24
 */

//@Entity
//@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "authority_name")
    private String authorityName;

    @Column(name = "url")
    private String url;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "desc")
    private String desc;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
