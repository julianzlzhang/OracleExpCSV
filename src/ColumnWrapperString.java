import java.sql.ResultSet;


public class ColumnWrapperString extends ColumnWrapper {

	public ColumnWrapperString(int columnIndex) {
		super(columnIndex);
	}

	@Override
	public String getTextImpl(Object obj) throws Exception {
		String text = (String) obj;
		
		if(text.contains("\"")){
			text = text.replace("\"", "\"\"");
		}
		
		if(text.contains("\"") || text.contains("^") || text.contains("\n") || text.contains("\r")){
			text = "\"" + text + "\"";
		}
		
		return text;
	}

}
