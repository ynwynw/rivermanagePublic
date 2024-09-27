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


import com.cl.dao.ZhilixinxiDao;
import com.cl.entity.ZhilixinxiEntity;
import com.cl.service.ZhilixinxiService;
import com.cl.entity.view.ZhilixinxiView;

@Service("zhilixinxiService")
public class ZhilixinxiServiceImpl extends ServiceImpl<ZhilixinxiDao, ZhilixinxiEntity> implements ZhilixinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhilixinxiEntity> page = this.selectPage(
                new Query<ZhilixinxiEntity>(params).getPage(),
                new EntityWrapper<ZhilixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhilixinxiEntity> wrapper) {
		  Page<ZhilixinxiView> page =new Query<ZhilixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ZhilixinxiView> selectListView(Wrapper<ZhilixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhilixinxiView selectView(Wrapper<ZhilixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
