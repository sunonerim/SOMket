package com.sunonerim.framework.command;

import com.sunonerim.framework.exception.BException;

public class CommandItem {

	/**
	 * Command
	 * 
	 * @param parameter_map
	 * @return XML
	 * @throws CommandException
	 */
	private String CommandCD;
	private String ClassName;	
	private	String WriterClass;

	public CommandItem(String cmd_cd, String class_name) {
		super();
		CommandCD 	= cmd_cd;
		ClassName 	= class_name;
		WriterClass	= null;
	}
	
	public CommandItem(String cmd_cd, String class_name, String writer_class) {
		super();
		CommandCD 	= cmd_cd;
		ClassName 	= class_name;
		WriterClass	= writer_class;
	}

	public String getCommandCD() {
		return CommandCD;
	}

	public void setCommandCD(String commandCD) {
		CommandCD = commandCD;
	}

	public ACommand getCommandInstance() throws BException {
		Class cmd_cls = null;
		try {
			cmd_cls = Class.forName(ClassName);			
			ACommand cmd = (ACommand) cmd_cls.newInstance();
			return cmd;
		} catch (Exception e) {
			System.out.println("NO command class exist --" + ClassName);
			throw new BException(ClassName + " Not Exist Coammnd ?");
		}
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return ClassName;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		ClassName = className;
	}

	public String getWriterClass() {
		return WriterClass;
	}



	public void setWriterClass(String writerClass) {
		WriterClass = writerClass;
	}

}
