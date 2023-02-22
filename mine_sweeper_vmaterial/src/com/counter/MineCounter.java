package com.counter;

import com.board.Square;

/**
 * 地雷の数を算出するクラス。
 * 
 * @version 1.0
 * @since 2022/09/22
 * @author y.sawada
 */
public class MineCounter {

	/**
	 *  ターゲットのマスの、縦横斜めひとつ隣の地雷の数を算出する。
	 *  
	 *  @param x          マスの縦位置
	 *  @param y          マスの横位置
	 *  @param squareInfo 全てのマスの情報
	 *  
	 *  @return 地雷の数
	 */
	
	public static int countMineNumber(Square[][] squareInfo, int x, int y) {
		
		int mineNumber = 0;
		// 上
		if (y != 8 && squareInfo[x][y + 1].hasMine()) {
			mineNumber++;
		}
		
		// 下
		if (y != 0 && squareInfo[x][y - 1].hasMine()) {
			mineNumber++;
		}
		
		// 左
		if (x != 0 && squareInfo[x - 1][y].hasMine()) {
			mineNumber++;
		}
		
		// 右
		if (x != 8 && squareInfo[x + 1][y].hasMine()) {
			mineNumber++;
		}
		
		// 上左
		if (x != 0 && y != 8 && squareInfo[x - 1][y + 1].hasMine()) {
			mineNumber++;
		}
		
		// 上右
		if (x != 8 && y != 8 && squareInfo[x + 1][y + 1].hasMine()) {
			mineNumber++;
		}
		
		// 下左
		if (x != 0 && y != 0 && squareInfo[x - 1][y - 1].hasMine()) {
			mineNumber++;
		}
		
		// 下右
		if (x != 8 && y != 0 && squareInfo[x + 1][y - 1].hasMine()) {
			mineNumber++;
		}
		
		return mineNumber;
	}
	
	/* 1-7 自作コード
	public static int countMineNumber(Square[][] squareInfo, int x, int y) {
		int mineNumber = 0;
		boolean mine = squareInfo[x][y].hasMine();
		if (!mine) {
			for (int i = x - 1; i < x + 2; i++) {
				for (int j = y - 1; j < y + 2; j++) {
					if (i == x && j == y) {
						continue;
					}
					try {
						Square neighborSquare = squareInfo[i][j];
						boolean neighborSquareMine = neighborSquare.hasMine();
						if (neighborSquareMine) {
							mineNumber = mineNumber + 1;
						}
					} catch(Exception e) {
						continue;
					}
				}
			}
		}
		return mineNumber;
	}
	*/
}
