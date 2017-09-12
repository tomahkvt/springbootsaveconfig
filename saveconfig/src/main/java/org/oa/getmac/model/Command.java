/*
 * Class for mapping table "Command"
 */

package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.oa.getmac.modelTDO.DTOCommand;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "commands")
@XmlRootElement(name = "Command")
public class Command {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@XmlElement(name = "id_template")
	@JsonProperty("idTemplate")
	@Column(name = "id_template")
	private int idTemplate;

	@XmlElement
	@Column(name = "command_order")
	private int order;

	@XmlElement
	@Column(name = "command", length = 50)
	private String command;

	@XmlElement
	@Column(name = "wait_for", length = 50)
	private String waitFor;

	@XmlElement
	@Column(name = "wait_time")
	private Integer waitTime;

	@XmlElement
	@Column(name = "save")
	private boolean save;

	public Command() {

	}

	public Command(int idTemplate, int order, String command, String waitFor, Integer waitTime, boolean save) {
		super();
		this.idTemplate = idTemplate;
		this.order = order;
		this.command = command;
		this.waitFor = waitFor;
		this.waitTime = waitTime;
		this.save = save;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(int idTemplate) {
		this.idTemplate = idTemplate;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getWaitFor() {
		return waitFor;
	}

	public void setWaitFor(String waitFor) {
		this.waitFor = waitFor;
	}

	public Integer getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(Integer waitTime) {
		this.waitTime = waitTime;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	@Override
	public String toString() {
		return "Command [getId()=" + getId() + "\n" + ", getIdTemplate()=" + getIdTemplate() + "\n" + ", getOrder()="
				+ getOrder() + "\n" + ", getCommand()=" + getCommand() + "\n" + ", getWaitFor()=" + getWaitFor() + "\n"
				+ ", getWaitTime()=" + getWaitTime() + ", isSave()=" + isSave() + "]";
	}

	public DTOCommand getDTOCommand() {
		DTOCommand dtoCommand = new DTOCommand();
		dtoCommand.setId(this.getId());
		dtoCommand.setIdTemplate(this.getIdTemplate());
		dtoCommand.setCommand(this.getCommand());
		dtoCommand.setOrder(this.getOrder());
		dtoCommand.setWaitFor(this.getWaitFor());
		dtoCommand.setWaitTime(this.getWaitTime());
		dtoCommand.setSave(this.isSave());
		return dtoCommand;
	}

	public void setDTOCommand(DTOCommand dtoCommand) {
		this.setId(dtoCommand.getId());
		this.setIdTemplate(dtoCommand.getIdTemplate());
		this.setCommand(dtoCommand.getCommand());
		this.setOrder(dtoCommand.getOrder());
		this.setWaitFor(dtoCommand.getWaitFor());
		this.setWaitTime(dtoCommand.getWaitTime());
		this.setSave(dtoCommand.isSave());
	}
}
