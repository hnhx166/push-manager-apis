package com.vinux.web.push;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.vinux.common.mq.MQ_CHANNEL;
import com.vinux.common.mq.Producer;
import com.vinux.common.page.Pagination;
import com.vinux.dao.entity.PushMessage;
import com.vinux.service.PushMessageService;

//设置允许跨域
@CrossOrigin
@RequestMapping("push")
@RestController
public class PushMessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PushMessageController.class);
	
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
	public Map<String, Object> getMessages(@RequestBody Map<String, Object> conditionItems){
		Map<String, Object> resultData = new HashMap<String, Object>();
		try {
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
	
	@RequestMapping(value = "pushToServer", method = RequestMethod.POST)
	public void pushToServer(HttpServletRequest request){
//		String receiveId = message.getString("receiveId");
//		String appId = message.getString("appId");
//		String msg = message.getString("message");
//		String memberId = message.getString("memberId");
//		Date sendTime = message.getDate("sendTime");
		
		String appId = request.getHeader("appId");
		logger.debug("接收到appId:" + appId);
		
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			logger.info("接收到应用 " + appId + " 推送的数据：" + sb.toString());
		} catch (IOException e) {
			logger.error("接收到应用 " + appId + " 推送的数据出错。");
			e.printStackTrace();
			return ;
		}
		try {
			JSONObject message = JSONObject.parseObject(sb.toString());
			//TODO 此处会阻塞，需要优化
			Producer.pushMessage(message, MQ_CHANNEL.CHANNEL_PUSH_TO_SERVER);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
}
