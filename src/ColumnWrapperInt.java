import java.sql.ResultSet;


public class ColumnWrapperInt extends ColumnWrapper {

	public ColumnWrapperInt(int columnIndex) {
		super(columnIndex);
	}

	@Override
	public String getTextImpl(ResultSet rs) throws Exception{
		return ""+rs.getInt(columnIndex);
	}
	
}
