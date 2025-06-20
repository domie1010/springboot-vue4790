package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.SusheTousuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 宿舍投诉
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("sushe_tousu")
public class SusheTousuView extends SusheTousuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 投诉类型的值
	*/
	@ColumnInfo(comment="投诉类型的字典表值",type="varchar(200)")
	private String susheTousuValue;
	/**
	* 投诉状态的值
	*/
	@ColumnInfo(comment="投诉状态的字典表值",type="varchar(200)")
	private String susheTousuZhuangtaiValue;

	//级联表 宿舍
		/**
		* 宿舍名称
		*/

		@ColumnInfo(comment="宿舍名称",type="varchar(200)")
		private String susheName;
		/**
		* 宿舍编号
		*/

		@ColumnInfo(comment="宿舍编号",type="varchar(200)")
		private String susheUuidNumber;
		/**
		* 宿舍照片
		*/

		@ColumnInfo(comment="宿舍照片",type="varchar(200)")
		private String sushePhoto;
		/**
		* 宿舍位置
		*/

		@ColumnInfo(comment="宿舍位置",type="varchar(200)")
		private String susheAddress;
		/**
		* 楼层
		*/
		@ColumnInfo(comment="楼层",type="int(11)")
		private Integer loucengTypes;
			/**
			* 楼层的值
			*/
			@ColumnInfo(comment="楼层的字典表值",type="varchar(200)")
			private String loucengValue;
		/**
		* 楼宇
		*/
		@ColumnInfo(comment="楼宇",type="int(11)")
		private Integer danyuanTypes;
			/**
			* 楼宇的值
			*/
			@ColumnInfo(comment="楼宇的字典表值",type="varchar(200)")
			private String danyuanValue;
		/**
		* 宿舍类型
		*/
		@ColumnInfo(comment="宿舍类型",type="int(11)")
		private Integer susheTypes;
			/**
			* 宿舍类型的值
			*/
			@ColumnInfo(comment="宿舍类型的字典表值",type="varchar(200)")
			private String susheValue;
		/**
		* 宿舍热度
		*/

		@ColumnInfo(comment="宿舍热度",type="int(11)")
		private Integer susheClicknum;
		/**
		* 宿舍备注
		*/

		@ColumnInfo(comment="宿舍备注",type="text")
		private String susheContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer susheDelete;
	//级联表 学生
		/**
		* 学生姓名
		*/

		@ColumnInfo(comment="学生姓名",type="varchar(200)")
		private String xueshengName;
		/**
		* 学生手机号
		*/

		@ColumnInfo(comment="学生手机号",type="varchar(200)")
		private String xueshengPhone;
		/**
		* 学生身份证号
		*/

		@ColumnInfo(comment="学生身份证号",type="varchar(200)")
		private String xueshengIdNumber;
		/**
		* 学生头像
		*/

		@ColumnInfo(comment="学生头像",type="varchar(200)")
		private String xueshengPhoto;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 学生邮箱
		*/

		@ColumnInfo(comment="学生邮箱",type="varchar(200)")
		private String xueshengEmail;



	public SusheTousuView() {

	}

	public SusheTousuView(SusheTousuEntity susheTousuEntity) {
		try {
			BeanUtils.copyProperties(this, susheTousuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 投诉类型的值
	*/
	public String getSusheTousuValue() {
		return susheTousuValue;
	}
	/**
	* 设置： 投诉类型的值
	*/
	public void setSusheTousuValue(String susheTousuValue) {
		this.susheTousuValue = susheTousuValue;
	}
	//当前表的
	/**
	* 获取： 投诉状态的值
	*/
	public String getSusheTousuZhuangtaiValue() {
		return susheTousuZhuangtaiValue;
	}
	/**
	* 设置： 投诉状态的值
	*/
	public void setSusheTousuZhuangtaiValue(String susheTousuZhuangtaiValue) {
		this.susheTousuZhuangtaiValue = susheTousuZhuangtaiValue;
	}


	//级联表的get和set 宿舍

		/**
		* 获取： 宿舍名称
		*/
		public String getSusheName() {
			return susheName;
		}
		/**
		* 设置： 宿舍名称
		*/
		public void setSusheName(String susheName) {
			this.susheName = susheName;
		}

		/**
		* 获取： 宿舍编号
		*/
		public String getSusheUuidNumber() {
			return susheUuidNumber;
		}
		/**
		* 设置： 宿舍编号
		*/
		public void setSusheUuidNumber(String susheUuidNumber) {
			this.susheUuidNumber = susheUuidNumber;
		}

		/**
		* 获取： 宿舍照片
		*/
		public String getSushePhoto() {
			return sushePhoto;
		}
		/**
		* 设置： 宿舍照片
		*/
		public void setSushePhoto(String sushePhoto) {
			this.sushePhoto = sushePhoto;
		}

		/**
		* 获取： 宿舍位置
		*/
		public String getSusheAddress() {
			return susheAddress;
		}
		/**
		* 设置： 宿舍位置
		*/
		public void setSusheAddress(String susheAddress) {
			this.susheAddress = susheAddress;
		}
		/**
		* 获取： 楼层
		*/
		public Integer getLoucengTypes() {
			return loucengTypes;
		}
		/**
		* 设置： 楼层
		*/
		public void setLoucengTypes(Integer loucengTypes) {
			this.loucengTypes = loucengTypes;
		}


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
		/**
		* 获取： 楼宇
		*/
		public Integer getDanyuanTypes() {
			return danyuanTypes;
		}
		/**
		* 设置： 楼宇
		*/
		public void setDanyuanTypes(Integer danyuanTypes) {
			this.danyuanTypes = danyuanTypes;
		}


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
		/**
		* 获取： 宿舍类型
		*/
		public Integer getSusheTypes() {
			return susheTypes;
		}
		/**
		* 设置： 宿舍类型
		*/
		public void setSusheTypes(Integer susheTypes) {
			this.susheTypes = susheTypes;
		}


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

		/**
		* 获取： 宿舍热度
		*/
		public Integer getSusheClicknum() {
			return susheClicknum;
		}
		/**
		* 设置： 宿舍热度
		*/
		public void setSusheClicknum(Integer susheClicknum) {
			this.susheClicknum = susheClicknum;
		}

		/**
		* 获取： 宿舍备注
		*/
		public String getSusheContent() {
			return susheContent;
		}
		/**
		* 设置： 宿舍备注
		*/
		public void setSusheContent(String susheContent) {
			this.susheContent = susheContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getSusheDelete() {
			return susheDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setSusheDelete(Integer susheDelete) {
			this.susheDelete = susheDelete;
		}
	//级联表的get和set 学生

		/**
		* 获取： 学生姓名
		*/
		public String getXueshengName() {
			return xueshengName;
		}
		/**
		* 设置： 学生姓名
		*/
		public void setXueshengName(String xueshengName) {
			this.xueshengName = xueshengName;
		}

		/**
		* 获取： 学生手机号
		*/
		public String getXueshengPhone() {
			return xueshengPhone;
		}
		/**
		* 设置： 学生手机号
		*/
		public void setXueshengPhone(String xueshengPhone) {
			this.xueshengPhone = xueshengPhone;
		}

		/**
		* 获取： 学生身份证号
		*/
		public String getXueshengIdNumber() {
			return xueshengIdNumber;
		}
		/**
		* 设置： 学生身份证号
		*/
		public void setXueshengIdNumber(String xueshengIdNumber) {
			this.xueshengIdNumber = xueshengIdNumber;
		}

		/**
		* 获取： 学生头像
		*/
		public String getXueshengPhoto() {
			return xueshengPhoto;
		}
		/**
		* 设置： 学生头像
		*/
		public void setXueshengPhoto(String xueshengPhoto) {
			this.xueshengPhoto = xueshengPhoto;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 学生邮箱
		*/
		public String getXueshengEmail() {
			return xueshengEmail;
		}
		/**
		* 设置： 学生邮箱
		*/
		public void setXueshengEmail(String xueshengEmail) {
			this.xueshengEmail = xueshengEmail;
		}


	@Override
	public String toString() {
		return "SusheTousuView{" +
			", susheTousuValue=" + susheTousuValue +
			", susheTousuZhuangtaiValue=" + susheTousuZhuangtaiValue +
			", xueshengName=" + xueshengName +
			", xueshengPhone=" + xueshengPhone +
			", xueshengIdNumber=" + xueshengIdNumber +
			", xueshengPhoto=" + xueshengPhoto +
			", newMoney=" + newMoney +
			", xueshengEmail=" + xueshengEmail +
			", susheName=" + susheName +
			", susheUuidNumber=" + susheUuidNumber +
			", sushePhoto=" + sushePhoto +
			", susheAddress=" + susheAddress +
			", susheClicknum=" + susheClicknum +
			", susheContent=" + susheContent +
			", susheDelete=" + susheDelete +
			"} " + super.toString();
	}
}
