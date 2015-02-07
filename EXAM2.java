/**
 * 同値部分列の課題
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
public class EXAM2 {
	 /**
	 * プログラムスタートポイント
	 */
	public static void main (String[] args) {
		System.out.println("----------------------Class: コンストラクタに値を渡し同値部分列の解析を行う．そして専用の型でgetする[値を使い回す場合のbest practice]----------------------");
		CEquivalenceNumberCount cCount = new CEquivalenceNumberCount("011011100");	
		// 以下で値の変更も可能
		//cCount.equivalenceNumberCount("0000111001111111");
		CEquivalenceNumberCountInfo cinfo = cCount.get_CEquivalenceNumberCountInfo();
		System.out.println("From: " + cinfo.CFrom+ ", To: " +cinfo.CTo + ", Sum: " + cinfo.CSum);
		
	}
	
}

/**
 * 同値部分列の開始地点，終了地点，個数を保存するData群
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
class CEquivalenceNumberCountInfo{
	// 同値部分列の開始
	public int CFrom = -1;		 
	// 同値部分列の終了
	 public int CTo = -1;
	 // 同値部分列の数
	 public int CSum = -1;
}



 /**
  * 同値部分列を求めるClass
  * 
  * @author Shintaro Hashimoto
  * @version 1.0
  */
 class CEquivalenceNumberCount{
	 // 同値部分列の開始地点，終了地点，個数を保存する型
	 private CEquivalenceNumberCountInfo cEquivalenceNumberCountInfo;
	 
	 /**
	  * CIncreasingNumberCountクラスのコンストラクタ
	  * 
	  * @version 1.0
	  */
	 public CEquivalenceNumberCount(){
		 // コンストラクタが呼び出された時にnewしておく
		 cEquivalenceNumberCountInfo  = new CEquivalenceNumberCountInfo();
		 return;
	 }
	 
	 /**
	  * 
	  * CEquivalenceNumberCountクラスの同値部分列を求めるコンストラクタ
	  * 最初に渡された値を解析し，開始地点・終了地点・個数を求めて変数に保存する
	  * 
	  * @version 1.0
	  * @param _digitRow 同値部分列を求めたい数列
	  */
	 public CEquivalenceNumberCount(String _digitRow){
		 System.out.println("Your input number: " + _digitRow);
		 	// 同値部分列保存用
		 	cEquivalenceNumberCountInfo  = new CEquivalenceNumberCountInfo();
			// 同値部分列数のカウント
			int _equivalence_count = 0;
			// 同値部分列の最大値　保存用
			int _max_equivalence_count = 0;
			// 同値部分列開始地点の記憶
			int _tmp_i;
			
			// 同値部分列開始地点のforループ
			for(int i = 0; i < _digitRow.length() - 1; i++){
				// 値の初期化と同値部分列開始地点の記憶
				_equivalence_count = 0;
    			_tmp_i = i;
    			// 同値部分列終了地点のforループ
        		for(int j = i + 1; j < _digitRow.length(); j++){
        			// 比較対象の保存
					int _compare_prev_number = Character.getNumericValue(_digitRow.charAt(j - 1));
					int _compare_now_number = Character.getNumericValue(_digitRow.charAt(j));
					
					// Validate
	        		if(_compare_prev_number == -1 || _compare_now_number == -1){
	        			// もし数字列でなく英数字などが含まれいたらエラーを返す
                    	System.out.println("Not a numeric value.");
                    	cEquivalenceNumberCountInfo.CTo = -1;
                    	cEquivalenceNumberCountInfo.CFrom = -1;
                    	cEquivalenceNumberCountInfo.CSum = -1;
                    	return;
                    }

	        		// 比較
                	if(_compare_now_number == _compare_prev_number){
                		// １つ前の数字と比較し，同じであればカウントアップ
                		_equivalence_count ++;
                		// 開始地点をずらすことで，一度走査した値を省く
                		i = j;
                	}else{
                		// 同じでない場合は，それ以上forループを回すのは無駄なのでbreak
                		break;
                	}
                	
                	// 同値部分列のカウント数が，これより前で出た同値部分列の最大カウント数を超えていたら書き換える．
            		if(_equivalence_count > _max_equivalence_count){
            			 _max_equivalence_count = _equivalence_count;
            			 cEquivalenceNumberCountInfo.CFrom = _tmp_i;
            			 cEquivalenceNumberCountInfo.CTo = j;
            			 cEquivalenceNumberCountInfo.CSum = _max_equivalence_count + 1;
            		}
                	
				} // for(int i = 0; i < _digitRow.length() - 1; i++)
			} // for(int j = i + 1; j < _digitRow.length(); j++)
		    return;
	 }

	 /**
	  * 
	  * Setter 新規に同値部分列を求める関数
	  * 
	  * @version 1.0
	  * @param _digitRow 同値部分列を求めたい数列
	  */
	 public void equivalenceNumberCount(String _digitRow){
		 System.out.println("Your input number: " + _digitRow);
		 	// 同値部分列保存用
		 	cEquivalenceNumberCountInfo  = new CEquivalenceNumberCountInfo();
			// 同値部分列数のカウント
			int _equivalence_count = 0;
			// 同値部分列の最大値　保存用
			int _max_equivalence_count = 0;
			// 同値部分列開始地点の記憶
			int _tmp_i;
			
			// 同値部分列開始地点のforループ
			for(int i = 0; i < _digitRow.length() - 1; i++){
				// 値の初期化と同値部分列開始地点の記憶
				_equivalence_count = 0;
    			_tmp_i = i;
    			// 同値部分列終了地点のforループ
        		for(int j = i + 1; j < _digitRow.length(); j++){
        			// 比較対象の保存
					int _compare_prev_number = Character.getNumericValue(_digitRow.charAt(j - 1));
					int _compare_now_number = Character.getNumericValue(_digitRow.charAt(j));
					
					// Validate
	        		if(_compare_prev_number == -1 || _compare_now_number == -1){
	        			// もし数字列でなく英数字などが含まれいたらエラーを返す
                    	System.out.println("Not a numeric value.");
                    	cEquivalenceNumberCountInfo.CTo = -1;
                    	cEquivalenceNumberCountInfo.CFrom = -1;
                    	cEquivalenceNumberCountInfo.CSum = -1;
                    	return;
                    }

	        		// 比較
                	if(_compare_now_number == _compare_prev_number){
                		// １つ前の数字と比較し，同じであればカウントアップ
                		_equivalence_count ++;
                		// 開始地点をずらすことで，一度走査した値を省く
                		i = j;
                	}else{
                		// 同じでない場合は，それ以上forループを回すのは無駄なのでbreak
                		break;
                	}
                	
                	// 同値部分列のカウント数が，これより前で出た同値部分列の最大カウント数を超えていたら書き換える．
            		if(_equivalence_count > _max_equivalence_count){
            			 _max_equivalence_count = _equivalence_count;
            			 cEquivalenceNumberCountInfo.CFrom = _tmp_i;
            			 cEquivalenceNumberCountInfo.CTo = j;
            			 cEquivalenceNumberCountInfo.CSum = _max_equivalence_count + 1;
            		}
                	
				} // for(int i = 0; i < _digitRow.length() - 1; i++)
			} // for(int j = i + 1; j < _digitRow.length(); j++)
		    return;
	 }
	 
	 /**
	  * 
	  * Getter 同値部分列をCEquivalenceNumberCountInfo型で返す
	  * 
	  * 
	  * @version 1.0
	  * @return 同値部分列をCEquivalenceNumberCountInfo型で返す
	  */
	public CEquivalenceNumberCountInfo get_CEquivalenceNumberCountInfo(){
		return cEquivalenceNumberCountInfo;
	}	 
 }