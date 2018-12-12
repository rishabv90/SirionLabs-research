package springBoot.model;

//Rishab Verma
//29/10/2018
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
//import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
//import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.HTable;

import java.util.List;

@RestController
public class Example {
	
	@Autowired
	private TableService tableService;
	
	
	@RequestMapping(value = "/logs" ,method = RequestMethod.GET)
	public List<TableContent> getAllContent(){
		System.out.println("\n\nExample Controller\n\n");
		//getTableFromHbase();
		return tableService.getAllContent();
		
	}

	@RequestMapping("/logs/{entityId}")
    public TableContent getTableName(@PathVariable String entityId){
	    return tableService.getTableName(entityId);
    }


    @RequestMapping(method=RequestMethod.POST, value="/logs")
    public void addTableContent(@RequestBody TableContent tableContent){
        addTableContentToHbase();
        tableService.addTableContent(tableContent);
    }

    public String getTableFromHbase() {

        try {
            String str1 = "";
            String jString = "";

            String colFamily = "auditFamily";
            String colName = "entityId";
            String tableName = "auditLog";

            Configuration config = HBaseConfiguration.create();
            Connection connection = ConnectionFactory.createConnection(config);
            System.out.println("P1----------------->" + connection );
            Table table = connection.getTable(TableName.valueOf(tableName));
            System.out.println("P2----------------->" + table);

            Scan scan = new Scan(Bytes.toBytes("") );
            scan.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(colName));
            ResultScanner scanner = table.getScanner(scan);


          for (Result result = scanner.next(); result != null; result = scanner.next()){
             System.out.println("Found row : " + result);
             String str = new String (result.getValue(Bytes.toBytes(colFamily), Bytes.toBytes(colName)));
             System.out.println("Val : " + str);
             str1 = str1 + str;
             }

          jString = "TableName : " + tableName + "" + "Column Family : " + colFamily + " Column Name1: " + colName + " Value : " + str1;
            scanner.close();

            return str1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR FOUND IN GET TABLE from HBase METHOD";
    }


    public String addTableContentToHbase(){

        try{

            String tableName = "emp";
            String colFamily = "personal";
            String colName = "city";
            String valueA = "Country";
            String valueB = "India";

            Configuration config  = HBaseConfiguration.create();
            //HTable table =  new HTable(config, "XXX");
            Connection connection = ConnectionFactory.createConnection(config);
            Table table =  connection.getTable(TableName.valueOf(tableName));

            Put put = new Put(Bytes.toBytes("row2"));
            put.addColumn(Bytes.toBytes("personal"), Bytes.toBytes(valueA), Bytes.toBytes(valueB));
            table.put(put);

            System.out.println("Values inserted : ");
            table.close();

            return "";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR FOUND IN POST Details Method";

    }

}