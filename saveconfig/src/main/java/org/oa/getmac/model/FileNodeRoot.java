/*
 * Class uses for json mapping FileNodeRoot
 */

package org.oa.getmac.model;

import java.util.List;

public class FileNodeRoot extends FileNodeGeneral {
	private String id;
	private String text;
	private String icon;
	private List<FileNode> children;

	public FileNodeRoot() {

	}

	public FileNodeRoot(String id, String text, String icon, List<FileNode> children) {
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

	public List<FileNode> getChildren() {
		return children;
	}

	public void setChildren(List<FileNode> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
