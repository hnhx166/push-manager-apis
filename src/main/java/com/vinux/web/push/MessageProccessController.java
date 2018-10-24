package com.vinux.web.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.vinux.common.mq.MessageCacheCustomer;
import com.vinux.common.mq.MessageCustomer;

@Component
@Order(value = 1)
public class MessageProccessController implements CommandLineRunner{

	@Autowired
	MessageCustomer messageCustomer;
	
	@Autowired
	MessageCacheCustomer messageCacheCustomer;
	
	@Override
	public void run(String... args) throws Exception {
		messageCustomer.initCustomer();
		messageCacheCustomer.initCustomer();
	}

}
