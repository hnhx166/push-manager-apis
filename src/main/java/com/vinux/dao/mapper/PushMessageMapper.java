package com.vinux.dao.mapper;

import java.util.List;
import java.util.Map;

import com.vinux.dao.entity.PushMessage;

public interface PushMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PushMessage record);

    int insertSelective(PushMessage record);
    
    List<PushMessage> selectMessages(Map<String, Object> conditionItems);

    PushMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PushMessage record);

    int updateByPrimaryKey(PushMessage record);
}