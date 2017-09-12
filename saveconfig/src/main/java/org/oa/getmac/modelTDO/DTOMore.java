/*
 * class for display data in JSON
 */

package org.oa.getmac.modelTDO;

public class DTOMore {
	private int id;
	private int idTemplate;
	private String more;
	private String moreDo;
	private boolean isdelete;
	private String moreDelete;

	public DTOMore() {

	}

	public DTOMore(int id, int idTemplate, String more, String moreDo, boolean isdelete, String moreDelete) {
		super();
		this.id = id;
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

	public void setMoreDo(String moreDo) {
		this.moreDo = moreDo;
	}

	@Override
	public String toString() {
		return "DTOMore [getId()=" + getId() + ", getIdTemplate()=" + getIdTemplate() + ", getMore()=" + getMore()
				+ ", getMoreDo()=" + getMoreDo() + ", isIsdelete()=" + isIsdelete() + ", getMoreDelete()="
				+ getMoreDelete() + "]";
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
}
