package com.docm.page;

import java.util.List;



public class DataTablePageDto<T> {

	/**
	 * 
	 */
	private List<T> data;         //包含的数据
    private Integer draw=1;        //当前页
    private Long recordsTotal;//总长度
    private Long recordsFiltered;//过滤后的长度
    private Integer start=0;    //第几条数据开始查询
    private Integer length=10;    //每页显示几条
    
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Integer getDraw() {
//		draw = start/length + 1;
		return draw;
	}
	public void setDraw(Integer draw) {
		
		this.draw = draw;
	}
	public Long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	
	public Integer getCurrentPage() {
		return start/length + 1;
	}
	
    
    
    
}
