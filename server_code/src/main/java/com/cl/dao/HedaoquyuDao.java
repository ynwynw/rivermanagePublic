package com.cl.dao;

import com.cl.entity.HedaoquyuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.HedaoquyuView;


/**
 * 河道区域
 * 
 * @author 
 * @email 
 * @date 2024-01-16 08:56:07
 */
public interface HedaoquyuDao extends BaseMapper<HedaoquyuEntity> {
	
	List<HedaoquyuView> selectListView(@Param("ew") Wrapper<HedaoquyuEntity> wrapper);

	List<HedaoquyuView> selectListView(Pagination page,@Param("ew") Wrapper<HedaoquyuEntity> wrapper);
	
	HedaoquyuView selectView(@Param("ew") Wrapper<HedaoquyuEntity> wrapper);
	

}
