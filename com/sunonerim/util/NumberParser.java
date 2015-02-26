package com.sunonerim.util;

public class NumberParser {
	public static	Float	toFloat( String float_str){
		Float	f = null;
		try{
			f = Float.parseFloat(float_str);	
		}catch( Exception e){
			f = null;
		}
		return f;
	}
	
	public static	float	toFloat( String float_str, float def_val){
		Float	f = def_val;
		try{
			f = Float.parseFloat(float_str);	
		}catch( Exception e){
			f = def_val;
		}
		return f;
	}
	
	public	static Long	toLong( String long_str){
		Long	l = null;
		try{
			l = Long.parseLong(long_str);	
		}catch( Exception e){
			l = null;
		}
		return l;
	}

	public	static long	toLong( String int_str, long def_val){
		long	l = def_val;
		try{
			l = Integer.parseInt(int_str);	
		}catch( Exception e){
			l = def_val;
		}
		return l;
	}
	
	
	public	static Integer	toInt( String int_str){
		Integer	i = null;
		try{
			i = Integer.parseInt(int_str);	
		}catch( Exception e){
			i = null;
		}
		return i;
	}
	
	public	static int	toInt( String int_str, int def_val){
		int	i = def_val;
		try{
			i = Integer.parseInt(int_str);	
		}catch( Exception e){
			i = def_val;
		}
		return i;
	}
	
	
	public	static	Boolean	toBool(String bool_str){
		Boolean	b = null;
		try{
			b = Boolean.parseBoolean(bool_str);	
		}catch( Exception e){
			b = null;
		}
		return b;
	}
	
	public	static	String	toDate(String in_str){
		return in_str;
	}
	
	
	public static void main(String[] args){
		Float g = NumberParser.toFloat("234234.4353");
		System.out.println("g : " + g);
		
		Long l= NumberParser.toLong("3245353f3455534534");
		System.out.println("l : " + l);
		
		Integer i= NumberParser.toInt("334534");
		System.out.println("i : " + i);
		
		Boolean	b = NumberParser.toBool("true");
		System.out.println("b : " + b);

	}
	
	
}
