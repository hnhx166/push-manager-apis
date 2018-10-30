package com.vinux.service;

import java.util.Map;

import com.vinux.common.page.Pagination;
import com.vinux.dao.entity.EquipMent;

public interface EquipMentService {

	int deleteByPrimaryKey(Long id);

    int insert(EquipMent record);

    int insertSelective(EquipMent record);

    EquipMent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EquipMent record);

    int updateByPrimaryKey(EquipMent record);
    
    
    Pagination <EquipMent> getEquipments(Map<String, Object> conditionItems);
}
