package com.sunonerim.util;

import java.net.InetAddress;

public class GUID {
	public	 static String next() {
		return next("23432423");	
	}
	
	public	static	String	next(String seed ){
		int	rand = (int)(Math.random() * 1000000);
		
		StringBuffer sb = new StringBuffer();
		
		try{
			InetAddress thisIp = InetAddress.getLocalHost();
			sb.append(System.currentTimeMillis()).append("-").append(rand);
		}catch(Exception e) {
		    e.printStackTrace();
		}				
		    
		String	temp_str =sb.toString(); 
		return sb.toString().format("%-20s", temp_str ).replace(' ', '0');
	}
	
	public	static	void	main(String[] args){
		for ( int i = 0; i<100; i++){
			String	guid = GUID.next();
			System.out.println( guid + "["  + guid.length() +  "]");
		}
	}
}
