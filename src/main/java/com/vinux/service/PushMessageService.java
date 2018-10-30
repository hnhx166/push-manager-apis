package com.vinux.service;

import java.util.Map;

import com.vinux.common.page.Pagination;
import com.vinux.dao.entity.PushMessage;

public interface PushMessageService {

	int insert(PushMessage record);
	
	Pagination<PushMessage> selectMessages(Map<String, Object> conditionItems);

    PushMessage selectByPrimaryKey(Long id);
}
