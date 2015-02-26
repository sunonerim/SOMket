package com.sunonerim.sample;

import com.sunonerim.framework.command.ACommand;
import com.sunonerim.framework.core.Parameter;
import com.sunonerim.framework.exception.BException;
import com.sunonerim.framework.ui.User;

public class SampleCommand extends ACommand {

	@Override
	public void execute(Parameter parameter) throws BException {
		addResult("Hello", "World");
		addResult("List", new String[] {"Font", "Line", "Shape"});
		addResult("User", new User("sunny", "임성원"));
	}

}
