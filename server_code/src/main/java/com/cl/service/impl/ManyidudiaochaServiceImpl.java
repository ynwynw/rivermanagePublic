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


import com.cl.dao.ManyidudiaochaDao;
import com.cl.entity.ManyidudiaochaEntity;
import com.cl.service.ManyidudiaochaService;
import com.cl.entity.view.ManyidudiaochaView;

@Service("manyidudiaochaService")
public class ManyidudiaochaServiceImpl extends ServiceImpl<ManyidudiaochaDao, ManyidudiaochaEntity> implements ManyidudiaochaService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ManyidudiaochaEntity> page = this.selectPage(
                new Query<ManyidudiaochaEntity>(params).getPage(),
                new EntityWrapper<ManyidudiaochaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ManyidudiaochaEntity> wrapper) {
		  Page<ManyidudiaochaView> page =new Query<ManyidudiaochaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ManyidudiaochaView> selectListView(Wrapper<ManyidudiaochaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ManyidudiaochaView selectView(Wrapper<ManyidudiaochaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<ManyidudiaochaEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<ManyidudiaochaEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<ManyidudiaochaEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
