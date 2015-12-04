package com;

public class expression {
	int level;
	double res;
	double t;
	int symbol;
	int now;
	int len;
	String code;
	int[] type;
	expression right;
	int num;

	void init() {
		level = 0;
		res = 0;
		now = 0;
		num = 0;
		symbol = 0;
	}

	public expression(double var) {
		t = var;
		init();
		type = new int[256];
		for (int i = 0; i < 256; i++) {
			type[i] = 0;
		}
		type['+'] = 1;
		type['-'] = 2;
		type['*'] = 3;
		type['/'] = 4;
	}

	public void work(int beg, int en) {
		right.accept(code.substring(beg, en));
		if (symbol == 0) {
			res = right.res;
		}
		if (symbol == 1) {
			res += right.res;
		}
		if (symbol == 2) {
			res -= right.res;
		}
		if (symbol == 3) {
			res *= right.res;
		}
		if (symbol == 4) {
			res /= right.res;
		}
		if (symbol == 5) {
			res = Math.pow(res, right.res);
		}
		symbol = 0;
	}

	int getLevel(int n, int x) {
		return n * 10 + (x + 1) / 2;
	}

	double getValue(String s) {
		System.out.println("the value we want to get is : " + s);
		int ii = 0;
		int jj = s.length() - 1;
		while (ii < s.length() && s.charAt(ii) == '(') {
			ii++;
		}
		while (jj >= 0 && s.charAt(jj) == ')') {
			jj--;
		}
		if (s.substring(ii, jj + 1).compareToIgnoreCase("t") == 0) {
			System.out.println("the value we get is : " + t);
			return t;
		}
		if (s.substring(ii, jj + 1).compareToIgnoreCase("pi") == 0) {
			System.out.println("the value we get is : " + Math.PI);
			return Math.PI;
		}
		int fun = ii;
		while (fun < s.length() && s.charAt(fun) != '(') {
			fun++;
		}
		boolean isFun = false;
		String funName = s.substring(ii, fun);
		if (funName.compareToIgnoreCase("sin") == 0) {
			isFun = true;
		}
		if (funName.compareToIgnoreCase("cos") == 0) {
			isFun = true;
		}
		if (funName.compareToIgnoreCase("tan") == 0) {
			isFun = true;
		}
		if (funName.compareToIgnoreCase("ln") == 0) {
			isFun = true;
		}
		if (funName.compareToIgnoreCase("exp") == 0) {
			isFun = true;
		}
		if (funName.compareToIgnoreCase("sqrt") == 0) {
			isFun = true;
		}
		double ans = 0;
		if (isFun) {
			System.out.println("find a function : " + funName);
			int fend = fun;
			int tmp = 0;
			while (fend < s.length()) {
				if (s.charAt(fend) == '(') {
					tmp++;
				}
				if (s.charAt(fend) == ')') {
					tmp--;
				}
				if (tmp == 0) {
					break;
				}
				fend++;
			}
			right = new expression(t);
			right.accept(s.substring(fun + 1, fend));
			if (funName.compareToIgnoreCase("sin") == 0) {
				ans = Math.sin(right.res);
				System.out.println("the value we get is : " + ans);
				return ans;
			}
			if (funName.compareToIgnoreCase("cos") == 0) {
				ans = Math.cos(right.res);
				System.out.println("the value we get is : " + ans);
				return ans;
			}
			if (funName.compareToIgnoreCase("tan") == 0) {
				ans = Math.tan(right.res);
				System.out.println("the value we get is : " + ans);
				return ans;
			}
			if (funName.compareToIgnoreCase("ln") == 0) {
				ans = Math.log(right.res);
				System.out.println("the value we get is : " + ans);
				return ans;
			}
			if (funName.compareToIgnoreCase("exp") == 0) {
				ans = Math.exp(right.res);
				System.out.println("the value we get is : " + ans);
				return ans;
			}
			if (funName.compareToIgnoreCase("sqrt") == 0) {
				ans = Math.sqrt(right.res);
				System.out.println("the value we get is : " + ans);
				return ans;
			}
		}

		boolean dot = false;
		double p = 0.1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')')
				continue;
			if (s.charAt(i) == ' ') {
				continue;
			}
			if (s.charAt(i) == '.') {
				dot = true;
				continue;
			}
			if (dot) {
				ans += p * (double) (s.charAt(i) - '0');
				p /= 10;
			} else {
				ans = ans * 10 + (int) (s.charAt(i) - '0');
			}
		}
		System.out.println("the value we get is : " + ans);
		return ans;
	}

	double cal(double a, double b) {
		if (symbol == 1) {
			return a + b;
		}
		if (symbol == 2) {
			return a - b;
		}
		if (symbol == 3) {
			return a * b;
		}
		if (symbol == 4) {
			return a / b;
		}
		return Math.pow(a, b);
	}

	public int read() {
		int to = now;
		int inFun = 0;
		while (to < len && (inFun == 1 || type[code.charAt(to)] == 0)) {
			if (inFun > 0) {
				if (code.charAt(to) == '(') {
					inFun++;
				}
				if (code.charAt(to) == ')') {
					inFun--;
				}
				to++;
				continue;
			}
			if (code.charAt(to) == '(') {
				if (to - now >= 2) {
					if (code.substring(to - 2, to).compareToIgnoreCase("ln") == 0) {
						inFun = 1;
						to++;
						continue;
					}
				}
				if (to - now >= 3) {
					if (code.substring(to - 3, to).compareToIgnoreCase("exp") == 0) {
						inFun = 1;
						to++;
						continue;
					}
					if (code.substring(to - 3, to).compareToIgnoreCase("cos") == 0) {
						inFun = 1;
						to++;
						continue;
					}
					if (code.substring(to - 3, to).compareToIgnoreCase("sin") == 0) {
						inFun = 1;
						to++;
						continue;
					}
					if (code.substring(to - 3, to).compareToIgnoreCase("tan") == 0) {
						inFun = 1;
						to++;
						continue;
					}
				}
				if (to - now >= 4) {
					if (code.substring(to - 4, to).compareToIgnoreCase("sqrt") == 0) {
						inFun = 1;
						to++;
						continue;
					}
				}
				num++;
			}
			if (code.charAt(to) == ')') {
				num--;
			}
			to++;
		}
		double value = getValue(code.substring(now, to));
		if (to == len) {
			if (symbol == 0) {
				res = value;
			} else {
				res = cal(res, value);
			}
			return to;
		}
		int newSymbol = type[code.charAt(to)];
		if (newSymbol == 3) {
			if (to + 1 < len && code.charAt(to + 1) == '*') {
				to++;
				newSymbol = 5;
			}
		}
		int newLevel = getLevel(num, newSymbol);
		if (symbol == 0) {
			res = value;
			symbol = newSymbol;
			level = newLevel;
			return to;

		} else {
			if (level > newLevel) {
				res = cal(res, value);
				symbol = newSymbol;
				level = newLevel;
				return to;
			} else {
				int too = to + 1;
				int tmp = 0;
				while (too < len) {
					if (type[code.charAt(too)] == '(') {
						tmp++;
					}
					if (type[code.charAt(too)] == ')') {
						tmp--;
					}
					if (type[code.charAt(too)] > 0) {
						if (level >= getLevel(num + tmp, type[code.charAt(too)])) {
							break;
						}
					}
					too++;
				}
				right = new expression(t);
				right.res = value;
				right.symbol = newSymbol;
				right.level = newLevel;
				work(now, too);
				return too;
			}
		}
	}

	public int accept(String s) {
		init();
		code = s;
		len = s.length();
		System.out.println("the expression is : " + code);
		now = 0;
		while (now < len) {
			now = read() + 1;
			if (now == -1)
				return 0;
		}
		return 1;
	}

}
