/**
 * 増加部分列の課題
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
public class EXAM1 {
	 /**
	 * プログラムスタートポイント
	 */
	public static void main (String[] args) {
		
		// Static Class
		System.out.println("----------------------Static Class: 参照渡し----------------------");
		int tmp1[] = {1}, tmp2[] = {1};
		int sum = SCIncreasingNumberCount.increasingNumberCount("134297381",tmp1, tmp2);
		System.out.println("From: " + tmp1[0]+ ", To: " +tmp2[0] + ", Sum: " + sum);
		
		System.out.println("----------------------Static Class: バイナリ分割[最も軽量]----------------------");
		int from2to = SCIncreasingNumberCount.increasingNumberCount("134297381");
		System.out.println("From: " + (from2to >> 12) + ", To: " + (from2to & 0x000FFF));

		System.out.println("----------------------Static Class: Returnを選択----------------------");
		System.out.println("From: " + SCIncreasingNumberCount.increasingNumberCount("134297381", SCIncreasingNumberCount.RETURN_FROM) + 
							", To: " + SCIncreasingNumberCount.increasingNumberCount("134297381", SCIncreasingNumberCount.RETURN_TO) +
							", Sum: " + SCIncreasingNumberCount.increasingNumberCount("134297381", SCIncreasingNumberCount.RETURN_SUM));
		
		System.out.println("----------------------Static Class: 戻り値が専用の型[値がよく変わるのであればbest practice]----------------------");
		CIncreasingNumberCountInfo cinfo = SCIncreasingNumberCount.return_CIncreasingNumberCountInfo("134297381");
		System.out.println("From: " + cinfo.CFrom+ ", To: " +cinfo.CTo + ", Sum: " + cinfo.CSum);
		
		
		// Generally Class
		System.out.println("----------------------Class: コンストラクタに値を渡し，getで値をそれぞれ取得----------------------");
		CIncreasingNumberCount cCount = new CIncreasingNumberCount("134297381");		
		System.out.println("From: " + cCount.get_DigitRow_From()+ ", To: " +cCount.get_DigitRow_To() + ", Sum: " + cCount.get_DigitRow_Sum());
		
		System.out.println("----------------------Class: コンストラクタに値を渡し，getで専用の型を使う[値を使い回すのであればbest practice]----------------------");
		cinfo = cCount.get_CIncreasingNumberCountInfo();
		System.out.println("From: " + cinfo.CFrom+ ", To: " +cinfo.CTo + ", Sum: " + cinfo.CSum);
	}
	
}
/**
 * 増加部分列の開始と終了等を保存するData群
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
class CIncreasingNumberCountInfo{
	// 増加部分列の開始
	public int CFrom = -1;		 
	// 増加部分列の終了
	 public int CTo = -1;
	 // 増加部分列の数
	 public int CSum = -1;
}

/**
 * 増加部分列を求める関数群をまとめたStatic Class
 * 
 * @author Shintaro Hashimoto
 * @version 1.0
 */
 class SCIncreasingNumberCount
{	
	 // 引数にReturnさせたい値を選択する際の定数
	 public static final char RETURN_TO = 1;
	 public static final char RETURN_FROM = 2;
	 public static final char RETURN_SUM = 3;
	 
