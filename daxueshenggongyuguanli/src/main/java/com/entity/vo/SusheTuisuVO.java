package com.entity.vo;

import com.entity.SusheTuisuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 退宿申请
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("sushe_tuisu")
public class SusheTuisuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 申请编号
     */

    @TableField(value = "sushe_tuisu_uuid_number")
    private String susheTuisuUuidNumber;


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
     * 申请缘由
     */

    @TableField(value = "sushe_tuisu_text")
    private String susheTuisuText;


    /**
     * 退宿申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 申请状态
     */

    @TableField(value = "sushe_tuisu_yesno_types")
    private Integer susheTuisuYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "sushe_tuisu_yesno_text")
    private String susheTuisuYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "sushe_tuisu_shenhe_time")
    private Date susheTuisuShenheTime;


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
    public String getSusheTuisuUuidNumber() {
        return susheTuisuUuidNumber;
    }


    /**
	 * 获取：申请编号
	 */

    public void setSusheTuisuUuidNumber(String susheTuisuUuidNumber) {
        this.susheTuisuUuidNumber = susheTuisuUuidNumber;
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
	 * 设置：申请缘由
	 */
    public String getSusheTuisuText() {
        return susheTuisuText;
    }


    /**
	 * 获取：申请缘由
	 */

    public void setSusheTuisuText(String susheTuisuText) {
        this.susheTuisuText = susheTuisuText;
    }
    /**
	 * 设置：退宿申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：退宿申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：申请状态
	 */
    public Integer getSusheTuisuYesnoTypes() {
        return susheTuisuYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setSusheTuisuYesnoTypes(Integer susheTuisuYesnoTypes) {
        this.susheTuisuYesnoTypes = susheTuisuYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getSusheTuisuYesnoText() {
        return susheTuisuYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setSusheTuisuYesnoText(String susheTuisuYesnoText) {
        this.susheTuisuYesnoText = susheTuisuYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getSusheTuisuShenheTime() {
        return susheTuisuShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setSusheTuisuShenheTime(Date susheTuisuShenheTime) {
        this.susheTuisuShenheTime = susheTuisuShenheTime;
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
