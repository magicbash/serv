package com.magicbash.stats;

public class Redirects {
	private String uri;
	private int numOfRedir = 0;
	
	public Redirects(String uri) {
		// TODO Auto-generated constructor stub
		this.uri = uri;
		this.numOfRedir++;
	}
	
	public void incNumOfRedir(){
		this.numOfRedir++;
	}
	
	public String getUri(){
		return uri;
	}
	
	public int getNumOfRedir(){
		return numOfRedir;
	}
	
	
	
}
