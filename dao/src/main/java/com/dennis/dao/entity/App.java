package com.dennis.dao.entity;

import java.util.Date;

public class App {
    /**
     * 应用主键ID pk_id
     */
    private Integer pkId;

    /**
     * 服务器ID server_id
     */
    private Integer serverId;

    /**
     * 应用名称 nickname
     */
    private String nickname;

    /**
     * 监控文件路径 path
     */
    private String path;

    /**
     * 正则表达式 regex
     */
    private String regex;

    /**
     * 是否监控 0：否 1：是 is_monitor
     */
    private Integer isMonitor;

    /**
     * 是否删除 0：否 1：是 is_delete
     */
    private Integer isDelete;

    /**
     *   创建时间 create_time
     */
    private Date createTime;

    /**
     * 应用主键ID
     * @author Dennis
     * @return pk_id 应用主键ID
     */
    public Integer getPkId() {
        return pkId;
    }

    /**
     * 应用主键ID
     * @author Dennis
     * @param pkId 应用主键ID
     */
    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    /**
     * 服务器ID
     * @author Dennis
     * @return server_id 服务器ID
     */
    public Integer getServerId() {
        return serverId;
    }

    /**
     * 服务器ID
     * @author Dennis
     * @param serverId 服务器ID
     */
    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    /**
     * 应用名称
     * @author Dennis
     * @return nickname 应用名称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 应用名称
     * @author Dennis
     * @param nickname 应用名称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 监控文件路径
     * @author Dennis
     * @return path 监控文件路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 监控文件路径
     * @author Dennis
     * @param path 监控文件路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 正则表达式
     * @author Dennis
     * @return regex 正则表达式
     */
    public String getRegex() {
        return regex;
    }

    /**
     * 正则表达式
     * @author Dennis
     * @param regex 正则表达式
     */
    public void setRegex(String regex) {
        this.regex = regex == null ? null : regex.trim();
    }

    /**
     * 是否监控 0：否 1：是
     * @author Dennis
     * @return is_monitor 是否监控 0：否 1：是
     */
    public Integer getIsMonitor() {
        return isMonitor;
    }

    /**
     * 是否监控 0：否 1：是
     * @author Dennis
     * @param isMonitor 是否监控 0：否 1：是
     */
    public void setIsMonitor(Integer isMonitor) {
        this.isMonitor = isMonitor;
    }

    /**
     * 是否删除 0：否 1：是
     * @author Dennis
     * @return is_delete 是否删除 0：否 1：是
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除 0：否 1：是
     * @author Dennis
     * @param isDelete 是否删除 0：否 1：是
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     *   创建时间
     * @author Dennis
     * @return create_time   创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *   创建时间
     * @author Dennis
     * @param createTime   创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", serverId=").append(serverId);
        sb.append(", nickname=").append(nickname);
        sb.append(", path=").append(path);
        sb.append(", regex=").append(regex);
        sb.append(", isMonitor=").append(isMonitor);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}