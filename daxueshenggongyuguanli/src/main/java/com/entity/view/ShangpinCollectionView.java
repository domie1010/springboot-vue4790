package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShangpinCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 二手商品收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shangpin_collection")
public class ShangpinCollectionView extends ShangpinCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String shangpinCollectionValue;

	//级联表 二手商品
					 
		/**
		* 二手商品 的 学生
		*/
		@ColumnInfo(comment="学生",type="int(11)")
		private Integer shangpinXueshengId;
		/**
		* 二手商品名称
		*/

		@ColumnInfo(comment="二手商品名称",type="varchar(200)")
		private String shangpinName;
		/**
		* 二手商品编号
		*/

		@ColumnInfo(comment="二手商品编号",type="varchar(200)")
		private String shangpinUuidNumber;
		/**
		* 二手商品照片
		*/

		@ColumnInfo(comment="二手商品照片",type="varchar(200)")
		private String shangpinPhoto;
		/**
		* 交易位置
		*/

		@ColumnInfo(comment="交易位置",type="varchar(200)")
		private String shangpinAddress;
		/**
		* 商品类型
		*/
		@ColumnInfo(comment="商品类型",type="int(11)")
		private Integer shangpinTypes;
			/**
			* 商品类型的值
			*/
			@ColumnInfo(comment="商品类型的字典表值",type="varchar(200)")
			private String shangpinValue;
		/**
		* 二手数量
		*/

		@ColumnInfo(comment="二手数量",type="int(11)")
		private Integer shangpinKucunNumber;
		/**
		* 现价
		*/
		@ColumnInfo(comment="现价",type="decimal(10,2)")
		private Double shangpinNewMoney;
		/**
		* 二手商品热度
		*/

		@ColumnInfo(comment="二手商品热度",type="int(11)")
		private Integer shangpinClicknum;
		/**
		* 二手商品介绍
		*/

		@ColumnInfo(comment="二手商品介绍",type="longtext")
		private String shangpinContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer shangpinDelete;
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



	public ShangpinCollectionView() {

	}

	public ShangpinCollectionView(ShangpinCollectionEntity shangpinCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, shangpinCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getShangpinCollectionValue() {
		return shangpinCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setShangpinCollectionValue(String shangpinCollectionValue) {
		this.shangpinCollectionValue = shangpinCollectionValue;
	}


	//级联表的get和set 二手商品
		/**
		* 获取：二手商品 的 学生
		*/
		public Integer getShangpinXueshengId() {
			return shangpinXueshengId;
		}
		/**
		* 设置：二手商品 的 学生
		*/
		public void setShangpinXueshengId(Integer shangpinXueshengId) {
			this.shangpinXueshengId = shangpinXueshengId;
		}

		/**
		* 获取： 二手商品名称
		*/
		public String getShangpinName() {
			return shangpinName;
		}
		/**
		* 设置： 二手商品名称
		*/
		public void setShangpinName(String shangpinName) {
			this.shangpinName = shangpinName;
		}

		/**
		* 获取： 二手商品编号
		*/
		public String getShangpinUuidNumber() {
			return shangpinUuidNumber;
		}
		/**
		* 设置： 二手商品编号
		*/
		public void setShangpinUuidNumber(String shangpinUuidNumber) {
			this.shangpinUuidNumber = shangpinUuidNumber;
		}

		/**
		* 获取： 二手商品照片
		*/
		public String getShangpinPhoto() {
			return shangpinPhoto;
		}
		/**
		* 设置： 二手商品照片
		*/
		public void setShangpinPhoto(String shangpinPhoto) {
			this.shangpinPhoto = shangpinPhoto;
		}

		/**
		* 获取： 交易位置
		*/
		public String getShangpinAddress() {
			return shangpinAddress;
		}
		/**
		* 设置： 交易位置
		*/
		public void setShangpinAddress(String shangpinAddress) {
			this.shangpinAddress = shangpinAddress;
		}
		/**
		* 获取： 商品类型
		*/
		public Integer getShangpinTypes() {
			return shangpinTypes;
		}
		/**
		* 设置： 商品类型
		*/
		public void setShangpinTypes(Integer shangpinTypes) {
			this.shangpinTypes = shangpinTypes;
		}


			/**
			* 获取： 商品类型的值
			*/
			public String getShangpinValue() {
				return shangpinValue;
			}
			/**
			* 设置： 商品类型的值
			*/
			public void setShangpinValue(String shangpinValue) {
				this.shangpinValue = shangpinValue;
			}

		/**
		* 获取： 二手数量
		*/
		public Integer getShangpinKucunNumber() {
			return shangpinKucunNumber;
		}
		/**
		* 设置： 二手数量
		*/
		public void setShangpinKucunNumber(Integer shangpinKucunNumber) {
			this.shangpinKucunNumber = shangpinKucunNumber;
		}

		/**
		* 获取： 现价
		*/
		public Double getShangpinNewMoney() {
			return shangpinNewMoney;
		}
		/**
		* 设置： 现价
		*/
		public void setShangpinNewMoney(Double shangpinNewMoney) {
			this.shangpinNewMoney = shangpinNewMoney;
		}

		/**
		* 获取： 二手商品热度
		*/
		public Integer getShangpinClicknum() {
			return shangpinClicknum;
		}
		/**
		* 设置： 二手商品热度
		*/
		public void setShangpinClicknum(Integer shangpinClicknum) {
			this.shangpinClicknum = shangpinClicknum;
		}

		/**
		* 获取： 二手商品介绍
		*/
		public String getShangpinContent() {
			return shangpinContent;
		}
		/**
		* 设置： 二手商品介绍
		*/
		public void setShangpinContent(String shangpinContent) {
			this.shangpinContent = shangpinContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getShangpinDelete() {
			return shangpinDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setShangpinDelete(Integer shangpinDelete) {
			this.shangpinDelete = shangpinDelete;
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
		return "ShangpinCollectionView{" +
			", shangpinCollectionValue=" + shangpinCollectionValue +
			", shangpinName=" + shangpinName +
			", shangpinUuidNumber=" + shangpinUuidNumber +
			", shangpinPhoto=" + shangpinPhoto +
			", shangpinAddress=" + shangpinAddress +
			", shangpinKucunNumber=" + shangpinKucunNumber +
			", shangpinNewMoney=" + shangpinNewMoney +
			", shangpinClicknum=" + shangpinClicknum +
			", shangpinContent=" + shangpinContent +
			", shangpinDelete=" + shangpinDelete +
			", xueshengName=" + xueshengName +
			", xueshengPhone=" + xueshengPhone +
			", xueshengIdNumber=" + xueshengIdNumber +
			", xueshengPhoto=" + xueshengPhoto +
			", newMoney=" + newMoney +
			", xueshengEmail=" + xueshengEmail +
			"} " + super.toString();
	}
}
