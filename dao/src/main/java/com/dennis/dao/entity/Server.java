package com.dennis.dao.entity;

import java.util.Date;

public class Server {
    /**
     * 服务器主键ID pk_id
     */
    private Integer pkId;

    /**
     * 用户ID user_id
     */
    private Integer userId;

    /**
     * IP 地址 host
     */
    private String host;

    /**
     * 用户名 username
     */
    private String username;

    /**
     * 密码 公钥加密 登录私钥解密 password
     */
    private String password;

    /**
     * 端口 port
     */
    private String port;

    /**
     * 名称 nickname
     */
    private String nickname;

    /**
     * 最后登录时间 last_login
     */
    private Date lastLogin;

    /**
     * 监控环境是否部署  0：否  1：是 is_deploy
     */
    private Integer isDeploy;

    /**
     * 是否删除 is_delete
     */
    private Integer isDelete;

    /**
     * 创建时间 create_time
     */
    private Date createTime;

    /**
     * 最后修改时间 update_time
     */
    private Date updateTime;

    /**
     * 服务器主键ID
     * @author Dennis
     * @return pk_id 服务器主键ID
     */
    public Integer getPkId() {
        return pkId;
    }

    /**
     * 服务器主键ID
     * @author Dennis
     * @param pkId 服务器主键ID
     */
    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    /**
     * 用户ID
     * @author Dennis
     * @return user_id 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @author Dennis
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * IP 地址
     * @author Dennis
     * @return host IP 地址
     */
    public String getHost() {
        return host;
    }

    /**
     * IP 地址
     * @author Dennis
     * @param host IP 地址
     */
    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    /**
     * 用户名
     * @author Dennis
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     * @author Dennis
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 密码 公钥加密 登录私钥解密
     * @author Dennis
     * @return password 密码 公钥加密 登录私钥解密
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码 公钥加密 登录私钥解密
     * @author Dennis
     * @param password 密码 公钥加密 登录私钥解密
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 端口
     * @author Dennis
     * @return port 端口
     */
    public String getPort() {
        return port;
    }

    /**
     * 端口
     * @author Dennis
     * @param port 端口
     */
    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    /**
     * 名称
     * @author Dennis
     * @return nickname 名称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 名称
     * @author Dennis
     * @param nickname 名称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
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
     * 监控环境是否部署  0：否  1：是
     * @author Dennis
     * @return is_deploy 监控环境是否部署  0：否  1：是
     */
    public Integer getIsDeploy() {
        return isDeploy;
    }

    /**
     * 监控环境是否部署  0：否  1：是
     * @author Dennis
     * @param isDeploy 监控环境是否部署  0：否  1：是
     */
    public void setIsDeploy(Integer isDeploy) {
        this.isDeploy = isDeploy;
    }

    /**
     * 是否删除
     * @author Dennis
     * @return is_delete 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除
     * @author Dennis
     * @param isDelete 是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 创建时间
     * @author Dennis
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @author Dennis
     * @param createTime 创建时间
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
     * @mbg.generated 2019-04-03 20:30:22
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", userId=").append(userId);
        sb.append(", host=").append(host);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", port=").append(port);
        sb.append(", nickname=").append(nickname);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append(", isDeploy=").append(isDeploy);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}