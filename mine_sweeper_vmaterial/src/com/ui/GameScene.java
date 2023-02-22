package com.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.constraint.Constraint;
import com.logic.Logic;
import com.util.SoundUtil;

/**
 * ゲーム画面クラス
 * 
 * @version 1.0
 * @since 2022/09/22
 * @author y.sawada
 */

public class GameScene extends JFrame implements Constraint{

	/** 全てのマス上のラベル */
	private JLabel[][] lblGroup = new JLabel[9][9];
	/** 全てのマス上のボタン */
	private JButton[][] btnGroup = new JButton[9][9];
	/** ロジッククラスのインスタスンス */
	private Logic logic;
	/** ゲーム画面のフレーム本体 */
	static GameScene frame;

	/** 9×9の地雷ラベル */
	//private JLabel[][] mineLabel = new JLabel[9][9];
	/**
	 * コンストラクタ
	 * 
	 * @param title タイトル
	 * @param mineLabel 地雷ラベル
	 */

	public GameScene(String title){
		//logic = new Logic(btnGroup); //1-5 自作コード
		setTitle(title);
		setBounds(500, 150, 400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setLayout(new FlowLayout());

		JPanel pnlUpper = new JPanel();
		pnlUpper.setPreferredSize(new Dimension(400, 80));
		pnlUpper.setLayout(null);

		ImageIcon iconMine = new ImageIcon("./img/mine_normal.png");
		JLabel lblMine = new JLabel(iconMine);
		lblMine.setBounds(10, 10, 60, 60);

		JLabel lblMineNumber = new JLabel(MINE_TOTAL + "");
		Font font = new Font("メイリオ", Font.BOLD, 20);
		lblMineNumber.setFont(font);
		lblMineNumber.setBounds(70, 10, 60, 60);

		/*
		Font flagModeFont = new Font("メイリオ", Font.BOLD, 20);
		lblFlagMode.setFont(flagModeFont);
		lblFlagMode.setBounds(270, 10, 60, 60);
		 */

		// 旗のアイコンとflagModeのオン・オフラベル
		/*
		ImageIcon iconFlagNormalOff = new ImageIcon("./img/btn_flag_normal_off.png");
		ImageIcon iconFlagNormalOn = new ImageIcon("./img/btn_flag_normal_on.png");
		JButton btnFlagNormal = new JButton("", iconFlagNormalOff);
		btnFlagNormal.setBounds(320, 10, 60, 60);
		btnFlagNormal.setFocusPainted(false);
		btnFlagNormal.addActionListener(new ActionListener(){

			// 旗のアイコンがクリックされた場合の処理
			@Override
			public void actionPerformed(ActionEvent e) {
				if(logic.isFlagMode()) {
					// flagModeがON(true)の場合の処理
					boolean flagMode = false;
					logic.setFlagMode(flagMode);
					btnFlagNormal.setIcon(iconFlagNormalOff);
					lblFlagMode.setText(FLAG_MODE_OFF);
				} else {
					// flagModeがOFF(false)の場合の処理
					boolean flagMode = true;
					logic.setFlagMode(flagMode);
					btnFlagNormal.setIcon(iconFlagNormalOn);
					lblFlagMode.setText(FLAG_MODE_ON);
				}
			}
		});
		*/

		// 1-3ラベルを敷き詰める
		// 9×9のラベルを敷き詰める用のパネルを作る
		JPanel pnlBottom = new JPanel();
		pnlBottom.setLayout(null);
		// pnlBottomの大きさを360×360にする。
		pnlBottom.setPreferredSize(new Dimension(360, 360)); 
		// pnlBottomの背景をLightGrayにする。
		pnlBottom.setBackground(Color.lightGray);
		
		JLabel lblFlgMode = new JLabel(FLAG_MODE_OFF);
		lblFlgMode.setFont(font);
		lblFlgMode.setBounds(270, 10, 60, 60);
		
		ImageIcon iconFlgOn = new ImageIcon("./img/btn_flag_normal_on.png");
		ImageIcon iconFlgOff = new ImageIcon("./img/btn_flag_normal_off.png");
		JButton btnFlg = new JButton(iconFlgOff);
		btnFlg.setBounds(320, 10, 60, 60);
		btnFlg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				SoundUtil.playSoundButtonFlag();
				// 旗を立てられるモードを反転する。
				logic.setFlagMode(!logic.isFlagMode());
				if (logic.isFlagMode()) {
					btnFlg.setIcon(iconFlgOn);
					lblFlgMode.setText(FLAG_MODE_ON);
				} else {
					btnFlg.setIcon(iconFlgOff);
					lblFlgMode.setText(FLAG_MODE_OFF);
				}
			}
			
		});

		ImageIcon iconSmile = new ImageIcon("./img/btn_smile.png");
		JButton btnFace = new JButton("", iconSmile);
		btnFace.setBounds(170, 10, 60, 60);
		btnFace.setFocusPainted(false);
		btnFace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SoundUtil.playSoundButtonFace();
				// リプレイ
				//btnFlagNormal.setIcon(iconFlagNormalOff);
				logic.init();
				btnFace.setIcon(iconSmile);
				btnFlg.setIcon(iconFlgOff);
				lblFlgMode.setText(FLAG_MODE_OFF);
				logic.setFlagMode(false);
			}
		});

		// マス上のボタンの初期設定
		for(int i = 0; i < btnGroup.length; i++) {
			for(int j = 0; j < btnGroup.length; j++) {
				//int x = i; 1-5自作コード
				//int y = j; 1-5自作コード
				//String si = String.valueOf(i);
				//String sj = String.valueOf(j);
				btnGroup[i][j] = new JButton();
				btnGroup[i][j].setBounds(40 * i, 40 * j, 40, 40);

				final int x = i;
				final int y = j;
				btnGroup[i][j].addActionListener(new ActionListener() { // ActionListenerをimplementsする必要はなし？

					@Override
					public void actionPerformed(ActionEvent e) {
						if(logic.openSquare(x, y)) {
							if(logic.isWin()) {
								SoundUtil.playSoundClear();
								ImageIcon iconLaugh = new ImageIcon("./img/btn_laugh.png");
								btnFace.setIcon(iconLaugh);
								JOptionPane.showMessageDialog(frame, new JLabel("クリアしました!!"));
							} else {
								ImageIcon iconDizzy = new ImageIcon("./img/btn_dizzy.png");
								btnFace.setIcon(iconDizzy);
							}
						} else {
							SoundUtil.playSoundSquare();
						}
					}
				});

				pnlBottom.add(btnGroup[i][j]);
			}
		}

		// マス上のラベルの初期設定
		for(int i = 0; i < lblGroup.length; i++) {
			for(int j = 0; j < lblGroup.length; j++) {
				lblGroup[i][j] = new JLabel("");
				lblGroup[i][j].setBounds(40 * i, 40 * j, 40, 40);
				lblGroup[i][j].setHorizontalAlignment(JLabel.CENTER);
				lblGroup[i][j].setVerticalAlignment(JLabel.CENTER);
				lblGroup[i][j].setBorder(new LineBorder(Color.GRAY, 1, false));
				pnlBottom.add(lblGroup[i][j]);
			}
		}

		pnlUpper.add(lblMine);
		pnlUpper.add(lblMineNumber);
		pnlUpper.add(btnFace);
		pnlUpper.add(lblFlgMode);
		pnlUpper.add(btnFlg);

		Container contentPane = getContentPane();
		contentPane.add(pnlUpper);
		contentPane.add(pnlBottom);
		//contentPane.add(pnlUnder); 1-3自作コード

		logic = new Logic(btnGroup, lblGroup);

		// ロジッククラスの初期設定。
		logic.init();
	}
}
