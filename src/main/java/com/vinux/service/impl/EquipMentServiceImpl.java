package com.vinux.service.impl;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vinux.common.page.Pagination;
import com.vinux.dao.entity.EquipMent;
import com.vinux.dao.mapper.EquipMentMapper;
import com.vinux.service.EquipMentService;

@Service
public class EquipMentServiceImpl implements EquipMentService {
	
	@Autowired
	EquipMentMapper equipMentMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return equipMentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EquipMent record) {
		// TODO Auto-generated method stub
		return equipMentMapper.insert(record);
	}

	@Override
	public int insertSelective(EquipMent record) {
		// TODO Auto-generated method stub
		return equipMentMapper.insertSelective(record);
	}

	@Override
	public EquipMent selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return equipMentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EquipMent record) {
		// TODO Auto-generated method stub
		return equipMentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EquipMent record) {
		// TODO Auto-generated method stub
		return equipMentMapper.updateByPrimaryKey(record);
	}

	@Override
	public Pagination<EquipMent> getEquipments(Map<String, Object> conditionItems) {
		Integer pageNum = 1;
		Integer pageSize = 10;
		if(MapUtils.isNotEmpty(conditionItems)){
			try {
				pageNum = Integer.parseInt(conditionItems.get("pageNum").toString());
				pageSize = Integer.parseInt(conditionItems.get("pageSize").toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		Page<EquipMent> equipMentList = PageHelper.startPage(pageNum, pageSize);
		
		Pagination<EquipMent> p = new Pagination<>();
		equipMentMapper.getEquipments(conditionItems);
		p.setList(equipMentList.getResult());
		p.setPageNum(equipMentList.getPageNum());
		p.setPageSize(equipMentList.getPageSize());
		p.setPages(equipMentList.getPages());
		p.setTotal(equipMentList.getTotal());
		return p;
	}

}
