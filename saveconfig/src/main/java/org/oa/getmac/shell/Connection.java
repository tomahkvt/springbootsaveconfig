package org.oa.getmac.shell;

import java.util.List;
import java.util.Map;

import org.oa.getmac.model.Command;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.More;

public interface Connection {
	public boolean connect();
	public String getConnect();
	public boolean disconnect();
	public String getDisconnect();
	public boolean command(String command, String waitFor, int timeout);
	public boolean command(Device device, Command command);
	public String getCommand();
	public void setMoreList(List<More> mores);
	public List<More> getMoreList();
	public void doAllCommand(Device device);
}
