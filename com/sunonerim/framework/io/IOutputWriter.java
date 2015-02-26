package com.sunonerim.framework.io;


import java.io.IOException;
import java.io.OutputStream;

import com.sunonerim.framework.core.ResultMap;
import com.sunonerim.framework.exception.BException;

public interface IOutputWriter {
	public void write2Output(ResultMap result_map,  OutputStream out) throws IOException;
}
