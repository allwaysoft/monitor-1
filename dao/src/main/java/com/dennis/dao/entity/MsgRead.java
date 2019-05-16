package com.dennis.dao.entity;

import java.util.Date;

public class MsgRead {
    /**
     * 主键 pk_id
     */
    private Integer pkId;

    /**
     * 用户ID user_id
     */
    private Integer userId;

    /**
     * 消息ID msg_id
     */
    private Integer msgId;

    /**
     * 阅读时间 read_time
     */
    private Date readTime;

    /**
     * 主键
     * @author Dennis
     * @return pk_id 主键
     */
    public Integer getPkId() {
        return pkId;
    }

    /**
     * 主键
     * @author Dennis
     * @param pkId 主键
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
     * 消息ID
     * @author Dennis
     * @return msg_id 消息ID
     */
    public Integer getMsgId() {
        return msgId;
    }

    /**
     * 消息ID
     * @author Dennis
     * @param msgId 消息ID
     */
    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    /**
     * 阅读时间
     * @author Dennis
     * @return read_time 阅读时间
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * 阅读时间
     * @author Dennis
     * @param readTime 阅读时间
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", userId=").append(userId);
        sb.append(", msgId=").append(msgId);
        sb.append(", readTime=").append(readTime);
        sb.append("]");
        return sb.toString();
    }
}