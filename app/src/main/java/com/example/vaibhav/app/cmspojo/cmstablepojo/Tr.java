package com.example.vaibhav.app.cmspojo.cmstablepojo;

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "tr")
public class Tr {
	
	@ElementList(name = "th",required = false,inline=true)
	private ArrayList<Th> th;
	
	public Tr() {
		super();
	}

	public Tr(ArrayList<Th> th) {
		super();
		this.th = th;
	}

	public ArrayList<Th> getTh() {
		return th;
	}
	

}
