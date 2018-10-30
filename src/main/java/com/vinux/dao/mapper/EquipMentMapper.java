package com.vinux.dao.mapper;

import java.util.List;
import java.util.Map;

import com.vinux.dao.entity.EquipMent;

public interface EquipMentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EquipMent record);

    int insertSelective(EquipMent record);

    EquipMent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EquipMent record);

    int updateByPrimaryKey(EquipMent record);
    
    List<EquipMent> getEquipments(Map<String, Object> conditionItems);
}