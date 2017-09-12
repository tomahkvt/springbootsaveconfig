/*
 * Class controller for FileTree
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.FileNode;
import org.oa.getmac.model.FileNodeContent;
import org.oa.getmac.model.FileNodeRoot;
import org.oa.getmac.model.GlobalParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/filetree")
public class FileTree {

	private static final String APPLICATION_PDF = "text/html;charset=UTF-8";

	private static Logger log = Logger.getLogger(FileTree.class);
	@Autowired
	private GlobalParam globalParam;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/save", method = RequestMethod.GET, produces = APPLICATION_PDF)
	public @ResponseBody void downloadA(HttpServletResponse response,
			@RequestParam(value = "id", required = false) String idnode) throws IOException {
		log.info("Save: " + idnode);
		String currentpath = globalParam.getSavePath();
		File file = new File(currentpath + "\\" + idnode);
		InputStream in = new FileInputStream(file);
		response.setContentType(APPLICATION_PDF);
		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		response.setHeader("Content-Length", String.valueOf(file.length()));
		FileCopyUtils.copy(in, response.getOutputStream());
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Object getFiletree(@RequestParam(value = "operation", defaultValue = "") String operation,
			@RequestParam(value = "id", required = false) String idnode) {
		String currentpath = globalParam.getSavePath();
		log.info(currentpath);
		log.info(idnode);
		switch (operation) {
		case "get_node":
			if (idnode.equals("#") == true) {
				log.info("equeal");
				File fileCurrentPath = new File(currentpath);
				File[] arrayFiles = fileCurrentPath.listFiles();
				ArrayList<String> files = new ArrayList<>();
				ArrayList<String> directoryes = new ArrayList<>();
				ArrayList<FileNode> childrens = new ArrayList<>();
				for (File file : arrayFiles) {
					if (file.isDirectory()) {
						directoryes.add(file.getName());
					} else {
						files.add(file.getName());
					}
				}
				Collections.sort(directoryes);
				Collections.sort(files);
				for (String dir : directoryes) {
					childrens.add(new FileNode(dir, dir, "folder", true));
				}

				for (String file : files) {
					childrens.add(new FileNode(file, file, "file", false));
				}

				List<FileNodeRoot> result = new ArrayList<>();
				result.add(new FileNodeRoot("/", "root", "folder", childrens));
				return result;
			} else {
				File fileCurrentPath = new File(currentpath + "\\" + idnode);
				File[] arrayFiles = fileCurrentPath.listFiles();
				ArrayList<String> files = new ArrayList<>();
				ArrayList<String> directoryes = new ArrayList<>();
				ArrayList<FileNode> childrens = new ArrayList<>();

				for (File file : arrayFiles) {
					if (file.isDirectory()) {
						directoryes.add(file.getName());
					} else {
						files.add(file.getName());
					}
				}
				Collections.sort(directoryes);
				Collections.sort(files);

				for (String dir : directoryes) {

					childrens.add(new FileNode(idnode + "\\" + dir, dir, "folder", true));
				}

				for (String file : files) {
					childrens.add(new FileNode(idnode + "\\" + file, file, "file", false));
				}
				return childrens;

			}

		case "get_content":
			log.info("get_content");
			String content = "";
			File f = new File(currentpath + "\\" + idnode);
			try {
				byte[] bytes = Files.readAllBytes(f.toPath());
				content = new String(bytes, "UTF-8");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			FileNodeContent fileNodeContent = new FileNodeContent(content, "text");
			return fileNodeContent;
		default:
			return null;
		}
	}
}
