/**
 * MongoDB 의 데이터베이스를 다루는 기능을 제공함
 * 
 */
package com.sunonerim.framework.mongodb;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sunonerim.framework.core.Parameter;

public class MongoDBMan {
//	String	Host = "54.65.237.205";
	
	public static String	Host 	= "localhost";	
	public static String	DB   	= "testdb";		//"allkoreaDB";
	public static String	MetaTable  = "SAFMeta";	// Meta 정보 데이터베이스
	

	public final	static String	CollectionPrefix = "@";	// 콜렉션임을 표시한다.
	public final	static String	FieldPrefix 	 = "#";
	
	int		DefaultPageSize = 10;
	int		DefaultPage     = 1;
	
	public	MongoDBMan( String _host, String _db){
		Host = _host;
		DB = _db;
	}
	
	
	/**
	 * Parameter에 있는 사용자 아이디를 이용하여 SAFMeta 에 저장된 해당 사용자의 데이터를 저장하는 Collection 명을 가져온다.
	 * 
	 * @param _param
	 * @return	collection의 이름 		
	 * @throws UnknownHostException
	 */
	public	String	getCollectionFromParameter( Parameter _param) throws UnknownHostException{
    	MongoClient mongo = new MongoClient( Host, 27017);
    	
    	/**** Get database ****/
    	// if database doesn't exists, MongoDB will create it for you
    	DB db = mongo.getDB( DB );
    	DBCollection coll = db.getCollection(MetaTable);
    	
    	BasicDBObject query = new BasicDBObject("UserID", _param.getLoginUserID() );
    	BasicDBObject fields = new BasicDBObject();
    	fields.put("UserCollection", 1);
    	
    	DBCursor cursor = coll.find(query,fields);
    	String	collection  = null;
    	try {
    		if( cursor.count() == 1 ){
    			cursor.next();
    			DBObject obj = cursor.curr();
    			collection = obj.get("UserCollection").toString();    			
    		}
    	} finally {
    	   cursor.close();
    	}
    	
    	return collection;
	}
	
	public	DBObject[]	getDocumentMeta(String _user_id, String _doc_type) throws UnknownHostException{
    	MongoClient mongo = new MongoClient( Host, 27017);
    	
    	/**** Get database ****/
    	// if database doesn't exists, MongoDB will create it for you
    	DB db = mongo.getDB( DB );
    	DBCollection coll = db.getCollection(MetaTable);
    	
    	BasicDBObject query = new BasicDBObject("UserID", _user_id );    	
    	BasicDBObject fields = new BasicDBObject();
    	fields.put("DocMetas", 1);
    	
    	DBObject obj = coll.findOne(query,fields);
    	String	collection  = null;
    	
		Map objs = obj.toMap();
		Set<String> keys = objs.keySet();
		
		for ( Object key : keys ){
			System.out.println("key is " + key.toString() );
		}

		
    	System.out.println("Collection >>" + collection );
    	return null;		
	}
	

	public	List<DBObject> select(String _coll, BasicDBObject _query, String[] _show_fields, BasicDBObject _sort) throws UnknownHostException{
		return select( _coll, _query, _show_fields, _sort, DefaultPage, DefaultPageSize );    			
	}
	
	
	public	List<DBObject> select(String _coll, BasicDBObject _query, String[] _show_fields, BasicDBObject _sort, int _page, int _page_size) throws UnknownHostException{
		MongoClient mongo = new MongoClient( Host, 27017);
    	
    	/**** Get database ****/
    	// if database doesn't exists, MongoDB will create it for you
    	DB db = mongo.getDB( DB );
    	DBCollection coll = db.getCollection( _coll );
    	

    	List<DBObject>	doc_list = null;
    	
    	BasicDBObject fields = new BasicDBObject();
    	if( _show_fields != null ){
    		for ( String show_field : _show_fields )
    			fields.put( show_field , 1);
    	}
    	fields.put( "_id" , 0);
    	
    	DBCursor cursor = coll.find( _query, fields).sort(_sort).skip( (_page-1)*_page_size).limit(_page_size);
    	if( cursor.count() > 0 ) doc_list = new ArrayList<DBObject>();
    	
    	try {
    			while ( cursor.hasNext()){
	    			cursor.next();
	    			DBObject obj = cursor.curr();	  
	    			doc_list.add(obj);	    			
    			}
    	} finally {
    	   cursor.close();
    	}
		return doc_list;    			
	}
	
	
	