	 /**
	  * 
	  * 増加部分列の開始番号と終了番号，個数を求めるプログラム．
	  * 増加部分列の開始番号と終了番号は配列による参照渡しで値を返す．
	  * 
	  * @version 1.0
	  * @param _digitRow 増加部分列を求めたい数列
	  * @param outFrom 増加部分列の開始番号を格納する参照渡し（output）
	  * @param outTo 増加部分列の終了番号を格納する参照渡し（output）
	  * @return 正常終了の場合は，増加部分列の個数を返す．異常終了の場合は，-1を返す．
	  */
	public static int increasingNumberCount(String _digitRow, int[] outFrom, int[] outTo)
	{
		System.out.println("Your input number: " + _digitRow);
		// 増加部分列増加数のカウント
		int _increasing_count = 0;
		// 増加部分列増加数の最大値　保存用
		int _return_max_increasing_count = 0;
		// 増加部分列開始地点の記憶
		int _tmp_i;

		// 増加部分列開始地点のforループ
		for(int i = 0; i < _digitRow.length() - 1; i++){
			// 0~9の増加をカウントするので9以上はありえないので，breakする
			if (_return_max_increasing_count == 9) break;
			// 値の初期化と増加部分列開始地点の記憶
			_increasing_count = 0;
			_tmp_i = i;
			// 増加部分列終了地点のforループ
    		for(int j = i + 1; j < _digitRow.length(); j++){
    			// 比較対象の保存
				int _compare_prev_number = Character.getNumericValue(_digitRow.charAt(j - 1));
				int _compare_now_number = Character.getNumericValue(_digitRow.charAt(j));
				
				// Validate
        		if(_compare_prev_number == -1 || _compare_now_number == -1){
        			// もし数字列でなく英数字などが含まれいたらエラーを返す
                	System.out.println("Not a numeric value.");
                	outTo[0] = -1;
                	outFrom[0] = -1;
                	// 異常終了として-1を返す．
                	return -1;
                }

        		// 比較
            	if(_compare_now_number > _compare_prev_number){
            		// １つ前の数字と比較し，大きければカウントアップ
            		_increasing_count ++;
            		// 開始地点をずらすことで，一度走査した値を省く
            		i = j;
            	}else{
            		// 小さい値が出た場合は，それ以上forループを回すのは無駄なのでbreak
            		break;
            	}
            	
            	// 増加部分列のカウント数が，これより前で出た増加部分列の最大カウント数を超えていたら書き換える．
        		if(_increasing_count > _return_max_increasing_count){
        			 _return_max_increasing_count = _increasing_count;
        			 outFrom[0] = _tmp_i;
        			 outTo[0] = j;
        		}
            	
			} // for(int i = 0; i < _digitRow.length() - 1; i++)
		} // for(int j = i + 1; j < _digitRow.length(); j++){
		
		// 正常終了した場合は増加部分列の個数を返す
	    return _return_max_increasing_count + 1;
	} // public static int increasingNumberCountInfo(String _digitRow, int[] outFrom, int[] outTo)

	 /**
	  * 
	  * 増加部分列の開始番号と終了番号を求めるプログラム．
	  * 増加部分列の開始番号と終了番号はバイナリ列によって返される．
	  * int from2to = SCIncreasingNumberCount.increasingNumberCountInfo("134297381");
	  * System.out.println("From: " + (from2to >> 12) + ", To: " + (from2to & 0x000FFF));
	  * Int型の取りうる最大値が0x7FFFFFFFなので，これを２分割し，4095文字まで対応可能
	  * 
	  * @version 1.0
	  * @param _digitRow 増加部分列を求めたい数列
	  * @return 正常終了の場合は，増加部分列の開始番号と終了番号がバイナリ列によって返される．異常終了の場合は，-1を返す．
	  */
	public static int increasingNumberCount(String _digitRow)
	{
		System.out.println("Your input number: " + _digitRow);
		// 増加部分列増加数のカウント
		int _increasing_count = 0;
		// 増加部分列増加数の最大値　保存用
		int _max_increasing_count = 0;
		// 増加部分列開始，終了地点をバイナリ列でまとめて返す
		int _return_from_to = 0x000000;
		// 増加部分列開始終了地点の記憶
		int _tmp_i;
		int _to = 0;
		int _from = 0;
		
		// 増加部分列開始地点のforループ
		for(int i = 0; i < _digitRow.length() - 1; i++){
			// 0~9の増加をカウントするので9以上はありえないので，breakする
			if (_max_increasing_count == 9) break;
			// 値の初期化と増加部分列開始地点の記憶
			_increasing_count = 0;
			_tmp_i = i;
			// 増加部分列終了地点のforループ
    		for(int j = i + 1; j < _digitRow.length(); j++){
    			// 比較対象の保存
				int _compare_prev_number = Character.getNumericValue(_digitRow.charAt(j - 1));
				int _compare_now_number = Character.getNumericValue(_digitRow.charAt(j));
				
				// Validate
        		if(_compare_prev_number == -1 || _compare_now_number == -1){
        			// もし数字列でなく英数字などが含まれいたらエラーを返す
                	System.out.println("Not a numeric value.");
                	_to = -1;
                	_from = -1;
                	// 異常終了として-1を返す．
                	return -1;
                }

        		// 比較
            	if(_compare_now_number > _compare_prev_number){
            		// １つ前の数字と比較し，大きければカウントアップ
            		_increasing_count ++;
            		// 開始地点をずらすことで，一度走査した値を省く
            		i = j;
            	}else{
            		break;
            	}
            	
            	// 増加部分列のカウント数が，これより前で出た増加部分列の最大カウント数を超えていたら書き換える．
        		if(_increasing_count > _max_increasing_count){
        			 _max_increasing_count = _increasing_count;
        			 _to = _tmp_i;
        			 _from = j;
        		}
            	
			} // for(int i = 0; i < _digitRow.length() - 1; i++)
		} // for(int j = i + 1; j < _digitRow.length(); j++)

		// 増加部分列開始，終了地点をバイナリ列でまとめて返すための足し算
		_return_from_to = _return_from_to | _to << 12;
		_return_from_to = _return_from_to | _from;
	    return _return_from_to;
	} // increasingNumberCount
	
