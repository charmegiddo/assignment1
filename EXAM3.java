/**
 * 同字部分列の課題
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
public class EXAM3 {
	 /**
	 * プログラムスタートポイント
	 */
	public static void main (String[] args) {
		System.out.println("----------------------Class: コンストラクタに文字列を渡し同字部分列の解析を行う．そして専用の型でgetする[文字列を使い回す場合のbest practice]----------------------");
		CEquivalenceStringCount cCount = new CEquivalenceStringCount("aaBBBcC");	
		// 以下で値の変更も可能
		//cCount.equivalenceStringCount("aBBBcassWDDSsAWWWQQQQQQQQQQQqqQQQQQQW");
		CEquivalenceStringCountInfo cinfo = cCount.get_cEquivalenceStringCountInfo();
		System.out.println("From: " + cinfo.CFrom+ ", To: " +cinfo.CTo + ", Sum: " + cinfo.CSum);
		
	}
	
}

/**
 * 同字部分列の開始地点，終了地点，個数を保存するData群
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
class CEquivalenceStringCountInfo{
	// 同字部分列の開始
	public int CFrom = -1;		  
	// 同字部分列の終了
	public int CTo = -1;
	// 同字部分列の数
	public int CSum = -1;
}


/**
 * 同字部分列を求めるClass
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
class CEquivalenceStringCount{
	 // 同字部分列の開始地点，終了地点，個数を保存する型
	 private CEquivalenceStringCountInfo cEquivalenceStringCountInfo;
	 
	 /**
	  * CEquivalenceStringCountクラスのコンストラクタ
	  * 
	  * @version 1.0
	  */
	 public CEquivalenceStringCount(){
		 // コンストラクタが呼び出された時にnewしておく
		 cEquivalenceStringCountInfo  = new CEquivalenceStringCountInfo();
		 return;
	 }
	 
	 /**
	  * 
	  * CEquivalenceStringCountクラスの同字部分列を求めるコンストラクタ
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
		 	// 同字部分列保存用
		 	cEquivalenceStringCountInfo  = new CEquivalenceStringCountInfo();
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
           			 cEquivalenceStringCountInfo.CFrom = _tmp_i;
           		 	 cEquivalenceStringCountInfo.CTo = j;
           			 cEquivalenceStringCountInfo.CSum = _max_equivalence_count + 1;
           		}
               	
				} // for(int i = 0; i < _digitRow.length() - 1; i++)
			} // for(int j = i + 1; j < _digitRow.length(); j++)
		    return;
	 }

	 /**
	  * 
	  * Setter 新規に同字部分列を求める関数
	  * 
	  * @version 1.0
	  * @param _digitRow 同字部分列を求めたい数列
	  */
	 public void equivalenceStringCount(String _digitRow){
	 System.out.println("Your input string: " + _digitRow);
	 	
	 	// Validate
	 	if(!_digitRow.matches("[a-zA-Z]+")){
	 		System.out.println("Not a alphabet [a-zA-Z].");
	 		return;
	 	}
	 	// 同字部分列保存用
	 	cEquivalenceStringCountInfo  = new CEquivalenceStringCountInfo();
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
    			 cEquivalenceStringCountInfo.CFrom = _tmp_i;
    		 	 cEquivalenceStringCountInfo.CTo = j;
    			 cEquivalenceStringCountInfo.CSum = _max_equivalence_count + 1;
    		}
        	
			} // for(int i = 0; i < _digitRow.length() - 1; i++)
		} // for(int j = i + 1; j < _digitRow.length(); j++)
	    return;
	 }
	 
	 
	 /**
	  * 
	  * Getter 同字部分列をCEquivalenceStringCountInfo型で返す
	  * 
	  * 
	  * @version 1.0
	  * @return 同字部分列をCEquivalenceStringCountInfo型で返す
	  */
	public CEquivalenceStringCountInfo get_cEquivalenceStringCountInfo(){
		return cEquivalenceStringCountInfo;
	}	 
}