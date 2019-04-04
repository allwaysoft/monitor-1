package com.dennis.dao.entity;

import java.util.Date;

public class EmailRecord {
    /**
     * 主键ID pk_id
     */
    private Integer pkId;

    /**
     * 目标邮箱 email
     */
    private String email;

    /**
     * 目标用户ID target
     */
    private Integer target;

    /**
     * 发送时间 send_time
     */
    private Date sendTime;

    /**
     * 邮件内容 content
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
     * 目标邮箱
     * @author Dennis
     * @return email 目标邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 目标邮箱
     * @author Dennis
     * @param email 目标邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 目标用户ID
     * @author Dennis
     * @return target 目标用户ID
     */
    public Integer getTarget() {
        return target;
    }

    /**
     * 目标用户ID
     * @author Dennis
     * @param target 目标用户ID
     */
    public void setTarget(Integer target) {
        this.target = target;
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
     * 邮件内容
     * @author Dennis
     * @return content 邮件内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 邮件内容
     * @author Dennis
     * @param content 邮件内容
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
        sb.append(", email=").append(email);
        sb.append(", target=").append(target);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}