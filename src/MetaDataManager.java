import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MetaDataManager {
	private List<ColumnWrapper> columns;
	
	public MetaDataManager(ResultSetMetaData metaData) throws Exception{
		columns = new ArrayList<ColumnWrapper>();
		for(int i=1; i<= metaData.getColumnCount();i++){
			int columnType = metaData.getColumnType(i);
			switch(columnType){
			case Types.INTEGER:
			case Types.BIGINT:
				columns.add(new ColumnWrapperInt(i));
				break;
			case Types.TIMESTAMP:
				columns.add(new ColumnWrapperTimestamp(i));
				break;
			case Types.VARCHAR:
			case Types.CHAR:
				columns.add(new ColumnWrapperString(i));
				break;
			case Types.NUMERIC:
				columns.add(new ColumnWrapperNumeric(i));
				break;
			default:
				throw new RuntimeException("UnHandled column type:" + columnType);
					
			}
		}
		
	}

	public void appendOneRow(Writer writer, ResultSet rs) throws Exception{
		ColumnWrapper wrapper;
		Iterator<ColumnWrapper> iter = columns.iterator();
		while(iter.hasNext()){
			wrapper = iter.next();
			writer.append(wrapper.getText(rs));
			if(iter.hasNext()){
				writer.append('^');
			}
		}
		writer.append('\n');
	}
}
