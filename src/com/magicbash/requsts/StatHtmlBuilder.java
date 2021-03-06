package com.magicbash.requsts;

import java.text.SimpleDateFormat;
import java.util.ListIterator;

import com.magicbash.server.Server;
import com.magicbash.stats.Log;
import com.magicbash.stats.Redirects;
import com.magicbash.stats.Requests;

public class StatHtmlBuilder {
	public final static String HTML_HEADER = "<!doctype html><html><head><meta charset=\"utf-8\"> <title>Status page</title> </head> <body> <h1>Status Page</h1>";
	public final static String HTML_FOOTER = "</body> </html>";
	public final static String REQ_NUMBERS = "<p>Number of request: ";
	public final static String REQ_UNIQ_NUMBERS = "<p>Number of uniqum request(IP): ";
	public final static String TABLE_COUNTER_HEADER = "<table width=\"699\" border=\"1\"> <caption> Request counter </caption> <tr> <th width=\"402\" scope=\"col\">IP</th> <th width=\"335\" scope=\"col\">Number of requset</th> <th width=\"262\" scope=\"col\">Time of last request</th> </tr>";
	public final static String TABLE_FOOTER = "</Table>";
	public final static String TABLE_REDIR_HEADER = "<table width=\"699\" border=\"1\"> <caption> Number of redirects </caption> <tr> <th width=\"587\" scope=\"col\">URL</th> <th width=\"96\" scope=\"col\">Number</th> </tr>";
	public final static String NUM_OPEN_CONNECTIONS = "<p>Number of open connections: ";
	public final static String TABLE_LOG_HEADER = "<table width=\"699\" border=\"1\"><caption>Log</caption><tr><th width=\"109\" scope=\"col\">Src_ip</th><th width=\"203\" scope=\"col\">URI</th><th width=\"94\" scope=\"col\">timestamp</th><th width=\"80\" scope=\"col\">send</th><th width=\"80\" scope=\"col\">recive</th><th width=\"128\" scope=\"col\">speed (byte/sec)</th></tr>";

	public static String reqestNum (int a){
		return REQ_NUMBERS + a + "</p>";
	}
	
	public static String reqestUniqNum (int a){
		return REQ_UNIQ_NUMBERS + a + "</p>";
	}
	
	public static String counterTable(){
		StringBuilder sb = new StringBuilder();
		sb.append(TABLE_COUNTER_HEADER);
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		for (Requests value : Server.STAT.getRequests().values()) {
			
		    sb.append("<tr>");
		    sb.append("<td>");
		    sb.append(value.getIp());
		    sb.append("</td>");
		    
		    sb.append("<td>");
		    sb.append(value.getNumOfReq());
		    sb.append("</td>");
		    
			sb.append("<td>");
		    sb.append(form.format(value.getTime().getTime()));
		    sb.append("</td>");
		    sb.append("</tr>");
		}
		//content
		sb.append(TABLE_FOOTER);
		return sb.toString();
	}
	
	public static String redirTable(){
		StringBuilder sb = new StringBuilder();
		sb.append(TABLE_REDIR_HEADER);
		for (Redirects value : Server.STAT.getRedirects().values()) {
		    sb.append("<tr>");
		    sb.append("<td>");
		    sb.append(value.getUri());
		    sb.append("</td>");
		    
		    sb.append("<td>");
		    sb.append(value.getNumOfRedir());
		    sb.append("</td>");
		    
		    sb.append("</tr>");
		}
		//content
		sb.append(TABLE_FOOTER);
		return sb.toString();
	}
	
	public static String openConn(int a){
		return NUM_OPEN_CONNECTIONS + a + "</p>";
	}
	
	public static String logTable(){
		StringBuilder sb = new StringBuilder();
		sb.append(TABLE_LOG_HEADER);
		//content
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		Log lg;
		ListIterator<Log> li = Server.STAT.getLog().listIterator();
		while (li.hasNext()){
			li.next();
		}
		while(li.hasPrevious()) {
			lg = li.previous();
		    sb.append("<tr>");
		    sb.append("<td>");
		    sb.append(lg.getIp());
		    sb.append("</td>");
		    
		    sb.append("<td>");
		    sb.append(lg.getUri());
		    sb.append("</td>");
		    
		    sb.append("<td>");
		    sb.append(form.format(lg.getTime().getTime()));
		    sb.append("</td>");
		    
		    sb.append("<td>");
		    sb.append(lg.getSendBytes());
		    sb.append("</td>");
		    
		    
		    sb.append("<td>");
		    sb.append(lg.getReciveBytes());
		    sb.append("</td>");
		    
		    sb.append("<td>");
		    sb.append(lg.getSpeed());
		    sb.append("</td>");
		    sb.append("</tr>");
		}
		//content
		sb.append(TABLE_FOOTER);
		sb.append(TABLE_FOOTER);
		return sb.toString();
	}
	
	public static String createStat(){
		StringBuilder sb = new StringBuilder();
		sb.append(reqestNum(Server.STAT.getNumOfRequests()));
		sb.append(reqestUniqNum(Server.STAT.getNumOfUniqRequests()));
		sb.append(counterTable());
		sb.append(redirTable());
		sb.append(openConn(Server.STAT.getNumOfConnections()));
		sb.append(logTable());
		sb.append(HTML_FOOTER);
		return sb.toString();
	}
	
	
}
