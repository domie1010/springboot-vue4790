package com.dao;

import com.entity.SushexueshengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SushexueshengView;

/**
 * 宿舍学生 Dao 接口
 *
 * @author 
 */
public interface SushexueshengDao extends BaseMapper<SushexueshengEntity> {

   List<SushexueshengView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
