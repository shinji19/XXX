import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;




public class ChangeLanguageForXml extends DefaultHandler{
	
	
	private Dictionary mDictionary;
	
	/**
	 * 変換する
	 * @param filePath 入力ファイルパス
	 * @param outputFileName 出力ファイルパス
	 * @param dictionaryPath 辞書のファイルパス
	 */
	public ChangeLanguageForXml(String filePath, String outputFileName, String dictionaryPath){
		
		mDictionary = new Dictionary(dictionaryPath);
		
		String result = "";
		File file = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			
			String line = br.readLine();
			while (line != null){
				if (line.contains("ATTR") && line.contains("P-NAME")){
					String[] temp = line.split("P-NAME");
					//temp[1] = temp[1].concat(str)
					String s = changeString(temp[1]);
					
					line = temp[0] + "P-NAME" + s;
				}
				result += line + "\n";
				
				line = br.readLine();
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);

		
	}
	
	public void bufferedReaderVer(){
		
	}
	
	public void StAXVer(){
		/*
		//File xml = new File(filePath);
		try {
			InputStream inputStream = new FileInputStream(filePath);
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLEventReader reader = inputFactory.createXMLEventReader(
					inputStream);
			
			XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
			StringWriter stringWriter = new StringWriter();
			XMLEventWriter writer = outputFactory.createXMLEventWriter(stringWriter);
			
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			
			while (reader.hasNext()){
				XMLEvent event = reader.nextEvent();
				
				if (event.isStartElement()){
					StartElement startElement = event.asStartElement();
					//System.out.println(startElement.getName());
					
					if (startElement.getName().getLocalPart().equals("ATTR")){
						
						Attribute attribute = startElement.getAttributeByName(new QName("P-NAME"));
						System.out.println(attribute.getValue());
						
						//event = eventFactory.createStartE
					}
				}
				
				writer.add(event);
			}
			writer.close();
			
			System.out.println(stringWriter.toString());
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			
			String url = filePath;
			//DefaultHandler handler = new ObjectBrowserErHandler();
			
			parser.parse(new FileInputStream(url), this);
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	/**
	 * for StAX
	 */
	public void startElement(String namaspaceURI, String localName, String qName, Attributes atts){
		//System.out.println(qName);
		if (qName.equals("ATTR")){
			String pName = atts.getValue("P-NAME");
			System.out.println(pName);
		}
	}
	
	/**
	 * for StAX
	 */
	public void characters(char[] c, int start, int length){
		
		
		
		System.out.println(new String(c, start, length));
		
	}

	/**
	 * 文字列を辞書に基づき変換する。
	 * 辞書はスネーク記法である。（よてい）
	 * 
	 * @param s 変換もとの文字列
	 * @return 変換後の文字列
	 */
	private String changeString(String s){
		String[] key = mDictionary.getKeyList();
		
		String result = new String(s);
		for (int i = 0; i < key.length; i++){
			if (result.contains(key[i])){
				result = result.replace(key[i], mDictionary.getvalue(key[i]));
				//break;
			}
		}
		
		return result;
	}

}
