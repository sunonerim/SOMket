// version 1.6
//	fix JSON string producing error when string type field value contain "
package	com.sunonerim.framework.ui;

import java.util.Set;

public class Menu  {

	private	String 	MenuID		= null;		//메뉴아이디
	private	String 	MenuName	= null;		//메뉴명
	private	String 	LinkPageURL	= null;		//매뉴 링크
	private	Integer	MenuOrder	= null;		//메뉴 순서

	private	transient 	Set<String>	AccessGroupIDs = null;	// 해당 메뉴를 엑세스할 수 있는 그룹아이디
	
	public	void	setMenuID(String  _MenuID) {
		MenuID = _MenuID;
	}
	
	public	String  getMenuID() {
		return MenuID;
	}
	public	void	setMenuName(String  _MenuName) {
		MenuName = _MenuName;
	}
	
	public	String  getMenuName() {
		return MenuName;
	}
	public	void	setLinkPageURL(String  _LinkPageURL) {
		LinkPageURL = _LinkPageURL;
	}
	
	public	String  getLinkPageURL() {
		return LinkPageURL;
	}
	public	void	setMenuOrder(Integer  _MenuOrder) {
		MenuOrder = _MenuOrder;
	}
	
	public	Integer  getMenuOrder() {
		return MenuOrder;
	}

	public boolean	isSamePrimaryKeyObject( Menu _object ){
		if(MenuID == _object.MenuID
			)return true;
		else return false;
    }
        

	public String	toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("=========== <Menu> ============\n");
		builder.append("MenuID : " + MenuID + "\n");
		builder.append("MenuName : " + MenuName + "\n");
		builder.append("LinkPageURL : " + LinkPageURL + "\n");
		builder.append("MenuOrder : " + MenuOrder + "\n");
		return builder.toString();	
	}
	
}