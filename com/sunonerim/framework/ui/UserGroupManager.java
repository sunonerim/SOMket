package com.sunonerim.framework.ui;

import java.util.ArrayList;
import java.util.List;

public class UserGroupManager {
	List<User>		UserList	= null;
	List<Group>		GroupList	= null;
	List<Menu>		MenuList	= null;
	
	
	public	void	addGroup( Group _group ){
		if(GroupList == null )	GroupList = new ArrayList<Group>();
		GroupList.add(_group);
	}
	
	public	void	addUser ( User _user){
		if(UserList == null )	UserList = new ArrayList<User>();
		UserList.add(_user);
	}
	
	public	void	addMenu ( Menu _menu){
		if(MenuList == null )	MenuList = new ArrayList<Menu>();
		MenuList.add(_menu);
	}
	
	public	boolean	canAccessMenu( User _user, Menu _menu){
		return true;
	}
}
