package com.constraint;

import java.awt.Color;

public interface Constraint {
	/** 地雷の総数 */
	public final int MINE_TOTAL = 10;
	/** 近隣の地雷の数の色 */
	public final Color[] NEIGHBOR_MINE_NUMBER_COLORS = new Color[]{ Color.BLUE, Color.GREEN, Color.RED, new Color(0, 0, 128), new Color(139, 69, 19), Color.CYAN, Color.BLACK, Color.DARK_GRAY };
    /** 旗を立てられるモード(ON) */
	public final String FLAG_MODE_ON = "ON";
	/** 旗を立てられるモード(OFF) */
    public final String FLAG_MODE_OFF = "OFF";
}
