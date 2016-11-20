/**
 * 
 */
package com.example.vaibhav.app.cmspojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author vaibhaverma
 *
 */
public class CMSHTMLTable  implements Serializable {
	ArrayList<CMSHTMLTableRow> rows = new ArrayList<CMSHTMLTableRow>();
	String title;

	@Attribute(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Element(name = "rows")
	public ArrayList<CMSHTMLTableRow> getRows() {
		return rows;
	}

	public void setRows(ArrayList<CMSHTMLTableRow> rows) {
		this.rows = rows;
	}
}