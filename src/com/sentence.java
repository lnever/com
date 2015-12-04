package com;

import java.util.ArrayList;

public class sentence {
	int state;
	point resPoint;
	double resNum;
	double step;
	double lb, ub;
	ArrayList<Double> resX;
	ArrayList<Double> resY;
	String code;
	int now;
	int len;
	word w;
	expression e;
	int[][] trans;

	public sentence() {
		resX = new ArrayList<>();
		resY = new ArrayList<>();
		trans = new int[100][100];
		resPoint = new point();
		w = new word();
		e = new expression(0);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				trans[i][j] = -1;
			}
		}
		trans[0][1] = 1;
		trans[0][2] = 2;
		trans[0][3] = 3;
		trans[0][5] = 4;

		trans[4][7] = 5;
		trans[5][6] = 11;

		trans[1][4] = 10;
		trans[2][4] = 20;
		trans[3][4] = 21;

		trans[10][1] = 90;
		trans[11][1] = 6;
		trans[6][8] = 12;
		trans[12][1] = 7;
		trans[7][9] = 13;
		trans[13][1] = 8;
		trans[8][10] = 22;

		trans[20][1] = 91;
		trans[21][1] = 92;
		trans[22][1] = 93;

		init();
	}

	void init() {
		resX.clear();
		resY.clear();
		lb = ub = 0;
		state = 0;
	}

	void delBlank() {
		while (now < len && code.charAt(now) == ' ') {
			now++;
		}
	}

	public int accept(String s) {
		code = s;
		len = code.length();
		now = 0;
		int to, res = -1;
		while (now < len && state < 90) {
			delBlank();
			to = now;
			if (state < 10) {
				while (to < len && code.charAt(to) != ' ')
					to++;
				res = w.accept(code.substring(now, to));
			} else {
				if (state < 20) {
					while (to < len && code.charAt(to) != ' ')
						to++;
					res = e.accept(code.substring(now, to));
					if (state == 10) {
						resNum = e.res;
					}
					if (state == 11) {
						lb = e.res;
					}
					if (state == 12) {
						ub = e.res;
					}
					if (state == 13) {
						step = e.res;
					}
				} else {
					if (state < 30) {
						int comma = now;
						while (comma < len && code.charAt(comma) != ',') {
							comma++;
						}
						to = comma;
						while (to > now && (code.charAt(to) == ' ' || code.charAt(to) == ',')) {
							to--;
						}
						to++;
						now++;
						if (state == 22) {
							for (double i = lb; i <= ub; i += step) {
								e.t = i;
								res = e.accept(code.substring(now, to));
								resX.add(e.res);
							}

						} else {
							res = e.accept(code.substring(now, to));
							resPoint.x = e.res;
						}
						now = comma + 1;
						delBlank();
						to = len - 1;
						while (to > now && code.charAt(to) != ')') {
							to--;
						}
						while (to > now && code.charAt(to) == ' ') {
							to--;
						}
						if (state == 22) {
							for (double i = lb; i <= ub; i += step) {
								e.t = i;
								res = e.accept(code.substring(now, to));
								resY.add(e.res);
							}

						} else {
							res = e.accept(code.substring(now, to));
							resPoint.y = e.res;
						}
					}
				}
			}
			if (res == -1) {
				return -1;
			}
			now = to;
			state = trans[state][res];
			System.out.println("state is now : " + state);
		}

		return state;
	}
}
