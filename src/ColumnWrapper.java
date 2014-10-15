import java.sql.ResultSet;


public class ColumnWrapper {

	protected int columnIndex;
	
	public ColumnWrapper(int columnIndex){
		this.columnIndex = columnIndex;
	}
	
	public String getText(ResultSet rs) throws Exception{
		Object obj = rs.getObject(columnIndex); 
		if(obj == null){
			return "";
		}else{
			return getTextImpl(obj);
		}
	}
	
	public String getTextImpl(Object obj) throws Exception{
		return obj.toString();
	}
}
