package com.sunonerim.framework.command;

import com.sunonerim.framework.core.Parameter;
import com.sunonerim.framework.exception.BException;

public class DummyCommand extends ACommand {

	@Override
	public void execute(Parameter parameter) throws BException {
		// TODO Auto-generated method stub	
		addResult("Message", "Hello, this is from the Sunonerim Web Application Framework");				
	}
}
