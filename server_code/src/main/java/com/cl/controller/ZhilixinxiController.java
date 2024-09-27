package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.ZhilixinxiEntity;
import com.cl.entity.view.ZhilixinxiView;

import com.cl.service.ZhilixinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MD5Util;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 治理信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-01-16 08:56:07
 */
@RestController
@RequestMapping("/zhilixinxi")
public class ZhilixinxiController {
    @Autowired
    private ZhilixinxiService zhilixinxiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhilixinxiEntity zhilixinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gongzuorenyuan")) {
			zhilixinxi.setGongzuorenyuanzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZhilixinxiEntity> ew = new EntityWrapper<ZhilixinxiEntity>();

		PageUtils page = zhilixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhilixinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhilixinxiEntity zhilixinxi, 
		HttpServletRequest request){
        EntityWrapper<ZhilixinxiEntity> ew = new EntityWrapper<ZhilixinxiEntity>();

		PageUtils page = zhilixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhilixinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhilixinxiEntity zhilixinxi){
       	EntityWrapper<ZhilixinxiEntity> ew = new EntityWrapper<ZhilixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhilixinxi, "zhilixinxi")); 
        return R.ok().put("data", zhilixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhilixinxiEntity zhilixinxi){
        EntityWrapper< ZhilixinxiEntity> ew = new EntityWrapper< ZhilixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhilixinxi, "zhilixinxi")); 
		ZhilixinxiView zhilixinxiView =  zhilixinxiService.selectView(ew);
		return R.ok("查询治理信息成功").put("data", zhilixinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhilixinxiEntity zhilixinxi = zhilixinxiService.selectById(id);
        return R.ok().put("data", zhilixinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhilixinxiEntity zhilixinxi = zhilixinxiService.selectById(id);
        return R.ok().put("data", zhilixinxi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        ZhilixinxiEntity zhilixinxi = zhilixinxiService.selectById(id);
        if(type.equals("1")) {
        	zhilixinxi.setThumbsupnum(zhilixinxi.getThumbsupnum()+1);
        } else {
        	zhilixinxi.setCrazilynum(zhilixinxi.getCrazilynum()+1);
        }
        zhilixinxiService.updateById(zhilixinxi);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhilixinxiEntity zhilixinxi, HttpServletRequest request){
    	zhilixinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhilixinxi);
        zhilixinxiService.insert(zhilixinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhilixinxiEntity zhilixinxi, HttpServletRequest request){
    	zhilixinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhilixinxi);
        zhilixinxiService.insert(zhilixinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhilixinxiEntity zhilixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhilixinxi);
        zhilixinxiService.updateById(zhilixinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZhilixinxiEntity> list = new ArrayList<ZhilixinxiEntity>();
        for(Long id : ids) {
            ZhilixinxiEntity zhilixinxi = zhilixinxiService.selectById(id);
            zhilixinxi.setSfsh(sfsh);
            zhilixinxi.setShhf(shhf);
            list.add(zhilixinxi);
        }
        zhilixinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhilixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
