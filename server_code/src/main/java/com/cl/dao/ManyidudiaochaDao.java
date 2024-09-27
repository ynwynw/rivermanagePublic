package com.cl.dao;

import com.cl.entity.ManyidudiaochaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ManyidudiaochaView;


/**
 * 满意度调查
 * 
 * @author 
 * @email 
 * @date 2024-01-16 08:56:07
 */
public interface ManyidudiaochaDao extends BaseMapper<ManyidudiaochaEntity> {
	
	List<ManyidudiaochaView> selectListView(@Param("ew") Wrapper<ManyidudiaochaEntity> wrapper);

	List<ManyidudiaochaView> selectListView(Pagination page,@Param("ew") Wrapper<ManyidudiaochaEntity> wrapper);
	
	ManyidudiaochaView selectView(@Param("ew") Wrapper<ManyidudiaochaEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ManyidudiaochaEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ManyidudiaochaEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ManyidudiaochaEntity> wrapper);



}
