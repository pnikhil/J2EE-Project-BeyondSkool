package com.beyondskool.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import com.google.gson.Gson;

@Path("/upload")
public class ServiceJAXRS {

	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("BS");

	@Context
	private ServletContext context;
	@Resource
	ServletContext servletContext;

	@POST
	@Path("/file")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)

	public Response uploadFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		String json = null;
		Gson gson = new Gson();
		try {
			File backUpCentre = null;
			String folderName = getFolderName(fileMetaData.getFileName());
			int read = 0;
			int read2 = 0;
			byte[] bytes = new byte[1024];
			byte[] bytes2 = new byte[1024];
			String fileName = fileMetaData.getFileName();
			InputStream backupStream = IOUtils.toBufferedInputStream(fileInputStream);
			File dirForCentre = new File(context.getRealPath("uploads") + File.separator + folderName);
			if (!dirForCentre.exists()) {
				new File(context.getRealPath("uploads") + File.separator + folderName).mkdirs();
			}

			File file = new File(context.getRealPath("uploads") + File.separator + folderName + File.separator,
					fileName);
			OutputStream out = new FileOutputStream(file);
			while ((read = fileInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			fileInputStream.close();

			backUpCentre = new File(
					Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString() + "\\" + folderName);
			if (!backUpCentre.exists()) {
				new File(Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString() + "\\" + folderName)
						.mkdirs();
			}

			File file2 = new File(
					Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString() + "\\" + folderName,
					fileName);
			OutputStream out2 = new FileOutputStream(file2);

			while ((read2 = backupStream.read(bytes2)) != -1) {
				out2.write(bytes2, 0, read2);
			}
			out2.flush();
			out2.close();
			backupStream.close();

			String success = "Files Uploaded Successfully";
			json = gson.toJson(success);
		} catch (IOException e) {
			json = gson.toJson("Error in file upload");
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	public String getFolderName(String fileName) {

		String folderName = null;
		StringTokenizer st = new StringTokenizer(fileName, "_");
		while (st.hasMoreTokens()) {
			folderName = st.nextToken();
			break;
		}
		return folderName;
	}
}