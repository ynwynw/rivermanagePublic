package com.cl.dao;

import com.cl.entity.ZhilixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZhilixinxiView;


/**
 * 治理信息
 * 
 * @author 
 * @email 
 * @date 2024-01-16 08:56:07
 */
public interface ZhilixinxiDao extends BaseMapper<ZhilixinxiEntity> {
	
	List<ZhilixinxiView> selectListView(@Param("ew") Wrapper<ZhilixinxiEntity> wrapper);

	List<ZhilixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<ZhilixinxiEntity> wrapper);
	
	ZhilixinxiView selectView(@Param("ew") Wrapper<ZhilixinxiEntity> wrapper);
	

}
