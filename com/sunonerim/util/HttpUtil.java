package com.sunonerim.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.sunonerim.framework.core.Parameter;


public class HttpUtil {

    public	static void	fillParameter(HttpServletRequest req, Parameter param) {
    	
    	// 한글 깨짐 현상을 막기의한 방법
//    	try {
//			req.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
    	
    	Enumeration<String> pnames = req.getParameterNames();
    	while (pnames.hasMoreElements()) {
    	    String pname 	= (String)pnames.nextElement();    	    
    	    String[] p_value 	= null;
			    
    	    p_value 		= req.getParameterValues(pname);
    	    
    	    if( p_value.length == 1 ){
    	    	System.out.println("p_value " + p_value[0]);
    	    	param.put(pname, p_value[0]);
    	    } else {
    	    	
    	    	param.put(pname, p_value);	
    	    }
    	}
    }
    
}
