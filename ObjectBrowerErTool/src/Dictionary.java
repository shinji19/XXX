import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Dictionary {

	private HashMap mDictionary;
	
	public Dictionary(String dictionaryPath){
		
		mDictionary = new HashMap();
		
		try {
			File csv = new File(dictionaryPath);
			BufferedReader br = new BufferedReader(new FileReader(csv));
			
			String line = "";
			while( (line = br.readLine()) != null ){
				String[] temp = line.split(",", -1);
				temp[0] = temp[0].replace("\"", "");
				temp[1] = temp[1].replace("\"", "");
				mDictionary.put(temp[0], temp[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getvalue(String key){
		return (String) mDictionary.get(key);
	}
	
	public void print(){
		Set<Map.Entry<String, String>> entrySet = mDictionary.entrySet();
		Iterator<Map.Entry<String, String>> it = entrySet.iterator();
		
		while(it.hasNext()){
			Map.Entry<String, String> entry = it.next();
			
			String key = entry.getKey();
			String value = entry.getValue();
			
			System.out.println(key + ":" + mDictionary.get(key));
		}
	}
	
}
