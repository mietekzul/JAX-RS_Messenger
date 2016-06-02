package pl.raziel.jaxrs.resources.beans;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {
	@QueryParam(value = "year") private int year;
	@QueryParam(value = "start") private int start; 
	@QueryParam(value = "size") private int size;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
