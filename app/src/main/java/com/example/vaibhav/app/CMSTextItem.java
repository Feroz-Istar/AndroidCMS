/**
 * 
 */
package com.example.vaibhav.app;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * @author vaibhaverma
 *
 */
@Root(name = "li")
public class CMSTextItem  implements Serializable  {
	@Element(name = "p",required = false)
	String text;
	@Element(name = "id",required = false)
	int id;
	@Element(name = "ul",required = false)
	CMSList list;
	@Element(name = "description",required = false)
	String description;
	@Element(name = "fragment_audio",required = false)
	String fragmentAudioUrl;
	@Element(name = "fragment_duration",required = false)
	int fragmentDuration;


	public CMSTextItem() {
		super();
	}

	public CMSTextItem(String text) {
		super();
		this.text = text;
	}

	public CMSTextItem(String text, String desc) {
		super();
		this.text = text;
		this.description = desc;
	}

	public CMSTextItem(String text, String desc, String audioUrl, int fragmentDuration) {
		super();
		this.text = text;
		this.description = desc;
		this.fragmentAudioUrl = audioUrl;
		this.fragmentDuration = fragmentDuration;
	}

	public CMSTextItem(String text, int id) {
		super();
		this.text = text;
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CMSList getList() {
		return list;
	}

	public void setList(CMSList list) {
		this.list = list;
	}

	public String getIndex(int kamini) {
		return (kamini - 1) * 200 + "px";
	}

	public String getIndexLeft(int kamini) {
		if (kamini == 5) {
			return "100px";
		} else {
			return "95px";
		}
	}



	public String getFragmentAudioUrl() {
		if (fragmentAudioUrl == null) {
			return "";
		}
		return fragmentAudioUrl;
	}

	public void setFragmentAudioUrl(String fragmentAudioUrl) {
		this.fragmentAudioUrl = fragmentAudioUrl;
	}

	public int getfragmentDuration() {
		return fragmentDuration;
	}

	public void setfragmentDuration(int fragmentDuration) {
		this.fragmentDuration = fragmentDuration;
	}

	public String getDescription() {
		if (null == description) {
			return "NO_DESC";
		} else {
			return description;
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}


}