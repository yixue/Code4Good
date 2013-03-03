package com.chase.code4good;

import java.util.ArrayList;

public class Worker {

	private int _id;
	private String _name;
	private String _email;
	private String _phone;
	
	public Worker(int id, String name, String email, String phone){
		this._id = id;
		this._name = name;
		this._email = email;
		this._phone = phone;
	}
	
	public Worker(){
		this._id = 0;
		this._name = "";
		this._email = "";
		this._phone = "";
	}
	
	//Getting ID
	public int getID(){
		return this._id;
	}
	
	//Setting ID
	public void setID(int id){
		this._id = id;
	}
	
	//Getting Name
	public String getName(){
		return this._name;
	}

	//Setting Name
	public void setName(String name){
		this._name = name;
	}
	
	//Getting Email
	public String getEmail(){
		return this._email;
	}

	//Setting Email
	public void setEmail(String email){
		this._email = email;
	}
	
	//Getting Phone
	public String getPhone(){
		return this._phone;
	}

	//Setting Phone
	public void setPhone(String phone){
		this._phone = phone;
	}
}
