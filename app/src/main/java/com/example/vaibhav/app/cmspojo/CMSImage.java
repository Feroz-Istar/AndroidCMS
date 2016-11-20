/**
 * 
 */
package com.example.vaibhav.app.cmspojo;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * @author vaibhaverma
 *
 */
public class CMSImage   implements Serializable  {
	@Attribute(name = "url",required = false)
	String url;
	@Element(name = "description",required = false)
	String description;
	@Attribute(name = "title",required = false)
	String title;
	@Attribute(name = "fragment_audio",required = false)
	String fragmentAudioUrl;
	@Attribute(name = "fragment_duration",required = false)
	int fragmentDuration;

	public CMSImage() {
		super();
	}

	public CMSImage(String url, String description, String title) {
		super();
		this.url = url;
		this.description = description;
		this.title = title;
	}

	public CMSImage(String url, String description, String title, String fragmentAudioUrl, int fragmentDuration) {
		super();
		this.url = url;
		this.description = description;
		this.title = title;
		this.fragmentAudioUrl = fragmentAudioUrl;
		this.fragmentDuration = fragmentDuration;
	}

	public String getFragmentAudioUrl() {
		return fragmentAudioUrl;
	}

	public void setFragmentAudioUrl(String fragmentAudioUrl) {
		this.fragmentAudioUrl = fragmentAudioUrl;
	}

	public int getFragmentDuration() {
		return fragmentDuration;
	}

	public void setFragmentDuration(int fragmentDuration) {
		this.fragmentDuration = fragmentDuration;
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