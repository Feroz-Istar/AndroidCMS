/**
 * 
 */
package com.example.vaibhav.app;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

import java.io.Serializable;

/**
 * @author Vaibhav
 *
 */
public class CMSTitle  implements Serializable {
	@Text(data = true,required = false)
	private String text;
	@Attribute(name = "fragment_audio",required = false)
	private String fragmentAudioUrl;
	@Attribute(name = "fragment_duration",required = false)

	private int fragmentDuration;

	public CMSTitle() {
		super();
	}

	public CMSTitle(String text, String fragmentAudioUrl, int fragmentDuration) {
		super();
		this.text = text;
		this.fragmentAudioUrl = fragmentAudioUrl;
		this.fragmentDuration = fragmentDuration;
	}

	public CMSTitle(String text) {
		super();
		this.text = text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
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
}
