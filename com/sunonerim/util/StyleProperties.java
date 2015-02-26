package com.sunonerim.util;

public class StyleProperties extends UProperties {
	public	StyleProperties(String style ){
		super();
		loadFromString(style.replace(":", "=").replace(";", "\n"));
	}
	
	/**
	 * return integer value from property's value. 
	 * If it has alphabets such as 'px' , it would ignore them.
	 * 
	 * @return
	 */
	public	int	extractIntProperty(String key){
		String value   = getProperty(key);
		int    int_val = 0;
		
		if( value == null ) return 0;
		
		value   = value.replaceAll("[^0-9]", "");
		int_val = Integer.parseInt(value);
		
//		System.out.println("value :" + value );
		return int_val;
	}
	
	/**
	 * getProperty's value in the HEX expression.
	 * 
	 * @param key
	 * @return
	 */
	public	String	getHexProperty(String key){
		String value   = getProperty(key);
		
		if( value == null ) {
			return "0xFFFFFF";
		}
		
		value   = "0x" + value.replaceAll("[^0-9A-Fa-f]", "");		
		System.out.println("value :" + value );
		return value;
	}
	
	public	String	getPropertyWithIndex(String key, int index){
		String[]	values = getProperty(key).split("[\\s]");
		
		if( values == null ) return null;
		if( values.length <= index ) return null;
		
//		for ( int i = 0; i<values.length; i++){
//			System.out.println("val : " + values[i]);
//		}
		return values[index];
	}
	
	public	int		getIntPropertyWithIndex(String key, int index){
		String val = getPropertyWithIndex(key, index);
		if( val == null ) return -1;
		
		if( val.startsWith("#") ) return hexToInt ( extractHex(val) );
		else return  extractInt(val);
	}
	
	public	String		getHexPropertyWithIndex(String key, int index){
		String val = getPropertyWithIndex(key, index);
		return  extractHex(val);
	}
	
	protected	int	extractInt(String value){
		value   = value.replaceAll("[^0-9]", "");
		int int_val = Integer.parseInt(value);
		return int_val;
	}
	
	protected	String	extractHex(String value){
		value   = value.replaceAll("[^0-9A-Fa-f]", "");		
//		System.out.println("value :" + value );
		return value;		
	}
	
	protected	int		hexToInt( String hex ){
		return Integer.parseInt(hex, 16);
	}
}
