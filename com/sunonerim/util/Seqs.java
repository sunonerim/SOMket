// version 1.6
//	fix JSON string producing error when string type field value contain "
package	com.sunonerim.util;
import org.json.JSONException;
import org.json.JSONObject;





public class Seqs  {

	private	String 	SeqID	= null;			//순번아이디
	private	Integer 	SeqNo	= null;			//순번




	public	void	setSeqID(String  _SeqID) {
		SeqID = _SeqID;
	}
	
	public	String  getSeqID() {
		return SeqID;
	}
	public	void	setSeqNo(Integer  _SeqNo) {
		SeqNo = _SeqNo;
	}
	
	public	Integer  getSeqNo() {
		return SeqNo;
	}





	public boolean	isSamePrimaryKeyObject( Seqs _object ){
		if( 1== 1  
			&& SeqID == _object.SeqID
			)return true;
		else return false;
    }
        

	public String	toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("=========== <Seqs> ============\n");
		builder.append("SeqID : " + SeqID + "\n");
		builder.append("SeqNo : " + SeqNo + "\n");
		return builder.toString();	
	}

	public JSONObject	toJSON() {
		JSONObject	json = new JSONObject();
		
				try {
		json.put("SeqID", this.getSeqID ());
		json.put("SeqNo", this.getSeqNo ());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return json;				
	}
}