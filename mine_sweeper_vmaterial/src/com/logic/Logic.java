package com.logic;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.board.Square;
import com.constraint.Constraint;
import com.counter.MineCounter;
import com.util.SoundUtil;

/**
 * ロジッククラス
 * 
 * @version 1.0
 * @since 2022/09/22
 * @author y.sawada
 *
 */

public class Logic implements Constraint{
	/** 全てのマス上のボタン */
	private JButton[][] btnGroup = new JButton[9][9];
	/** 全てのマス上のラベル */
	private JLabel[][] lblGroup = new JLabel[9][9];
	/** 全てのマスの情報 */
	private Square[][] squareInfo = new Square[9][9];
	/** ゲーム終了のフラグ */
	private boolean isGameOver = false;
	/** 旗を立てるモード */
	private boolean flagMode = false;
	/**
	 * コンストラクタ
	 *
	 *@param btnGroup 全てのマス上のボタン
	 *@param labelGroup 全てのマス上のラベル
	 */
	public Logic(JButton[][] btnGroup, JLabel[][] lblGroup) {
		this.btnGroup = btnGroup;
		this.lblGroup = lblGroup;
	}

	/**
	 * マインスイーパのゲームの初期設定。
	 * 
	 * @return なし
	 */

	public void init() {
		isGameOver = false;
		flagMode = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				squareInfo[i][j] = new Square();
				lblGroup[i][j].setText("");
				lblGroup[i][j].setBackground(Color.lightGray);
				lblGroup[i][j].setIcon(null);
				ImageIcon iconNormal = new ImageIcon("./img/btn_org.png");
				btnGroup[i][j].setIcon(iconNormal);
				btnGroup[i][j].setVisible(true);
			}
		}

		// 9 * 9個の仮のリストに、10個の地雷を入れる。(Trueが地雷あり、Falseが地雷なし。)
		ArrayList<Boolean> list = new ArrayList<>();
		for (int i = 0; i < 9 * 9; i++) {
			list.add(i < MINE_TOTAL);
		}

		// 仮のリストをシャッフルする
		Collections.shuffle(list);
		ImageIcon iconMine = new ImageIcon("./img/mine_small.png");

		// 地雷を置く。(全てのマス上のラベルをループして、仮のリストから地雷を置く。)
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (list.get(i * 9 + j)) {
					lblGroup[i][j].setIcon(iconMine);
				}
				// 全てのマスの情報の初期化
				Square square = new Square();
				squareInfo[i][j] = square;
				// 地雷ありなしの情報の取得
				squareInfo[i][j].setMine(list.get(i * 9 + j));
			}
		}
		setNeighborMineNumber();
	}

	/**
	 * 勝ち負けを判定する。
	 * 
	 * @return 勝ち負け
	 */

	// 2022年11月28日 isWinメソッド実装中
	public boolean isWin() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (lblGroup[i][j].getBackground() == Color.RED) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * ゲームの終了を判定する。
	 * 
	 * @return ゲームが終了したかどうか？
	 */

	private boolean isGameOver() {
		return isGameOver;
	}


	public boolean isFlagMode() {
		return flagMode;
	}

	public void setFlagMode(boolean flagMode) {
		this.flagMode = flagMode;
	}

	/**
	 * マスをクリックした時の処理
	 * 
	 * @param x マスの縦位置
	 * @param y マスの横位置
	 * 
	 * @return なし
	 */

	public boolean openSquare(int x, int y) {
		if (isGameOver) {
			return true;
		}

		// 旗を立てるモード場合の処理
		if (isFlagMode()) {
			//旗を立てられるフラグを反転する。
			squareInfo[x][y].setFlag(!squareInfo[x][y].isFlag());
			if (squareInfo[x][y].isFlag()) {
				ImageIcon iconFlgOn = new ImageIcon("./img/btn_flag.png");
				btnGroup[x][y].setIcon(iconFlgOn);
			} else {
				ImageIcon iconNormal = new ImageIcon("./img/btn_org.png");
				btnGroup[x][y].setIcon(iconNormal);
			}
			// マスを開ける場合の処理
		} else {
			if (squareInfo[x][y].isFlag()) {
				return isGameOver;
			}

			// 開くべき位置のxyのリストを取得する。
			ArrayList<int[]> squareList = getSquareToOpen(x, y);
			for(int i = 0; i < squareList.size(); i++) {
				btnGroup[squareList.get(i)[0]][squareList.get(i)[1]].setVisible(false);
			}
			
			int counter = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if(btnGroup[i][j].isVisible()) {
						counter++;
					}
				}
			}
			if (counter == MINE_TOTAL) {
				isGameOver = true;
				return true;
			}

		}
		return isGameOver;
	}
	

/**
 * 開くべき位置のxyのリストを取得する。
 * 
 * @param x
 * @param y
 * 
 * @return ArrayList<int []> インデックス0はx、インデックス1はy。
 */
