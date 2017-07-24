/*
 * class uses save data to file
 */

package org.oa.getmac.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveConfig {
	static public void writeToFile(String pathToSave, Device device, Command command, String data) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat timeFormat = new SimpleDateFormat("HH_mm");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		String timeString = timeFormat.format(date);
		String stringPath = pathToSave + device.getDeviceName();

		String fileName = command.getCommand()
				.replace(" ", "")
				.replace("\n", "") + "." +timeString + ".cfg";
		stringPath = stringPath + "/" + dateString + "/" + fileName;

		File file = new File(stringPath);
		Path path = Paths.get(stringPath);
		File directory = new File(path.getParent().toString());
		if (!file.exists()) {
			if (!directory.exists()) {
				directory.mkdirs();
			}
			try {
				if (file.createNewFile()) {
					// System.out.println(stringPath + " are created");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter writer;
		try {
			writer = new FileWriter(stringPath, false);

			writer.write(data);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