	public	void	insertWithParameter(String _coll, Parameter _param) throws UnknownHostException{
    	MongoClient mongo = new MongoClient( Host, 27017);
    	
    	/**** Get database ****/
    	// if database doesn't exists, MongoDB will create it for you
    	DB db = mongo.getDB( DB );
     
    	
    	    	
    	// if collection doesn't exists, MongoDB will create it for you
    	DBCollection table = db.getCollection( _coll );
    	
    	BasicDBObject insertQuery = new BasicDBObject();
    	insertQuery.putAll( _param.getParameterMap() );    	    		
    	table.insert( insertQuery );		
	}
	
	
	/**
	 * 몽고디비의 내용을 갱신한다.
	 *  
	 * 조건절에 들어가는 파라미터의 이름은 #로 시작한다. 
	 * 예를 들면 parameter.put("#writer", "Lim SY");의 경 
	 * writer 라는 필드 값이 Lim SY 인 레코드가 갱신의 대상이 된다.
	 * 
	 * 갱신하고자는 필드는 그냥 필드이름, 값을 쌍으로 parameter에 입력된 아이템이다. 
	 *  
	 * @param _coll
	 * @param _param
	 * @throws UnknownHostException
	 */
	public	void	updateWithParameter(String _coll, Parameter _param ) throws UnknownHostException{
		MongoClient mongo = new MongoClient( Host, 27017);
    	
    	/**** Get database ****/
    	// if database doesn't exists, MongoDB will create it for you
    	DB db = mongo.getDB( DB );
     
    	/**** Get collection / table from 'testdb' ****/
    	// if collection doesn't exists, MongoDB will create it for you
    	DBCollection table = db.getCollection( _coll );
    	
    	// 조건 질의 객체와 갱신객체를 생성한다. 
    	// searchQuery를 작성한다.
    	BasicDBObject updateQuery = new BasicDBObject();
    	BasicDBObject updateObject = new BasicDBObject();
    	BasicDBObject searchQuery = new BasicDBObject();
    	
    	
    	Set<String>	keys = _param.getParameterMap().keySet();
    	
    	for ( String key : keys ){
    		if( key.startsWith( FieldPrefix )){
    			if (key.equals("#_id") ){
    				searchQuery.put("_id", new ObjectId((String)_param.get(key)));
    			} else {
    				String condition_key = key.substring(1);
        			searchQuery.put( condition_key, _param.get(key));	
    			}
    			
    		} else {
    			System.out.println("Key:" + key  + ">>updated");
    			updateObject.append( key, _param.get(key));    			
    		}
    	}    	    		
    	
    	updateQuery.append("$set", updateObject );
    	table.updateMulti(searchQuery, updateQuery);
	}
	
	
	/**
	 * 
	 * @param _coll
	 * @param _update_query
	 * @param _search_obj
	 * @throws UnknownHostException
	 */
	public	void	updateWithQuery( String _coll, BasicDBObject _update_obj, BasicDBObject _search_obj) throws UnknownHostException{
	MongoClient mongo = new MongoClient( Host, 27017);
    	

    	// if database doesn't exists, MongoDB will create it for you
    	DB db = mongo.getDB( DB );
     
    	// if collection doesn't exists, MongoDB will create it for you
    	DBCollection table = db.getCollection( _coll );
    	
    	// 조건 질의 객체와 갱신객체를 생성한다. 
    	// searchQuery를 작성한다.
    	
    	BasicDBObject update_query = new BasicDBObject();
    	
    	update_query.append("$set", _update_obj );
    	table.updateMulti(_search_obj, update_query);		
	}
	
	
		
	public static void test_insert(){
		MongoDBMan MMan = new MongoDBMan(Host, DB);
		
	    Parameter	parameter = new Parameter(); 
	    parameter.setLoginUserID("sunone");
	    
	    parameter.put("title",   "韩国女团4MINUTE - Crazy");
	    parameter.put("content", "4minute#第六张迷你专辑主打曲《疯》(Crazy)完整版公开~!此次新曲是最近流行的“Trap Hip-Hop”曲风,强烈的节奏感,充满Power的Rap十分抓耳,搭配米妮们强劲的舞姿,让人疯狂中毒");
	    parameter.put("createDatetime",  new Date(System.currentTimeMillis()));
	    parameter.put("writer", "举报当前视频");
	    parameter.put("tags.0", "school");
	    parameter.put("tags.1", "ㅁㄹㅅㄷ");
	    parameter.put("tags.2", "이상함");
	    
	    try {
			MMan.insertWithParameter("Products", parameter);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}		
	}
	
	
	/**
	 * 배열을 값을 입력하는 테스트 
	 */
	public static void test_inset_array(){
		MongoDBMan MMan = new MongoDBMan(Host, DB);
		
	    Parameter	parameter = new Parameter(); 
	    parameter.setLoginUserID("sunone");
	    
	    parameter.put("title",   "韩国女团4MINUTE - RRRR");
	    parameter.put("content", "4minute#第六张迷你专辑主打曲《疯》(Crazy)完整版公开~!此次新曲是最近流行的“Trap Hip-Hop”曲风,强烈的节奏感,充满Power的Rap十分抓耳,搭配米妮们强劲的舞姿,让人疯狂中毒");
	    parameter.put("createDatetime",  new Date(System.currentTimeMillis()));
	    parameter.put("writer", "举报当前视频");
	    parameter.put("tags", new String[] {"school", "after", "fx"} );

	    try {
			MMan.insertWithParameter("contents", parameter);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}		
	}
	
