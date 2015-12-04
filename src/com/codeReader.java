package com;

import java.util.ArrayList;

public class codeReader {
	double rot;
	point scale;
	point origin;
	ArrayList<point> img;
	sentence s;

	public codeReader() {
		img = new ArrayList<>();
		s = new sentence();
	}

	public void init() {
		scale = new point(1, 1);
		origin = new point(0, 0);
		rot = 0;
		img.clear();
	}

	public void accept(String code) {
		int num = 1;
		int from, to, len;
		to = -1;
		len = code.length();
		while (to < len) {
			from = to + 1;
			while (from < len && (int) code.charAt(from) == 10) {
				from++;
				num++;
			}
			if (from == len) {
				break;
			}
			to = from;
			while (to < len && (int) code.charAt(to) != ';') {
				to++;
			}
			s.init();
			int res = s.accept(code.substring(from, to));
			if (res < 90) {
				System.out.println("compile error at line : " + num);
			}
			if (res == 93) {
				System.out.println("set point");
				for (int i = 0; i < s.resX.size(); i++) {
					double x = s.resX.get(i);
					double y = s.resY.get(i);
					x = x * scale.x;
					y = y * scale.y;
					x = x * Math.cos(rot) + y * Math.sin(rot);
					y = y * Math.cos(rot) + x * Math.sin(rot);
					x += origin.x;
					y += origin.y;
					img.add(new point(x, y));
					System.out.println(x + " " + y);
				}
			}
			if (res == 90) {
				rot = s.resNum;
				System.out.println("rot is changed : " + rot);
			}
			if (res == 91) {
				scale = new point(s.resPoint.x,s.resPoint.y);
				System.out.println("scale is changed : " + scale.x+","+scale.y);
			}
			if (res == 92) {
				origin = new point(s.resPoint.x,s.resPoint.y);
				origin.x /= 50;
				origin.y /= 50;
				System.out.println("origin is changed : " + origin.x+","+origin.y);
			}
			while (to < len && code.charAt(to) != '\n') {
				to++;
			}
		}

	}

}
