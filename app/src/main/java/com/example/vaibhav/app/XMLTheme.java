/**
 * 
 */
package com.example.vaibhav.app;

import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * @author Vaibhav
 *
 */
public class XMLTheme   implements Serializable {
	@Element(name = "id", required = false)
	private Integer id;
	@Element(name = "name", required = false)
	private String name;
	@Element(name = "backgroundColor", required = false)
	private String backgroundColor;
	@Element(name = "titleFontSize", required = false)
	private String titleFontSize;
	@Element(name = "titleLineHeight", required = false)
	private String titleLineHeight;
	@Element(name = "titleFontWeight", required = false)
	private String titleFontWeight;
	@Element(name = "titleTextAlignment", required = false)
	private String titleTextAlignment;
	@Element(name = "titleFontColor", required = false)
	private String titleFontColor;
	@Element(name = "titleFontFamily", required = false)
	private String titleFontFamily;
	@Element(name = "subtitleFontSize", required = false)
	private String subtitleFontSize;
	@Element(name = "subtitleLineHeight", required = false)
	private String subtitleLineHeight;
	@Element(name = "subtitleFontWeight", required = false)
	private String subtitleFontWeight;
	@Element(name = "subtitleTextAlignment", required = false)
	private String subtitleTextAlignment;
	@Element(name = "subtitleFontColor", required = false)
	private String subtitleFontColor;
	@Element(name = "subtitleFontFamily", required = false)
	private String subtitleFontFamily;
	@Element(name = "listitemFontSize", required = false)
	private String listitemFontSize;
	@Element(name = "listitemLineHeight", required = false)
	private String listitemLineHeight;
	@Element(name = "listitemFontWeight", required = false)
	private String listitemFontWeight;
	@Element(name = "listitemTextAlignment", required = false)
	private String listitemTextAlignment;
	@Element(name = "listitemFontColor", required = false)
	private String listitemFontColor;
	@Element(name = "listitemFontFamily", required = false)
	private String listitemFontFamily;
	@Element(name = "paragraphFontSize", required = false)
	private String paragraphFontSize;
	@Element(name = "paragraphLineHeight", required = false)
	private String paragraphLineHeight;
	@Element(name = "paragraphFontWeight", required = false)
	private String paragraphFontWeight;
	@Element(name = "paragraphTextAlignment", required = false)
	private String paragraphTextAlignment;
	@Element(name = "paragraphFontColor", required = false)
	private String paragraphFontColor;
	@Element(name = "paragraphFontFamily", required = false)
	private String paragraphFontFamily;
	
	
	public XMLTheme(Integer id, String name, String backgroundColor, String titleFontSize, String titleLineHeight, String titleFontWeight, String titleTextAlignment, String titleFontColor, String titleFontFamily, String subtitleFontSize, String subtitleLineHeight, String subtitleFontWeight, String subtitleTextAlignment, String subtitleFontColor, String subtitleFontFamily, String listitemFontSize, String listitemLineHeight, String listitemFontWeight, String listitemTextAlignment,
			String listitemFontColor, String listitemFontFamily, String paragraphFontSize, String paragraphLineHeight, String paragraphFontWeight, String paragraphTextAlignment, String paragraphFontColor, String paragraphFontFamily) {
		super();
		this.id = id;
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.titleFontSize = titleFontSize;
		this.titleLineHeight = titleLineHeight;
		this.titleFontWeight = titleFontWeight;
		this.titleTextAlignment = titleTextAlignment;
		this.titleFontColor = titleFontColor;
		this.titleFontFamily = titleFontFamily;
		this.subtitleFontSize = subtitleFontSize;
		this.subtitleLineHeight = subtitleLineHeight;
		this.subtitleFontWeight = subtitleFontWeight;
		this.subtitleTextAlignment = subtitleTextAlignment;
		this.subtitleFontColor = subtitleFontColor;
		this.subtitleFontFamily = subtitleFontFamily;
		this.listitemFontSize = listitemFontSize;
		this.listitemLineHeight = listitemLineHeight;
		this.listitemFontWeight = listitemFontWeight;
		this.listitemTextAlignment = listitemTextAlignment;
		this.listitemFontColor = listitemFontColor;
		this.listitemFontFamily = listitemFontFamily;
		this.paragraphFontSize = paragraphFontSize;
		this.paragraphLineHeight = paragraphLineHeight;
		this.paragraphFontWeight = paragraphFontWeight;
		this.paragraphTextAlignment = paragraphTextAlignment;
		this.paragraphFontColor = paragraphFontColor;
		this.paragraphFontFamily = paragraphFontFamily;
	}
	public XMLTheme() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public String getTitleFontSize() {
		return titleFontSize;
	}
	public void setTitleFontSize(String titleFontSize) {
		this.titleFontSize = titleFontSize;
	}
	
