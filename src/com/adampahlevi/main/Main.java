package com.adampahlevi.main;

import java.io.File;

import com.adampahlevi.solvers.JLogEngine;
import com.adampahlevi.solvers.SWIPrologEngine;
import com.adampahlevi.solvers.TuPrologEngine;

public class Main {
	public static void main(String[] args) {
		AquariusBenchmarker benchmarker = new AquariusBenchmarker();
		
		SWIPrologEngine swiEngine = new SWIPrologEngine("benches");
		//jlog lag of append, !, and member
		JLogEngine jlogEngine = new JLogEngine("benches");
		TuPrologEngine tuPrologEngine = new TuPrologEngine("benches");
		
		benchmarker.setIteration(100);		
		benchmarker.setOutputStream(new File("result", "tupl"));
		benchmarker.changeEngine(tuPrologEngine);
		benchmarker.runAll();
		
		System.out.println("END OF TUPROLOG");
		
		benchmarker.setOutputStream(new File("result", "swi"));
		benchmarker.changeEngine(swiEngine);
		benchmarker.runAll();

		System.out.println("END OF SWI PROLOG");
		
		benchmarker.setOutputStream(new File("result", "jlog"));
		benchmarker.changeEngine(jlogEngine);
		benchmarker.runAll();
		
		System.out.println("END OF JLOG");
	}
	
	@SuppressWarnings("unused")
	private static void testJLOG() {
		JLogEngine jlog = new JLogEngine("benches");
		jlog.load("harness.pl");
		jlog.load("itak.pl");
		
		Long res[] = jlog.solve("bench(20).");
		for(Long r: res) {
			System.out.println(r);
		}
	}
	
	@SuppressWarnings("unused")
	private static void testSWIProlog() {
		SWIPrologEngine swipl = new SWIPrologEngine("benches");
		swipl.load("harness.pl");
		swipl.load("itak.pl");
		
		Long res[] = swipl.solve("bench", Integer.valueOf(20));
		for(Long r: res) {
			System.out.println(r);
		}
		System.out.println(res[1] - res[0]);
	}
	
	@SuppressWarnings("unused")
	private static void testTuProlog() {
		TuPrologEngine tupl = new TuPrologEngine("benches");
		tupl.load("harness.pl");
		tupl.load("itak.pl");
		
		Long res[] = tupl.solve("bench(20).");
		for(Long r: res) {
			System.out.println(r);
		}
		System.out.println(res[1] - res[0]);
	}
}
