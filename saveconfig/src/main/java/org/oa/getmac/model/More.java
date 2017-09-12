/*
 * Class uses for mapping table "more"
 */

package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.oa.getmac.modelTDO.DTOMore;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "more")
@XmlRootElement(name = "more")
public class More {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@JsonProperty("idTemplate")
	@Column(name = "id_template")
	private int idTemplate;

	@XmlElement
	@Column(name = "more", length = 50)
	private String more;

	@XmlElement
	@Column(name = "more_do", length = 50)
	private String moreDo;

	@XmlElement
	@Column(name = "isdelete")
	private boolean isdelete;

	@XmlElement
	@Column(name = "more_delete", length = 50)
	private String moreDelete;

	public More() {

	}

	public More(int idTemplate, String more, String moreDo, boolean isdelete, String moreDelete) {
		super();
		this.idTemplate = idTemplate;
		this.more = more;
		this.moreDo = moreDo;
		this.isdelete = isdelete;
		this.moreDelete = moreDelete;
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

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getMoreDo() {
		return moreDo;
	}

	public void setMoreDo(String more_do) {
		this.moreDo = more_do;
	}

	public DTOMore getDTOMore() {
		DTOMore dtoMore = new DTOMore();
		dtoMore.setId(this.getId());
		dtoMore.setIdTemplate(this.getIdTemplate());
		dtoMore.setMore(this.getMore());
		dtoMore.setMoreDo(this.getMoreDo());
		dtoMore.setIsdelete(this.isIsdelete());
		dtoMore.setMoreDelete(this.getMoreDelete());
		return dtoMore;
	}

	public void setDTOMore(DTOMore dtoMore) {
		this.setId(dtoMore.getId());
		this.setIdTemplate(dtoMore.getIdTemplate());
		this.setMore(dtoMore.getMore());
		this.setMoreDo(dtoMore.getMoreDo());
		this.setIsdelete(dtoMore.isIsdelete());
		this.setMoreDelete(dtoMore.getMoreDelete());
	}

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

	public String getMoreDelete() {
		return moreDelete;
	}

	public void setMoreDelete(String moreDelete) {
		this.moreDelete = moreDelete;
	}

	@Override
	public String toString() {
		return "More [getId()=" + getId() + ", getIdTemplate()=" + getIdTemplate() + ", getMore()=" + getMore()
				+ ", getMoreDo()=" + getMoreDo() + ", getDTOMore()=" + getDTOMore() + ", isIsdelete()=" + isIsdelete()
				+ ", getMoreDelete()=" + getMoreDelete() + "]";
	}

}
