/*
 * Class uses for json mapping Node
 */

package org.oa.getmac.model;

public class FileNode extends FileNodeGeneral {
	private String id;
	private String text;
	private String icon;
	private Boolean children;

	public FileNode() {

	}

	public FileNode(String id, String text, String icon, Boolean children) {
		super();
		this.id = id;
		this.text = text;
		this.icon = icon;
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getChildren() {
		return children;
	}

	public void setChildren(Boolean children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