private ArrayList<int[]> getSquareToOpen(int x, int y) {
	// 開くべき位置のxyのリスト
	ArrayList<int[]> squareList = new ArrayList<int[]>();
	// xyのマスが爆弾の場合は空のリストを返却する
	if (squareInfo[x][y].hasMine()) {
		// explodeMineメソッドの実行。
		explodeMine(x, y);
		// xyのマスに隣の地雷の数がある場合は、xyのマスだけ返却する
	} else if (squareInfo[x][y].getMineNumber() != 0) {
		squareList.add(new int[]{ x, y });
		// xyのマスに隣の地雷の数がない場合は、該当のリストを返却する
	} else {
		// 全てのマスをチェックしていない状態にする。
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				squareInfo[i][j].setSquareChecked(false);
			}
		}

		// 以下は、開くマスを確定するためのロジック。地雷の数なしのまわりは全て開く。
		boolean flg = true;
		int squareX = x;
		int squareY = y;

		// 地雷の数なしがあれば、さらにループする。
		while (flg) {
			flg = false;

			// ターゲットのマスを含む、縦横斜めのまわりのマスの、xyのリストを作る。
			ArrayList<int[]> tempList = getTempList(squareX, squareY);

			// 開けるべきマスのxyのリストに、重複しないように追加する
			for (int[] temp : tempList) {
				if (!squareList.contains(temp)) {
					squareList.add(temp);
				}
			}
			for (int[] squareXY : squareList) {
				// tempListに、地雷の数なしのマスがまだあれば、ターゲットのxyを変えてループさせるフラグをtrueにする。
				if (!squareInfo[squareXY[0]][squareXY[1]].isSquareChecked()) {
					flg = true;
					squareX = squareXY[0];
					squareY = squareXY[1];
					break;
				}
			}
		}
	}
	return squareList;
}

/**
 *  縦横斜め1つ隣りの地雷の数を設定する。
 *  
 *  @return なし
 */
private void setNeighborMineNumber() {
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			// 地雷の場合は、数を設定しない
			if (!squareInfo[i][j].hasMine()) {
				// 縦横斜め1つ隣りの地雷の数を取得する。
				squareInfo[i][j].setMineNumber(MineCounter.countMineNumber(squareInfo, i, j));
				if (squareInfo[i][j].getMineNumber() != 0) {
					lblGroup[i][j].setText(squareInfo[i][j].getMineNumber() + "");
					Font font = new Font("メイリオ", Font.BOLD, 16);
					lblGroup[i][j].setFont(font);
					lblGroup[i][j].setForeground(NEIGHBOR_MINE_NUMBER_COLORS[squareInfo[i][j].getMineNumber() - 1]);
				}
			}
		}
	}
}

/**
 * ターゲットマスの縦位置x横位置yの、開けるべきマスの縦位置x横位置yのリストを取得する。
 * 
 * @param x マスの縦位置
 * @param y マスの横位置
 * 
 * @return ArrayList<int[]> インデックス0は縦位置x、インデックス1は横位置y。
 */
private ArrayList<int[]> getTempList(int x, int y) {
	ArrayList<int[]> tempList = new ArrayList<int[]>();
	// ターゲットのマスをリストに追加する。
	tempList.add(new int[] { x, y });
	// ターゲットのマスは、チェック済みにする。
	squareInfo[x][y].setSquareChecked(true);
	// 縦横斜め ひとつ隣
	// 上
	if (y != 8) {
		tempList.add(new int[] {x, y + 1});
	}
	// 下
	if (y != 0) {
		tempList.add(new int[] {x, y - 1});
	}
	// 左
	if (x != 0) {
		tempList.add(new int[] {x - 1, y});
	}
	// 右
	if (x != 8) {
		tempList.add(new int[] {x + 1, y});
	}
	// 上左
	if (x != 0 && y != 8) {
		tempList.add(new int[] {x - 1, y + 1});
	}
	// 上右
	if (x != 8 && y != 8) {
		tempList.add(new int[] {x + 1, y + 1});
	}
	// 下左
	if (x != 0 && y != 0) {
		tempList.add(new int[] {x - 1, y - 1});
	}
	// 下右
	if (x != 8 && y != 0) {
		tempList.add(new int[] {x + 1, y - 1});
	}
	// ターゲットのマスで、隣の地雷の数がある場合は、チェック済みにする。
	for (int[] temp : tempList) {
		if (squareInfo[temp[0]][temp[1]].getMineNumber() != 0) {
			squareInfo[temp[0]][temp[1]].setSquareChecked(true);
		}
	}
	return tempList;
}

/**
 * 地雷を踏んだときの処理。
 * 
 * @param x マスの縦位置
 * @param y マスの横位置
 * 
 * @return なし
 */
private void explodeMine(int x, int y) {
	// ゲームが終了フラグをtrueに。
	isGameOver = true;
	// 地雷を踏んだマスを赤くする。
	lblGroup[x][y].setOpaque(true);
	lblGroup[x][y].setBackground(Color.RED);
	SoundUtil.playSoundMine();
	// 地雷のマスを全て開く。
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			boolean hasMine = squareInfo[i][j].hasMine();
			if (hasMine) {
				// 地雷のマスを全て開く。
				btnGroup[i][j].setVisible(false);
			}
		}
	}
}


/* 1-7 自作コード
	private void setNeighborMineNumber() {
		for (int k = 0; k < 9; k++) {
			for (int l = 0; l < 9; l++) {
				int mineNumber = MineCounter.countMineNumber(squareInfo, k, l);
				squareInfo[k][l].setMineNumber(mineNumber);
				if(mineNumber > 0) {
					lblGroup[k][l].setText(String.valueOf(mineNumber));
				}
			}
		}
	}
 */

}

/*@Override // 1-5自作コード
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		JButton jButton = (JButton)e.getSource();
		int jx = jButton.getBounds().x / 40;
		int jy = jButton.getBounds().y / 40;
		openSquare(jx, jy);
	}*/
