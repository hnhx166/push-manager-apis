package com.vinux.web.push;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.vinux.common.page.Pagination;
import com.vinux.dao.entity.PushMessage;
import com.vinux.service.PushMessageService;

//设置允许跨域
@CrossOrigin
@RequestMapping("push")
@RestController
public class PushMessageController {
	
	@Autowired
	private PushMessageService pushMessageService;

	@RequestMapping("save")
	public Map<String, Object> save(JSONObject message){
		
		PushMessage pm = new PushMessage();
		
		pm.setAppId(message.getString("appId"));
		pm.setGroupId(message.getString("groupId"));
		pm.setMessage(message.getString("msg"));
		pm.setMessageType(message.getInteger("msgType"));
		pm.setSendId(message.getString("uid"));
		pm.setReceiverId(message.getString("receiveId"));
		pm.setReceiveType(message.getInteger("receiveType"));
		pm.setSendTime(message.getDate("sendTime"));
		pm.setReceiveTime(message.getDate("receiveTime"));
		pm.setVersion(message.getInteger("version"));
		pm.setStatus(message.getInteger("status"));
		pm.setServerSendTime(message.getDate("serverSendTime"));
		
		Map<String, Object> resultData = new HashMap<String, Object>();
		try {
			int result = pushMessageService.insert(pm);
			if(result > 0) {
				resultData.put("status", 200);
				resultData.put("msg", "保存消息成功。");
			}else {
				resultData.put("status", 300);
				resultData.put("msg", "保存消息失败。");
			}
		}catch(Exception e) {
			resultData.put("status", 500);
			resultData.put("msg", "保存消息出现异常。");
			e.printStackTrace();
		}
		return resultData;
	}
	
//	@RequestMapping("save")
	public void cacheMessage(JSONObject message){
		System.out.println("api 缓存数据");
		System.out.println(message);
		System.out.println("api 缓存数据");
	}
	
	@RequestMapping("getMessages")
	public Map<String, Object> getMessages(@RequestBody Map<String, Object> conditionItems/*,String sendId,
			String receiverId,
			String appId,//select默认选择，为空时
			String message,
			String status,
			String startTime,
			String endTime,Integer pageNum, Integer pageSize*/){
		Map<String, Object> resultData = new HashMap<String, Object>();
		try {
//			if(pageNum == null) {
//				pageNum = 1;
//			}
//			if(pageSize == null) {
//				pageSize = 10; 
//			}
//			
//			Map<String, Object> conditionItems = new HashMap<String, Object>();
//			if(StringUtils.isNotBlank(sendId)) {
//				conditionItems.put("sendId", sendId);
//			}
//			
//			if(StringUtils.isNotBlank(receiverId)) {
//				conditionItems.put("receiverId", receiverId);
//			}
//			if(StringUtils.isNotBlank(appId)) {
//				conditionItems.put("appId", appId);
//			}
//			
//			if(StringUtils.isNotBlank(message)) {
//				conditionItems.put("message", message);
//			}
//			
//			if(StringUtils.isNotBlank(status))
//				conditionItems.put("status", status);
//			
//			if(StringUtils.isNotBlank(startTime))
//				conditionItems.put("startTime", startTime);
//			
//			if(StringUtils.isNotBlank(endTime))
//				conditionItems.put("endTime", endTime);
			Pagination<PushMessage> result = pushMessageService.selectMessages(conditionItems);
			resultData.put("status", 200);
			resultData.put("data", result);
			resultData.put("msg", "获取消息成功。");
		}catch(Exception e) {
			resultData.put("status", 300);
			resultData.put("msg", "获取消息出错了。");
		}
		return resultData;
	}
	
}
