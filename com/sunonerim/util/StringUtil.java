package com.sunonerim.util;

import java.util.List;

public class StringUtil {
	/**
	 * make String Array into SQL in phrase 
	 * 	{"aaa", "bbb", "ccc"} -->  ("aaa", "bbb", "ccc")  
	 * 
	 * @param args
	 * @return
	 */
	public static	String	convertIN(String[] args){
		StringBuffer	in_sb = new StringBuffer();
		for ( int i=0; i<args.length; i++ ){
			if( in_sb.length() > 0 ) in_sb.append( ",'").append( args[i]).append("'") ;
			else in_sb.append("'").append( args[i]).append("'"); 
		}
		
		in_sb.insert( 0, "(" ).append(")");
		return in_sb.toString();
	}
	
	
	public static	String	convertIN_String( List  args){
		StringBuffer	in_sb = new StringBuffer();
		for ( int i=0; i<args.size(); i++ ){
			
			if( in_sb.length() > 0 ) in_sb.append( ",'").append( args.get(i)).append("'") ;			
			else in_sb.append("'").append( args.get(i)).append("'"); 
		}
		
		in_sb.insert( 0, "(" ).append(")");
		return in_sb.toString();
	}
	
	public static	String	convertIN_number( List  args){
		StringBuffer	in_sb = new StringBuffer();
		for ( int i=0; i<args.size(); i++ ){
			
			if( in_sb.length() > 0 ) in_sb.append( ", ").append( args.get(i));			
			else in_sb.append( args.get(i)); 
		}
		
		in_sb.insert( 0, "(" ).append(")");
		return in_sb.toString();
	}
	
	
	/**
	 * 
	 */
	public	static	String touchForJson(String str){
		if( str == null ) return "";
		return str.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
	}
	
	public static void main(String[] args){
		String	test = "sdfs\"fd";
		System.out.println(test);
		System.out.println( StringUtil.touchForJson(test));
	}
}
