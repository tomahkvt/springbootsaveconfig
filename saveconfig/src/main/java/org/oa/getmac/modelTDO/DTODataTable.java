/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import java.util.List;

public class DTODataTable {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<DTResult> data;
	
	public DTODataTable(){
		
	}
	
	public DTODataTable(int draw, int recordsTotal, int recordsFiltered, List<DTResult> data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<DTResult> getData() {
		return data;
	}
	public void setData(List<DTResult> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DTODataTable [getDraw()=" + getDraw() + ", getRecordsTotal()=" + getRecordsTotal()
				+ ", getRecordsFiltered()=" + getRecordsFiltered() + ", getData()=" + getData() + "]";
	}
}
