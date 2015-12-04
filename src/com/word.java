package com;

public class word {
	String[] map;

	public word() {
		map = new String[11];
		map[1] = "rot";
		map[2] = "scale";
		map[3] = "origin";
		map[4] = "is";
		map[5] = "for";
		map[6] = "from";
		map[7] = "t";
		map[8] = "to";
		map[9] = "step";
		map[10] = "draw";

	}

	public int accept(String s) {
		System.out.println("the word is : "+ s);
		for (int i = 1; i <= 10; i++) {
			if (s.compareToIgnoreCase(map[i]) == 0) {
				return i;
			}
		}
		return -1;
	}
}
