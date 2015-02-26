package com.sunonerim.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;






public class SeqUtil {

	static public	int	nextSeq( String seq_id) throws SQLException{
		
		Connection conn = DBUtil.openConnect();
		SeqsDao seq_dao = new SeqsDao();
		Seqs	seq = new Seqs();
		
		seq.setSeqID(seq_id);
		List<Seqs> seq_list = seq_dao.searchMatching(conn, seq);
		if( seq_list.size() == 0  ){
			seq.setSeqNo(1);
			seq_dao.create(conn, seq);
		} else {
			seq = seq_list.get(0);
			seq.setSeqNo(  seq.getSeqNo( ) + 1 );		
			seq_dao.save(conn, seq );
		}
				
		 DBUtil.closeConnect(conn);
		 
		 return seq.getSeqNo();
	}
	
}
