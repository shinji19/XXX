
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length != 3) {
			System.out.println("入力の数が正しくありません。");
			System.out.println("実行例： >java Main 'inputfile.edm' 'outputfile.edm' 'dictionaryfile' ");
			return;
		}

		String inputPath = args[0];
		String outputPath = args[1];
		String dictionaryPath = args[2];
		
		//String inputPath = "./input.edm";
		//String dictionaryPath = "./dictionary.csv";
		//String outputPath = "./output.edm";
		
		//new ChangeLanguageForXml("./input.edm", "./dictionary.csv", "./output.edm");
		new ChangeLanguageForXml(inputPath, outputPath, new Dictionary(dictionaryPath));
		

		//new Dictionary(dictionaryPath).setValue("a", "A");
		
/*
		Dictionary d = new Dictionary(dictionaryPath);
		String[] results = d.getKeys();
		for (int i = 0; i < results.length; i++){
			System.out.println(results[i]);
		}
		
		results = d.getValues();
		for (int i = 0; i < results.length; i++){
			System.out.println(results[i]);
		}
		
		results = d.getSnakeCaseValues();
		for (int i = 0; i < results.length; i++){
			System.out.println(results[i]);
		}*/
	}

}
