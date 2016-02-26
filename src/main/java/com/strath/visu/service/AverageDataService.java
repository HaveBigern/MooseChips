package com.strath.visu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.strath.visu.domain.DataClass;
import com.strath.visu.domain.Route;
import com.strath.visu.domain.Type;
import com.strath.visu.repository.DataClassRepository;
import com.strath.visu.repository.RouteRepository;
import com.strath.visu.repository.TypeRepository;

@Service
public class AverageDataService {

	@Inject
	private RouteRepository routeRepository;
	
	@Inject
	private TypeRepository typeRepository;

	@Inject
	private DataClassRepository dataClassRepository;
	
	public void createAverageRoutes() {
		List<Type> types = typeRepository.findAll();
		for(Type type : types) {
			if(type.getAverageRoute() != null) {
				routeRepository.deleteRoute(type.getAverageRoute().getRouteId());
			}
			List<Route> routes = routeRepository.getByExperimentType(type.getTypeId());
			Route averageRoute = createAverageDataClassList(routes);
			averageRoute.setName(type.getName() + " Average");
			averageRoute.setType(type);
			type.setAverageRoute(averageRoute);
			type.addChild(averageRoute);
			averageRoute.addChildType(type);
			typeRepository.save(type);
		}
	}
	
	private Route createAverageDataClassList(List<Route> routes) {
		List<List<DataClass>> listOfDataClassLists = new ArrayList<>();
		for(Route route : routes) {
			List<DataClass> dataClasses = dataClassRepository.getDataClassByRoute(route.getRouteId());
			listOfDataClassLists.add(dataClasses);
		}
		int largestList = 0;
		List<DataClass> avgDataClassList = new ArrayList<>();
		for(List<DataClass> dataClassList : listOfDataClassLists) {
			if(dataClassList.size() > largestList) {
				largestList = dataClassList.size();
			}
		}
		for(int i = 0; i <= largestList; i++) {
			DataClass avgDataClass = new DataClass();
			avgDataClass.setNumberOfClasses(0);
			avgDataClass.setBrakingCount(0);
			avgDataClass.setCollisionOppDir(0.0);
			avgDataClass.setCollisionTimeSameDir(0.0);
			avgDataClass.setCrashCount(0);
			avgDataClass.setDistanceOppDir(0.0);
			avgDataClass.setDistanceSameDir(0.0);
			avgDataClass.setDistanceTravelled(0.0);
			avgDataClass.setGear(0);
			avgDataClass.setLateralAccel(0.0);
			avgDataClass.setLateralLanePosition(0.0);
			avgDataClass.setLateralVelocity(0.0);
			avgDataClass.setLongAccelBrake(0.0);
			avgDataClass.setLongAccelThrottle(0.0);
			avgDataClass.setLongitudeAccel(0.0);
			avgDataClass.setLongitudeVelocity(0.0);
			avgDataClass.setOperatorMarker(0.0);
			avgDataClass.setRoadCurvature(0.0);
			avgDataClass.setSpeed(0.0);
			avgDataClass.setSpeedLimit(0);
			avgDataClass.setSpeedLimitFtSec(0);
			avgDataClass.setSteeringCount(0);
			avgDataClass.setSteeringRate(0.0);
			avgDataClass.setThrottleCount(0);
			avgDataClass.setTimeElapsed(0.0);
			avgDataClass.setTotalPitchingAngle(0.0);
			avgDataClass.setTotalRollingAngle(0.0);
			avgDataClass.setTrafficLightState(0);
			avgDataClass.setVehicleCurvature(0.0);
			avgDataClass.setYawRate(0.0);
			avgDataClassList.add(avgDataClass);
		}
		for(List<DataClass> dataClassList : listOfDataClassLists) {
			Collections.sort(dataClassList, new Comparator<DataClass>() {
			    @Override
			    public int compare(DataClass c1, DataClass c2) {
			        return Double.compare(c1.getTimeElapsed(), c2.getTimeElapsed());
			    }
			});
			for(int i = 0; i < dataClassList.size(); i++) {
				avgDataClassList.get(i).setBrakingCount(avgDataClassList.get(i).getBrakingCount() + dataClassList.get(i).getBrakingCount());
				avgDataClassList.get(i).setCollisionOppDir(avgDataClassList.get(i).getCollisionOppDir() + dataClassList.get(i).getCollisionOppDir() );
				avgDataClassList.get(i).setCollisionTimeSameDir(avgDataClassList.get(i).getCollisionTimeSameDir() + dataClassList.get(i).getCollisionTimeSameDir() );
				avgDataClassList.get(i).setCrashCount(avgDataClassList.get(i).getCrashCount() + dataClassList.get(i).getCrashCount() );
				avgDataClassList.get(i).setDistanceOppDir(avgDataClassList.get(i).getDistanceOppDir() + dataClassList.get(i).getDistanceOppDir() );
				avgDataClassList.get(i).setDistanceSameDir(avgDataClassList.get(i).getDistanceSameDir() + dataClassList.get(i).getDistanceSameDir() );
				avgDataClassList.get(i).setDistanceTravelled(avgDataClassList.get(i).getDistanceTravelled() + dataClassList.get(i).getDistanceTravelled() );
				avgDataClassList.get(i).setGear(avgDataClassList.get(i).getGear() + dataClassList.get(i).getGear() );
				avgDataClassList.get(i).setLateralAccel(avgDataClassList.get(i).getLateralAccel() + dataClassList.get(i).getLateralAccel() );
				avgDataClassList.get(i).setLateralLanePosition(avgDataClassList.get(i).getLateralLanePosition() + dataClassList.get(i).getLateralLanePosition() );
				avgDataClassList.get(i).setLateralVelocity(avgDataClassList.get(i).getLateralVelocity() + dataClassList.get(i).getLateralVelocity() );
				avgDataClassList.get(i).setLongAccelBrake(avgDataClassList.get(i).getLongAccelBrake() + dataClassList.get(i).getLongAccelBrake() );
				avgDataClassList.get(i).setLongAccelThrottle(avgDataClassList.get(i).getLongAccelThrottle() + dataClassList.get(i).getLongAccelThrottle() );
				avgDataClassList.get(i).setLongitudeAccel(avgDataClassList.get(i).getLongitudeAccel() + dataClassList.get(i).getLongitudeAccel() );
				avgDataClassList.get(i).setLongitudeVelocity(avgDataClassList.get(i).getLongitudeVelocity() + dataClassList.get(i).getLongitudeVelocity() );
				avgDataClassList.get(i).setOperatorMarker(avgDataClassList.get(i).getOperatorMarker() + dataClassList.get(i).getOperatorMarker() );
				avgDataClassList.get(i).setRoadCurvature(avgDataClassList.get(i).getRoadCurvature() + dataClassList.get(i).getRoadCurvature() );
				avgDataClassList.get(i).setSpeed(avgDataClassList.get(i).getSpeed() + dataClassList.get(i).getSpeed() );
				avgDataClassList.get(i).setSpeedLimit(avgDataClassList.get(i).getSpeedLimit() + dataClassList.get(i).getSpeedLimit() );
				avgDataClassList.get(i).setSpeedLimitFtSec(avgDataClassList.get(i).getSpeedLimitFtSec() + dataClassList.get(i).getSpeedLimitFtSec() );
				avgDataClassList.get(i).setSteeringCount(avgDataClassList.get(i).getSteeringCount() + dataClassList.get(i).getSteeringCount() );
				avgDataClassList.get(i).setSteeringRate(avgDataClassList.get(i).getSteeringRate() + dataClassList.get(i).getSteeringRate() );
				avgDataClassList.get(i).setThrottleCount(avgDataClassList.get(i).getThrottleCount() + dataClassList.get(i).getThrottleCount() );
				avgDataClassList.get(i).setTimeElapsed(avgDataClassList.get(i).getTimeElapsed() + dataClassList.get(i).getTimeElapsed() );
				avgDataClassList.get(i).setTotalPitchingAngle(avgDataClassList.get(i).getTotalPitchingAngle() + dataClassList.get(i).getTotalPitchingAngle() );
				avgDataClassList.get(i).setTotalRollingAngle(avgDataClassList.get(i).getTotalRollingAngle() + dataClassList.get(i).getTotalRollingAngle() );
				avgDataClassList.get(i).setTrafficLightState(avgDataClassList.get(i).getTrafficLightState() + dataClassList.get(i).getTrafficLightState() );
				avgDataClassList.get(i).setVehicleCurvature(avgDataClassList.get(i).getVehicleCurvature() + dataClassList.get(i).getVehicleCurvature() );
				avgDataClassList.get(i).setYawRate(avgDataClassList.get(i).getYawRate() + dataClassList.get(i).getYawRate() );
				avgDataClassList.get(i).setNumberOfClasses(avgDataClassList.get(i).getNumberOfClasses() + 1);
			}	
		}
		for(DataClass dataClass : avgDataClassList) {
			if(dataClass.getNumberOfClasses() > 0) {
				dataClass.setBrakingCount(dataClass.getBrakingCount()/dataClass.getNumberOfClasses());
				dataClass.setCollisionOppDir(dataClass.getCollisionOppDir()/dataClass.getNumberOfClasses());
				dataClass.setCollisionTimeSameDir(dataClass.getCollisionTimeSameDir()/dataClass.getNumberOfClasses());
				dataClass.setCrashCount(dataClass.getCrashCount()/dataClass.getNumberOfClasses());
				dataClass.setDistanceOppDir(dataClass.getDistanceOppDir()/dataClass.getNumberOfClasses());
				dataClass.setDistanceSameDir(dataClass.getDistanceSameDir()/dataClass.getNumberOfClasses());
				dataClass.setDistanceTravelled(dataClass.getDistanceTravelled()/dataClass.getNumberOfClasses());
				dataClass.setGear(dataClass.getGear()/dataClass.getNumberOfClasses());
				dataClass.setLateralAccel(dataClass.getLateralAccel()/dataClass.getNumberOfClasses());
				dataClass.setLateralLanePosition(dataClass.getLateralLanePosition()/dataClass.getNumberOfClasses());
				dataClass.setLateralVelocity(dataClass.getLateralVelocity()/dataClass.getNumberOfClasses());
				dataClass.setLongAccelBrake(dataClass.getLongAccelBrake()/dataClass.getNumberOfClasses());
				dataClass.setLongAccelThrottle(dataClass.getLongAccelBrake()/dataClass.getNumberOfClasses());
				dataClass.setLongitudeAccel(dataClass.getLongitudeAccel()/dataClass.getNumberOfClasses());
				dataClass.setLongitudeVelocity(dataClass.getLongitudeVelocity()/dataClass.getNumberOfClasses());
				dataClass.setOperatorMarker(dataClass.getOperatorMarker()/dataClass.getNumberOfClasses());
				dataClass.setRoadCurvature(dataClass.getRoadCurvature()/dataClass.getNumberOfClasses());
				dataClass.setSpeed(dataClass.getSpeed()/dataClass.getNumberOfClasses());
				dataClass.setSpeedLimit(dataClass.getSpeedLimit()/dataClass.getNumberOfClasses());
				dataClass.setSpeedLimitFtSec(dataClass.getSpeedLimitFtSec()/dataClass.getNumberOfClasses());
				dataClass.setSteeringCount(dataClass.getSteeringCount()/dataClass.getNumberOfClasses());
				dataClass.setSteeringRate(dataClass.getSteeringRate()/dataClass.getNumberOfClasses());
				dataClass.setThrottleCount(dataClass.getThrottleCount()/dataClass.getNumberOfClasses());
				dataClass.setTimeElapsed(dataClass.getTimeElapsed()/dataClass.getNumberOfClasses());
				dataClass.setTotalPitchingAngle(dataClass.getTotalPitchingAngle()/dataClass.getNumberOfClasses());
				dataClass.setTotalRollingAngle(dataClass.getTotalRollingAngle()/dataClass.getNumberOfClasses());
				dataClass.setTrafficLightState(dataClass.getTrafficLightState()/dataClass.getNumberOfClasses());
				dataClass.setVehicleCurvature(dataClass.getVehicleCurvature()/dataClass.getNumberOfClasses());
				dataClass.setYawRate(dataClass.getYawRate()/dataClass.getNumberOfClasses());
			}
		}
		Route avgRoute = new Route();
		Collections.sort(avgDataClassList, new Comparator<DataClass>() {
		    @Override
		    public int compare(DataClass c1, DataClass c2) {
		        return Double.compare(c1.getTimeElapsed(), c2.getTimeElapsed());
		    }
		});
		avgRoute.setDataClasses(avgDataClassList);
		for(DataClass dataClass : avgRoute.getDataClasses()) {
			avgRoute.addChild(dataClass);
		}
		return avgRoute;
	}


}
