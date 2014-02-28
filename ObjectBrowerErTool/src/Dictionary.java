import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * 辞書クラス　
 * @author Shinji
 *
 */
public class Dictionary {

	//private final static int KEY = 0;		
	//private final static int VALUE = 1;
	//private String[][] mDataSet; 		//mDataSet[KEY][]:キー一覧、mDataSet[VALUE][]:値一覧
	
	private HashMap mHashMap;
	//private String[] mKeyList;
	
	/**
	 * コンストラクタ
	 * @param dictionaryPath 辞書ファイル(csv)のファイルパス
	 */
	public Dictionary(String dictionaryPath){
		mHashMap = new HashMap();
		
		try {
			File file = new File(dictionaryPath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			//読み込んだキーを一時的にＡｒｒａｙＬｉｓｔへ保存し、最後まで保存したら、
			//mDataSetに保存する
			//ArrayList<String> keyList = new ArrayList<String>();
			//ArrayList<String> valueList = new ArrayList<String>();
			
			String line = null;
			while ( (line = br.readLine()) != null){
				//lineをカンマで二分割
				String[] temp = line.split(",", -1);
				
				//ダブルコーテーションを取り除く
				temp[0] = temp[0].replaceAll("\"", "");
				temp[1] = temp[1].replaceAll("\"", "");
				
				//valueは変数名に使われることもあるので空白も除く
				temp[1] = temp[1].replaceAll(" ", "");
				
				//リストにキーと値を登録
				//keyList.add(temp[0]);
				//valueList.add(temp[1]);
				mHashMap.put(temp[0], temp[1]);
			}
			
			//ArrayListの内容をmDataSetへ移す。
			//mDataSet = new String[2][];
			//mDataSet[KEY] = (String[]) keyList.toArray(new String[0]);
			//mDataSet[VALUE] = (String[]) valueList.toArray(new String[0]);
			//sortDataSet(mDataSet);
			/*mKeyList = new String[keyList.size()];
			for (int i = 0; i < keyList.size(); i++){
				mKeyList[i] = new String(keyList.get(i));
			}*/
			
			//keyを文字列の長さ順にする
			/*Arrays.sort(mKeyList, new Comparator<String>(){

				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return ((String)o2).length() - ((String)o1).length();
				}
				
			});*/
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Dictionary Class:file not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Dictionary Class:bufferedReader can't read line");
			e.printStackTrace();
		}
		
		//print();
	}
	
	public void print(){
		/*for (int i = 0; i < mDataSet[KEY].length; i++){
			System.out.println(mDataSet[KEY][i]);
		}*/
		
		String[] keys = getKeys();
		for (int i = 0; i < keys.length; i++){
			System.out.println("key:" + keys[i] + " value:" + mHashMap.get(keys[i]));
		}
	}
	
	/**
	 * keyの長い順にソートする
	 * @param data
	 */
	/*
	public void sortDataSet(String[][] data){
		for (int i = 0; i < data[KEY].length - 1; i++){
			for (int j = i + 1; j < data[KEY].length; j++){
				if (data[KEY][i].length() < data[KEY][j].length()){
					String temp = data[KEY][i];
					data[KEY][i] = data[KEY][j];
					data[KEY][j] = temp;
					
					temp = data[VALUE][i];
					data[VALUE][i] = data[VALUE][j];
					data[VALUE][j] = temp;
				}
			}
		}
	}*/
	
	/**
	 * 辞書のキーリストを得る
	 * getValuesで得られる値リストと対応している
	 * @return 辞書のキーリスト
	 */
	public String[] getKeys(){
		/*String[] result = new String[mDataSet[KEY].length];
		
		//resultsへコピーする
		for (int i = 0; i < result.length; i++){
			result[i] = new String(mDataSet[KEY][i]);
		}
		
		return result;*/
		/*
		String[] results = new String[mKeyList.length];
		
		for (int i = 0; i < results.length; i++){
			results[i] = new String(mKeyList[i]);
		}
		*/
		
		Set<String> keySet = mHashMap.keySet();
		ArrayList<String> list = new ArrayList<String>();
		for (String s : keySet){
			list.add(new String(s));
		}
		
		String[] results = new String[list.size()];
		for (int i = 0; i < results.length; i++){
			results[i] = new String(list.get(i));
		}
		
		
		//String[] results = (String[]) keySet.toArray();
		Arrays.sort(results, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return ((String)o2).length() - ((String)o1).length();
			}
			
		});
		
