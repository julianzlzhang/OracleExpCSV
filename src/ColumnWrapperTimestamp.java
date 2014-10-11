import java.sql.Timestamp;
import java.sql.ResultSet;


public class ColumnWrapperTimestamp extends ColumnWrapper {

	public ColumnWrapperTimestamp(int columnIndex) {
		super(columnIndex);
	}

	@Override
	public String getTextImpl(ResultSet rs) throws Exception {
		Timestamp value = rs.getTimestamp(columnIndex);
		return value.toString();
	}

}
