package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.DiscusszhilixinxiDao;
import com.cl.entity.DiscusszhilixinxiEntity;
import com.cl.service.DiscusszhilixinxiService;
import com.cl.entity.view.DiscusszhilixinxiView;

@Service("discusszhilixinxiService")
public class DiscusszhilixinxiServiceImpl extends ServiceImpl<DiscusszhilixinxiDao, DiscusszhilixinxiEntity> implements DiscusszhilixinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusszhilixinxiEntity> page = this.selectPage(
                new Query<DiscusszhilixinxiEntity>(params).getPage(),
                new EntityWrapper<DiscusszhilixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusszhilixinxiEntity> wrapper) {
		  Page<DiscusszhilixinxiView> page =new Query<DiscusszhilixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DiscusszhilixinxiView> selectListView(Wrapper<DiscusszhilixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusszhilixinxiView selectView(Wrapper<DiscusszhilixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
