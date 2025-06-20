package com.entity.vo;

import com.entity.SusheEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 宿舍
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("sushe")
public class SusheVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 宿舍名称
     */

    @TableField(value = "sushe_name")
    private String susheName;


    /**
     * 宿舍编号
     */

    @TableField(value = "sushe_uuid_number")
    private String susheUuidNumber;


    /**
     * 宿舍照片
     */

    @TableField(value = "sushe_photo")
    private String sushePhoto;


    /**
     * 宿舍位置
     */

    @TableField(value = "sushe_address")
    private String susheAddress;


    /**
     * 楼层
     */

    @TableField(value = "louceng_types")
    private Integer loucengTypes;


    /**
     * 楼宇
     */

    @TableField(value = "danyuan_types")
    private Integer danyuanTypes;


    /**
     * 宿舍类型
     */

    @TableField(value = "sushe_types")
    private Integer susheTypes;


    /**
     * 宿舍热度
     */

    @TableField(value = "sushe_clicknum")
    private Integer susheClicknum;


    /**
     * 宿舍备注
     */

    @TableField(value = "sushe_content")
    private String susheContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "sushe_delete")
    private Integer susheDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：宿舍名称
	 */
    public String getSusheName() {
        return susheName;
    }


    /**
	 * 获取：宿舍名称
	 */

    public void setSusheName(String susheName) {
        this.susheName = susheName;
    }
    /**
	 * 设置：宿舍编号
	 */
    public String getSusheUuidNumber() {
        return susheUuidNumber;
    }


    /**
	 * 获取：宿舍编号
	 */

    public void setSusheUuidNumber(String susheUuidNumber) {
        this.susheUuidNumber = susheUuidNumber;
    }
    /**
	 * 设置：宿舍照片
	 */
    public String getSushePhoto() {
        return sushePhoto;
    }


    /**
	 * 获取：宿舍照片
	 */

    public void setSushePhoto(String sushePhoto) {
        this.sushePhoto = sushePhoto;
    }
    /**
	 * 设置：宿舍位置
	 */
    public String getSusheAddress() {
        return susheAddress;
    }


    /**
	 * 获取：宿舍位置
	 */

    public void setSusheAddress(String susheAddress) {
        this.susheAddress = susheAddress;
    }
    /**
	 * 设置：楼层
	 */
    public Integer getLoucengTypes() {
        return loucengTypes;
    }


    /**
	 * 获取：楼层
	 */

    public void setLoucengTypes(Integer loucengTypes) {
        this.loucengTypes = loucengTypes;
    }
    /**
	 * 设置：楼宇
	 */
    public Integer getDanyuanTypes() {
        return danyuanTypes;
    }


    /**
	 * 获取：楼宇
	 */

    public void setDanyuanTypes(Integer danyuanTypes) {
        this.danyuanTypes = danyuanTypes;
    }
    /**
	 * 设置：宿舍类型
	 */
    public Integer getSusheTypes() {
        return susheTypes;
    }


    /**
	 * 获取：宿舍类型
	 */

    public void setSusheTypes(Integer susheTypes) {
        this.susheTypes = susheTypes;
    }
    /**
	 * 设置：宿舍热度
	 */
    public Integer getSusheClicknum() {
        return susheClicknum;
    }


    /**
	 * 获取：宿舍热度
	 */

    public void setSusheClicknum(Integer susheClicknum) {
        this.susheClicknum = susheClicknum;
    }
    /**
	 * 设置：宿舍备注
	 */
    public String getSusheContent() {
        return susheContent;
    }


    /**
	 * 获取：宿舍备注
	 */

    public void setSusheContent(String susheContent) {
        this.susheContent = susheContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getSusheDelete() {
        return susheDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setSusheDelete(Integer susheDelete) {
        this.susheDelete = susheDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
