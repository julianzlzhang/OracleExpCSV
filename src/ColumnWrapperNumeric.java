import java.math.BigDecimal;
import java.sql.ResultSet;


public class ColumnWrapperNumeric extends ColumnWrapper {

	public ColumnWrapperNumeric(int columnIndex) {
		super(columnIndex);
	}

	@Override
	public String getTextImpl(ResultSet rs) throws Exception {
		BigDecimal value = rs.getBigDecimal(columnIndex);
		return value.toString();
	}

}
