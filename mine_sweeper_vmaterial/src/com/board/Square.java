package com.board;

public class Square {
	/**
	 * マスの情報を格納するクラス
	 *
	 * @version 1.0
	 * @since 2022/09/22
	 * @author y.sawada
	 */
	
	// 変数
	/** 地雷 */
	private boolean mine = false;
	
	/** 縦横斜め1つ隣りの地雷の数 */
	private int mineNumber = 0;
	
	/** 旗 */
	private boolean flag = false;
	
	/** マスを開くチェックフラグ */
	private boolean squareChecked = false;
	
	/**
	 * 地雷が埋まっているかどうかを取得する。
	 * 
	 * @return 地雷が埋まっているかどうか
	 */
	
	public boolean hasMine() {
		return mine;
	}

	/**
	 * 地雷が埋まっているかどうかをセットする。
	 *  
	 * @return 地雷が埋まっているかどうか
	 */
	public void setMine(boolean mine) {
		this.mine = mine;
	}

	/**
	 * 縦横斜め1つ隣の地雷の数を取得する。
	 * 
	 * @return 縦横斜め1つ隣の地雷の数
	 */
	public int getMineNumber() {
		return mineNumber;
	}

	/**
	 * 縦横斜め1つ隣の地雷の数をセットする。
	 * 
	 * @return なし
	 */
	public void setMineNumber(int mineNumber) {
		this.mineNumber = mineNumber;
	}

	/**
	 * マスに旗が立っているかどうか取得する。
	 * 
	 * @return マスに旗が立っているかどうか
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * マスに旗が立っているかどうかをセットする。
	 * 
	 * @return なし
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	/**
	 * マスをチェックしたかどうかを取得する。
	 * 
	 * @return マスのチェック
	 */
	public boolean isSquareChecked() {
		return squareChecked;
	}
	
	/**
	 * マスをチェックしたかどうかをセットする。
	 * @param squareChecked マスのチェック
	 */
	public void setSquareChecked(boolean squareChecked) {
		this.squareChecked = squareChecked;
	}
}