		return results;
	}
	
	/**
	 * 辞書の値リストを得る
	 * getKeysで得られるキーリストと対応している
	 * @return 辞書の値リスト
	 */
	/*
	public String[] getValues(){
		String[] results = new String[mDataSet[VALUE].length];
		
		//resultsへコピーする
		for (int i = 0; i < results.length; i++){
			results[i] = new String(mDataSet[VALUE][i]);
		}
		
		return results;
	}*/
	
	/**
	 * 辞書の値リスト（スネーク記法）を得る
	 * getKeysで得られるキーリストと対応している
	 * @return 辞書の値リスト(スネーク記法)
	 */
	/*
	public String[] getSnakeCaseValues(){
		String[] results = new String[mDataSet[VALUE].length];
		
		//キャメル記法に変換しresultsへコピー
		for (int i = 0; i < results.length; i++){
			results[i] = new String( convertToSnakeCaseFromCamelCase(mDataSet[VALUE][i]) );
		}
		
		return results;
	}
	*/
	
	/**
	 * キーに対応するスネーク記法の値を返す
	 * @param key キー
	 * @return スネーク記法の値
	 */
	/*
	public String getSnakeCaseValue(String key){
		String[] values = getSnakeCaseValues();
		
		String result = null;

		for (int i = 0; i < mDataSet[KEY].length; i++){
			if (mDataSet[KEY][i].equals(key)){
				result = values[i];
				break;
			}
				
		}
		
		return result;
	}
	*/
	
	/**
	 * index番目の値を返す
	 * 適切なindexで無い場合nullが返る
	 * @param index 値リストのインデックス
	 * @return index番目の値
	 */
	/*
	public String getValue(int index){
		String result;
		
		//もしリストの長さ以上、または負のインデックスならnullを返すように設定
		if (index >= mDataSet[VALUE].length || index < 0) result = null;
		else result = mDataSet[VALUE][index];
		
		return result;
	}
	*/
	
	/**
	 * keyに対応する値を返す
	 * @param key キー
	 * @return keyに対応する値
	 */
	public String getValue(String key){
		/*String result = null;
		
		for (int i = 0; i < mDataSet[KEY].length; i++){
			//キーリストからkeyが見つかったとき
			if (mDataSet[KEY][i].equals(key)){
				result = mDataSet[VALUE][i];
				break;
			}
		}
		
		return result;
		*/
		
		return (String) mHashMap.get(key);
	}
	
	/**
	 * 文字列s（キャメル記法）をスネーク記法に変換する
	 * @param s	キャメル記法の文字列
	 * @return sをスネーク記法にしたもの
	 */
	/*
	public String convertToSnakeCaseFromCamelCase(String s){
		String result;
		
		if (s.length() == 0) return "";
		
		//int codeOfa = "a".charAt(0);
		//int codeOfz = "z".charAt(0);
		result = s.charAt(0) + "";
		for (int i = 1; i < s.length(); i++){
			if ((s.charAt(i) + "").matches("[a-z]+")){
			//if (s.charAt(i) < codeOfa || codeOfz < s.charAt(i) ){
				result += (s.charAt(i)+"").toUpperCase();
			}
			else{
				result += "_" + s.charAt(i);
			}
		}
		
		return result;
	}
	*/
	
	//前処理フラグ
	
	public void setValue(String key, String value){
		mHashMap.put(key, value);
	}
	
}
