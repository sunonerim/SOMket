/**
 * Open Market 구조의 전자상거애 코어 로직임
 */
package com.sunonerim.app.commerce;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sunonerim.framework.mongodb.MongoDBMan;

public class CommerceMan extends MongoDBMan{
	/** Collection List **/
	public	static	final	String	ProductsCollection	= "Products";	// PRODUCTS Collection
	public	static	final	String	CategoryCollection	= "Categorys";	// PRODUCTS Collection
	
	/** Products Fields **/
	public	static	final	String	UserId		= "userid";		// userid field
	public	static	final	String	Category	= "category";	// category field
	
	public	static	final	String	DescOrderPrefix	= "-";	// descending order prefix
	
	public	CommerceMan (){
		super(MongoDBMan.Host, MongoDBMan.DB);
	}
	

	/**
	 * 조건에 맞는 Products의 목록을 가져옴
	 *  
	 * @param _user_id	사용자의 아이디로 여기서는 머천트의 아이디 임
	 * @param _category 상품이 소속된 카테고리 정보로 카테고리의 구애없이 조회하는 경우 NULL 값임
	 * @param _fields   결과로 받아오는 필드의 이름. 만약 sub entity 의 경우 DOT. 으로 하여 표현한다. options.optionLabel의 경우 옵셥라벨 정보는 결과의 대상 
	 * @param _order_by 소팅하고자는 하는 필드이름의 문자열배열이다. - 로 시작하는 경우 해당 필드는 역순으로 소팅됨
	 * @param _page     결과 문서의 갯수별 페이지수 예를 들면 _page_size가 10이고 _page 2 인 경우 11번째 부터 20번째 문서를 리턴한다.
	 * @param _page_size 결과문서의 갯수
	 * @return	조건에 맞는 문서 DBOject의 List
	 */
	public	List<DBObject>	selectProducts( String _user_id, String _category, String[] _fields, String[] _order_by, int _page, int _page_size){
		
		// 상품의 선택 조건을 생성한다.
		BasicDBObject	select_query = new BasicDBObject( UserId, _user_id );
		if( _category != null ) select_query.put(Category, _category);
		
		// make up Sort condition
		BasicDBObject	sort = new BasicDBObject();
		for ( String order : _order_by ){
			if( order.startsWith(DescOrderPrefix) ) {
				// 역순으로 소팅
				sort.put( order.substring(1) , -1);
			} else 
				sort.put( order, 1);	
		}
		
		try {
			return select( ProductsCollection, select_query,_fields, sort, _page, _page_size);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
		return null;	
	}

	/**
	 * 조건에 맞는 Products의 목록을 가져옴. 이때 모든 필드의 값을 가져온다.
	 * 
	 * @return
	 */
	public	List<DBObject>	selectProductsAllField( String _user_id, String _category, String[] _order_by, int _page, int _page_size){
		return selectProducts(_user_id, _category, null, _order_by, _page, _page_size);
	}
	
	
	public static void main(String[] args) {
		
		CommerceMan	comm_man = new CommerceMan();		
		List<DBObject>	product_list = comm_man.selectProducts("sunonerim", "ae1201", new String[]{ "price", "name", "options.optionLabel"}, new String[]{ "price", "-name"}, 1, 10);
		for ( DBObject product : product_list){
			System.out.println("OBJ>>" + product.toString() );
		}
    	
    	System.out.println("============================================================================");
    	
    	product_list = comm_man.selectProducts("sunonerim", null, new String[]{ "price", "name"}, new String[] {"-price", "name"}, 3,3);
		for ( DBObject product : product_list){
			System.out.println("OBJ>>" + product.toString() );
		}
	}
}
