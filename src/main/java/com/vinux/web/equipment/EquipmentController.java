package com.vinux.web.equipment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinux.common.page.Pagination;
import com.vinux.dao.entity.EquipMent;
import com.vinux.service.EquipMentService;

@CrossOrigin
@RestController
@RequestMapping("equipment")
public class EquipmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	
	@Autowired
	EquipMentService equipMentService;
	
	
	@RequestMapping("save")
	public Map<String, Object> save(@RequestBody EquipMent equipment){
		Map<String, Object> resultData = new HashMap<String, Object>();
		try {
			Date cdate = new Date(System.currentTimeMillis());
			equipment.setCdate(cdate);
			int result = equipMentService.insert(equipment);
			if(result > 0) {
				resultData.put("status", 200);
				resultData.put("msg", "保存成功。");
			}else {
				resultData.put("status", 300);
				resultData.put("msg", "保存失败。");
			}
		}catch(Exception e) {
			resultData.put("status", 500);
			resultData.put("msg", "保存出现异常。");
			e.printStackTrace();
		}
		return resultData;
	}
	
	@RequestMapping("update")
	public Map<String, Object> update(@RequestBody EquipMent equipment){
		Map<String, Object> resultData = new HashMap<String, Object>();
		try {
			int result = equipMentService.updateByPrimaryKey(equipment);
			if(result > 0) {
				resultData.put("status", 200);
				resultData.put("msg", "修改成功。");
			}else {
				resultData.put("status", 300);
				resultData.put("msg", "修改失败。");
			}
		}catch(Exception e) {
			resultData.put("status", 500);
			resultData.put("msg", "修改出现异常。");
			e.printStackTrace();
		}
		return resultData;
	}

	@RequestMapping("getEquipments")
	public Map<String, Object> getEquipments(@RequestBody Map<String, Object> conditionItems){
		Map<String, Object> resultData = new HashMap<String, Object>();
		try {
			Pagination<EquipMent> result = equipMentService.getEquipments(conditionItems);
			resultData.put("status", 200);
			resultData.put("data", result);
			resultData.put("msg", "获取数据成功。");
			logger.debug("getEquipments 获取数据成功。");
		}catch(Exception e) {
			resultData.put("status", "500");
			resultData.put("msg", "获取数据失败。");
			logger.error("getEquipments 获取数据失败。" + e.getMessage());
			e.printStackTrace();
		}
		return resultData;
	}
}
