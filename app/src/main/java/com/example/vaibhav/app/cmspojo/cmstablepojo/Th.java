package com.example.vaibhav.app.cmspojo.cmstablepojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "th")
public class Th {
	
	@Text
    private String content;

	public Th() {
		super();
	}

	public Th(String content) {
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
