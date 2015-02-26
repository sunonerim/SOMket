package	com.sunonerim.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.sunonerim.framework.db.RecordList;
import com.sunonerim.util.Seqs;


public class SeqsDao {

    // Cache contents:
    private boolean cacheOk;
    private List cacheData;


    public SeqsDao() {
        resetCache();
    }

    private void resetCache() {
		
	}
	
    public Seqs getObject(Connection conn
                                  , String  _SeqID
                                  ) throws SQLException {

 		  String sql = "SELECT * FROM Seqs ";
 		  sql       += " WHERE ( 1=1  AND SeqID = ?  ) ";
 		  sql       += " ORDER BY    SeqID ASC   ";
 		  


		PreparedStatement stmt = conn.prepareStatement(sql);

          stmt.setString ( 1, _SeqID);

		
		Seqs valueObject = null;
		ResultSet result = null;
		
		
          try {
              result = stmt.executeQuery();
			  Set	column_set = buildColumnSet(null, result);
              if( result.next() ) {
              	valueObject = new Seqs();
		if( existColumn(  column_set, "SeqID")) valueObject.setSeqID(result.getString ("SeqID")); 	
				if( existColumn(  column_set, "SeqNo")) valueObject.setSeqNo( getIntWithNull ( result ,  "SeqNo"));  
						}
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

    	
    	return valueObject;		
    }


    public List loadAll(Connection conn) throws SQLException {

          // Check the cache status and use Cache if possible.
          if (cacheOk) {
              return cacheData;
          }
          String sql = "SELECT * FROM Seqs ORDER BY    SeqID ASC   ";
          List searchResults = listQuery(conn, sql);

          // Update cache and mark it ready.
          cacheData = searchResults;
          cacheOk = true;

          return searchResults;
    }
    
    /**
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    public RecordList listQuery(Connection conn, String sql) throws SQLException {
		  PreparedStatement stmt = conn.prepareStatement(sql);
          RecordList searchResults = new RecordList();
          ResultSet result = null;

          try {
              result = stmt.executeQuery();
			  Set	column_set = buildColumnSet(searchResults, result);
			  			  
              while (result.next()) {
                   Seqs valueObject = new Seqs();
		if( existColumn(  column_set, "SeqID")) valueObject.setSeqID(result.getString ("SeqID")); 	
				if( existColumn(  column_set, "SeqNo")) valueObject.setSeqNo( getIntWithNull ( result ,  "SeqNo"));  
		

            if( hasSamePrimaryKeyObject(searchResults, valueObject) == null)
                   		searchResults.add(valueObject);
			}
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

          return searchResults;
    }
    
    //    //
    // Seqs instance return.
    // 
    public Seqs	loadOne(Connection conn, String sql) throws SQLException {
    	
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		Seqs valueObject = null;
		ResultSet result = null;

          try {
              result = stmt.executeQuery();
			  Set	column_set = buildColumnSet(null, result);
			  
              if( result.next() ) {
              	valueObject = new Seqs();
		if( existColumn(  column_set, "SeqID")) valueObject.setSeqID(result.getString ("SeqID")); 	
				if( existColumn(  column_set, "SeqNo")) valueObject.setSeqNo( getIntWithNull ( result ,  "SeqNo"));  
						}
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
    	return valueObject;
    }
    
    
    
    	//    	//
    	//	
        public ArrayList searchMatching(Connection conn, Seqs valueObject) throws SQLException {

          RecordList searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM Seqs WHERE 1=1 ");
          
          if (valueObject.getSeqID() != null ) {
              if (first) { first = false; }
              sql.append( " AND SeqID LIKE '%" + valueObject.getSeqID() + "%'" );
          }
          if (valueObject.getSeqNo() != null ) {
              if (first) { first = false; }
              sql.append(  " AND SeqNo = " + valueObject.getSeqNo()  );
          }
          sql.append(" ORDER BY    SeqID ASC  ");

          // Prevent accidential full table results.
          // Use loadAll if all rows must be returned.
          if (first)
               searchResults = new RecordList();
          else
               searchResults = listQuery(conn, sql.toString());

          return searchResults;
    }
    
    
    
    
    
    /** 
     *	INSERT A Record into Database	
     *
     *
     */
    public synchronized void create(Connection conn, Seqs valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO Seqs (     SeqID  ,  SeqNo ) VALUES (      ?   ,    ?  ) ";
               stmt = conn.prepareStatement(sql);
               
               int i=1;
               stmt.setString ( i, valueObject.getSeqID());
				i++;
               if( valueObject.getSeqNo() != null ) stmt.setInt ( i, valueObject.getSeqNo());
               else stmt.setNull( i, java.sql.Types.INTEGER  );               
				i++;

               int rowcount = databaseUpdate(conn, stmt);
               if (rowcount != 1) {
                    //System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("PrimaryKey Error when updating DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }


    }
    
    
    public void save(Connection conn, Seqs valueObject) throws SQLException {

          String sql = "UPDATE Seqs SET SeqNo = ? WHERE 1 = 1  AND SeqID = ? ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
               if( valueObject.getSeqNo() != null) stmt.setInt ( 1, valueObject.getSeqNo());
               else stmt.setNull( 1, java.sql.Types.INTEGER  );               

              stmt.setString (2, valueObject.getSeqID());

              int rowcount = databaseUpdate(conn, stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be saved! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public void saveNotNull(Connection conn, Seqs valueObject) throws SQLException {
    	ArrayList	params = new ArrayList();
    	
        String sql = "UPDATE Seqs SET ";
        String set_sql = "";
        String where_sql = "";

          PreparedStatement stmt = null;
        if( valueObject.getSeqNo() != null) {
        	if( set_sql.length() > 0 ) 	set_sql += ",";
        	set_sql +=" SeqNo = ? ";
        	params.add(valueObject.getSeqNo());
        }

        if( where_sql.length() > 0 ) where_sql += " and ";
        where_sql += "SeqID = ? ";
        params.add(valueObject.getSeqID());
        sql += set_sql + "where " + where_sql ;
        
        try {
            stmt = conn.prepareStatement(sql);
        
	        for ( int i=0; i<params.size(); i++){
	        	stmt.setObject(i+1, params.get(i));
	        }
        

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                 //System.out.println("Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                 //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                 throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
        
    }
    


    public void delete(Connection conn, Seqs valueObject) 
          throws  SQLException {

          String sql = "DELETE FROM Seqs WHERE 1 = 1  AND SeqID = ? ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString (1, valueObject.getSeqID());

              int rowcount = databaseUpdate(conn, stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be deleted (PrimaryKey not found)");
                   //throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }
    
    
    
    public int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {
          int result = stmt.executeUpdate();
          resetCache();
          return result;
    }

    protected Seqs	hasSamePrimaryKeyObject( List list, Seqs object){
    	for(int i=0; i<list.size(); i++ ){
           if ( !object.getSeqID().equals( ((Seqs)list.get(i)).getSeqID() )) break;					 
    	}
    	return null;
    }    
    

	public	Map	createMapFromObject(  Seqs  obj){
		HashMap	objmap = new HashMap();
		objmap.put("SeqID", obj.getSeqID());
		objmap.put("SeqNo", obj.getSeqNo());
		return objmap;
	}
	
    protected   Long getLongWithNull ( ResultSet rs, String column_name ) throws SQLException{
    	Long val = null;
    	val = new Long( rs.getLong(column_name));
    	if ( rs.wasNull() ) return null;
    	else return val;
    }

    protected   Integer getIntWithNull ( ResultSet rs, String column_name ) throws SQLException{
    	Integer val = null;
    	val = new Integer( rs.getInt(column_name));
    	if ( rs.wasNull() ) return null;
    	else return val;
    }

    protected   Float getFloatWithNull ( ResultSet rs, String column_name ) throws SQLException{
    	Float val = null;
    	val = new Float( rs.getFloat(column_name));
    	if ( rs.wasNull() ) return null;
    	else return val;
    }
    
    protected   Double getDoubleWithNull ( ResultSet rs, String column_name ) throws SQLException{
    	Double val = null;
    	val = new Double( rs.getDouble(column_name));
    	if ( rs.wasNull() ) return null;
    	else return val;
    }

    protected   boolean	existColumn( Set col_set, String col_name ){
    	col_name = col_name.toUpperCase();
    	
    	return ( col_set.contains(col_name)); 
    }
    
    protected	Set	buildColumnSet ( RecordList	results, ResultSet rs){
    	if ( rs == null ) return null;

    	HashSet	 column_set = new HashSet();
    	
    	ResultSetMetaData rsmd;		
		try {
			rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
		     for ( int i=1; i<=numberOfColumns; i++ ){		    	 
		    	 column_set.add( rsmd.getColumnName(i).toUpperCase() );
		    	 if( results != null ) results.addMetaItem(rsmd.getColumnName(i).toUpperCase(), rsmd.getColumnLabel(i),  rsmd.getColumnType(i),  rsmd.getPrecision(i));		    	 
		    }
		} catch (SQLException e) {
			return null;
		}		
		return column_set;
    }
    
 
	 public	RecordList	transferJsonList( RecordList obj_list) {
    	RecordList	rec_list = new RecordList();
    	for ( int i=0; i<obj_list.size(); i++ ){
    		Seqs	obj = (Seqs)obj_list.get(i);
    		rec_list.add( obj.toJSON() );    		
    	}
    	    	
    	rec_list.copyMeta(obj_list);
    	return rec_list;
    }
    
        
}