package com.adampahlevi.solvers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import alice.tuprolog.*;

public class TuPrologEngine extends Engine {
	Prolog engine;
	public TuPrologEngine(String dir) {
		super(new File(dir));
		// TODO Auto-generated constructor stub
		engine = new Prolog();
	}
	
	public java.lang.Long[] solve(String query) {
		java.lang.Long[] res = new java.lang.Long[2];
		engine.clearTheory();

		//load theories
		String curFileAbsolute = "";
		try {
			for(File theory: super.files) {
				curFileAbsolute = theory.getAbsolutePath();
				FileInputStream fis = new FileInputStream(theory);
				engine.addTheory(new Theory(fis));
				fis.close();
			}			
		} catch(InvalidTheoryException | IOException ex) {
			res[0] = -1L;
			System.err.println("TuProlog can't load: " + curFileAbsolute);
			System.err.println("Reason: " + ex.getMessage());
		}
		
		//do the query
		if (res[0] == null) res[0] = System.currentTimeMillis();
		@SuppressWarnings("unused")
		SolveInfo solution;
		try {
			solution = engine.solve(query);
		} catch(MalformedGoalException mge) {
			res[0] = -1L;
			System.err.println("TuProlog can't execute the query, because: " + mge.getMessage());
		}
		
		try {
			while(engine.hasOpenAlternatives()) {
				solution = engine.solveNext();
			}			
		} catch (NoMoreSolutionException nme) 
		{
			//just ignore			
		}
		
		res[1] = System.currentTimeMillis();
		return res;
	}
}
