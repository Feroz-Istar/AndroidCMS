/**
 * 
 */
package com.example.vaibhav.app.cmspojo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author ayrus
 *
 */
@Root(name = "presentation")
public class CMSPresentation {
	public CMSPresentation() {
		super();
	}

	@ElementList(name = "slide",required = false,inline = true)
	List<CMSSlide> cmsslides;



	public void setSlides(List<CMSSlide> cmsslides) {
		this.cmsslides = cmsslides;
	}
	public List<CMSSlide> getSlides() {
		return cmsslides;
	}
}
