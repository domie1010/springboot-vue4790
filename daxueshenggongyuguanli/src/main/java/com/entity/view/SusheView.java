package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.SusheEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 宿舍
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("sushe")
public class SusheView extends SusheEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 楼层的值
	*/
	@ColumnInfo(comment="楼层的字典表值",type="varchar(200)")
	private String loucengValue;
	/**
	* 楼宇的值
	*/
	@ColumnInfo(comment="楼宇的字典表值",type="varchar(200)")
	private String danyuanValue;
	/**
	* 宿舍类型的值
	*/
	@ColumnInfo(comment="宿舍类型的字典表值",type="varchar(200)")
	private String susheValue;




	public SusheView() {

	}

	public SusheView(SusheEntity susheEntity) {
		try {
			BeanUtils.copyProperties(this, susheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 楼层的值
	*/
	public String getLoucengValue() {
		return loucengValue;
	}
	/**
	* 设置： 楼层的值
	*/
	public void setLoucengValue(String loucengValue) {
		this.loucengValue = loucengValue;
	}
	//当前表的
	/**
	* 获取： 楼宇的值
	*/
	public String getDanyuanValue() {
		return danyuanValue;
	}
	/**
	* 设置： 楼宇的值
	*/
	public void setDanyuanValue(String danyuanValue) {
		this.danyuanValue = danyuanValue;
	}
	//当前表的
	/**
	* 获取： 宿舍类型的值
	*/
	public String getSusheValue() {
		return susheValue;
	}
	/**
	* 设置： 宿舍类型的值
	*/
	public void setSusheValue(String susheValue) {
		this.susheValue = susheValue;
	}




	@Override
	public String toString() {
		return "SusheView{" +
			", loucengValue=" + loucengValue +
			", danyuanValue=" + danyuanValue +
			", susheValue=" + susheValue +
			"} " + super.toString();
	}
}
