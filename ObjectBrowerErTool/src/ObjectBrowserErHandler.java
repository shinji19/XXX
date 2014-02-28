import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * ”pŠü‚æ‚Ä‚¢
 * @author Shinji
 *
 */
public class ObjectBrowserErHandler extends DefaultHandler{

	
	public void startElement(String namaspaceURI, String localName, String qName, Attributes atts){
		
		if (qName.equals("ATTR")){
			String pName = atts.getValue("P-NAME");
			System.out.println(pName);
			
			
			
		}
	}
	
	
	
	public void characters(char[] c, int start, int length){
		
		//System.out.print("characters:");
		
		//System.out.println(new String(c).substring(start, start + length));
		
		
	}
	
}
