/*
 * Class uses for replace sentence
 */

package org.oa.getmac.model;

import java.util.HashMap;
import java.util.Map;

public class Macros {
	private Map<String, String> macros = new HashMap<String, String>();
	public Macros(){
		
	}
	public Map<String, String> getMacros() {
		return macros;
	}
	public void setMacros(Map<String, String> macros) {
		this.macros = macros;
	}
	
	
}
