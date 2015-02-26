package com.sunonerim.framework.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * RecordList is ArrayList of ValueObject and also has field meta data at internal MetaMap
 * @author sunone
 *
 */
public class RecordList extends ArrayList {
	private	HashMap MetaMap = new HashMap();
	
	protected	class RecordMeta {
		public	String FieldName;
		public	String FieldLabel;
		public	int 	FieldType;
		public	int		  FieldLen;
	}
	
	public HashMap getMetaMap() {
		return MetaMap;
	}

	public void setMetaMap(HashMap metaMap) {
		MetaMap = metaMap;
	}

	public	void	addMetaItem( String field_name, String field_label, int field_type, int field_len ){
			RecordMeta	new_record_meta = new RecordMeta();
			new_record_meta.FieldName 	= field_name;
			new_record_meta.FieldLabel 	= field_label;
			new_record_meta.FieldType 		= field_type;
			new_record_meta.FieldLen 		= field_len;
			
			MetaMap.put(field_name, new_record_meta);
	}
	
	public	String	getMetaFieldLabel(String field_name ){
		
		RecordMeta	record_meta	= (RecordMeta) MetaMap.get(field_name);
		return record_meta.FieldLabel;
	}
	
	public	int	getMetaFieldType(String field_name ){
		RecordMeta	record_meta	= (RecordMeta) MetaMap.get(field_name);
		return record_meta.FieldType;
	}
	
	public	int	getMetaFieldLen(String field_name ){
		RecordMeta	record_meta	= (RecordMeta) MetaMap.get(field_name);
		return record_meta.FieldLen;
	}
	
	
	public	RecordList newInstanceWithMeta( ){
		RecordList rec_list = new RecordList();
		rec_list.copyMeta(this);
		return rec_list;
	}
	
	public	void	copyMeta( RecordList rec_list ) {
		setMetaMap(rec_list.getMetaMap());
	}
	
	public	Object	getFieldValue( int row_num, String field_name){
		Map	record = (Map) get(row_num);
		if( record == null ) return null;
		return record.get(field_name);
	}
}