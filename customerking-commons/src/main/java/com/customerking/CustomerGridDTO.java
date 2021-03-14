package com.customerking;

import java.util.List;
import org.json.simple.JSONArray;

public class CustomerGridDTO {

	List<String> columnNames;
	
	JSONArray gridRecords;

	String count;
	
	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public JSONArray getGridRecords() {
		return gridRecords;
	}

	public void setGridRecords(JSONArray gridRecords) {
		this.gridRecords = gridRecords;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
}
