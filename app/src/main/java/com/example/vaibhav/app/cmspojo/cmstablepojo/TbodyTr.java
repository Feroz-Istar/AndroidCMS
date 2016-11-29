package com.example.vaibhav.app.cmspojo.cmstablepojo;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "tr")
public class TbodyTr {
	
	@ElementList(name = "td",required = false,inline = true)
	private ArrayList<Td> td;

	public TbodyTr() {
		super();
	}

	public TbodyTr(ArrayList<Td> td) {
		super();
		this.td = td;
	}

	public ArrayList<Td> getTd() {
		return td;
	}

	public void setTd(ArrayList<Td> td) {
		this.td = td;
	}


}
