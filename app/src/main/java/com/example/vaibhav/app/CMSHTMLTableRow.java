/**
 * 
 */
package com.example.vaibhav.app;

import org.simpleframework.xml.Element;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Vaibhav
 *
 */
public class CMSHTMLTableRow  implements Serializable {
	ArrayList<String> cells = new ArrayList<String>();

	@Element(name = "cell")
	public ArrayList<String> getCells() {
		return cells;
	}

	public void setCells(ArrayList<String> cells) {
		this.cells = cells;
	}
}
