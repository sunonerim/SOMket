package com.sunonerim.framework.core;

import java.io.IOException;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sunonerim.framework.command.CommandItem;
import com.sunonerim.framework.command.ACommand;
import com.sunonerim.framework.command.io.CommandItemLoader;
import com.sunonerim.framework.exception.BException;
//import com.sunonerim.framework.io.IOutputWriter;

public abstract class ACommandExecutor {

	public 	Logger logger = Logger.getRootLogger();
	
	abstract public void doBeforeExecute( String command_id,  Parameter	param ) throws BException;
	abstract public void doAfterExecute(  String command_id,  Parameter	param,  ResultMap	result_map) throws BException;
	abstract public void reloadCommand();

	
	final static public	String	DummyComm = "/dummy";	// 사전에 정의된 Command
	final static public	String	ReloadComm = "/reload";
	
	static 				HashMap<String, CommandItem> Commands  = null ;
	
	
	public ACommandExecutor(){
		loadCommand();
	}
		
	public void addCommand(String command, String command_class) {
		addCommand( command,  command_class, null);
	}
	
	public void addCommand(String command, String command_class, String writer_class) {
		if( Commands == null ) return ;
		
		CommandItem cmd_item = new CommandItem(command, command_class, writer_class);		
		Commands.put(command, cmd_item);
	}

	public void loadCommand() {
		this.Commands =  new HashMap<String, CommandItem>();				
		try {			
			addCommand( DummyComm,  "com.sunonerim.framework.command.DummyCommand", null);				// 
	        addCommand( ReloadComm, "com.sunonerim.framework.command.ReloadCommand", null);				// 			
		} catch (Exception e) {
			e.printStackTrace();
		}
		reloadCommand();
	}

	/**
	 * properties 파일을 이용하여 커맨드를 로드하는 로직
	 * 
	 * @param command_list_file_path
	 */
	public void loadCommandDefault(String command_list_file_path) {
		Class cmd_cls;

		// load command ID and command class from the command.list file.
		URL url = ClassLoader.getSystemResource(command_list_file_path);

		Properties props = new Properties();
		try {
			props.load(url.openStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (Enumeration e = props.propertyNames(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			System.out.println(key + ":" + props.getProperty(key));
			addCommand(key, props.getProperty(key),null );
		}
	}

	/**
	 * 
	 * @param cmd_id
	 * @return
	 * @throws CommandException
	 */
	public ACommand lookupCommand(String cmd_id) throws BException {
		 System.out.println("in lookupCommand>" + cmd_id);
		if (cmd_id == null) {
			System.out.println("cmd_id NULL");
			throw new BException("command Identifier must be requierd!");
		}

		if (Commands == null) {
			System.out.println("commands NULL");
			throw new BException("commands is not initiated!");
		}

		if (!Commands.containsKey(cmd_id)) {
			throw new BException("Invalid Command Identifier");
		}

		// System.out.println("before Command find OK.");
		CommandItem cmd_item = (CommandItem) Commands.get(cmd_id);
		ACommand command = cmd_item.getCommandInstance();
		return command;
	}

	
	public	String	getWriterClassOfCommand( String cmd_id) {
		CommandItem cmd_item = (CommandItem) Commands.get(cmd_id);
		if( cmd_item == null ) return null;
		return cmd_item.getWriterClass();
	}
	
	/*
	public	static Connection	createConnection() {
		Connection connection = null;
		try {
			Class.forName( CommandExecutor.Driver );
			try {
				connection = DriverManager.getConnection(CommandExecutor.Url, CommandExecutor.ID, CommandExecutor.Password);
				System.out.println("JDBC URL : " + CommandExecutor.Url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return connection;
	}	
	*/

	private ResultMap executeReal(String cmd_id, Parameter parameter) throws BException {
		
		System.out.println("before  execute " + cmd_id);

			ACommand cmd = lookupCommand(cmd_id);
			if (cmd == null) throw new BException("No Command exist");
			
			cmd.getResultMap().setParameter(parameter);		
			cmd.execute(parameter);
			return cmd.getResultMap();
		
	}

    private	ResultMap	checkAndDoReload(String cmd_id ){
	    if( cmd_id.equals(ReloadComm )) {
			ResultMap	result_map = new ResultMap(ReloadComm);
			result_map.addResult( "status", "All Commands have been reloaded");
			return result_map;
		} else return null;
    }
    
	/**
	 * 
	 * @param cmd_id
	 * @param parameter
	 * @param out
	 *           
	 * @return
	 * @throws BException
	 * @throws CommandException
	 */
	public ResultMap execute(String cmd_id, Parameter parameter) throws BException {
		
			doBeforeExecute( cmd_id, parameter );
			ResultMap	result_map = checkAndDoReload(cmd_id);
			if( result_map == null ) result_map = executeReal    ( cmd_id, parameter );
			doAfterExecute( cmd_id, parameter, result_map);
    		    		
			return result_map;
		
	}
}	
