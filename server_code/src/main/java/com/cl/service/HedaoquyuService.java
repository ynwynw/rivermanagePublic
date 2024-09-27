package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.HedaoquyuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.HedaoquyuView;


/**
 * 河道区域
 *
 * @author 
 * @email 
 * @date 2024-01-16 08:56:07
 */
public interface HedaoquyuService extends IService<HedaoquyuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<HedaoquyuView> selectListView(Wrapper<HedaoquyuEntity> wrapper);
   	
   	HedaoquyuView selectView(@Param("ew") Wrapper<HedaoquyuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<HedaoquyuEntity> wrapper);
   	

}

