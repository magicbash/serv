package com.magicbash.stats;


import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;

import java.util.Calendar;


public class Log extends ChannelTrafficShapingHandler{
	public Log(long checkInterval) {
		super(checkInterval);
		// TODO Auto-generated constructor stub
	}

	private String ip;
	private String uri;
	private Calendar time;
	private long sendBytes;
	private long reciveBytes;
	private int speed;
	
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
		// TODO Auto-generated method stub
		//super.doAccounting(counter);
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
	
	public int getSpeed(){
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
