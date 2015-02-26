/**
 * 커머스용으로 테스트를 한다.
 */
package com.sunonerim.app.commerce;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.sunonerim.framework.mongodb.MongoDBMan;


public class TestSelect {

	public static void test_user_select() {
		MongoDBMan MMan = new MongoDBMan(MongoDBMan.Host, MongoDBMan.DB);
		
    	try {
			MMan.select( "Products", new BasicDBObject(), null, null);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	System.out.println("test_user_select( ) DONE");
	}
	
	public static void main(String[] args) {
		test_user_select();
	}

}
