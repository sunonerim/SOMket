package com.sunonerim.framework.exception;

public class BException extends Exception {

	String	Code = null;
	String	Reason = null;
	String	OptionMessage 	= null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 182828272L;

	public BException(String exception_reason ) {		
		super(exception_reason);
		Reason = exception_reason;
	}
	
	public BException(String exception_code, String exception_reason ) {		
		super(exception_reason);
		Code = exception_code;
		Reason = exception_reason;
	}

	public BException(String exception_code, String exception_reason , String option_content) {		
		super(exception_reason);
		Code = exception_code;
		Reason = exception_reason;
		OptionMessage = option_content;
	}
	
	public String getCode() {
		return Code;
	}

	public void setCode(String mExceptionCode) {
		this.Code = mExceptionCode;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String mExceptionReason) {
		this.Reason = mExceptionReason;
	}

	public String getOptionMessage() {
		return OptionMessage;
	}

	public void setOptionMessage(String mOptionContent) {
		this.OptionMessage = mOptionContent;
	}	
	
}
