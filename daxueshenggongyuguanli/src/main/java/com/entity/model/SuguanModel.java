package com.entity.model;

import com.entity.SuguanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 宿管
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SuguanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 宿管姓名
     */
    private String suguanName;


    /**
     * 宿管手机号
     */
    private String suguanPhone;


    /**
     * 宿管身份证号
     */
    private String suguanIdNumber;


    /**
     * 宿管头像
     */
    private String suguanPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 宿管邮箱
     */
    private String suguanEmail;


    /**
     * 创建时间
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

    }
