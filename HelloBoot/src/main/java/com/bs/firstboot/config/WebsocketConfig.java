package com.bs.firstboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bs.firstboot.common.ChattingServer;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer{
	
	@Autowired
	private ChattingServer chatserver;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatserver, "/chat");		
	}

	
	
}
