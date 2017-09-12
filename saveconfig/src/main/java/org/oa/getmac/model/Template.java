/*
 * class for mapping table "template"
 */
package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.oa.getmac.modelTDO.DTOTemplate;
import org.oa.getmac.modelTDO.DTOTerminalServer;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "template")
@XmlRootElement(name = "template")
public class Template {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne()
	@JoinColumn(name = "id_terminal_server")
	@XmlElement(name = "terminalserver")
	@JsonProperty("terminalserver")
	private TerminalServer terminalServer;
	@XmlElement
	@Column(name = "template_name", length = 50)
	private String templateName;
	

	public Template(){
		
	}

	public Template(TerminalServer terminalServer, String templateName) {
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

	public TerminalServer getTerminalServer() {
		return terminalServer;
	}

	public void setTerminalServer(TerminalServer terminalServer) {
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
		return "Template [getId()=" + getId() + ", getTerminalServer()=" + getTerminalServer() + ", getTemplateName()="
				+ getTemplateName() + "]";
	}
	
	public DTOTemplate getDTOTemplateForTable(){
		DTOTemplate dtoTemplate = new DTOTemplate();
		DTOTerminalServer dtoTerminalServer = new DTOTerminalServer();
		dtoTemplate.setId(this.getId());
		dtoTemplate.setTemplateName(this.getTemplateName());
		dtoTerminalServer.setId(this.getTerminalServer().getId());
		dtoTerminalServer.setDeviceName(this.getTerminalServer().getDeviceName());
		dtoTerminalServer.setDeviceIp(this.getTerminalServer().getDeviceIp());
		dtoTemplate.setTerminalServer(dtoTerminalServer);
		return dtoTemplate;
	}
	
	public void setDTOTemplate(DTOTemplate dtoTemplate){
		TerminalServer terminalServer = new TerminalServer();
		this.setId(dtoTemplate.getId());
		this.setTemplateName(dtoTemplate.getTemplateName());
		terminalServer.setId(dtoTemplate.getTerminalServer().getId());
		this.setTerminalServer(terminalServer);
	}
	
	public DTOTemplate getDTOTemplate(){
		DTOTemplate dtoTemplate = new DTOTemplate();
		DTOTerminalServer dtoTerminalServer = new DTOTerminalServer();
		dtoTemplate.setId(this.getId());
		dtoTemplate.setTemplateName(this.getTemplateName());
		dtoTerminalServer.setId(this.getTerminalServer().getId());
		dtoTemplate.setTerminalServer(dtoTerminalServer);
		return dtoTemplate;
	}

	public DTOTemplate getDTOTemplateForList() {
		DTOTemplate dtoTemplate = new DTOTemplate();
		dtoTemplate.setId(this.getId());
		dtoTemplate.setTemplateName(this.getTemplateName());
		return dtoTemplate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
		result = prime * result + ((terminalServer == null) ? 0 : terminalServer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Template other = (Template) obj;
		if (id != other.id)
			return false;
		if (templateName == null) {
			if (other.templateName != null)
				return false;
		} else if (!templateName.equals(other.templateName))
			return false;
		if (terminalServer == null) {
			if (other.terminalServer != null)
				return false;
		} else if (!terminalServer.equals(other.terminalServer))
			return false;
		return true;
	}
}
