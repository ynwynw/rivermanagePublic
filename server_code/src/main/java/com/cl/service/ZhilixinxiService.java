package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZhilixinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZhilixinxiView;


/**
 * 治理信息
 *
 * @author 
 * @email 
 * @date 2024-01-16 08:56:07
 */
public interface ZhilixinxiService extends IService<ZhilixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhilixinxiView> selectListView(Wrapper<ZhilixinxiEntity> wrapper);
   	
   	ZhilixinxiView selectView(@Param("ew") Wrapper<ZhilixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhilixinxiEntity> wrapper);
   	

}

