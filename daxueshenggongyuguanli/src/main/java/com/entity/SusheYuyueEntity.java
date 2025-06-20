package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 入住申请
 *
 * @author 
 * @email
 */
@TableName("sushe_yuyue")
public class SusheYuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public SusheYuyueEntity() {

	}

	public SusheYuyueEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 申请编号
     */
    @ColumnInfo(comment="申请编号",type="varchar(200)")
    @TableField(value = "sushe_yuyue_uuid_number")

    private String susheYuyueUuidNumber;


    /**
     * 宿舍
     */
    @ColumnInfo(comment="宿舍",type="int(11)")
    @TableField(value = "sushe_id")

    private Integer susheId;


    /**
     * 学生
     */
    @ColumnInfo(comment="学生",type="int(11)")
    @TableField(value = "xuesheng_id")

    private Integer xueshengId;


    /**
     * 申请理由
     */
    @ColumnInfo(comment="申请理由",type="text")
    @TableField(value = "sushe_yuyue_text")

    private String susheYuyueText;


    /**
     * 宿舍申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="宿舍申请时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 申请状态
     */
    @ColumnInfo(comment="申请状态",type="int(11)")
    @TableField(value = "sushe_yuyue_yesno_types")

    private Integer susheYuyueYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="text")
    @TableField(value = "sushe_yuyue_yesno_text")

    private String susheYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "sushe_yuyue_shenhe_time")

    private Date susheYuyueShenheTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SusheYuyue{" +
            ", id=" + id +
            ", susheYuyueUuidNumber=" + susheYuyueUuidNumber +
            ", susheId=" + susheId +
            ", xueshengId=" + xueshengId +
            ", susheYuyueText=" + susheYuyueText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", susheYuyueYesnoTypes=" + susheYuyueYesnoTypes +
            ", susheYuyueYesnoText=" + susheYuyueYesnoText +
            ", susheYuyueShenheTime=" + DateUtil.convertString(susheYuyueShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
