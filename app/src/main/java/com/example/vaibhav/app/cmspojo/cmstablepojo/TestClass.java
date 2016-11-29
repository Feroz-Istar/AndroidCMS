package com.example.vaibhav.app.cmspojo.cmstablepojo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;


public class TestClass {
	
	
	public static void main(String[] args) {
		
     	
	Serializer serializer = new Persister();
	
	ArrayList<Th> Thdata = new ArrayList<Th>();
	
	 Thdata.add(new Th("HEAD1"));
	 Thdata.add(new Th("HEAD1"));
	 Thdata.add(new Th("HEAD1"));
	 
     Tr trdata1 = new Tr(Thdata);

	 
		ArrayList<Td> Tddata = new ArrayList<Td>();
		Td tddata1 = new Td("BODY1");
		Td tddata2 = new Td("BODY2");
		Td tddata3 = new Td("BODY3");
		Tddata.add(tddata1);
		Tddata.add(tddata2);
		Tddata.add(tddata3);
	 
	
	ArrayList<TbodyTr> TbodyTrdata = new ArrayList<TbodyTr>();
	
	
	TbodyTr trdata2 = new TbodyTr();
	trdata2 = new TbodyTr(Tddata);
	TbodyTrdata.add(trdata2);

	
	
	
	Table table = new Table(new Thead(trdata1),new Tbody(TbodyTrdata));
	File result = new File("example.xml");

	try {
		serializer.write(table, result);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
