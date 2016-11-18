/**
 * 
 */
package com.example.vaibhav.app;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author vaibhaverma
 *
 */
public class CMSList  implements Serializable {
	@Attribute(name = "list_type",required = false)
	String list_type;
	@Attribute(name = "merged_audio",required = false)
	String mergedAudioURL;
	@Element(name = "mergedAudioDuration",required = false)
	int mergedAudioDuration;
	@ElementList(name = "li",required = false,inline = true)
	ArrayList<CMSTextItem> items;

	public CMSList() {
		super();
	}

	public CMSList(ArrayList<CMSTextItem> items, String list_type, String mergedAudioURL, int mergedAudioDuration) {
		super();
		this.items = items;
		this.list_type = list_type;
		this.mergedAudioURL = mergedAudioURL;
		this.mergedAudioDuration = mergedAudioDuration;
	}

	public int getMergedAudioDuration() {
		return mergedAudioDuration;
	}

	public void setMergedAudioDuration(int mergedAudioDuration) {
		this.mergedAudioDuration = mergedAudioDuration;
	}

	public ArrayList<CMSTextItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<CMSTextItem> items) {
		this.items = items;
	}

	public String getList_type() {
		return list_type;
	}

	public void setList_type(String list_type) {
		this.list_type = list_type;
	}

	public String getMergedAudioURL() {
		if (mergedAudioURL == null || mergedAudioURL.isEmpty()) {
			return "none";
		}
		return mergedAudioURL;
	}

	public void setMergedAudioURL(String mergedAudioURL) {
		this.mergedAudioURL = mergedAudioURL;
	}
}