package com.sunonerim.framework.io;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;


import com.google.gson.Gson;
import com.sunonerim.framework.core.ResultMap;

public class JsonWriter implements IOutputWriter {
	static final Logger logger = Logger.getLogger(JsonWriter.class);
	
	@Override
	public void write2Output(ResultMap result_map, OutputStream out) throws IOException {
		Gson	gson = new Gson();
		out.write(  gson.toJson(result_map).getBytes());
		out.flush();		
	}
}
