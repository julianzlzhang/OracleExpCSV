import java.sql.ResultSet;


public class ColumnWrapperString extends ColumnWrapper {

	public ColumnWrapperString(int columnIndex) {
		super(columnIndex);
	}

	@Override
	public String getTextImpl(ResultSet rs) throws Exception {
		String text = rs.getString(columnIndex);
		
		if(text.contains("\"")){
			text = text.replace("\"", "\"\"");
		}
		
		if(text.contains("\"") || text.contains("^") || text.contains("\n")){
			text = "\"" + text + "\"";
		}
		
		return text;
	}

}
