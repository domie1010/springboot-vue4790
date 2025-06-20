package com.entity.model;

import com.entity.SusheTuisuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 退宿申请
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SusheTuisuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 申请编号
     */
    private String susheTuisuUuidNumber;


    /**
     * 宿舍
     */
    private Integer susheId;


    /**
     * 学生
     */
    private Integer xueshengId;


    /**
     * 申请缘由
     */
    private String susheTuisuText;


    /**
     * 退宿申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 申请状态
     */
    private Integer susheTuisuYesnoTypes;


    /**
     * 审核回复
     */
    private String susheTuisuYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date susheTuisuShenheTime;


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
    public String getSusheTuisuUuidNumber() {
        return susheTuisuUuidNumber;
    }


    /**
	 * 设置：申请编号
	 */
    public void setSusheTuisuUuidNumber(String susheTuisuUuidNumber) {
        this.susheTuisuUuidNumber = susheTuisuUuidNumber;
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
	 * 获取：申请缘由
	 */
    public String getSusheTuisuText() {
        return susheTuisuText;
    }


    /**
	 * 设置：申请缘由
	 */
    public void setSusheTuisuText(String susheTuisuText) {
        this.susheTuisuText = susheTuisuText;
    }
    /**
	 * 获取：退宿申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：退宿申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getSusheTuisuYesnoTypes() {
        return susheTuisuYesnoTypes;
    }


    /**
	 * 设置：申请状态
	 */
    public void setSusheTuisuYesnoTypes(Integer susheTuisuYesnoTypes) {
        this.susheTuisuYesnoTypes = susheTuisuYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getSusheTuisuYesnoText() {
        return susheTuisuYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setSusheTuisuYesnoText(String susheTuisuYesnoText) {
        this.susheTuisuYesnoText = susheTuisuYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getSusheTuisuShenheTime() {
        return susheTuisuShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setSusheTuisuShenheTime(Date susheTuisuShenheTime) {
        this.susheTuisuShenheTime = susheTuisuShenheTime;
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
