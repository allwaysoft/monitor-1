package com.dennis.dao.entity;

import java.util.Date;

public class Message {
    /**
     * 主键ID pk_id
     */
    private Integer pkId;

    /**
     *   消息标题 title
     */
    private String title;

    /**
     * 消息类型  1：公告 2：通知 type
     */
    private Integer type;

    /**
     * 发送目标用户ID target
     */
    private Integer target;

    /**
     * 管理员主键ID sender
     */
    private Integer sender;

    /**
     * 发送时间 send_time
     */
    private Date sendTime;

    /**
     * 消息内容 content
     */
    private String content;

    /**
     * 主键ID
     * @author Dennis
     * @return pk_id 主键ID
     */
    public Integer getPkId() {
        return pkId;
    }

    /**
     * 主键ID
     * @author Dennis
     * @param pkId 主键ID
     */
    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    /**
     *   消息标题
     * @author Dennis
     * @return title   消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     *   消息标题
     * @author Dennis
     * @param title   消息标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 消息类型  1：公告 2：通知
     * @author Dennis
     * @return type 消息类型  1：公告 2：通知
     */
    public Integer getType() {
        return type;
    }

    /**
     * 消息类型  1：公告 2：通知
     * @author Dennis
     * @param type 消息类型  1：公告 2：通知
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 发送目标用户ID
     * @author Dennis
     * @return target 发送目标用户ID
     */
    public Integer getTarget() {
        return target;
    }

    /**
     * 发送目标用户ID
     * @author Dennis
     * @param target 发送目标用户ID
     */
    public void setTarget(Integer target) {
        this.target = target;
    }

    /**
     * 管理员主键ID
     * @author Dennis
     * @return sender 管理员主键ID
     */
    public Integer getSender() {
        return sender;
    }

    /**
     * 管理员主键ID
     * @author Dennis
     * @param sender 管理员主键ID
     */
    public void setSender(Integer sender) {
        this.sender = sender;
    }

    /**
     * 发送时间
     * @author Dennis
     * @return send_time 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 发送时间
     * @author Dennis
     * @param sendTime 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 消息内容
     * @author Dennis
     * @return content 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 消息内容
     * @author Dennis
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        sb.append(", title=").append(title);
        sb.append(", type=").append(type);
        sb.append(", target=").append(target);
        sb.append(", sender=").append(sender);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}