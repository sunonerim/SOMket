package com.sunonerim.framework.core;


import java.util.HashMap;

import com.sunonerim.framework.exception.BException;


/**
 * 
 * 기존의 ResultMap에서 다음과 같은 사항이 바뀐었다. ( 2015/02/10) 
 * Data라는 멤버로 hashMap을 갖도록 하였다. 이유는 기본적으로 REsultMap의 내용을 클라이언트에 전달하는 경우
 * jSON의 형식이므로 이에 맞게 한번에 출력을 하기 위해서 멤버변수로 처리 하였다.
 *  
 * @author 임성원
 *
 */
public class ResultMap {
	
	private	boolean						Result 		= true;
	private	HashMap<String, Object>		Data 		= new HashMap();
	private	BException	Exception   = null;
	
	private	transient	String		CommandID 	= null;
	private	transient	Parameter	Parameter  	= null;	
		
	
	
	public static String	Success = "OK";
	public static String	Failure = "NG";
	public static String	Notice  = "NT";
	
	
	
	public	ResultMap(String command_id ){
		super();
		CommandID = command_id;
	}
	
	public Parameter getParameter() {
		return this.Parameter;
	}

	public void setParameter(Parameter aParameter) {
		this.Parameter = aParameter;
	}
	
	public boolean isResult() {
		return Result;
	}

	public void setResult(boolean result) {
		Result = result;
	}

	public	void	setError( BException exception){
		Result = false;
		setException( exception );
	}
	
	public String getCommandID() {
		return CommandID;
	}

	public void setCommandID(String commandID) {
		CommandID = commandID;
	}

		
	public	void	addResult( String key, Object value){
		Data.put(key, value);
	}
	
	public BException getException() {
		return Exception;
	}

	public void setException(BException exception) {
		Exception = exception;
	}

	public	void	ariseException(Exception e){
//		State = Failure;
//		
//		StringBuffer	sb = new StringBuffer();
//		
//		StackTraceElement[] 	stack_tracks = e.getStackTrace();
//		for ( int i=0; i<stack_tracks.length; i++ ){
//			sb.append(stack_tracks[i].toString() + "\n");
//		}
//		StackTrack = sb.toString();
//		setError( e.getMessage() );
//		
//		logger.error(StackTrack);
	}
	
	public	void	notice(String notice_msg){
//		State = Notice;
//		ErrorMessage = notice_msg;	
	}
	
	public	String	toString() {
		StringBuffer	sb = new StringBuffer();
		java.util.Set keyset = Data.keySet();
	
		Object[] hashkeys = keyset.toArray();
		for(int i=0; i<hashkeys.length; i++) {
		  
		   String key = (String)hashkeys[i];
		  
		   sb.append(key ).append(" - ").append( Data.get(key));
		}

		return sb.toString();
	}
}