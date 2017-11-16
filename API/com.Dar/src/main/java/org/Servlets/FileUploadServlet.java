package org.Servlets;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.Dao.interfaces.SpotDao;
import org.Entite.Spot;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.exceptions.NotFoundException;
import org.exceptions.UnauthorizedException;
import org.utils.CryptoUtils;
import org.utils.DAOFactory;
import org.utils.StringUtil;

public class FileUploadServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 1L;

	final String UPLOAD_DIR = "uploads/";
	final int MAX_UPLOAD_BYTES = 3 * 1024 * 1024; // 3 Mo
	final int MAX_RAM_BYTES = MAX_UPLOAD_BYTES;
	final String REQ_FILE_FIELD = "file";

	public FileUploadServlet() {
		super();
	}

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new Exception("no file uploaded");
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();

		factory.setSizeThreshold(MAX_RAM_BYTES); // RAM
		factory.setRepository(new File(UPLOAD_DIR));
				
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(MAX_UPLOAD_BYTES);
		
		List<FileItem> fileItems = upload.parseRequest(request);
		
		for(FileItem fi : fileItems) {
			if ( !fi.isFormField () && fi.getFieldName().equals(REQ_FILE_FIELD)) {
				
				// ensure file is jpeg
				String contentType = fi.getContentType();
				if (!contentType.equals("image/jpeg")) {
					throw new Exception("File must be jpg !");
				}

				// Write the file
				String rndFileName = CryptoUtils.randomHash() + ".jpg";
				System.out.println("Writing to : "+rndFileName);
				File file = new File("/tmp/"+rndFileName);
				fi.write(file);
				
				// Add image to requested ressource
				String ressourceName = StringUtil.getLastParam(request.getRequestURI(), 1);
				switch (ressourceName) {
				
				case "spot" : // route = /spot/{id}
					int spotId = Integer.parseInt(StringUtil.getLastParam(request.getRequestURI(), 0));
					SpotDao dao = DAOFactory.getSpot();
					Spot spot = dao.getById(spotId);
					
					// check spot is valid
					if (spot == null) {
						throw new NotFoundException("Spot not found");
					}
					
					// check you own the spot
					int uid = (int) request.getAttribute("userId");
					int expectedUid = spot.getCreator().getIdUser();
					
					if (uid != expectedUid) {
						throw new UnauthorizedException();
					}
					
					spot.getImages().add(rndFileName);
					dao.update(spot);
					break;
					
				default :
					throw new NotFoundException("Invalid ressource name "+ressourceName);
				}
				
				return "{ \"success\" : true }";
			}
		}

		throw new Exception("Field "+REQ_FILE_FIELD+" is not a file...");
	}


	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Route");
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Route");
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Route");
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Route");
	}
}
