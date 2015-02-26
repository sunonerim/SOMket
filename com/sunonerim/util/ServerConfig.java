package com.sunonerim.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * �ý��� ȯ�漳�� ���� Ŭ����
 * JVM ȯ�溯�� (-Dserver.type) ���� ���� �ش� ������Ƽ ���� �ε�
 * @author Administrator
 *
 */
public class ServerConfig extends Properties{

	private static final long serialVersionUID = 1L;
	private	static ServerConfig ServerConfigInstance = null;
	
	public Logger logger = Logger.getRootLogger();
	
	public static final String SERVER_TYPE_LOCAL = "local";
	public static final String SERVER_TYPE_DEV   = "dev";
	public static final String SERVER_TYPE_REAL  = "real";
	
	public static final String CONFIG_FILE = "serverConfig.properties";
	
	public ServerConfig(){
		super();
		try{
			String configFileName = "serverConfig.properties";			
			java.io.InputStream i = 
				this.getClass().getClassLoader().getResourceAsStream(configFileName);
			load(i);
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	public static	ServerConfig	getInstance(){
		if( ServerConfigInstance == null ){
			ServerConfigInstance	= new ServerConfig();
		}
		return ServerConfigInstance;
	}
	
	public String getProperty(String prop_key){
		String value = null;
		try {
			value = new String( super.getProperty(prop_key).getBytes("ISO-8859-1"), "euc-kr");
		} catch (Exception e) {
			value ="";
		}	
		return value ;
	}
	
	public String getProperty(String prop_key,String DecoderChk){
		String value = null;
		try {
			if (DecoderChk.equals("1")){
				value = new String( super.getProperty(prop_key).getBytes("ISO-8859-1"), "euc-kr");
			}
			else{//"0"
				value =  super.getProperty(prop_key);
				
			}
			
		} catch (Exception e) {
			value ="";
		}	
		return value ;
	}	
	
	static	public	String	getValue(String key){
		ServerConfig	server_config = getInstance();
		return server_config.getProperty(key);
	}
	
	public static void main ( String args[]){
//		ServerConfig	sv = new ServerConfig(); 
//		String com  = sv.getProperty("db.driver");
//		
//
		System.out.println("Company : " +  ServerConfig.getValue("db.driver") );
	}
	
}
