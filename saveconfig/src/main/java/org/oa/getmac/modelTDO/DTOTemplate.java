/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOTemplate {
	@JsonProperty("DT_RowId")
	private int id;
	private DTOTerminalServer terminalServer;
	@NotEmpty
	private String templateName;

	public DTOTemplate() {

	}

	public DTOTemplate(DTOTerminalServer terminalServer, String templateName) {
		super();
		this.terminalServer = terminalServer;
		this.templateName = templateName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DTOTerminalServer getTerminalServer() {
		return terminalServer;
	}

	public void setTerminalServer(DTOTerminalServer terminalServer) {
		this.terminalServer = terminalServer;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Override
	public String toString() {
		return "DTOTemplate [getId()=" + getId() + ", getTerminalServer()=" + getTerminalServer()
				+ ", getTemplateName()=" + getTemplateName() + "]";
	}
}
