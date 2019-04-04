package com.dennis.dao.entity;

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
        sb.append(", userId=").append(userId);
        sb.append(", msgId=").append(msgId);
        sb.append("]");
        return sb.toString();
    }
}