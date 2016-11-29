package com.example.vaibhav.app.cmspojo.cmstablepojo;

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="tbody")
public class Tbody {
	
	@ElementList(name = "tr",required = false, inline = true)
	private ArrayList<TbodyTr> tr;

	public Tbody() {
		super();
	}

	public Tbody(ArrayList<TbodyTr> tr) {
		super();
		this.tr = tr;
	}

	public ArrayList<TbodyTr> getTr() {
		return tr;
	}

	public void setTr(ArrayList<TbodyTr> tr) {
		this.tr = tr;
	}



	
}
