// version 1.6
//	fix JSON string producing error when string type field value contain "
package	com.sunonerim.framework.ui;

import java.util.Set;

public class User  {

	private	String 	UserID	= null;				//유저아이디
	private	String 	UserName	= null;			//이용자 이름
	private	String 	Password	= null;			//암호
	private	String 	RelateID	= null;			//관련 식별자	
	private	transient 	Set<String>	GroupIDs = null;	

	public	User( String _user_id, String _user_name){
		UserID = _user_id;
		UserName = _user_name;
	}

	public	void	setUserID(String  _UserID) {
		UserID = _UserID;
	}
	
	public	String  getUserID() {
		return UserID;
	}
	public	void	setUserName(String  _UserName) {
		UserName = _UserName;
	}
	
	public	String  getUserName() {
		return UserName;
	}
	public	void	setPassword(String  _Password) {
		Password = _Password;
	}
	
	public	String  getPassword() {
		return Password;
	}
	public	void	setRelateID(String  _RelateID) {
		RelateID = _RelateID;
	}
	
	public	String  getRelateID() {
		return RelateID;
	}

	public	void	addGroupID( String _group_id){
		if( !GroupIDs.contains(_group_id)) GroupIDs.add(_group_id);		
	}

	public boolean	isSamePrimaryKeyObject( User _object ){
		if(  UserID == _object.UserID) return true;
		else return false;
    }
        

	public String	toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("=========== <User> ============\n");
		builder.append("UserID : " + UserID + "\n");
		builder.append("UserName : " + UserName + "\n");
		builder.append("Password : " + Password + "\n");
		builder.append("RelateID : " + RelateID + "\n");
		return builder.toString();	
	}

}