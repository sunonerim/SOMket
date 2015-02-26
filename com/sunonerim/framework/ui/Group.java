// version 1.6
//	fix JSON string producing error when string type field value contain "
package	com.sunonerim.framework.ui;

public class Group  {

	private	String 	GroupID		= null;			//그룹아이디
	private	String 	GroupName	= null;			//그룹이름
	private	String 	Description	= null;			//설명


	public	void	setGroupID(String  _GroupID) {
		GroupID = _GroupID;
	}
	
	public	String  getGroupID() {
		return GroupID;
	}
	public	void	setGroupName(String  _GroupName) {
		GroupName = _GroupName;
	}
	
	public	String  getGroupName() {
		return GroupName;
	}
	public	void	setDescription(String  _Description) {
		Description = _Description;
	}
	
	public	String  getDescription() {
		return Description;
	}


	public boolean	isSamePrimaryKeyObject( Group _object ){
		if( GroupID == _object.GroupID	)return true;
		else return false;
    }
        

	public String	toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("=========== <Group> ============\n");
		builder.append("GroupID : " + GroupID + "\n");
		builder.append("GroupName : " + GroupName + "\n");
		builder.append("Description : " + Description + "\n");
		return builder.toString();	
	}

}