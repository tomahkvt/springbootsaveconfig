/*
 * Class for maping  from table "globalparam"
 */


package org.oa.getmac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "globalparam")
@XmlRootElement(name = "globalparam")
public class GlobalParamDB {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@XmlElement
	@Column(name = "paramkey", length = 50)
	private String paramkey;

	@XmlElement
	@Column(name = "paramvalue", length = 1000)
	private String paramvalue;

	public GlobalParamDB() {

	}

	public GlobalParamDB(String paramkey, String paramvalue) {
		super();
		this.id = id;
		this.paramkey = paramkey;
		this.paramvalue = paramvalue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParamkey() {
		return paramkey;
	}

	public void setParamkey(String paramkey) {
		this.paramkey = paramkey;
	}

	public String getParamvalue() {
		return paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	@Override
	public String toString() {
		return "GlobalParam [getId()=" + getId() + ", getParamkey()=" + getParamkey() + ", getParamvalue()="
				+ getParamvalue() + "]";
	}

}