	public static void test_update(){
		
		MongoDBMan MMan = new MongoDBMan(Host, DB);
		
	    Parameter	parameter = new Parameter(); 
	    	    
	    parameter.put("image",   "http://www.journaldev.com/wp-content/uploads/2014/12/3d_book-268x268.png");
	    parameter.put("title",   "샘플 프로그램>>>>>");
	    parameter.put("content", "时尚杂事《OH BOYS》方通过官方facebook上传了一张照片，并发文道：“赵寅成2006。");
	    parameter.put("tags",    "BIY,JX,KLS,터치미");
	    parameter.put("#_id",    "54dbfb990364a961d730f5f8");

	    try {
			MMan.updateWithParameter("contents", parameter);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}		
	}	
	
	public static void test_update_array(){
		
		MongoDBMan MMan = new MongoDBMan(Host, DB);
		
//	    Parameter	parameter = new Parameter(); 	    	    	    
//	    parameter.put("tags", new String[] {"school", "after", "fx"} );
//	    parameter.put("#_id",    "54dbfb990364a961d730f5f8");


		
		BasicDBObject query   = new BasicDBObject("DocMetas.0.DocType", "school" );
		BasicDBObject set_obj = new BasicDBObject("DocMetas.0.DocType", "basic" );
		
		try {
			MMan.updateWithQuery( MetaTable, set_obj, query);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}		
		System.out.println("test_update_array( ) DONE");
	}


	public static void test_update_array2(){
		
		MongoDBMan MMan = new MongoDBMan(Host, DB);
		
	    Parameter	parameter = new Parameter(); 	    	    	    
	    parameter.put("DocMetas.0.DocType", new String[] {"school", "after", "fx"} );
	    parameter.put("#DocMetas.0.DocType", "Order");

		
		
		try {
			MMan.updateWithParameter( MetaTable,parameter );
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}		
		System.out.println("test_update_array2( ) DONE");
	}

	
	
	public static void test_user_collection(){
		
		MongoDBMan MMan = new MongoDBMan(Host, DB);
		
	    Parameter	parameter = new Parameter();
	    parameter.setLoginUserID("sunonerim");	    
	    parameter.put("tags", new String[] {"school", "after", "fx"} );
	    parameter.put("#_id",    "54dbfb990364a961d730f5f8");

	    try {
			String coll = MMan.getCollectionFromParameter( parameter);
			System.out.println("Collection " + coll );			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}		
	}

	
	public static void test_user_doc_metas(){
		
		MongoDBMan MMan = new MongoDBMan(Host, DB);
		
	    Parameter	parameter = new Parameter();
	    parameter.setLoginUserID("sunonerim");	    
	    parameter.put("tags", new String[] {"school", "after", "fx"} );
	    parameter.put("#_id",    "54dbfb990364a961d730f5f8");

	    try {
			MMan.getDocumentMeta( "sunonerim", "basic");
					
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}		
	}
	
	
	public static void test_user_select() {
		MongoDBMan MMan = new MongoDBMan(Host, DB);
    	BasicDBObject query = new BasicDBObject("DocMetas.DocFields.fieldName", "name" );
//    	BasicDBObject query = new BasicDBObject("DocMetas.DocType", "basic" );
    	try {
			MMan.select(MetaTable, query, new String[]{"UserID", "DocMetas.DocType"}, null);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	System.out.println("test_user_select( ) DONE");
	}

	/**
	 * 아래와 같이 특정 배열의 인덱스를 이용하는 질의의 결과. 아무런 문서도 출력되지 않는다.
	 * 내용 수정
	 * [ ]  대신 . 으로 사용하여 질의할 수 있다.
	 */
	public static void test_user_select_by_array_index() {
		MongoDBMan MMan = new MongoDBMan(Host, DB);
    	BasicDBObject query = new BasicDBObject("DocMetas.0.DocFields.0.fieldName", "name" );
    	
    	try {
			MMan.select(MetaTable, query, null, null );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	System.out.println("test_user_select_by_array_index( ) DONE");
	}
	
	
	
	
	public static void main(String[] args) {
//		test_inset_array();
//		test_user_doc_metas();
		test_insert();
//		test_update();
		
//		test_user_select();
//		test_update_array();
//		test_user_select();
		
	}
}
