package com.sunonerim.sample;

import java.io.IOException;


import com.sunonerim.framework.core.ACommandExecutor;
import com.sunonerim.framework.core.Parameter;
import com.sunonerim.framework.core.ResultMap;
import com.sunonerim.framework.exception.BException;
import com.sunonerim.framework.io.JsonWriter;


public class CommandExcutorSample extends ACommandExecutor {
	@Override
	public void doBeforeExecute(String command_id, Parameter param)
			throws BException {
		System.out.println("doBeforeExecute");

	}

	@Override
	public void doAfterExecute(String command_id, Parameter param,
			ResultMap result_map) throws BException {
		// TODO Auto-generated method stub
		System.out.println("doAfterExecute");
	}

	@Override
	public void reloadCommand() {
		// TODO Auto-generated method stub
		System.out.println("reloadCommand");
		this.addCommand("/sample", "com.sunonerim.sample.SampleCommand");
	}
	
	public static void main(String[] args) {
		CommandExcutorSample  sampleCmdExe = new CommandExcutorSample();
		
		
	    Parameter	parameter = new Parameter(); 
	    parameter.setLoginUserID("sunone");
	     
	    // 어떻게 될까 ?
	    ResultMap	result = null;
		try {
			result = sampleCmdExe.execute( "/sample" ,  parameter);
		} catch (BException e) {
			e.printStackTrace();
		}
		
		
				
		JsonWriter	json_writer = new JsonWriter();
		
		try {
			json_writer.write2Output(result, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
