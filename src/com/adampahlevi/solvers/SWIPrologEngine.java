package com.adampahlevi.solvers;

import java.io.File;
import java.util.Hashtable;

import jpl.*;

public class SWIPrologEngine extends Engine {
	public SWIPrologEngine(String dir) {
		super(new File(dir));
	}

	/**
	 * Solve the benchFileName with given n-times iteration
	 * @param predicate usually 'bench'
	 * @param iteration n
	 * @return ms before benchmarking, ms after benchmarking
	 */
	public Long[] solve(String predicate, java.lang.Integer iteration) {
		Long[] res = new Long[2];
		
		//consult files
		for(File theory: super.files) {
			Query q = new Query("consult", new jpl.Atom(theory.getAbsolutePath()));
			if (!q.hasSolution()) {
				throw new RuntimeException("SWIPL can't read file: " + theory.getAbsolutePath());
			}
		}

		//start querying
		Query q = new Query(predicate, new jpl.Integer(iteration));
		res[0] = System.currentTimeMillis();
		try {
			@SuppressWarnings("rawtypes")
			Hashtable[] hs = q.allSolutions();
			if (outit) { 
				for(@SuppressWarnings("rawtypes") Hashtable h: hs) {
					System.out.println(h);
				}
			}						
		} catch(JPLException ex) {
			res[0] = Long.valueOf(-1);
			System.err.println(predicate + " caught error: " + ex.getMessage());
		}
		res[1] = System.currentTimeMillis();
		
		
		return res;
	}
}
