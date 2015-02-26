package com.sunonerim.framework.command.io;


import java.util.HashMap;
import java.util.List;

import com.sunonerim.framework.command.CommandItem;

public abstract class CommandItemLoader {
	HashMap<String, CommandItem>	Commands = null;
	
	abstract public	List	loadCommandItems() throws Exception;
	
	public	HashMap<String, CommandItem> load() throws Exception{
		
		List comm_item_list = loadCommandItems();
		
		for ( int i=0; i<comm_item_list.size(); i++ ){
			CommandItem comm_item = (CommandItem)comm_item_list.get(i);			
			put(  comm_item );	
		}
		return	Commands;
	}
	
	protected	void	put( CommandItem cmd_item){
		if( Commands == null ) Commands = new HashMap<String, CommandItem>();
		Commands.put(cmd_item.getCommandCD(), cmd_item );
	}
}
