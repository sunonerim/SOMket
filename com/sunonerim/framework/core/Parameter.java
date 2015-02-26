package com.sunonerim.framework.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.sunonerim.framework.exception.BException;

public class Parameter {
	String	CommandID	= null;	
	String	LoginUserID = null;	
	Object 	ClientData  = null;
	HashMap<String, Object> ParameterMap = new HashMap<String, Object>();

	
	public String getCommandID() {
		return CommandID;
	}

	public void setCommandID(String commandID) {
		CommandID = commandID;
	}

	public String getLoginUserID() {
		return LoginUserID;
	}

	public void setLoginUserID(String loginUserID) {
		LoginUserID = loginUserID;
	}

	public boolean isNull(String key) {
		return (this.ParameterMap.get(key) == null);
	}

	public void put(String key, Object value) {
		ParameterMap.put(key, value);
	}

	public Object get(String key) {
		return ParameterMap.get(key);
	}

	public Integer getInteger(String key) throws BException {
		return getInt(key);
	}
	public Integer getInt(String key) throws BException {

		Integer val = null;
		Object obj = this.ParameterMap.get(key);

		if (obj != null) {
			if (obj.toString().trim().length() == 0)
				return null;
			try {
				val = Integer.parseInt(obj.toString().trim());
			} catch (Exception e) {
				throw new BException(
						"Data Convesion Error : Parameter.getInt()");
			}
		}
		return val;
	}

	public Long getLong(String key) throws BException {
		Long val = null;
		Object obj = ParameterMap.get(key);

		if (obj != null) {
			if (obj.toString().trim().length() == 0)
				return null;
			val = new Long(Long.parseLong(obj.toString()));
		}
		return val;
	}

	public Double getDouble(String key) throws BException {
		Double val = null;
		Object obj = this.ParameterMap.get(key);

		if (obj != null) {
			val = new Double(Double.parseDouble(obj.toString()));
		}
		// try {
		// val = Double.parseDouble( Parameter.get(key).toString() );
		// } catch( Exception e ){
		// throw new BException(
		// "Data Convesion Error : Parameter.getdouble()");
		// }
		return val;

	}

	public Float getFloat(String key) throws BException {
		Float val = null;
		Object obj = this.ParameterMap.get(key);

		if (obj != null) {
			val = new Float(Float.parseFloat(obj.toString()));
		}
		// try {
		// val = Double.parseDouble( Parameter.get(key).toString() );
		// } catch( Exception e ){
		// throw new BException(
		// "Data Convesion Error : Parameter.getdouble()");
		// }
		return val;

	}
	
	public Boolean getBoolean(String key) {
		Boolean val = null;
		Object obj = this.ParameterMap.get(key);
		
		if( "yes".equals( obj.toString().toLowerCase() )) return true;
		else return false;				
	}

	
	public String getString(String key)  {
		String val = null;
		Object obj = this.ParameterMap.get(key);

		if (obj != null)
			val = obj.toString();

		return val;
	}

	
	public Object getClientData() {
		return ClientData;
	}

	public void setClientData(Object clientData) {
		ClientData = clientData;
	}

	
	public HashMap<String, Object> getParameterMap() {
		return ParameterMap;
	}

	public void setParameterMap(HashMap<String, Object> parameterMap) {
		ParameterMap = parameterMap;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("Command:").append(CommandID).append("\n");
		Set keys = this.ParameterMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String value = (String) it.next();
			sb.append(value).append(":").append(this.ParameterMap.get(value))
					.append("\n");
		}
		return sb.toString();
	}

	

}
