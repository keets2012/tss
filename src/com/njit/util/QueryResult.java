package com.njit.util;

import java.util.List;

public class QueryResult {

	private List recordList ;
	
	public QueryResult() {
	}
	
	public QueryResult(List recordList) {
		this.recordList = recordList;
	}


	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

}
