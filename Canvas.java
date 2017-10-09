package com.Canvas;

class Coordinate {
	int x;
	int y;
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Container{
	Coordinate x1y1;
	Coordinate x2y2;
	Container(Coordinate x1y1, Coordinate x2y2) {
		this.x1y1 = x1y1;
		this.x2y2 = x2y2;
	}
}
