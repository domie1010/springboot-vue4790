package com.dao;

import com.entity.SusheYuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SusheYuyueView;

/**
 * 入住申请 Dao 接口
 *
 * @author 
 */
public interface SusheYuyueDao extends BaseMapper<SusheYuyueEntity> {

   List<SusheYuyueView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
