package com.chase.code4good;

import java.util.*;

public class Camps {
	int id;
	Date firstDate;
	String type;
	String badge;
	int noPeople;
	String imgURL;
	Date lastModifiedDate;
	int lastModifiedPerson;
	
	/**
	 Setter
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	
	public void setFirstDate(String firstDate) {
		this.firstDate = DateParser.parse(firstDate);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setBadge(String badge) {
		this.badge = badge;
	}
	
	public void setNoPeople(int noPeople) {
		this.noPeople = noPeople;
	}
	
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	public void setDateLastModified(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public void setDateLastModified(String lastModifiedDate) {
		this.lastModifiedDate = DateParser.parse(lastModifiedDate);
	}
	
	public void setLastModifiedPerson(int lastModifiedPerson) {
		this.lastModifiedPerson = lastModifiedPerson;
	}
	
	/**Getter*/
	public int getId() {
		return id;
	}
	
	public Date getFirstDate() {
		return firstDate;
	}
	
	public String getType() {
		return type;
	}
	
	public String getBadge() {
		return badge;
	}
	
	public int getNoPeople() {
		return noPeople;
	}
	
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public int getLastModifiedPerson() {
		return lastModifiedPerson;
	}
}
