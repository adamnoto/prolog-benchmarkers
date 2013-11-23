package com.adampahlevi.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import com.adampahlevi.solvers.Engine;
import com.adampahlevi.solvers.JLogEngine;
import com.adampahlevi.solvers.SWIPrologEngine;
import com.adampahlevi.solvers.TuPrologEngine;
import java.io.*;

public class AquariusBenchmarker {
	private Engine engine;
	private Integer iteration;
	private PrintStream out = System.out;
	
	/**
	 * Instantiate a benchmarkers with default 100 iterations
	 * @param engine
	 */
	public AquariusBenchmarker(Engine engine) {
		this.engine = engine;
		this.iteration = 100;
	}
	
	/**
	 * With no engine, must be set later
	 */
	public AquariusBenchmarker() {
		this.engine = null;
		this.iteration = 100;
	}
	
	public void changeEngine(Engine to) {
		this.engine = to;
	}
	
	public void setIteration(Integer to) {
		this.iteration = to;
	}
	
	public void setOutputStream(PrintStream out) {
		this.out = out;
	}
	
	public void setOutputStream(File file) {
		try {
			this.out = new PrintStream(file);
		} catch (FileNotFoundException e) {
			try {
				file.createNewFile();
				this.out = new PrintStream(file);
			} catch (IOException e1) {
				throw new RuntimeException(e1.getMessage());
			}
		}
	}
	
	private void run(String fileName, Integer iteration) {
		if (engine == null) {
			throw new RuntimeException("Assign an engine!");
		}
		
		out.println(String.format("Running: %s for %S iteration", fileName, iteration));
		//don't forget to new state it
		engine.newState();
		//then load harness, then the respective benchy file.
		engine.load("harness.pl");
		engine.load(fileName);
		if (engine instanceof JLogEngine) engine.load("helper.pl");
		
		
		Long[] res = new Long[2];
		if (engine instanceof JLogEngine) {
			res = ((JLogEngine) engine).solve("bench(" + iteration + ").");
		} else if (engine instanceof TuPrologEngine) {
			res = ((TuPrologEngine) engine).solve("bench(" + iteration + ").");
		} else if (engine instanceof SWIPrologEngine) {
			res = ((SWIPrologEngine) engine).solve("bench", iteration);
		}
		
		Long extime = res[1] - res[0];
		if (res[0] == -1) {
			out.println("[error must occured executing this]");
		}
		out.println("Running time (ms.): " + extime);
		out.println();
	}
	
	private void run(String fileName) {
		run(fileName, this.iteration);
	}
	
	public void runAll() {		
		runBoyer();
		runBrowse();
		runCharParser();
		runCrypt();
		runDeriv();
		runDynamicUnitClause();
		runFastMU();
		runFlatten();
		runItak();
		runMetaQSort();
		runMU();
		runNReverseBuiltIn();
		runNReverse();
		runPoly();
		runPrimes();
		runProver();
		runQSort();
		runQueens();
		runQuery();
		runReducer();
		runSendMore();
		runSimpleAnalyzer();
		runTak();
		runUnify();
		runZebra();
	}
	
	public void runBoyer() {
		run("boyer.pl");
	}
	
	public void runBrowse() {
		run("browse.pl");
	}
	
	public void runCharParser() {
		run("chat_parser.pl");
	}
	
	public void runCrypt() {
		run("crypt.pl");
	}
	
	public void runDeriv() {
		run("deriv.pl");
	}
	
	public void runDynamicUnitClause() {
		run("dynamic_unit_clause.pl");
	}
	
	public void runFastMU() {
		run("fast_mu.pl");
	}
	
	public void runFlatten() {
		run("flatten.pl");
	}
	
	public void runItak() {
		run("itak.pl");
	}
	
	public void runMetaQSort() {
		run("meta_qsort.pl");
	}
	
	public void runMU() {
		run("mu.pl");
	}
	
	public void runNReverseBuiltIn() { run("nreverse_builtin.pl"); }
	public void runNReverse() { run("nreverse.pl"); }
	public void runPoly() { run("poly.pl"); }
	public void runPrimes() { run("primes.pl"); }
	public void runProver() { run("prover.pl"); }
	public void runQSort() { run("qsort.pl"); }
	public void runQueens() { run("queens.pl"); }
	public void runQuery() { run("query.pl"); }
	public void runReducer() { run("reducer.pl"); }
	public void runSendMore() { run("sendmore.pl"); }
	public void runSimpleAnalyzer() { run("simple_analyzer.pl"); }
	public void runTak() { run("tak.pl"); }
	public void runUnify() { run("unify.pl"); }
	public void runZebra() { run("zebra.pl"); }
}