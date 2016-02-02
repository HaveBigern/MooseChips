package com.strath.visu.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.strath.visu.domain.DataClass;
import com.strath.visu.domain.Route;
import com.strath.visu.repository.RouteRepository;

@Service
public class ExcelConverterService {
	
	@Inject
	private RouteRepository routeRepository;

	public Route convertExcelToDataClass(MultipartFile file, Route route) {
		try {
			File excelFile = convert(file);
			FileInputStream inputStream = new FileInputStream(excelFile);
			Workbook workbook = WorkbookFactory.create(excelFile);
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			ArrayList<Cell> cellArray;
			List<DataClass> dataClasses = new ArrayList<DataClass>();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				cellArray = new ArrayList<>();
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					cellArray.add(cell);
				}
				dataClasses.add(createDataObject(cellArray));
			}
			route.setDataClasses(dataClasses);
			for(DataClass dataClass : route.getDataClasses()) {
				route.addChild(dataClass);
			}
			routeRepository.save(route);
			excelFile.delete();
			inputStream.close();
			return route;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private DataClass createDataObject(ArrayList<Cell> cells) {
		DataClass dataClass = new DataClass();
		dataClass.setBrakingCount((int) cells.get(22).getNumericCellValue());
		dataClass.setCollisionOppDir(cells.get(10).getNumericCellValue());
		dataClass.setCollisionTimeSameDir(cells.get(8).getNumericCellValue());
		dataClass.setCrashCount((int) cells.get(6).getNumericCellValue());
		dataClass.setDistanceOppDir(cells.get(11).getNumericCellValue());
		dataClass.setDistanceSameDir(cells.get(9).getNumericCellValue());
		dataClass.setDistanceTravelled(cells.get(0).getNumericCellValue());
		dataClass.setGear((int) cells.get(23).getNumericCellValue());
		dataClass.setLateralAccel(cells.get(13).getNumericCellValue());
		dataClass.setLateralLanePosition(cells.get(17).getNumericCellValue());
		dataClass.setLateralVelocity(cells.get(16).getNumericCellValue());
		dataClass.setLongAccelBrake(cells.get(5).getNumericCellValue());
		dataClass.setLongAccelThrottle(cells.get(4).getNumericCellValue());
		dataClass.setLongitudeAccel(cells.get(12).getNumericCellValue());
		dataClass.setLongitudeVelocity(cells.get(15).getNumericCellValue());
		dataClass.setOperatorMarker(cells.get(26).getNumericCellValue());
		dataClass.setRoadCurvature(cells.get(19).getNumericCellValue());
		dataClass.setSpeed(cells.get(3).getNumericCellValue());
		dataClass.setSpeedLimit((int) cells.get(2).getNumericCellValue());
		dataClass.setSpeedLimitFtSec((int) cells.get(14).getNumericCellValue());
		dataClass.setSteeringCount((int) cells.get(20).getNumericCellValue());
		dataClass.setSteeringRate(cells.get(7).getNumericCellValue());
		dataClass.setThrottleCount((int) cells.get(21).getNumericCellValue());
		dataClass.setTimeElapsed(cells.get(1).getNumericCellValue());
		dataClass.setTotalPitchingAngle(cells.get(27).getNumericCellValue());
		dataClass.setTotalRollingAngle(cells.get(28).getNumericCellValue());
		dataClass.setTrafficLightState((int) cells.get(24).getNumericCellValue());
		dataClass.setVehicleCurvature(cells.get(18).getNumericCellValue());
		dataClass.setYawRate(cells.get(25).getNumericCellValue());
		return dataClass;
	}
	
	public File convert(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}
	
	
}
