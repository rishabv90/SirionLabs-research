package springBoot.model;

//Rishab Verma
//29/10/2018


public class TableContent {
	private String tableName;
	private String colFamily;
	private String colName;
	private String colValue;
	
	
	public TableContent(String tableName, String colFamily, String colName) {
		super();
		this.tableName = tableName;
		this.colFamily = colFamily;
		this.colName = colName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColFamily() {
		return colFamily;
	}

	public void setColFamily(String colFamily) {
		this.colFamily = colFamily;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	

	public TableContent() {
	}

	

}