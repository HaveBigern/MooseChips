package com.strath.visu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.strath.visu.domain.DataClass;
import com.strath.visu.domain.Route;
import com.strath.visu.repository.DataClassRepository;
import com.strath.visu.repository.RouteRepository;

@Service
public class AverageDataService {

	@Inject
	private RouteRepository routeRepository;

	@Inject
	private DataClassRepository dataClassRepository;

	public void createAverageRoutes() {
		
		List<Route> controlRoutes = routeRepository.getByExperimentType(4);
		List<Route> audioMuteRoutes = routeRepository.getByExperimentType(1);
		List<Route> warningLightsRoutes = routeRepository.getByExperimentType(2);
		List<Route> audioWarningRoutes = routeRepository.getByExperimentType(3);
		
		Route averageControlRoute = createAverageDataClassList(controlRoutes);
		Route averageAudioMuteRoute = createAverageDataClassList(audioMuteRoutes);
		Route averageWarningLightsRoute = createAverageDataClassList(warningLightsRoutes);
		Route averageAudioWarningRoute = createAverageDataClassList(audioWarningRoutes);
		
		averageControlRoute.setName("Control Average");
		averageAudioMuteRoute.setName("Audio Mute Average");
		averageWarningLightsRoute.setName("Warning Lights Average");
		averageAudioWarningRoute.setName("Audio Warning Average");
		
		averageControlRoute.setType(5);
		averageAudioMuteRoute.setType(6);
		averageWarningLightsRoute.setType(7);
		averageAudioWarningRoute.setType(8);
		
		routeRepository.save(averageControlRoute);
		routeRepository.save(averageAudioMuteRoute);
		routeRepository.save(averageWarningLightsRoute);
		routeRepository.save(averageAudioWarningRoute);
	}
	
