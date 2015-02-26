package com.sunonerim.framework.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.PropertyConfigurator;

import com.sunonerim.framework.core.ACommandExecutor;
import com.sunonerim.framework.core.Parameter;
import com.sunonerim.framework.core.ResultMap;
import com.sunonerim.framework.exception.BException;
import com.sunonerim.framework.io.IOutputWriter;




public abstract class BaseServlet extends HttpServlet {
	
	/**
	 * internal Command dldldsfsd
	 */
	private static final long serialVersionUID = 1L;
	
	final static public String	UserID = "userid";
	final static public	String	ReloadComm = "/reload";
	
	ACommandExecutor	CommandExecutor = null;


	abstract public void doBeforeExecute( String command_id, HttpServletRequest req, HttpServletResponse res, Parameter	param ) throws BException;
	abstract public void doAfterExecute(  String command_id, HttpServletRequest req, HttpServletResponse res, ResultMap	result_map) throws BException;
	abstract public void reloadCommand();
	abstract public IOutputWriter instanceOfOutputWriter(String writer_cls);
	
    public void init(ServletConfig config) throws ServletException {    	
        super.init(config);        
        
        
        // init database access object
        CommandExecutor.Driver	= getServletConfig().getInitParameter("SWAF.jdbc");
        CommandExecutor.Url		= getServletConfig().getInitParameter("SWAF.dburl");
        CommandExecutor.ID		= getServletConfig().getInitParameter("SWAF.user");
        CommandExecutor.Password= getServletConfig().getInitParameter("SWAF.password");
                               
        CommandExecutor = new ACommandExecutor();
        
        reloadCommand();       
        
        // log 파일 환경 설정
        String  conf_path = config.getServletContext().getRealPath("/WEB-INF/classes/visviva/conf/log4j.properties");
        PropertyConfigurator.configure(conf_path);        
    }

    
    public void service(HttpServletRequest req, HttpServletResponse res)   throws ServletException, IOException   {
    	// 세션에서 로그인 정보를 가져옵니다. 
    	HttpSession session = req.getSession();
    	String user_id =  (String) session.getAttribute(UserID);
    	
    	// get commandID from request object
    	String	cmd_id = req.getPathInfo();
    	
    	// build Parameter from request object.
        Parameter	parameter = new Parameter();   
        parameter.setLoginUserID(user_id);
		buildParameter(req,parameter);		        	
		ResultMap	result_map = null;
		
		parameter.setCommandID(cmd_id);
		
		
    	try {
    		doBeforeExecute( cmd_id, req, res, parameter);    		
    		result_map =  checkAndDoReload(cmd_id);    		
    		if( result_map == null ) result_map = CommandExecutor.execute( cmd_id, parameter  );    		      
    		doAfterExecute( cmd_id, req, res, result_map);
    		
    		// 이전에 로그린 되지않앗고 처리결과 로그인 유저아이디가 생성된 경우 로그인처리를 한다. 
    		if( user_id == null && parameter.getLoginUserID() != null ){
    			doLogin( req, parameter.getLoginUserID() );
    		}
    		
		} catch (BException e) {		
			if( result_map == null ) result_map = new ResultMap(cmd_id);
			result_map.setError(e);
		}
    	IOutputWriter writer = instanceOfOutputWriter( CommandExecutor.getWriterClassOfCommand(cmd_id) );
    	if ( writer == null ){
    		res.getOutputStream().write( result_map.toString().getBytes()  );
    		res.getOutputStream().flush();
    	} else {
    		writer.write2Output(result_map, System.out );
    		writer.write2Output(result_map, res.getOutputStream() );
    	}
    	    	
    }

    public	ResultMap	checkAndDoReload(String cmd_id ){
	    if( cmd_id.equals(ReloadComm )) {
			ResultMap	result_map = new ResultMap(ReloadComm);
			result_map.put( "status", "All Commands have been reloaded");
			return result_map;
		} else return null;
    }
    
    public	void	buildParameter(HttpServletRequest req, Parameter param) {
    	try {
			req.setCharacterEncoding("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	java.util.Enumeration pnames = req.getParameterNames();
    	while (pnames.hasMoreElements()) {
    	    String pname = (String)pnames.nextElement();    	    
    	    String p_value = null;
			    
    	    p_value =req.getParameter(pname);
    	    
    	    try {				
				p_value = new String ( req.getParameter(pname).getBytes("ISO-8859-1"), "UTF-8" );    	    	
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	    
    	    
    	    System.out.println ( "parameter name: " + pname + ",   value: " + p_value );
    	    param.put(pname, p_value);
    	}
    }
    
    public	void doLogin(HttpServletRequest req, String user_id){
    	if( user_id != null ) {
    		req.getSession().setAttribute(UserID, user_id);
    	}
    }
    
	public ACommandExecutor getCommandExecutor() {
		return CommandExecutor;
	}
	public void setCommandExecutor(ACommandExecutor CommandExecutor) {
		this.CommandExecutor = CommandExecutor;
	}    
}
