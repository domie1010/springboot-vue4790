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
 * 宿舍投诉
 *
 * @author 
 * @email
 */
@TableName("sushe_tousu")
public class SusheTousuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public SusheTousuEntity() {

	}

	public SusheTousuEntity(T t) {
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
     * 投诉编号
     */
    @ColumnInfo(comment="投诉编号",type="varchar(200)")
    @TableField(value = "sushe_tousu_uuid_number")

    private String susheTousuUuidNumber;


    /**
     * 学生
     */
    @ColumnInfo(comment="学生",type="int(11)")
    @TableField(value = "xuesheng_id")

    private Integer xueshengId;


    /**
     * 宿舍
     */
    @ColumnInfo(comment="宿舍",type="int(11)")
    @TableField(value = "sushe_id")

    private Integer susheId;


    /**
     * 投诉事由
     */
    @ColumnInfo(comment="投诉事由",type="text")
    @TableField(value = "sushe_tousu_text")

    private String susheTousuText;


    /**
     * 投诉时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="投诉时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 投诉类型
     */
    @ColumnInfo(comment="投诉类型",type="int(11)")
    @TableField(value = "sushe_tousu_types")

    private Integer susheTousuTypes;


    /**
     * 投诉状态
     */
    @ColumnInfo(comment="投诉状态",type="int(11)")
    @TableField(value = "sushe_tousu_zhuangtai_types")

    private Integer susheTousuZhuangtaiTypes;


    /**
     * 投诉回复
     */
    @ColumnInfo(comment="投诉回复",type="text")
    @TableField(value = "xuesheng_kaoqin_content")

    private String xueshengKaoqinContent;


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
	 * 获取：投诉编号
	 */
    public String getSusheTousuUuidNumber() {
        return susheTousuUuidNumber;
    }
    /**
	 * 设置：投诉编号
	 */

    public void setSusheTousuUuidNumber(String susheTousuUuidNumber) {
        this.susheTousuUuidNumber = susheTousuUuidNumber;
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
	 * 获取：投诉事由
	 */
    public String getSusheTousuText() {
        return susheTousuText;
    }
    /**
	 * 设置：投诉事由
	 */

    public void setSusheTousuText(String susheTousuText) {
        this.susheTousuText = susheTousuText;
    }
    /**
	 * 获取：投诉时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：投诉时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：投诉类型
	 */
    public Integer getSusheTousuTypes() {
        return susheTousuTypes;
    }
    /**
	 * 设置：投诉类型
	 */

    public void setSusheTousuTypes(Integer susheTousuTypes) {
        this.susheTousuTypes = susheTousuTypes;
    }
    /**
	 * 获取：投诉状态
	 */
    public Integer getSusheTousuZhuangtaiTypes() {
        return susheTousuZhuangtaiTypes;
    }
    /**
	 * 设置：投诉状态
	 */

    public void setSusheTousuZhuangtaiTypes(Integer susheTousuZhuangtaiTypes) {
        this.susheTousuZhuangtaiTypes = susheTousuZhuangtaiTypes;
    }
    /**
	 * 获取：投诉回复
	 */
    public String getXueshengKaoqinContent() {
        return xueshengKaoqinContent;
    }
    /**
	 * 设置：投诉回复
	 */

    public void setXueshengKaoqinContent(String xueshengKaoqinContent) {
        this.xueshengKaoqinContent = xueshengKaoqinContent;
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
        return "SusheTousu{" +
            ", id=" + id +
            ", susheTousuUuidNumber=" + susheTousuUuidNumber +
            ", xueshengId=" + xueshengId +
            ", susheId=" + susheId +
            ", susheTousuText=" + susheTousuText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", susheTousuTypes=" + susheTousuTypes +
            ", susheTousuZhuangtaiTypes=" + susheTousuZhuangtaiTypes +
            ", xueshengKaoqinContent=" + xueshengKaoqinContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
