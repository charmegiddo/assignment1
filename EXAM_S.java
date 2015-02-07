/**
 * 全ての問題を一連の形と見たとき
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
public class EXAM_S {
	 /**
	 * プログラムスタートポイント
	 */
	public static void main (String[] args) {
		CEquivalenceStringCount ceCount = new CEquivalenceStringCount("AABBAAccccAA");
		CCountInfo cinfo = ceCount.get_CCountInfo();
		System.out.println("From: " + cinfo.CFrom+ ", To: " +cinfo.CTo + ", Sum: " + cinfo.CSum);
		System.out.print("継承クラスによるオペレーターのオーバーライド(ベースクラスは==):" );ceCount.culculate______();
	}
}
/**
 * 特定数列・文字列における開始地点，終了地点，個数を保存するData群
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
class CCountInfo{
	// 同字部分列の開始
	public int CFrom = -1;		  
	// 同字部分列の終了
	public int CTo = -1;
	// 同字部分列の数
	public int CSum = -1;
}

/**
 * 特定数列・文字列における開始地点，終了地点，個数を計算するベースクラス
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
class CCount{
	 protected CCountInfo cCountInfo;
	 
	 public CCount(){
		 cCountInfo = new CCountInfo();
	 }

	 /**
	  * 特定数列・文字列における開始地点，終了地点，個数を計算する祭の
	  * 比較部分を担当
	  * 比較部分が変わるだけであれば，継承クラスでこの関数をオーバーライドすれば良い．
	  * 
	  * @author Shintaro Hashimoto
	  * @version 1.0
	  */
	 protected void  compare______(){
		 System.out.print(" == ");
		 return;
	 }
	 
	 /**
	  * 特定数列・文字列における開始地点，終了地点，個数を計算する関数
	  * 
	  * @author Shintaro Hashimoto
	  * @version 1.0
	  */
	 public void  culculate______(){
		 compare______();
		 return;
	 }
	 
	 /**
	  * Getter 特定数列・文字列における開始地点，終了地点，個数を返す
	  * 
	  * @author Shintaro Hashimoto
	  * @version 1.0
	  */
	public CCountInfo get_CCountInfo(){
		return cCountInfo;
	}	 
	
}

/**
 * 特定数列・文字列における開始地点，終了地点，個数を計算する継承クラス
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
class CEquivalenceStringCount extends CCount{
	
	 /**
	  * exam1-1，exam1-2のように比較部分のオペレーターが変わるだけであれば，継承クラスでこの関数をオーバーライドすれば良い．
	  * そして，継承クラスからcalculate_____()を呼ぶことで任意の結果を得られる．
	  * しかし，exam1-3のように比較対象が数列から文字列に変わり，さらに比較条件なども揺らぐと，compareクラスだけでは対処できなくなるかもしれない．
	  * そのため，処理部分は一括して継承クラスに書いた方がいいかもしれない．
	  * 
	  * @author Shintaro Hashimoto
	  * @version 1.0
	  */
	 protected void  compare______(){
		 System.out.print(" > ");
		 return;
	 }

	 /**
	  * 
	  * 同字部分列を求めるコンストラクタ
	  * 最初に渡された値を解析し，開始地点・終了地点・個数を求めて変数に保存する
	  * 
	  * @version 1.0
	  * @param _digitRow 同字部分列を求めたい数列
	  */
	 public CEquivalenceStringCount(String _digitRow){
		 System.out.println("Your input string: " + _digitRow);
		 	
		 	// Validate
		 	if(!_digitRow.matches("[a-zA-Z]+")){
		 		System.out.println("Not a alphabet [a-zA-Z].");
		 		return;
		 	}
		 	// 同字部分列数のカウント
			int _equivalence_count = 0;
			// 同字部分列の最大値　保存用
			int _max_equivalence_count = 0;
			// 同字部分列開始地点の記憶
			int _tmp_i;
			
			// 同字部分列開始地点のforループ
			for(int i = 0; i < _digitRow.length() - 1; i++){
			// 値の初期化と同字部分列開始地点の記憶
			_equivalence_count = 0;
  			_tmp_i = i;
  			// 同字部分列終了地点のforループ
      		for(int j = i + 1; j < _digitRow.length(); j++){
      			// 比較対象の保存
				char _compare_prev_char = _digitRow.charAt(j - 1);
				char _compare_now_char = _digitRow.charAt(j);
	        	
				// 比較
              	if(_compare_now_char == _compare_prev_char){
              		// １つ前の数字と比較し，同じであればカウントアップ
              		_equivalence_count ++;
              		// 開始地点をずらすことで，一度走査した値を省く
              		i = j;
              	}else{
              		// 同じでない場合は，それ以上forループを回すのは無駄なのでbreak
              		break;
              	}
              	
              	// 同字部分列のカウント数が，これより前で出た同字部分列の最大カウント数を超えていたら書き換える．
          		if(_equivalence_count > _max_equivalence_count){
          			 _max_equivalence_count = _equivalence_count;
          			 cCountInfo.CFrom = _tmp_i;
          			 cCountInfo.CTo = j;
          			 cCountInfo.CSum = _max_equivalence_count + 1;
          		}
              	
				} // for(int i = 0; i < _digitRow.length() - 1; i++)
			} // for(int j = i + 1; j < _digitRow.length(); j++)
		    return;
	 }
}