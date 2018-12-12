package springBoot.model;

//RIshab Verma 29/10/2018

import java.util.List;

import springBoot.model.TableContent;

public interface TableService {
	
	public List<TableContent> getAllContent();
	public TableContent getTableName(String entintyId);
    public void addTableContent(TableContent tableContent);


}