	public String getTitleLineHeight() {
		return titleLineHeight;
	}
	public void setTitleLineHeight(String titleLineHeight) {
		this.titleLineHeight = titleLineHeight;
	}
	
	public String getTitleFontWeight() {
		return titleFontWeight;
	}
	public void setTitleFontWeight(String titleFontWeight) {
		this.titleFontWeight = titleFontWeight;
	}
	
	public String getTitleTextAlignment() {
		return titleTextAlignment;
	}
	public void setTitleTextAlignment(String titleTextAlignment) {
		this.titleTextAlignment = titleTextAlignment;
	}

	public String getTitleFontColor() {
		return titleFontColor;
	}
	public void setTitleFontColor(String titleFontColor) {
		this.titleFontColor = titleFontColor;
	}
	
	public String getTitleFontFamily() {
		return titleFontFamily;
	}
	public void setTitleFontFamily(String titleFontFamily) {
		this.titleFontFamily = titleFontFamily;
	}
	
	public String getSubtitleFontSize() {
		return subtitleFontSize;
	}
	public void setSubtitleFontSize(String subtitleFontSize) {
		this.subtitleFontSize = subtitleFontSize;
	}
	public String getSubtitleLineHeight() {
		return subtitleLineHeight;
	}
	public void setSubtitleLineHeight(String subtitleLineHeight) {
		this.subtitleLineHeight = subtitleLineHeight;
	}
	
	public String getSubtitleFontWeight() {
		return subtitleFontWeight;
	}
	public void setSubtitleFontWeight(String subtitleFontWeight) {
		this.subtitleFontWeight = subtitleFontWeight;
	}
	
	public String getSubtitleTextAlignment() {
		return subtitleTextAlignment;
	}
	public void setSubtitleTextAlignment(String subtitleTextAlignment) {
		this.subtitleTextAlignment = subtitleTextAlignment;
	}

	public String getSubtitleFontColor() {
		return subtitleFontColor;
	}
	public void setSubtitleFontColor(String subtitleFontColor) {
		this.subtitleFontColor = subtitleFontColor;
	}
	
	public String getSubtitleFontFamily() {
		return subtitleFontFamily;
	}
	public void setSubtitleFontFamily(String subtitleFontFamily) {
		this.subtitleFontFamily = subtitleFontFamily;
	}
	
	public String getListitemFontSize() {
		return listitemFontSize;
	}
	public void setListitemFontSize(String listitemFontSize) {
		this.listitemFontSize = listitemFontSize;
	}

	public String getListitemLineHeight() {
		return listitemLineHeight;
	}
	public void setListitemLineHeight(String listitemLineHeight) {
		this.listitemLineHeight = listitemLineHeight;
	}
	
	public String getListitemFontWeight() {
		return listitemFontWeight;
	}
	public void setListitemFontWeight(String listitemFontWeight) {
		this.listitemFontWeight = listitemFontWeight;
	}
	
	public String getListitemTextAlignment() {
		return listitemTextAlignment;
	}
	public void setListitemTextAlignment(String listitemTextAlignment) {
		this.listitemTextAlignment = listitemTextAlignment;
	}

	public String getListitemFontColor() {
		return listitemFontColor;
	}
	public void setListitemFontColor(String listitemFontColor) {
		this.listitemFontColor = listitemFontColor;
	}
	
	public String getListitemFontFamily() {
		return listitemFontFamily;
	}
	public void setListitemFontFamily(String listitemFontFamily) {
		this.listitemFontFamily = listitemFontFamily;
	}
	
	public String getParagraphFontSize() {
		return paragraphFontSize;
	}
	public void setParagraphFontSize(String paragraphFontSize) {
		this.paragraphFontSize = paragraphFontSize;
	}

	public String getParagraphLineHeight() {
		return paragraphLineHeight;
	}
	public void setParagraphLineHeight(String paragraphLineHeight) {
		this.paragraphLineHeight = paragraphLineHeight;
	}

	public String getParagraphFontWeight() {
		return paragraphFontWeight;
	}
	public void setParagraphFontWeight(String paragraphFontWeight) {
		this.paragraphFontWeight = paragraphFontWeight;
	}

	public String getParagraphTextAlignment() {
		return paragraphTextAlignment;
	}
	public void setParagraphTextAlignment(String paragraphTextAlignment) {
		this.paragraphTextAlignment = paragraphTextAlignment;
	}
	
	public String getParagraphFontColor() {
		return paragraphFontColor;
	}
	public void setParagraphFontColor(String paragraphFontColor) {
		this.paragraphFontColor = paragraphFontColor;
	}
	public String getParagraphFontFamily() {
		return paragraphFontFamily;
	}
	public void setParagraphFontFamily(String paragraphFontFamily) {
		this.paragraphFontFamily = paragraphFontFamily;
	}
	
	
	
}
