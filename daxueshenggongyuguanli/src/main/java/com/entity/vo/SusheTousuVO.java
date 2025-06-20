package com.entity.vo;

import com.entity.SusheTousuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 宿舍投诉
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("sushe_tousu")
public class SusheTousuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 投诉编号
     */

    @TableField(value = "sushe_tousu_uuid_number")
    private String susheTousuUuidNumber;


    /**
     * 学生
     */

    @TableField(value = "xuesheng_id")
    private Integer xueshengId;


    /**
     * 宿舍
     */

    @TableField(value = "sushe_id")
    private Integer susheId;


    /**
     * 投诉事由
     */

    @TableField(value = "sushe_tousu_text")
    private String susheTousuText;


    /**
     * 投诉时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 投诉类型
     */

    @TableField(value = "sushe_tousu_types")
    private Integer susheTousuTypes;


    /**
     * 投诉状态
     */

    @TableField(value = "sushe_tousu_zhuangtai_types")
    private Integer susheTousuZhuangtaiTypes;


    /**
     * 投诉回复
     */

    @TableField(value = "xuesheng_kaoqin_content")
    private String xueshengKaoqinContent;


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
	 * 设置：投诉编号
	 */
    public String getSusheTousuUuidNumber() {
        return susheTousuUuidNumber;
    }


    /**
	 * 获取：投诉编号
	 */

    public void setSusheTousuUuidNumber(String susheTousuUuidNumber) {
        this.susheTousuUuidNumber = susheTousuUuidNumber;
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
	 * 设置：投诉事由
	 */
    public String getSusheTousuText() {
        return susheTousuText;
    }


    /**
	 * 获取：投诉事由
	 */

    public void setSusheTousuText(String susheTousuText) {
        this.susheTousuText = susheTousuText;
    }
    /**
	 * 设置：投诉时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：投诉时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：投诉类型
	 */
    public Integer getSusheTousuTypes() {
        return susheTousuTypes;
    }


    /**
	 * 获取：投诉类型
	 */

    public void setSusheTousuTypes(Integer susheTousuTypes) {
        this.susheTousuTypes = susheTousuTypes;
    }
    /**
	 * 设置：投诉状态
	 */
    public Integer getSusheTousuZhuangtaiTypes() {
        return susheTousuZhuangtaiTypes;
    }


    /**
	 * 获取：投诉状态
	 */

    public void setSusheTousuZhuangtaiTypes(Integer susheTousuZhuangtaiTypes) {
        this.susheTousuZhuangtaiTypes = susheTousuZhuangtaiTypes;
    }
    /**
	 * 设置：投诉回复
	 */
    public String getXueshengKaoqinContent() {
        return xueshengKaoqinContent;
    }


    /**
	 * 获取：投诉回复
	 */

    public void setXueshengKaoqinContent(String xueshengKaoqinContent) {
        this.xueshengKaoqinContent = xueshengKaoqinContent;
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
