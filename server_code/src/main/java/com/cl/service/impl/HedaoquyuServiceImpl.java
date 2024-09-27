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


import com.cl.dao.HedaoquyuDao;
import com.cl.entity.HedaoquyuEntity;
import com.cl.service.HedaoquyuService;
import com.cl.entity.view.HedaoquyuView;

@Service("hedaoquyuService")
public class HedaoquyuServiceImpl extends ServiceImpl<HedaoquyuDao, HedaoquyuEntity> implements HedaoquyuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HedaoquyuEntity> page = this.selectPage(
                new Query<HedaoquyuEntity>(params).getPage(),
                new EntityWrapper<HedaoquyuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<HedaoquyuEntity> wrapper) {
		  Page<HedaoquyuView> page =new Query<HedaoquyuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<HedaoquyuView> selectListView(Wrapper<HedaoquyuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public HedaoquyuView selectView(Wrapper<HedaoquyuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