	 /**
	  * 
	  * 増加部分列の開始番号と終了番号，個数を求めるプログラム．
	  * 増加部分列の開始番号と終了番号，個数はCIncreasingNumberCountInfo型で返す．
	  * 
	  * @version 1.0
	  * @param _digitRow 増加部分列を求めたい数列
	  * @return 正常終了の場合は，増加部分列の開始番号と終了番号，個数をCIncreasingNumberCountInfo型で返す．異常終了の場合は，-1を返す．
	  */
	public static CIncreasingNumberCountInfo return_CIncreasingNumberCountInfo(String _digitRow){
		System.out.println("Your input number: " + _digitRow);
		// 増加部分列返却用型
		CIncreasingNumberCountInfo _return_cIncreasingNumberCountInfo = new CIncreasingNumberCountInfo();
		// 増加部分列増加数のカウント
		int _increasing_count = 0;
		// 増加部分列増加数の最大値　保存用
		int _max_increasing_count = 0;
		// 増加部分列開始地点の記憶
		int _tmp_i;
		
		// 増加部分列開始地点のforループ
		for(int i = 0; i < _digitRow.length() - 1; i++){
			// 0~9の増加をカウントするので9以上はありえないので，breakする
			if (_max_increasing_count == 9) break;
			// 値の初期化と増加部分列開始地点の記憶
			_increasing_count = 0;
			_tmp_i = i;
			// 増加部分列終了地点のforループ
			for(int j = i + 1; j < _digitRow.length(); j++){
				// 比較対象の保存
				int _compare_prev_number = Character.getNumericValue(_digitRow.charAt(j - 1));
				int _compare_now_number = Character.getNumericValue(_digitRow.charAt(j));
				
				// Validate
				if(_compare_prev_number == -1 || _compare_now_number == -1){
		        	System.out.println("Not a numeric value.");
		        	_return_cIncreasingNumberCountInfo.CTo = -1;
		        	_return_cIncreasingNumberCountInfo.CFrom = -1;
		        	return _return_cIncreasingNumberCountInfo;
		        }
		
				// 比較
		    	if(_compare_now_number > _compare_prev_number){
		    		// １つ前の数字と比較し，大きければカウントアップ
		    		_increasing_count ++;
		    		// 開始地点をずらすことで，一度走査した値を省く
		    		i = j;
		    	}else{
		    		// 小さい値が出た場合は，それ以上forループを回すのは無駄なのでbreak
		    		break;
		    	}
		    	// 増加部分列のカウント数が，これより前で出た増加部分列の最大カウント数を超えていたら書き換える．
				if(_increasing_count > _max_increasing_count){
					 _max_increasing_count = _increasing_count;
					 _return_cIncreasingNumberCountInfo.CFrom = _tmp_i;
					 _return_cIncreasingNumberCountInfo.CTo = j;
					 _return_cIncreasingNumberCountInfo.CSum = _max_increasing_count + 1;
				}
		    	
			} // for(int i = 0; i < _digitRow.length() - 1; i++)
		} // for(int j = i + 1; j < _digitRow.length(); j++)
		
		// 正常終了した場合は増加部分列を返す
		return _return_cIncreasingNumberCountInfo;
	}
	
