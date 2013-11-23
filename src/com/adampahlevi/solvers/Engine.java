package com.adampahlevi.solvers;

import java.io.*;
import java.util.*;

public class Engine {
	protected List<File> files;
	private File dir;
	protected Boolean outit = false;
	public void displayOutput(Boolean b) { this.outit = b; }
	
	public Engine(File dir) {
		this.dir = dir;
		if (!dir.isDirectory()) {
			throw new RuntimeException("It's not directory: " + dir);
		}
		
		newState();
	}
	
	public void newState() {
		files = new ArrayList<>();
	}
	
	public void load(File file) {
		if (file.exists() && file.isFile()) {
			files.add(file);
		} else {
			throw new IllegalArgumentException("Has to be an exist file on the disk: " + file);			
		}
	}
	
	/**
	 * Load file name located in the dir set during instantiation of the engine
	 * @param fileName
	 */
	public void load(String fileName) {
		load(new File(dir, fileName));
	}
}
