package sample;

/**
 * コードとJavaDocを含むコメントの規約を示すクラス
 *
 * @version 1.0
 * @since 20yy/MM/dd
 * @author name
 */
public class CodingRules {

	/** 可変フィールド変数 */
	private String fieldParameta = "";

	/** 固定フィールド変数 */
	private String FIXED_VALUE = "固定値";

	/**
	 * コンストラクタ
	 *
	 * @param parameta 変数
	 */
	public CodingRules(String parameta) {
		this.fieldParameta = parameta;
	}

	/**
	 * 起動メソッド
	 *
	 * @param args 起動引数
	 */
	public static void main(String[] args) {
		// 変数は、直感的に第三者にも理解できる命名にする。
		// 強いて書かなければわかりにくい場合には、適宜//でコメントを入れる。

		// ctr+shif+o でインポート文を整形する。
		// ctr+shif+f でコードをフォーマットする。

		// 命名規約
		// 1.クラス名　
		// 英語の名詞で並べる　先頭は大文字、それ以外は小文字　単語の区切りは大文字、それ以外は小文字
		// 例）BlockBean
		//
		// 2.変数名（可変値）
		// 英語の名詞で並べる　先頭もそれ以外も小文字　単語の区切りは大文字、それ以外は小文字
		// 例）blockLocation
		//
		// 3.変数名（固定値）
		// 英語の名詞で並べる　先頭もそれ以外も大文字　単語の区切りごとにアンダーバーで区切る
		// 例）BLOCK_TOTAL
		//
		// 4.メソッド名
		// 英語の動詞から始める　先頭もそれ以外も小文字　単語の区切りは大文字、それ以外は小文字
		// 例）getName()
	}

	/**
	 * フィールド変数のゲッター
	 *
	 * @return フィールド変数
	 */
	public String getFieldParameta() {
		return fieldParameta;
	}

	/**
	 * フィールド変数のセッター
	 *
	 * @param fieldParameta フィールド変数
	 */
	public void setFieldParameta(String fieldParameta) {
		this.fieldParameta = fieldParameta;
	}
}