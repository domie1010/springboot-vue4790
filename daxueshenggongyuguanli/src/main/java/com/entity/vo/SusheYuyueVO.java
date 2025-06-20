package com.entity.vo;

import com.entity.SusheYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 入住申请
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("sushe_yuyue")
public class SusheYuyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 申请编号
     */

    @TableField(value = "sushe_yuyue_uuid_number")
    private String susheYuyueUuidNumber;


    /**
     * 宿舍
     */

    @TableField(value = "sushe_id")
    private Integer susheId;


    /**
     * 学生
     */

    @TableField(value = "xuesheng_id")
    private Integer xueshengId;


    /**
     * 申请理由
     */

    @TableField(value = "sushe_yuyue_text")
    private String susheYuyueText;


    /**
     * 宿舍申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 申请状态
     */

    @TableField(value = "sushe_yuyue_yesno_types")
    private Integer susheYuyueYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "sushe_yuyue_yesno_text")
    private String susheYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "sushe_yuyue_shenhe_time")
    private Date susheYuyueShenheTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：申请编号
	 */
    public String getSusheYuyueUuidNumber() {
        return susheYuyueUuidNumber;
    }


    /**
	 * 获取：申请编号
	 */

    public void setSusheYuyueUuidNumber(String susheYuyueUuidNumber) {
        this.susheYuyueUuidNumber = susheYuyueUuidNumber;
    }
    /**
	 * 设置：宿舍
	 */
    public Integer getSusheId() {
        return susheId;
    }


    /**
	 * 获取：宿舍
	 */

    public void setSusheId(Integer susheId) {
        this.susheId = susheId;
    }
    /**
	 * 设置：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 获取：学生
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 设置：申请理由
	 */
    public String getSusheYuyueText() {
        return susheYuyueText;
    }


    /**
	 * 获取：申请理由
	 */

    public void setSusheYuyueText(String susheYuyueText) {
        this.susheYuyueText = susheYuyueText;
    }
    /**
	 * 设置：宿舍申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：宿舍申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：申请状态
	 */
    public Integer getSusheYuyueYesnoTypes() {
        return susheYuyueYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setSusheYuyueYesnoTypes(Integer susheYuyueYesnoTypes) {
        this.susheYuyueYesnoTypes = susheYuyueYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getSusheYuyueYesnoText() {
        return susheYuyueYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setSusheYuyueYesnoText(String susheYuyueYesnoText) {
        this.susheYuyueYesnoText = susheYuyueYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getSusheYuyueShenheTime() {
        return susheYuyueShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setSusheYuyueShenheTime(Date susheYuyueShenheTime) {
        this.susheYuyueShenheTime = susheYuyueShenheTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
