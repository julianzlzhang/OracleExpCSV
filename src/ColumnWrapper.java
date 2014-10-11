import java.sql.ResultSet;


public abstract class ColumnWrapper {

	protected int columnIndex;
	
	public ColumnWrapper(int columnIndex){
		this.columnIndex = columnIndex;
	}
	
	public String getText(ResultSet rs) throws Exception{
		if(rs.getObject(columnIndex) == null){
			return "";
		}else{
			return getTextImpl(rs);
		}
	}
	
	public abstract String getTextImpl(ResultSet rs) throws Exception;
}
