package com.Canvas;

import java.util.Scanner;

class Canvas {
	char[][] canvasArray;
	int width, height;
	public Canvas(int width, int height) {
		height+=2;
		width+=2;
		this.width = width;
		this.height = height;
		canvasArray = new char[height][width];
		drawLine(createContainer(0, 0, this.width-1, 0), '-'); 
		drawLine(createContainer(0, this.height-1, this.width-1, this.height-1), '-');  
		drawLine(createContainer(0, 1, 0, this.height-2), '|');  
		drawLine(createContainer(this.width-1, 1, this.width-1, this.height-2), '|');   	
	}
	
	public Container createContainer(int x1, int y1, int x2, int y2) {
		return (new Container(new Coordinate(x1, y1), new Coordinate(x2, y2)));
	}
	
	public void render() {		
		for(int i=0;i<this.height;i++) {
			for(int j=0;j<this.width;j++) {				 
				System.out.print(canvasArray[i][j]);
			}
			System.out.println();
		}
	}
	
	public void drawLine(Container container, char mchar) {
		for(int i=container.x1y1.y; i<=container.x2y2.y; i++) {
			for(int j=container.x1y1.x; j<=container.x2y2.x; j++) {
				canvasArray[i][j] = mchar;				
			}
		}		 
  	} 
	
	public void drawRectangle(Container container, char mchar) {
		drawLine(createContainer(container.x1y1.x,container.x1y1.y, container.x2y2.x, container.x1y1.y), mchar);
		drawLine(createContainer(container.x1y1.x,container.x1y1.y, container.x1y1.x, container.x2y2.y), mchar);
		drawLine(createContainer(container.x2y2.x,container.x1y1.y, container.x2y2.x, container.x2y2.y), mchar);
		drawLine(createContainer(container.x1y1.x,container.x2y2.y, container.x2y2.x, container.x2y2.y), mchar);
	}
	
	public void bucketFill(int x, int y, char mchar) throws InterruptedException {
		if((int)canvasArray[y][x] != 0) {
			return;
		}
		
		if(x > 0 || x < this.height || y > 0 || y  < this.width) {
			if((int)canvasArray[y][x] == 0)
				canvasArray[y][x] = mchar;
			bucketFill(x+1,y, mchar);
			bucketFill(x-1,y, mchar);
			bucketFill(x,y-1, mchar);
			bucketFill(x,y+1, mchar);			
		}
	}
}

public class CanvasConsole {	
	static Canvas canvas;
	public static void main(String[] args) throws NumberFormatException, InterruptedException {		
		Scanner scan = new Scanner(System.in);
		String command = new String();
		while(!command.equals("Q")) {
			System.out.print("enter command:");
			command = scan.nextLine();
			draw(command);
		}
		System.out.println("Program Exited!");
		scan.close();
	}
	
	private static void draw(String command) throws NumberFormatException, InterruptedException {
		char ch = command.charAt(0);
		String[] cmd;
		try {
			switch(ch) {
				case 'C' :
					cmd = command.split(" ");
					canvas = new Canvas(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]));
					canvas.render();
				break;
				case 'L' :
					cmd = command.split(" ");
					if(canvas == null){
						System.err.println("Canvas Missing!");
						return;
					}
					canvas.drawLine(canvas.createContainer(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4])),'X');
					canvas.render();
				break;
				case 'R' :
					cmd = command.split(" ");
					if(canvas == null){
						System.err.println("Canvas Missing!");
						return;
					}
					canvas.drawRectangle(canvas.createContainer(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4])),'X');
					canvas.render();
				break;
				case 'B' :
					cmd = command.split(" ");
					if(canvas == null){
						System.err.println("Canvas Missing!");
						return;
					}
					canvas.bucketFill(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),cmd[3].charAt(0));
					canvas.render();
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid command. Try again!!\n");
		} catch (Exception e) {
			System.out.println("Error Occured\n");
			e.printStackTrace();
			System.exit(1);
		}
	}
}