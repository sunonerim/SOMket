/**
 * UProperties resolve character encoding problem. It can use UTF-8 encoding character.
 * 
 */
package com.sunonerim.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class UProperties extends Properties {
	public	UProperties(){
		super();
	}
	
	public	UProperties(String prop_str){
		super();
		loadFromString(prop_str);
	}
	
	public	void	loadFromString(String prop_str){
		try {
			load( new ByteArrayInputStream( prop_str.getBytes( "UTF-8" ) ) );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String prop_key){
		String value = null;
		try {
			value = new String( super.getProperty(prop_key).getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			value ="";
		}	
		return value ;
	}
}
