package com.strath.visu.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DataClass.
 */
@Entity
@Table(name = "data_class")
public class DataClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2180185105039010075L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "data_class_id")
	private Long data_class_id;

	@Column(name = "distance_travelled")
	private Double distanceTravelled;

	@Column(name = "time_elapsed")
	private Double timeElapsed;

	@Column(name = "speed_limit")
	private Integer speedLimit;

	@Column(name = "speed")
	private Double speed;

	@Column(name = "long_accel_throttle")
	private Double longAccelThrottle;

	@Column(name = "long_accel_brake")
	private Double longAccelBrake;

	@Column(name = "crash_count")
	private Integer crashCount;

	@Column(name = "steering_rate")
	private Double steeringRate;

	@Column(name = "collision_time_same_dir")
	private Double collisionTimeSameDir;

	@Column(name = "distance_same_dir")
	private Double distanceSameDir;

	@Column(name = "collision_opp_dir")
	private Double collisionOppDir;

	@Column(name = "distance_opp_dir")
	private Double distanceOppDir;

	@Column(name = "longitude_accel")
	private Double longitudeAccel;

	@Column(name = "lateral_accel")
	private Double lateralAccel;

	@Column(name = "speed_limit_ft_sec")
	private Integer speedLimitFtSec;

	@Column(name = "longitude_velocity")
	private Double longitudeVelocity;

	@Column(name = "lateral_velocity")
	private Double lateralVelocity;

	@Column(name = "lateral_lane_position")
	private Double lateralLanePosition;

	@Column(name = "vehicle_curvature")
	private Double vehicleCurvature;

	@Column(name = "road_curvature")
	private Double roadCurvature;

	@Column(name = "steering_count")
	private Integer steeringCount;

	@Column(name = "throttle_count")
	private Integer throttleCount;

	@Column(name = "braking_count")
	private Integer brakingCount;

	@Column(name = "gear")
	private Integer gear;

	@Column(name = "traffic_light_state")
	private Integer trafficLightState;

	@Column(name = "yaw_rate")
	private Double yawRate;

	@Column(name = "operator_marker")
	private Double operatorMarker;

	@Column(name = "total_pitching_angle")
	private Double totalPitchingAngle;

	@Column(name = "total_rolling_angle")
	private Double totalRollingAngle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "route_id")
	private Route route;

	public void setParent(Route route) {
		this.route = route;
	}

	public Long getId() {
		return data_class_id;
	}

	public void setId(Long id) {
		this.data_class_id = id;
	}

	public Double getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(Double distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public Double getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(Double timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public Integer getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(Integer speedLimit) {
		this.speedLimit = speedLimit;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getLongAccelThrottle() {
		return longAccelThrottle;
	}

	public void setLongAccelThrottle(Double longAccelThrottle) {
		this.longAccelThrottle = longAccelThrottle;
	}

	public Double getLongAccelBrake() {
		return longAccelBrake;
	}

	public void setLongAccelBrake(Double longAccelBrake) {
		this.longAccelBrake = longAccelBrake;
	}

	public Integer getCrashCount() {
		return crashCount;
	}

	public void setCrashCount(Integer crashCount) {
		this.crashCount = crashCount;
	}

	public Double getSteeringRate() {
		return steeringRate;
	}

	public void setSteeringRate(Double steeringRate) {
		this.steeringRate = steeringRate;
	}

	public Double getCollisionTimeSameDir() {
		return collisionTimeSameDir;
	}

	public void setCollisionTimeSameDir(Double collisionTimeSameDir) {
		this.collisionTimeSameDir = collisionTimeSameDir;
	}

	public Double getDistanceSameDir() {
		return distanceSameDir;
	}

	public void setDistanceSameDir(Double distanceSameDir) {
		this.distanceSameDir = distanceSameDir;
	}

	public Double getCollisionOppDir() {
		return collisionOppDir;
	}

	public void setCollisionOppDir(Double collisionOppDir) {
		this.collisionOppDir = collisionOppDir;
	}

	public Double getDistanceOppDir() {
		return distanceOppDir;
	}

	public void setDistanceOppDir(Double distanceOppDir) {
		this.distanceOppDir = distanceOppDir;
	}

	public Double getLongitudeAccel() {
		return longitudeAccel;
	}

	public void setLongitudeAccel(Double longitudeAccel) {
		this.longitudeAccel = longitudeAccel;
	}

	public Double getLateralAccel() {
		return lateralAccel;
	}

	public void setLateralAccel(Double lateralAccel) {
		this.lateralAccel = lateralAccel;
	}

	public Integer getSpeedLimitFtSec() {
		return speedLimitFtSec;
	}

	public void setSpeedLimitFtSec(Integer speedLimitFtSec) {
		this.speedLimitFtSec = speedLimitFtSec;
	}

	public Double getLongitudeVelocity() {
		return longitudeVelocity;
	}

	public void setLongitudeVelocity(Double longitudeVelocity) {
		this.longitudeVelocity = longitudeVelocity;
	}

	public Double getLateralVelocity() {
		return lateralVelocity;
	}

	public void setLateralVelocity(Double lateralVelocity) {
		this.lateralVelocity = lateralVelocity;
	}

	public Double getLateralLanePosition() {
		return lateralLanePosition;
	}

	public void setLateralLanePosition(Double lateralLanePosition) {
		this.lateralLanePosition = lateralLanePosition;
	}

	public Double getVehicleCurvature() {
		return vehicleCurvature;
	}

	public void setVehicleCurvature(Double vehicleCurvature) {
		this.vehicleCurvature = vehicleCurvature;
	}

	public Double getRoadCurvature() {
		return roadCurvature;
	}

	public void setRoadCurvature(Double roadCurvature) {
		this.roadCurvature = roadCurvature;
	}

	public Integer getSteeringCount() {
		return steeringCount;
	}

	public void setSteeringCount(Integer steeringCount) {
		this.steeringCount = steeringCount;
	}

	public Integer getThrottleCount() {
		return throttleCount;
	}

	public void setThrottleCount(Integer throttleCount) {
		this.throttleCount = throttleCount;
	}

	public Integer getBrakingCount() {
		return brakingCount;
	}

	public void setBrakingCount(Integer brakingCount) {
		this.brakingCount = brakingCount;
	}

	public Integer getGear() {
		return gear;
	}

	public void setGear(Integer gear) {
		this.gear = gear;
	}

	public Integer getTrafficLightState() {
		return trafficLightState;
	}

	public void setTrafficLightState(Integer trafficLightState) {
		this.trafficLightState = trafficLightState;
	}

	public Double getYawRate() {
		return yawRate;
	}

	public void setYawRate(Double yawRate) {
		this.yawRate = yawRate;
	}

	public Double getOperatorMarker() {
		return operatorMarker;
	}

	public void setOperatorMarker(Double operatorMarker) {
		this.operatorMarker = operatorMarker;
	}

	public Double getTotalPitchingAngle() {
		return totalPitchingAngle;
	}

	public void setTotalPitchingAngle(Double totalPitchingAngle) {
		this.totalPitchingAngle = totalPitchingAngle;
	}

	public Double getTotalRollingAngle() {
		return totalRollingAngle;
	}

	public void setTotalRollingAngle(Double totalRollingAngle) {
		this.totalRollingAngle = totalRollingAngle;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DataClass dataClass = (DataClass) o;
		return Objects.equals(data_class_id, dataClass.data_class_id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(data_class_id);
	}

	@Override
	public String toString() {
		return "DataClass{" + "id=" + data_class_id + ", distanceTravelled='" + distanceTravelled + "'"
				+ ", timeElapsed='" + timeElapsed + "'" + ", speedLimit='" + speedLimit + "'" + ", speed='" + speed
				+ "'" + ", longAccelThrottle='" + longAccelThrottle + "'" + ", longAccelBrake='" + longAccelBrake + "'"
				+ ", crashCount='" + crashCount + "'" + ", steeringRate='" + steeringRate + "'"
				+ ", collisionTimeSameDir='" + collisionTimeSameDir + "'" + ", distanceSameDir='" + distanceSameDir
				+ "'" + ", collisionOppDir='" + collisionOppDir + "'" + ", distanceOppDir='" + distanceOppDir + "'"
				+ ", longitudeAccel='" + longitudeAccel + "'" + ", lateralAccel='" + lateralAccel + "'"
				+ ", speedLimitFtSec='" + speedLimitFtSec + "'" + ", longitudeVelocity='" + longitudeVelocity + "'"
				+ ", lateralVelocity='" + lateralVelocity + "'" + ", lateralLanePosition='" + lateralLanePosition + "'"
				+ ", vehicleCurvature='" + vehicleCurvature + "'" + ", roadCurvature='" + roadCurvature + "'"
				+ ", steeringCount='" + steeringCount + "'" + ", throttleCount='" + throttleCount + "'"
				+ ", brakingCount='" + brakingCount + "'" + ", gear='" + gear + "'" + ", trafficLightState='"
				+ trafficLightState + "'" + ", yawRate='" + yawRate + "'" + ", operatorMarker='" + operatorMarker + "'"
				+ ", totalPitchingAngle='" + totalPitchingAngle + "'" + ", totalRollingAngle='" + totalRollingAngle
				+ "'" + '}';
	}
}
