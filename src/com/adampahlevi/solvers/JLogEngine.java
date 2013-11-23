package com.adampahlevi.solvers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import ubc.cs.JLog.Foundation.jPrologAPI;

public class JLogEngine extends Engine {
	jPrologAPI engine;
	public JLogEngine(String dir) {
		super(new File(dir));
	}
	
	public Long[] solve(String query) {
		Long[] res = new Long[2];
		try {
			engine = new jPrologAPI(getTheories());
			res[0] = System.currentTimeMillis();
			engine.query(query);
			engine.stop();
		} catch(Exception ex) {
			System.err.println(super.files);
			System.err.println("error for " + query + ": " + ex.getMessage());
			res[0] = -1L;
		}
		res[1] = System.currentTimeMillis();
		return res;
	}
	
	private String getTheories() throws Exception {
		StringBuilder sb = new StringBuilder();
		
		for(File f: super.files) {
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			int i;
			while((i = bis.read()) != -1) {
				sb.append((char) i);
			}
			bis.close();
			fis.close();
		}
		
		return sb.toString();
	}
}
