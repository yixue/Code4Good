package com.chase.code4good;

public class Person {
	//Initialize private variables
	private int _id;
	private String _name;
	private String _imageurl;
	private String _audiourl;
	private String _tags;
	private int _camp;
	
	//Constructor
	public Person(int id, String name, String imageurl, String audiourl, String tags, int camp){
		this._id = id;
		this._name = name;
		this._imageurl = imageurl;
		this._audiourl = audiourl;
		this._tags = tags;
		this._camp = camp;
	}
	
	public Person (){
		this._id = 0;
		this._name = "";
		this._imageurl = "";
		this._audiourl = "";
		this._tags = "";
		this._camp = 0;
	}
	//Getting id
	public int getID(){
		return this._id;
	}
	
	//Setting id
	public void setID(int id){
		this._id = id;
	}
	
	//Getting name
	public String getName(){
		return this._name;
	}
		
	//Setting name
	public void setName(String name){
	this._name = name;
	}
	
	//Getting imageurl
	public String getImageURL(){
	return this._imageurl;
	}
		
	//Setting imageurl
	public void setImageURL(String imageurl){
	this._imageurl = imageurl;
	}
	
	//Getting audiourl
	public String getAudioURL(){
	return this._audiourl;
	}
		
	//Setting audiourl
	public void setAudioURL(String audiourl){
	this._audiourl = audiourl;
	}
	
	//Getting tags
	public String getTags(){
		return this._tags;
	}

	//Setting tags
	public void setTags(String tags){
		this._tags = tags;
	}

	//Getting camp
	public int getCamp(){
		return this._camp;
	}

	//Setting camp
	public void setCamp(int camp){
		this._camp = camp;
	}

}

