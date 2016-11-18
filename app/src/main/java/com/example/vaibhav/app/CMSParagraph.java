/**
 * 
 */
package com.example.vaibhav.app;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.io.Serializable;

/**
 * @author Vaibhav
 *
 */
@Root(name = "p")
public class CMSParagraph  implements Serializable {
	@Text(data = true,required = false)
	String text;
	@Attribute(name = "fragment_audio",required = false)
	String fragmentAudioUrl;
	@Attribute(name = "fragment_duration",required = false)
	int fragmentDuration;

	public CMSParagraph(String text) {
		super();
		this.text = text;
	}

	public CMSParagraph() {
		super();
	}

	public CMSParagraph(String text, String fragmentAudioUrl, int fragmentDuration) {
		super();
		this.text = text;
		this.fragmentAudioUrl = fragmentAudioUrl;
		this.fragmentDuration = fragmentDuration;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String toString() {
		return text;
	}
}
