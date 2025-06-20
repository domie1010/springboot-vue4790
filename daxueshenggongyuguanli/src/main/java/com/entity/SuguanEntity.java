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
 * 宿管
 *
 * @author 
 * @email
 */
@TableName("suguan")
public class SuguanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public SuguanEntity() {

	}

	public SuguanEntity(T t) {
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
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 宿管姓名
     */
    @ColumnInfo(comment="宿管姓名",type="varchar(200)")
    @TableField(value = "suguan_name")

    private String suguanName;


    /**
     * 宿管手机号
     */
    @ColumnInfo(comment="宿管手机号",type="varchar(200)")
    @TableField(value = "suguan_phone")

    private String suguanPhone;


    /**
     * 宿管身份证号
     */
    @ColumnInfo(comment="宿管身份证号",type="varchar(200)")
    @TableField(value = "suguan_id_number")

    private String suguanIdNumber;


    /**
     * 宿管头像
     */
    @ColumnInfo(comment="宿管头像",type="varchar(200)")
    @TableField(value = "suguan_photo")

    private String suguanPhoto;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 宿管邮箱
     */
    @ColumnInfo(comment="宿管邮箱",type="varchar(200)")
    @TableField(value = "suguan_email")

    private String suguanEmail;


    /**
     * 创建时间
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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：宿管姓名
	 */
    public String getSuguanName() {
        return suguanName;
    }
    /**
	 * 设置：宿管姓名
	 */

    public void setSuguanName(String suguanName) {
        this.suguanName = suguanName;
    }
    /**
	 * 获取：宿管手机号
	 */
    public String getSuguanPhone() {
        return suguanPhone;
    }
    /**
	 * 设置：宿管手机号
	 */

    public void setSuguanPhone(String suguanPhone) {
        this.suguanPhone = suguanPhone;
    }
    /**
	 * 获取：宿管身份证号
	 */
    public String getSuguanIdNumber() {
        return suguanIdNumber;
    }
    /**
	 * 设置：宿管身份证号
	 */

    public void setSuguanIdNumber(String suguanIdNumber) {
        this.suguanIdNumber = suguanIdNumber;
    }
    /**
	 * 获取：宿管头像
	 */
    public String getSuguanPhoto() {
        return suguanPhoto;
    }
    /**
	 * 设置：宿管头像
	 */

    public void setSuguanPhoto(String suguanPhoto) {
        this.suguanPhoto = suguanPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 设置：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：宿管邮箱
	 */
    public String getSuguanEmail() {
        return suguanEmail;
    }
    /**
	 * 设置：宿管邮箱
	 */

    public void setSuguanEmail(String suguanEmail) {
        this.suguanEmail = suguanEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Suguan{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", suguanName=" + suguanName +
            ", suguanPhone=" + suguanPhone +
            ", suguanIdNumber=" + suguanIdNumber +
            ", suguanPhoto=" + suguanPhoto +
            ", sexTypes=" + sexTypes +
            ", suguanEmail=" + suguanEmail +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
