/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

public class DTOSystemParam {
	private String detectSystem;
	private String winSavePath;
	private String linuxSavePath;

	public DTOSystemParam() {

	}

	public String getDetectSystem() {
		return detectSystem;
	}

	public void setDetectSystem(String detectSystem) {
		this.detectSystem = detectSystem;
	}

	public String getWinSavePath() {
		return winSavePath;
	}

	public void setWinSavePath(String winSavePath) {
		this.winSavePath = winSavePath;
	}

	public String getLinuxSavePath() {
		return linuxSavePath;
	}

	public void setLinuxSavePath(String linuxSavePath) {
		this.linuxSavePath = linuxSavePath;
	}

	@Override
	public String toString() {
		return "DTOSystemParam [getDetectSystem()=" + getDetectSystem() + ", getWinSavePath()=" + getWinSavePath()
				+ ", getLinuxSavePath()=" + getLinuxSavePath() + "]";
	}
}
