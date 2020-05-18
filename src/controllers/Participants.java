package controllers;

import java.io.Serializable;

public class Participants implements Serializable {
	private int id;
	private String name;
	private String address;
	private String mob_num;
	private String org_name;
	private String email_id;

	public Participants() {
		name = null;
		address = null;
		mob_num = null;
		org_name = null;
		email_id = null;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	public void setname(String name) {
		this.name = name;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	public void setmobnum(String mobnum) {
		this.mob_num = mobnum;
	}

	public void setorgname(String orgname) {
		this.org_name = orgname;
	}

	public void setemailid(String id) {
		this.email_id = id;
	}

	public String getname() {
		return this.name;
	}

	public String getaddress() {
		return this.address;
	}

	public String getmobnum() {
		return this.mob_num;
	}

	public String getorgname() {
		return this.org_name;
	}

	public String getemailid() {
		return this.email_id;
	}

}