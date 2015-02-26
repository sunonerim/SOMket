package com.sunonerim.framework.command;


import com.sunonerim.framework.core.Parameter;
import com.sunonerim.framework.core.ResultMap;
import com.sunonerim.framework.exception.BException;

import org.apache.log4j.Logger;

public abstract class ACommand {
	
	static protected Logger logger = Logger.getLogger( ACommand.class );
	
	protected 	ResultMap	ResultMap	= null;
	public 	abstract void 	execute(Parameter	parameter) throws BException ;	
	

	public ACommand() {
		this.ResultMap = new ResultMap(this.getClass().getSimpleName());

	}
	
	public ResultMap getResultMap() {
		return this.ResultMap;
	}

	public void setResultMap(ResultMap resultMap) {
		ResultMap = resultMap;
	}

	public	void	addResult( String key, Object value){
		ResultMap.addResult(key, value);
	}
	
}
