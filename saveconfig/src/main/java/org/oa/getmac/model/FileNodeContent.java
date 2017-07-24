/*
 * Class uses for json mapping FileNodeContent
 */
package org.oa.getmac.model;

public class FileNodeContent extends FileNodeGeneral {
	private String content;
	private String type;

	public FileNodeContent() {

	}

	public FileNodeContent(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
