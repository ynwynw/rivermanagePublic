package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusszhilixinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusszhilixinxiView;


/**
 * 治理信息评论表
 *
 * @author 
 * @email 
 * @date 2024-01-16 08:56:07
 */
public interface DiscusszhilixinxiService extends IService<DiscusszhilixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusszhilixinxiView> selectListView(Wrapper<DiscusszhilixinxiEntity> wrapper);
   	
   	DiscusszhilixinxiView selectView(@Param("ew") Wrapper<DiscusszhilixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusszhilixinxiEntity> wrapper);
   	

}

