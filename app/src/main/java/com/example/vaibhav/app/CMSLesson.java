/**
 * 
 */
package com.example.vaibhav.app;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;



/**
 * @author vaibhaverma
 *
 */
@Root(name = "lesson")
public class CMSLesson  implements Serializable {
	String lessonTitle;
	String lessonDescription = "";
	String type;
	ArrayList<CMSSlide> slides;
	String teacherNotes;
	String studentNotes;

	@Element(name = "teacher_notes")
	public String getTeacherNotes() {
		return teacherNotes;
	}

	public void setTeacherNotes(String teacherNotes) {
		this.teacherNotes = teacherNotes;
	}

	@Element(name = "student_notes")
	public String getStudentNotes() {
		return studentNotes;
	}

	public void setStudentNotes(String studentNotes) {
		this.studentNotes = studentNotes;
	}

	public String getLessonTitle() {
		return lessonTitle;
	}

	 @Attribute(name = "h1")
	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
	}

	public String getType() {
		return type;
	}

	 @Attribute(name = "lesson_type")
	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<CMSSlide> getSlides() {
		return slides;
	}

	@Element(name = "slide")
	public void setSlides(ArrayList<CMSSlide> slides) {
		this.slides = slides;
	}

	public String getLessonDescription() {
		return lessonDescription;
	}

	 @Attribute(name = "description")
	public void setLessonDescription(String lessonDescription) {
		this.lessonDescription = lessonDescription;
	}
}