	 /**
	  * 
	  * 増加部分列の開始番号と終了番号，個数を求めるプログラム．
	  * 戻り値は引数の_returnTypeによって変更できる．
	  * 
	  * @version 1.0
	  * @param _digitRow 増加部分列を求めたい数列
	  * @param _returnType 戻り値は引数の_returnTypeによって変更できる（output）
	  * @return 正常終了の場合は，_returnTypeに合わせた値を返す．異常終了の場合は，-1を返す．
	  */
	public static int increasingNumberCount(String _digitRow, char _returnType)
	{
		System.out.println("Your input number: " + _digitRow);
		// 増加部分列増加数のカウント
		int _increasing_count = 0;
		// 増加部分列増加数の最大値　保存用
		int _return_max_increasing_count = 0;
		// 増加部分列開始地点の記憶
		int _tmp_i;
		int _return_to = 0;
		int _return_from = 0;

		// 増加部分列開始地点のforループ
		for(int i = 0; i < _digitRow.length() - 1; i++){
			// 0~9の増加をカウントするので9以上はありえないので，breakする
			if (_return_max_increasing_count == 9) break;
			// 値の初期化と増加部分列開始地点の記憶
			_increasing_count = 0;
			_tmp_i = i;
			// 増加部分列終了地点のforループ
    		for(int j = i + 1; j < _digitRow.length(); j++){
    			// 比較対象の保存
				int _compare_prev_number = Character.getNumericValue(_digitRow.charAt(j - 1));
				int _compare_now_number = Character.getNumericValue(_digitRow.charAt(j));
				
				// Validate
        		if(_compare_prev_number == -1 || _compare_now_number == -1){
        			// もし数字列でなく英数字などが含まれいたらエラーを返す
                	System.out.println("Not a numeric value.");
                	_return_to = -1;
                	_return_from = -1;
                	// 異常終了として-1を返す．
                	return -1;
                }

        		// 比較
            	if(_compare_now_number > _compare_prev_number){
            		// １つ前の数字と比較し，大きければカウントアップ
            		_increasing_count ++;
            		// 開始地点をずらすことで，一度走査した値を省く
            		i = j;
            	}else{
            		// 小さい値が出た場合は，それ以上forループを回すのは無駄なのでbreak
            		break;
            	}
            	// 増加部分列のカウント数が，これより前で出た増加部分列の最大カウント数を超えていたら書き換える．
        		if(_increasing_count > _return_max_increasing_count){
        			 _return_max_increasing_count = _increasing_count;
        			 _return_from = _tmp_i;
        			 _return_to = j;
        		}
            	
			} // for(int i = 0; i < _digitRow.length() - 1; i++)
		} // for(int j = i + 1; j < _digitRow.length(); j++)
		// 戻り値は引数の_returnTypeによって変更される
		if(_returnType == RETURN_TO) return _return_to;
		if(_returnType == RETURN_FROM) return _return_from;
		if(_returnType == RETURN_SUM) return _return_max_increasing_count + 1;
	    return -1;
	} // increasingNumberCount
	
}

 
 /**
  * 増加部分列を求めるClass
  * 
  * @author Shintaro Hashimoto
  * @version 1.0
  */
 class CIncreasingNumberCount{
	 // 増加部分列の開始地点，終了地点，個数を保存する型
	 private CIncreasingNumberCountInfo cIncreasingNumberCountInfo;
	 
	 /**
	  * CIncreasingNumberCountクラスのコンストラクタ
	  * 
	  * @version 1.0
	  */
	 public CIncreasingNumberCount(){
		 // コンストラクタが呼び出された時にnewしておく
		 cIncreasingNumberCountInfo  = new CIncreasingNumberCountInfo();
		 return;
	 }
	 
	 /**
	  * 
	  * CIncreasingNumberCountクラスの増加部分列を求めるコンストラクタ
	  * 最初に渡された値を解析し，開始地点・終了地点・個数を求めて変数に保存する
	  * 
	  * @version 1.0
	  * @param _digitRow 増加部分列を求めたい数列
	  */
	 public CIncreasingNumberCount(String _digitRow){
		 System.out.println("Your input number: " + _digitRow);
		 	// 増加部分列保存用
		 	cIncreasingNumberCountInfo  = new CIncreasingNumberCountInfo();
			// 増加部分列増加数のカウント
			int _increasing_count = 0;
			// 増加部分列増加数の最大値　保存用
			int _max_increasing_count = 0;
			// 増加部分列開始地点の記憶
			int _tmp_i;
			
			// 増加部分列開始地点のforループ
			for(int i = 0; i < _digitRow.length() - 1; i++){
				// 0~9の増加をカウントするので9以上はありえないので，breakする
				if (_max_increasing_count == 9) break;
				// 値の初期化と増加部分列開始地点の記憶
				_increasing_count = 0;
    			_tmp_i = i;
    			// 増加部分列終了地点のforループ
        		for(int j = i + 1; j < _digitRow.length(); j++){
        			// 比較対象の保存
					int _compare_prev_number = Character.getNumericValue(_digitRow.charAt(j - 1));
					int _compare_now_number = Character.getNumericValue(_digitRow.charAt(j));
					
					// Validate
	        		if(_compare_prev_number == -1 || _compare_now_number == -1){
	        			// もし数字列でなく英数字などが含まれいたらエラーを返す
                    	System.out.println("Not a numeric value.");
                    	cIncreasingNumberCountInfo.CTo = -1;
                    	cIncreasingNumberCountInfo.CFrom = -1;
                    	return;
                    }

	        		// 比較
                	if(_compare_now_number > _compare_prev_number){
                		// １つ前の数字と比較し，大きければカウントアップ
                		_increasing_count ++;
                		// 開始地点をずらすことで，一度走査した値を省く
                		i = j;
                	}else{
                		// 小さい値が出た場合は，それ以上forループを回すのは無駄なのでbreak
                		break;
                	}
                	
                	// 増加部分列のカウント数が，これより前で出た増加部分列の最大カウント数を超えていたら書き換える．
            		if(_increasing_count > _max_increasing_count){
            			 _max_increasing_count = _increasing_count;
            			 cIncreasingNumberCountInfo.CFrom = _tmp_i;
            			 cIncreasingNumberCountInfo.CTo = j;
            			 cIncreasingNumberCountInfo.CSum = _max_increasing_count + 1;
            		}
                	
				} // for(int i = 0; i < _digitRow.length() - 1; i++)
			} // for(int j = i + 1; j < _digitRow.length(); j++)
		    return;
	 }
	 
	 /**
	  * 
	  * Setter 新規に増加部分列を求める関数
	  * 
	  * 
	  * @version 1.0
	  * @param _digitRow 増加部分列を求めたい数列
	  */
	public void increasingNumberCount(String _digitRow){
		System.out.println("Your input number: " + _digitRow);
		// 増加部分列増加数のカウント
		int _increasing_count = 0;
		// 増加部分列増加数の最大値　保存用
		int _max_increasing_count = 0;
		// 増加部分列開始地点の記憶
		int _tmp_i;
	
		// 増加部分列開始地点のforループ
		for(int i = 0; i < _digitRow.length() - 1; i++){
			// 0~9の増加をカウントするので9以上はありえないので，breakする
			if (_max_increasing_count == 9) break;
			// 値の初期化と増加部分列開始地点の記憶
			_increasing_count = 0;
			_tmp_i = i;
			// 増加部分列終了地点のforループ
			for(int j = i + 1; j < _digitRow.length(); j++){
				// 比較対象の保存
				int _compare_prev_number = Character.getNumericValue(_digitRow.charAt(j - 1));
				int _compare_now_number = Character.getNumericValue(_digitRow.charAt(j));
				
				// Validate
	    		if(_compare_prev_number == -1 || _compare_now_number == -1){
	    			// もし数字列でなく英数字などが含まれいたらエラーを返す
	    			System.out.println("Not a numeric value.");
	            	cIncreasingNumberCountInfo.CTo = -1;
	            	cIncreasingNumberCountInfo.CFrom = -1;
	            	// 異常終了として-1を返す．
	            	return;
	            }
	
	    		// 比較
	        	if(_compare_now_number > _compare_prev_number){
	        		// １つ前の数字と比較し，大きければカウントアップ
	        		_increasing_count ++;
	        		// 開始地点をずらすことで，一度走査した値を省く
	        		i = j;
	        	}else{
	        		// 小さい値が出た場合は，それ以上forループを回すのは無駄なのでbreak
	        		break;
	        	}
	        	// 増加部分列のカウント数が，これより前で出た増加部分列の最大カウント数を超えていたら書き換える．
	    		if(_increasing_count > _max_increasing_count){
	    			 _max_increasing_count = _increasing_count;
	    			 cIncreasingNumberCountInfo.CFrom = _tmp_i;
	    			 cIncreasingNumberCountInfo.CTo = j;
	    			 cIncreasingNumberCountInfo.CSum = _max_increasing_count + 1;
	     		}
	        	
			} // for(int i = 0; i < _digitRow.length() - 1; i++)
		} // for(int j = i + 1; j < _digitRow.length(); j++)
	    return;
	}
		
	 /**
	  * 
	  * Getter 増加部分列の終了地点
	  * 
	  * @version 1.0
	  * @return 増加部分列の終了地点
	  */
	public int get_DigitRow_To(){
		return cIncreasingNumberCountInfo.CTo;
	}
	
	 /**
	  * 
	  * Getter 増加部分列の開始地点
	  * 
	  * 
	  * @version 1.0
	  * @return 増加部分列の開始地点
	  */
	public int get_DigitRow_From(){
		return cIncreasingNumberCountInfo.CFrom;
	}
	
	 /**
	  * 
	  * Getter 増加部分列の個数
	  * 
	  * 
	  * @version 1.0
	  * @return 増加部分列の個数
	  */
	public int get_DigitRow_Sum(){
		return cIncreasingNumberCountInfo.CSum;
	}
	
	 /**
	  * 
	  * Getter 増加部分列をCIncreasingNumberCountInfo型で返す
	  * 
	  * 
	  * @version 1.0
	  * @return 増加部分列をCIncreasingNumberCountInfo型で返す
	  */
	public CIncreasingNumberCountInfo get_CIncreasingNumberCountInfo(){
		return cIncreasingNumberCountInfo;
	}
}

