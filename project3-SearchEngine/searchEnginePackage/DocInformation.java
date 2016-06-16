package searchEnginePackage;

/* 
 * Assignment 3
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

public class DocInformation {
	public String docName;
	public String docURL;
	public String docBody;
	
	public DocInformation(String DocName, String DocURL, String DocBody){
		if (DocName.trim().length()==0){
			DocName = "No Title";
		}
		this.docName = DocName;
		this.docURL = DocURL;
		this.docBody = DocBody;
	}
	
	public String getDocName(){
		return this.docName;
	}

	public String getDocURL() {
		return docURL;
	}

	public String getDocBody() {
		return docBody;
	}
}
