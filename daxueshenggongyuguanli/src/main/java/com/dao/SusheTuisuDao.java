package com.dao;

import com.entity.SusheTuisuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SusheTuisuView;

/**
 * 退宿申请 Dao 接口
 *
 * @author 
 */
public interface SusheTuisuDao extends BaseMapper<SusheTuisuEntity> {

   List<SusheTuisuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
