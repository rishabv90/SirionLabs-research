package springBoot.model;

//RIshab Verma 29/10/2018

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class TableServiceImpl implements TableService {
	
	private List<TableContent> tc = new ArrayList<>(Arrays.asList(
			new TableContent("auditLog", "auditFamily", "entityId"),
			new  TableContent("auditLog", "auditFamily", "entityTypeId"),
			new TableContent("auditLog", "auditFamily", "id")
			));

	public List<TableContent> getAllContent(){

		return tc;

	}

    public TableContent getTableName(String entityId){

	    return tc.stream().filter(t -> t.getTableName().equals(entityId)).findFirst().get();

    }

    @Override
    public void addTableContent(TableContent tableContent) {
        String tableNamePost = tableContent.getTableName();
        String colFamilyPost = tableContent.getColFamily();
        String colNamePost = tableContent.getColName();
        //Needs one more addition

        tc.add(tableContent);

    }

}






