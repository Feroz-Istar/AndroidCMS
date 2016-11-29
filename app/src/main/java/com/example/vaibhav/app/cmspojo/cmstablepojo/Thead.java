package com.example.vaibhav.app.cmspojo.cmstablepojo;

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="thead")
public class Thead {
	
	@Element(name = "tr",required = false)
	private Tr tr;

	public Thead() {
		super();
	}

	public Thead(Tr tr) {
		super();
		this.tr = tr;
	}

	public Tr getTr() {
		return tr;
	}

	public void setTr(Tr tr) {
		this.tr = tr;
	}


	

}
