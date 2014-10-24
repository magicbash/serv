package com.magicbash.server;

import com.magicbash.stats.Log;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

public class ServerInitializer extends io.netty.channel.ChannelInitializer<SocketChannel>{
	public Log Lg = new Log(10_000L);
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		// TODO Auto-generated method stub
		
		String ip = arg0.remoteAddress().getHostString();
		this.
		Lg.setIp(ip);
		ChannelPipeline p = arg0.pipeline();
		ServerHandler sh = new ServerHandler();
		p.addLast(Lg);
		p.addLast("decoder", new HttpRequestDecoder());
		p.addLast("encoder", new HttpResponseEncoder());
		p.addLast("handler", sh);
		Lg.setUri(sh.getUri());
		
	}
	
	public void setUri(String uri){
		
	}

}
