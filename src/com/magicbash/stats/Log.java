package com.magicbash.stats;


import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;
import io.netty.channel.ChannelHandler;

import java.util.Calendar;

import com.magicbash.server.Server;

public class Log extends ChannelTrafficShapingHandler{
	
	public Log(long checkInterval) {
		
		super(checkInterval);
		// TODO Auto-generated constructor stub
	}
	private boolean isAdd = false;
	private String ip;
	private String uri = "undef";
	private Calendar time;
	private long sendBytes;
	private long reciveBytes;
	private long speed = 0;
	
	/*public Log() {
		// TODO Auto-generated constructor stub
	}
	public Log(String ip, String uri, int send, int recive, int speed) {
		// TODO Auto-generated constructor stub
		this.ip = ip;
		this.uri = uri;
		this.sendBytes = send;
		this.reciveBytes = recive;
		this.speed = speed;
		this.time = Calendar.getInstance();
	}
	*/
	@Override
	protected void doAccounting(TrafficCounter counter) {
		
		this.sendBytes = counter.cumulativeWrittenBytes();
		this.reciveBytes = counter.cumulativeReadBytes();
		this.setTime(Calendar.getInstance());
		this.speed = counter.lastWriteThroughput();
		// TODO Auto-generated method stub
		super.doAccounting(counter);
		if (!this.isAdd){
			Server.STAT.addLog(this);
			this.isAdd = true;
		}
	}
	public String getIp(){
		return ip;
	}
	
	public String getUri(){
		return uri;
	}
	
	public Calendar getTime(){
		return time;
	}
	
	public long getReciveBytes(){
		return reciveBytes;
	}
	
	public long getSendBytes(){
		return sendBytes;
	}
	
	public long getSpeed(){
		return speed;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	
	public void setTime(Calendar time){
		this.time = time;
	}
	
	public void setUri(String uri){
		this.uri = uri;
	}
}
