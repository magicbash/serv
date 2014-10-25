package com.magicbash.stats;


import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;




public class Statistic {
	private int numOfRequests = 0;
	private int numOfUniqRequests = 0;
	private int numOfConnections = 0;
	private HashMap<String, Requests> req = new HashMap<String, Requests>();
	private HashMap<String, Redirects> red = new HashMap<String, Redirects>();
	private LinkedList<Log> log = new LinkedList<Log>();
	
	public void incReq(){
		this.numOfRequests++;
		
		//use next method if ip is uniq 
	}
	
	private void incUniqReq() {
		this.numOfUniqRequests++;
	}
	
	public int getNumOfRequests(){
		return this.numOfRequests;
	}
	
	public int getNumOfUniqRequests(){
		return this.numOfUniqRequests;
	}
	
	public void incNumOfConnections (){
		this.numOfConnections++;
	}
	
	public void decNumOfConnections (){
		this.numOfConnections--;
	}
	
	public int getNumOfConnections (){
		return numOfConnections;
	}
	
	public void addRequest(Requests req){
		if (!this.req.containsKey(req.getIp())){
			this.req.put(req.getIp(), req);
			this.incUniqReq();
		}
		else{
			this.req.get(req.getIp()).incNumOfrequests();
			this.req.get(req.getIp()).setTime(Calendar.getInstance());
		}
		this.incReq();
	}
	
	public HashMap<String, Requests> getRequests() {
		return this.req;
	}
	
	public void addRedirect(Redirects red){
		if (!this.red.containsKey(red.getUri())){
			this.red.put(red.getUri(), red);
		}
		else {
			this.red.get(red.getUri()).incNumOfRedir();
		}
		
	}
	
	public HashMap<String, Redirects> getRedirects() {
		return this.red;
	}
	
	public void addLog(Log log) {
		if (log.getReciveBytes() == 0 & log.getSendBytes() == 0) return;
		if (this.log.size()>=16){
			this.log.removeFirst();
		}
		this.log.add(log);
	}
	
	public LinkedList<Log> getLog(){
		return log;
	}
	public Statistic() {
		// TODO Auto-generated constructor stub
	}
	
}
	