	private Route createAverageDataClassList(List<Route> routes) {
		List<List<DataClass>> listOfLists = new ArrayList<>();
		for(Route route : routes) {
			List<DataClass> dataClasses = dataClassRepository.getDataClassByRoute(route.getRouteId());
			Collections.sort(dataClasses, new Comparator<DataClass>() {
			    @Override
			    public int compare(DataClass c1, DataClass c2) {
			        return Double.compare(c1.getTimeElapsed(), c2.getTimeElapsed());
			    }
			});
			for(int i = 0; i < dataClasses.size(); i++) {
				while(listOfLists.size() < dataClasses.size()) {
					List<DataClass> temp = new ArrayList<>();
					listOfLists.add(temp);
				}
				listOfLists.get(i).add(dataClasses.get(i));
			}
		}
		List<DataClass> avgDataClassList = new ArrayList<>();
		for(List<DataClass> dataClassList : listOfLists) {			
			Integer brakingCount = 0;
			Double collisionOppDir = 0.0;
			Double collisionTimeSameDir = 0.0;
			Integer crashCount = 0;
			Double distanceOppDir = 0.0;
			Double distanceSameDir = 0.0;
			Double distanceTravelled = 0.0;
			Integer gear = 0;
			Double lateralAccel = 0.0;
			Double lateralLanePosition = 0.0;
			Double lateralVelocity = 0.0;
			Double longAccelBrake = 0.0;
			Double longAccelThrottle = 0.0;
			Double longitudeAccel = 0.0;
			Double longitudeVelocity = 0.0;
			Double operatorMarker = 0.0;
			Double roadCurvature = 0.0;
			Double speed = 0.0;
			Integer speedLimit = 0;
			Integer speedLimitFtSec = 0;
			Integer steeringCount = 0;
			Double steeringRate = 0.0;
			Integer throttleCount = 0;
			Double timeElapsed = 0.0;
			Double totalPitchingAngle = 0.0;
			Double totalRollingAngle = 0.0;
			Integer trafficLightState = 0;
			Double vehicleCurvature = 0.0;
			Double yawRate = 0.0;
			
			DataClass avgDataClass = new DataClass();
			
			for(DataClass dataClass : dataClassList) {
				brakingCount += dataClass.getBrakingCount();
				collisionOppDir += dataClass.getCollisionOppDir();
				collisionTimeSameDir += dataClass.getCollisionTimeSameDir();
				crashCount += dataClass.getCrashCount();
				distanceOppDir += dataClass.getDistanceOppDir();
				distanceSameDir += dataClass.getDistanceSameDir();
				distanceTravelled += dataClass.getDistanceTravelled();
				gear += dataClass.getGear();
				lateralAccel += dataClass.getLateralAccel();
				lateralLanePosition += dataClass.getLateralLanePosition();
				lateralVelocity += dataClass.getLateralVelocity();
				longAccelBrake += dataClass.getLongAccelBrake();
				longAccelThrottle += dataClass.getLongAccelThrottle();
				longitudeAccel += dataClass.getLongitudeAccel();
				longitudeVelocity += dataClass.getLongitudeVelocity();
				operatorMarker += dataClass.getOperatorMarker();
				roadCurvature += dataClass.getRoadCurvature();
				speed += dataClass.getSpeed();
				speedLimit += dataClass.getSpeedLimit();
				speedLimitFtSec += dataClass.getSpeedLimitFtSec();
				steeringCount += dataClass.getSteeringCount();
				steeringRate += dataClass.getSteeringRate();
				throttleCount += dataClass.getThrottleCount();
				timeElapsed += dataClass.getTimeElapsed();
				totalPitchingAngle += dataClass.getTotalPitchingAngle();
				totalRollingAngle += dataClass.getTotalRollingAngle();
				trafficLightState += dataClass.getTrafficLightState();
				vehicleCurvature += dataClass.getVehicleCurvature();
				yawRate += dataClass.getYawRate();
			}
			
			avgDataClass.setBrakingCount(brakingCount);
			avgDataClass.setCollisionOppDir(collisionOppDir);
			avgDataClass.setCollisionTimeSameDir(collisionTimeSameDir);
			avgDataClass.setCrashCount(crashCount);
			avgDataClass.setDistanceOppDir(distanceOppDir);
			avgDataClass.setDistanceSameDir(distanceSameDir);
			avgDataClass.setDistanceTravelled(distanceTravelled);
			avgDataClass.setGear(gear);
			avgDataClass.setLateralAccel(lateralAccel);
			avgDataClass.setLateralLanePosition(lateralLanePosition);
			avgDataClass.setLateralVelocity(lateralVelocity);
			avgDataClass.setLongAccelBrake(longAccelBrake);
			avgDataClass.setLongAccelThrottle(longAccelThrottle);
			avgDataClass.setLongitudeAccel(longitudeAccel);
			avgDataClass.setLongitudeVelocity(longitudeVelocity);
			avgDataClass.setOperatorMarker(operatorMarker);
			avgDataClass.setRoadCurvature(roadCurvature);
			avgDataClass.setSpeed(speed);
			avgDataClass.setSpeedLimit(speedLimit);
			avgDataClass.setSpeedLimitFtSec(speedLimitFtSec);
			avgDataClass.setSteeringCount(steeringCount);
			avgDataClass.setSteeringRate(steeringRate);
			avgDataClass.setThrottleCount(throttleCount);
			avgDataClass.setTimeElapsed(timeElapsed);
			avgDataClass.setTotalPitchingAngle(totalPitchingAngle);
			avgDataClass.setTotalRollingAngle(totalRollingAngle);
			avgDataClass.setTrafficLightState(trafficLightState);
			avgDataClass.setVehicleCurvature(vehicleCurvature);
			avgDataClass.setYawRate(yawRate);
		
			avgDataClassList.add(avgDataClass);
		}
		Route avgRoute = new Route();
		avgRoute.setDataClasses(avgDataClassList);
		for(DataClass dataClass : avgRoute.getDataClasses()) {
			avgRoute.addChild(dataClass);
		}
		return avgRoute;
	}


}
