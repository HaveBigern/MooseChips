package com.strath.visu.web.rest;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.strath.visu.domain.DataUser;
import com.strath.visu.domain.Route;
import com.strath.visu.repository.DataUserRepository;
import com.strath.visu.repository.TypeRepository;
import com.strath.visu.service.ExcelConverterService;

/**
 * REST controller for managing Excel uploads.
 */
@RestController
@RequestMapping("/upload")
public class UploadResource {

	private final Logger log = LoggerFactory.getLogger(DataClassResource.class);

	@Inject
	private ExcelConverterService converter;

	@Inject
	private DataUserRepository dataUserRepo;

	@Inject
	private TypeRepository typeRepository;

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Route> upload(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "route") String route) throws IOException, JSONException {
		log.debug("REST request to get File : {}");
		Route newRoute = new Route();
		JSONObject jsonRoute = new JSONObject(route);
		JSONObject dataUserJson = new JSONObject(jsonRoute.getString("dataUser"));
		Long userId = dataUserJson.getLong("dataUserId");
		DataUser dataUser = dataUserRepo.findOne(userId);
		newRoute.setName((String) jsonRoute.get("name"));
		newRoute.setType(typeRepository.findOne((long) jsonRoute.getInt("type")));
		newRoute.setDataUser(dataUser);
		return Optional.ofNullable(converter.convertExcelToDataClass(file, newRoute))
				.map(convertedFile -> new ResponseEntity<>(convertedFile, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
