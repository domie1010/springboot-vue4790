package com.entity.model;

import com.entity.SusheYuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 入住申请
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SusheYuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 申请编号
     */
    private String susheYuyueUuidNumber;


    /**
     * 宿舍
     */
    private Integer susheId;


    /**
     * 学生
     */
    private Integer xueshengId;


    /**
     * 申请理由
     */
    private String susheYuyueText;


    /**
     * 宿舍申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 申请状态
     */
    private Integer susheYuyueYesnoTypes;


    /**
     * 审核回复
     */
    private String susheYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date susheYuyueShenheTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：申请编号
	 */
    public String getSusheYuyueUuidNumber() {
        return susheYuyueUuidNumber;
    }


    /**
	 * 设置：申请编号
	 */
    public void setSusheYuyueUuidNumber(String susheYuyueUuidNumber) {
        this.susheYuyueUuidNumber = susheYuyueUuidNumber;
    }
    /**
	 * 获取：宿舍
	 */
    public Integer getSusheId() {
        return susheId;
    }


    /**
	 * 设置：宿舍
	 */
    public void setSusheId(Integer susheId) {
        this.susheId = susheId;
    }
    /**
	 * 获取：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 设置：学生
	 */
    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 获取：申请理由
	 */
    public String getSusheYuyueText() {
        return susheYuyueText;
    }


    /**
	 * 设置：申请理由
	 */
    public void setSusheYuyueText(String susheYuyueText) {
        this.susheYuyueText = susheYuyueText;
    }
    /**
	 * 获取：宿舍申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：宿舍申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getSusheYuyueYesnoTypes() {
        return susheYuyueYesnoTypes;
    }


    /**
	 * 设置：申请状态
	 */
    public void setSusheYuyueYesnoTypes(Integer susheYuyueYesnoTypes) {
        this.susheYuyueYesnoTypes = susheYuyueYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getSusheYuyueYesnoText() {
        return susheYuyueYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setSusheYuyueYesnoText(String susheYuyueYesnoText) {
        this.susheYuyueYesnoText = susheYuyueYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getSusheYuyueShenheTime() {
        return susheYuyueShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setSusheYuyueShenheTime(Date susheYuyueShenheTime) {
        this.susheYuyueShenheTime = susheYuyueShenheTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
