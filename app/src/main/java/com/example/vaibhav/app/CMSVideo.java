/**
 * 
 */
package com.example.vaibhav.app;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * @author vaibhaverma
 *
 */
public class CMSVideo   implements Serializable  {
	@Attribute(name = "url",required = false)
	String url;
	@Element(name = "description",required = false)
	String description;
	@Attribute(name = "title",required = false)
	String title;

	public CMSVideo() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}