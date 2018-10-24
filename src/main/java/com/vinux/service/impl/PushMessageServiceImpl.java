package com.vinux.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vinux.common.page.Pagination;
import com.vinux.dao.entity.PushMessage;
import com.vinux.dao.mapper.PushMessageMapper;
import com.vinux.service.PushMessageService;

@Service
public class PushMessageServiceImpl implements PushMessageService {
	
	@Autowired
	private PushMessageMapper pushMessageMapper;

	@Override
	public int insert(PushMessage record) {
		return pushMessageMapper.insert(record);
	}

	@Override
	public PushMessage selectByPrimaryKey(Long id) {
		return pushMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public Pagination<PushMessage> selectMessages(Map<String, Object> conditionItems, Integer pageNum, Integer pageSize) {
		//使用分页插件,核心代码就这一行
		Page<PushMessage> messageList = PageHelper.startPage(pageNum, pageSize);
		pushMessageMapper.selectMessages(conditionItems);
//		messageList.
		Pagination<PushMessage> p = new Pagination<PushMessage>();
		p.setList(messageList.getResult());
		p.setPageNum(messageList.getPageNum());
		p.setPageSize(messageList.getPageSize());
		p.setPages(messageList.getPages());
		p.setTotal(messageList.getTotal());
		
		return p;
	}
	
}
