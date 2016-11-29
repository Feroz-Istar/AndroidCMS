package com.example.vaibhav.app.cmspojo.cmstablepojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "td")
public class Td {
	
	@Text    
	private String content;

	public Td() {
		super();
	}

	public Td(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
