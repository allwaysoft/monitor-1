package com.dennis.dao.entity;

import java.util.Date;

public class User {
    /**
     * 用户主键ID pk_id
     */
    private Integer pkId;

    /**
     * 账户 手机 account
     */
    private String account;

    /**
     * 密码 password
     */
    private String password;

    /**
     * 盐值 salt
     */
    private String salt;

    /**
     * 头像 avatar
     */
    private String avatar;

    /**
     * 昵称 nickname
     */
    private String nickname;

    /**
     * 邮箱 email
     */
    private String email;

    /**
     * 是否开启异常通知到邮箱   0：否 1：是 is_email
     */
    private Integer isEmail;

    /**
     * 是否启用  0：是 1：否 is_enable
     */
    private Integer isEnable;

    /**
     * 是否删除  0：否 1：是 is_delete
     */
    private Integer isDelete;

    /**
     * 最后登录时间 last_login
     */
    private Date lastLogin;

    /**
     * 注册时间 create_time
     */
    private Date createTime;

    /**
     * 最后修改时间 update_time
     */
    private Date updateTime;

    /**
     * 用户主键ID
     * @author Dennis
     * @return pk_id 用户主键ID
     */
    public Integer getPkId() {
        return pkId;
    }

    /**
     * 用户主键ID
     * @author Dennis
     * @param pkId 用户主键ID
     */
    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    /**
     * 账户 手机
     * @author Dennis
     * @return account 账户 手机
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账户 手机
     * @author Dennis
     * @param account 账户 手机
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 密码
     * @author Dennis
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @author Dennis
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 盐值
     * @author Dennis
     * @return salt 盐值
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐值
     * @author Dennis
     * @param salt 盐值
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 头像
     * @author Dennis
     * @return avatar 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像
     * @author Dennis
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 昵称
     * @author Dennis
     * @return nickname 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     * @author Dennis
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 邮箱
     * @author Dennis
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @author Dennis
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 是否开启异常通知到邮箱   0：否 1：是
     * @author Dennis
     * @return is_email 是否开启异常通知到邮箱   0：否 1：是
     */
    public Integer getIsEmail() {
        return isEmail;
    }

    /**
     * 是否开启异常通知到邮箱   0：否 1：是
     * @author Dennis
     * @param isEmail 是否开启异常通知到邮箱   0：否 1：是
     */
    public void setIsEmail(Integer isEmail) {
        this.isEmail = isEmail;
    }

    /**
     * 是否启用  0：是 1：否
     * @author Dennis
     * @return is_enable 是否启用  0：是 1：否
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * 是否启用  0：是 1：否
     * @author Dennis
     * @param isEnable 是否启用  0：是 1：否
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 是否删除  0：否 1：是
     * @author Dennis
     * @return is_delete 是否删除  0：否 1：是
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除  0：否 1：是
     * @author Dennis
     * @param isDelete 是否删除  0：否 1：是
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 最后登录时间
     * @author Dennis
     * @return last_login 最后登录时间
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * 最后登录时间
     * @author Dennis
     * @param lastLogin 最后登录时间
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * 注册时间
     * @author Dennis
     * @return create_time 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 注册时间
     * @author Dennis
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改时间
     * @author Dennis
     * @return update_time 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 最后修改时间
     * @author Dennis
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated 2019-04-26 18:01:32
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", avatar=").append(avatar);
        sb.append(", nickname=").append(nickname);
        sb.append(", email=").append(email);
        sb.append(", isEmail=").append(isEmail);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}