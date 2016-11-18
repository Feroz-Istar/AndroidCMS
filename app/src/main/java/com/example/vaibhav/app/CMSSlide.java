/**
 * 
 */
package com.example.vaibhav.app;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;


/**
 * @author vaibhaverma
 *
 *///
@Root(name = "slide")
public class CMSSlide implements Serializable {
	public CMSSlide() {

	}
	@Attribute(name = "image_bg", required = false)
	String image_BG;
	@Attribute(name = "transition", required = false)
	String transition = "zoom";
	@Element(name = "h1", required = false)
	CMSTitle title;
	@Element(name = "h2", required = false)
	CMSTitle2 title2;
	@Element(name = "p", required = false)
	CMSParagraph paragraph;
	@Element(name = "ul", required = false)
	CMSList list;
	@Element(name = "img", required = false)
	CMSImage image;
	@Element(name = "video", required = false)
	CMSVideo video;
	@Attribute(name = "background", required = false)
	String background = "null";
	@Attribute(name = "background_transition", required = false)
	String backgroundTransition = "zoom";
	@Attribute(name = "position", required = false)
	String position;
	@Attribute(name = "template", required = false)
	String templateName;
	@Element(name = "teacher_notes", required = false)
	String teacherNotes;
	@Element(name = "student_notes", required = false)
	String studentNotes;
	@Element(name = "slide_audio", required = false)
	String audioUrl;
	@Element(name = "duration", required = false)
	int slideDuration;
	@Element(name = "theme", required = false)
	XMLTheme theme = new XMLTheme();

	
	
	public XMLTheme getTheme() {
		return theme;
	}

	public void setTheme(XMLTheme theme) {
		this.theme = theme;
	}

	public String getAudioUrl() {
		if (audioUrl == null || audioUrl.isEmpty()) {
			return "none";
		}
		return audioUrl;
	}

	public void setAudioUrl(String audio_url) {
		this.audioUrl = audio_url;
	}

	public int getSlideDuration() {
		if (slideDuration == 0) {
			return 5000;
		}
		return slideDuration;
	}

	public void setSlideDuration(int slide_duration) {
		this.slideDuration = slide_duration;
	}

	public String getStudentNotes() {
		if (studentNotes == null) {
			return "";
		}
		return studentNotes;
	}

	public void setStudentNotes(String studentNotes) {
		this.studentNotes = studentNotes;
	}

	public String getTeacherNotes() {
		if (teacherNotes == null) {
			return "";
		}
		return teacherNotes;
	}

	public void setTeacherNotes(String teacherNotes) {
		this.teacherNotes = teacherNotes;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public CMSTitle getTitle() {
		return title;
	}

	public void setTitle(CMSTitle title) {
		this.title = title;
	}

	public CMSParagraph getParagraph() {
		return paragraph;
	}

	public void setParagraph(CMSParagraph paragraph) {
		this.paragraph = paragraph;
	}

	public CMSList getList() {
		return list;
	}

	public void setList(CMSList list) {
		this.list = list;
	}

	public CMSImage getImage() {
		if (image == null) {
			CMSImage img = new CMSImage();
			img.setUrl("/content/media_upload?getfile=ToDo.png");
			img.setTitle("To Do");
			img.setDescription("Place filler Image");
			return img;
		} else {
			return image;
		}
	}

	public void setImage(CMSImage image) {
		this.image = image;
	}

	public CMSVideo getVideo() {
		return video;
	}

	public void setVideo(CMSVideo video) {
		this.video = video;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTransition() {
		return transition.toLowerCase();
	}

	public void setTransition(String transition) {
		this.transition = transition;
	}

	public String getBackground() {
		return background.toLowerCase();
	}

	public void setBackground(String background) {
		this.background = background.toLowerCase();
	}

	public String getBackgroundTransition() {
		return backgroundTransition.toLowerCase();
	}

	public void setBackgroundTransition(String backgroundTransition) {
		this.backgroundTransition = backgroundTransition.toLowerCase();
	}



	public String getImage_BG() {
		return image_BG;
	}

	public void setImage_BG(String image_BG) {
		this.image_BG = image_BG;
	}

	public CMSTitle2 getTitle2() {
		return title2;
	}

	public void setTitle2(CMSTitle2 title2) {
		this.title2 = title2;
	}
}