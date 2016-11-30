package com.example.vaibhav.app.cmspojo.cmstablepojo;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "tr")
public class TbodyTr {
	
	@ElementList(name = "td",required = false,inline = true)
	private ArrayList<Td> td;

	@ElementList(name = "th",required = false,inline=true)
	private ArrayList<Th> th;

    public TbodyTr() {
    }

    public TbodyTr(ArrayList<Th> th, ArrayList<Td> td) {
        this.th = th;
        this.td = td;
    }

    public ArrayList<Td> getTd() {
        return td;
    }

    public void setTd(ArrayList<Td> td) {
        this.td = td;
    }

    public ArrayList<Th> getTh() {
        return th;
    }

    public void setTh(ArrayList<Th> th) {
        this.th = th;
    }
}
