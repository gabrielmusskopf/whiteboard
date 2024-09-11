package br.com.gabrielmusskopf.wbserver;

import lombok.Getter;

@Getter
public class Whiteboard {

	private final int width = 10;
	private final int height = 10;
	private final int[][] board = new int[width][height];

	public void update(Tool tool, int[][] affected) {
	}

	public int[][] getCurrentBoard() {
		return board;
	}

}
