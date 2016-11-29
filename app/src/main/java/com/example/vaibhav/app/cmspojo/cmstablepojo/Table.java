package com.example.vaibhav.app.cmspojo.cmstablepojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="table")
public class Table {
	
	@Element(name = "thead",required = false)
    private Thead thead;

	@Element(name = "tbody",required = false)
    private Tbody tbody;

	public Table() {
		super();
	}

	public Table(Thead thead, Tbody tbody) {
		super();
		this.thead = thead;
		this.tbody = tbody;
	}

	public Thead getThead() {
		return thead;
	}

	public void setThead(Thead thead) {
		this.thead = thead;
	}

	public Tbody getTbody() {
		return tbody;
	}

	public void setTbody(Tbody tbody) {
		this.tbody = tbody;
	}

		

}
