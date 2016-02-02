package com.strath.visu.web.rest;

import com.strath.visu.Application;
import com.strath.visu.domain.DataClass;
import com.strath.visu.repository.DataClassRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DataClassResource REST controller.
 *
 * @see DataClassResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DataClassResourceIntTest {


    private static final Double DEFAULT_DISTANCE_TRAVELLED = 1D;
    private static final Double UPDATED_DISTANCE_TRAVELLED = 2D;

    private static final Double DEFAULT_TIME_ELAPSED = 1D;
    private static final Double UPDATED_TIME_ELAPSED = 2D;

    private static final Integer DEFAULT_SPEED_LIMIT = 1;
    private static final Integer UPDATED_SPEED_LIMIT = 2;

    private static final Double DEFAULT_SPEED = 1D;
    private static final Double UPDATED_SPEED = 2D;

    private static final Double DEFAULT_LONG_ACCEL_THROTTLE = 1D;
    private static final Double UPDATED_LONG_ACCEL_THROTTLE = 2D;

    private static final Double DEFAULT_LONG_ACCEL_BRAKE = 1D;
    private static final Double UPDATED_LONG_ACCEL_BRAKE = 2D;

    private static final Integer DEFAULT_CRASH_COUNT = 1;
    private static final Integer UPDATED_CRASH_COUNT = 2;

    private static final Double DEFAULT_STEERING_RATE = 1D;
    private static final Double UPDATED_STEERING_RATE = 2D;

    private static final Double DEFAULT_COLLISION_TIME_SAME_DIR = 1D;
    private static final Double UPDATED_COLLISION_TIME_SAME_DIR = 2D;

    private static final Double DEFAULT_DISTANCE_SAME_DIR = 1D;
    private static final Double UPDATED_DISTANCE_SAME_DIR = 2D;

    private static final Double DEFAULT_COLLISION_OPP_DIR = 1D;
    private static final Double UPDATED_COLLISION_OPP_DIR = 2D;

    private static final Double DEFAULT_DISTANCE_OPP_DIR = 1D;
    private static final Double UPDATED_DISTANCE_OPP_DIR = 2D;

    private static final Double DEFAULT_LONGITUDE_ACCEL = 1D;
    private static final Double UPDATED_LONGITUDE_ACCEL = 2D;

    private static final Double DEFAULT_LATERAL_ACCEL = 1D;
    private static final Double UPDATED_LATERAL_ACCEL = 2D;

    private static final Integer DEFAULT_SPEED_LIMIT_FT_SEC = 1;
    private static final Integer UPDATED_SPEED_LIMIT_FT_SEC = 2;

    private static final Double DEFAULT_LONGITUDE_VELOCITY = 1D;
    private static final Double UPDATED_LONGITUDE_VELOCITY = 2D;

    private static final Double DEFAULT_LATERAL_VELOCITY = 1D;
    private static final Double UPDATED_LATERAL_VELOCITY = 2D;

    private static final Double DEFAULT_LATERAL_LANE_POSITION = 1D;
    private static final Double UPDATED_LATERAL_LANE_POSITION = 2D;

    private static final Double DEFAULT_VEHICLE_CURVATURE = 1D;
    private static final Double UPDATED_VEHICLE_CURVATURE = 2D;

    private static final Double DEFAULT_ROAD_CURVATURE = 1D;
    private static final Double UPDATED_ROAD_CURVATURE = 2D;

    private static final Integer DEFAULT_STEERING_COUNT = 1;
    private static final Integer UPDATED_STEERING_COUNT = 2;

    private static final Integer DEFAULT_THROTTLE_COUNT = 1;
    private static final Integer UPDATED_THROTTLE_COUNT = 2;

    private static final Integer DEFAULT_BRAKING_COUNT = 1;
    private static final Integer UPDATED_BRAKING_COUNT = 2;

    private static final Integer DEFAULT_GEAR = 1;
    private static final Integer UPDATED_GEAR = 2;

    private static final Integer DEFAULT_TRAFFIC_LIGHT_STATE = 1;
    private static final Integer UPDATED_TRAFFIC_LIGHT_STATE = 2;

    private static final Double DEFAULT_YAW_RATE = 1D;
    private static final Double UPDATED_YAW_RATE = 2D;

    private static final Double DEFAULT_OPERATOR_MARKER = 1D;
    private static final Double UPDATED_OPERATOR_MARKER = 2D;

    private static final Double DEFAULT_TOTAL_PITCHING_ANGLE = 1D;
    private static final Double UPDATED_TOTAL_PITCHING_ANGLE = 2D;

    private static final Double DEFAULT_TOTAL_ROLLING_ANGLE = 1D;
    private static final Double UPDATED_TOTAL_ROLLING_ANGLE = 2D;

    @Inject
    private DataClassRepository dataClassRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDataClassMockMvc;

    private DataClass dataClass;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DataClassResource dataClassResource = new DataClassResource();
        ReflectionTestUtils.setField(dataClassResource, "dataClassRepository", dataClassRepository);
        this.restDataClassMockMvc = MockMvcBuilders.standaloneSetup(dataClassResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        dataClass = new DataClass();
        dataClass.setDistanceTravelled(DEFAULT_DISTANCE_TRAVELLED);
        dataClass.setTimeElapsed(DEFAULT_TIME_ELAPSED);
        dataClass.setSpeedLimit(DEFAULT_SPEED_LIMIT);
        dataClass.setSpeed(DEFAULT_SPEED);
        dataClass.setLongAccelThrottle(DEFAULT_LONG_ACCEL_THROTTLE);
        dataClass.setLongAccelBrake(DEFAULT_LONG_ACCEL_BRAKE);
        dataClass.setCrashCount(DEFAULT_CRASH_COUNT);
        dataClass.setSteeringRate(DEFAULT_STEERING_RATE);
        dataClass.setCollisionTimeSameDir(DEFAULT_COLLISION_TIME_SAME_DIR);
        dataClass.setDistanceSameDir(DEFAULT_DISTANCE_SAME_DIR);
        dataClass.setCollisionOppDir(DEFAULT_COLLISION_OPP_DIR);
        dataClass.setDistanceOppDir(DEFAULT_DISTANCE_OPP_DIR);
        dataClass.setLongitudeAccel(DEFAULT_LONGITUDE_ACCEL);
        dataClass.setLateralAccel(DEFAULT_LATERAL_ACCEL);
        dataClass.setSpeedLimitFtSec(DEFAULT_SPEED_LIMIT_FT_SEC);
        dataClass.setLongitudeVelocity(DEFAULT_LONGITUDE_VELOCITY);
        dataClass.setLateralVelocity(DEFAULT_LATERAL_VELOCITY);
        dataClass.setLateralLanePosition(DEFAULT_LATERAL_LANE_POSITION);
        dataClass.setVehicleCurvature(DEFAULT_VEHICLE_CURVATURE);
        dataClass.setRoadCurvature(DEFAULT_ROAD_CURVATURE);
        dataClass.setSteeringCount(DEFAULT_STEERING_COUNT);
        dataClass.setThrottleCount(DEFAULT_THROTTLE_COUNT);
        dataClass.setBrakingCount(DEFAULT_BRAKING_COUNT);
        dataClass.setGear(DEFAULT_GEAR);
        dataClass.setTrafficLightState(DEFAULT_TRAFFIC_LIGHT_STATE);
        dataClass.setYawRate(DEFAULT_YAW_RATE);
        dataClass.setOperatorMarker(DEFAULT_OPERATOR_MARKER);
        dataClass.setTotalPitchingAngle(DEFAULT_TOTAL_PITCHING_ANGLE);
        dataClass.setTotalRollingAngle(DEFAULT_TOTAL_ROLLING_ANGLE);
    }

    @Test
    @Transactional
    public void createDataClass() throws Exception {
        int databaseSizeBeforeCreate = dataClassRepository.findAll().size();

        // Create the DataClass

        restDataClassMockMvc.perform(post("/api/dataClasss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dataClass)))
                .andExpect(status().isCreated());

        // Validate the DataClass in the database
        List<DataClass> dataClasss = dataClassRepository.findAll();
        assertThat(dataClasss).hasSize(databaseSizeBeforeCreate + 1);
        DataClass testDataClass = dataClasss.get(dataClasss.size() - 1);
        assertThat(testDataClass.getDistanceTravelled()).isEqualTo(DEFAULT_DISTANCE_TRAVELLED);
        assertThat(testDataClass.getTimeElapsed()).isEqualTo(DEFAULT_TIME_ELAPSED);
        assertThat(testDataClass.getSpeedLimit()).isEqualTo(DEFAULT_SPEED_LIMIT);
        assertThat(testDataClass.getSpeed()).isEqualTo(DEFAULT_SPEED);
        assertThat(testDataClass.getLongAccelThrottle()).isEqualTo(DEFAULT_LONG_ACCEL_THROTTLE);
        assertThat(testDataClass.getLongAccelBrake()).isEqualTo(DEFAULT_LONG_ACCEL_BRAKE);
        assertThat(testDataClass.getCrashCount()).isEqualTo(DEFAULT_CRASH_COUNT);
        assertThat(testDataClass.getSteeringRate()).isEqualTo(DEFAULT_STEERING_RATE);
        assertThat(testDataClass.getCollisionTimeSameDir()).isEqualTo(DEFAULT_COLLISION_TIME_SAME_DIR);
        assertThat(testDataClass.getDistanceSameDir()).isEqualTo(DEFAULT_DISTANCE_SAME_DIR);
        assertThat(testDataClass.getCollisionOppDir()).isEqualTo(DEFAULT_COLLISION_OPP_DIR);
        assertThat(testDataClass.getDistanceOppDir()).isEqualTo(DEFAULT_DISTANCE_OPP_DIR);
        assertThat(testDataClass.getLongitudeAccel()).isEqualTo(DEFAULT_LONGITUDE_ACCEL);
        assertThat(testDataClass.getLateralAccel()).isEqualTo(DEFAULT_LATERAL_ACCEL);
        assertThat(testDataClass.getSpeedLimitFtSec()).isEqualTo(DEFAULT_SPEED_LIMIT_FT_SEC);
        assertThat(testDataClass.getLongitudeVelocity()).isEqualTo(DEFAULT_LONGITUDE_VELOCITY);
        assertThat(testDataClass.getLateralVelocity()).isEqualTo(DEFAULT_LATERAL_VELOCITY);
        assertThat(testDataClass.getLateralLanePosition()).isEqualTo(DEFAULT_LATERAL_LANE_POSITION);
        assertThat(testDataClass.getVehicleCurvature()).isEqualTo(DEFAULT_VEHICLE_CURVATURE);
        assertThat(testDataClass.getRoadCurvature()).isEqualTo(DEFAULT_ROAD_CURVATURE);
        assertThat(testDataClass.getSteeringCount()).isEqualTo(DEFAULT_STEERING_COUNT);
        assertThat(testDataClass.getThrottleCount()).isEqualTo(DEFAULT_THROTTLE_COUNT);
        assertThat(testDataClass.getBrakingCount()).isEqualTo(DEFAULT_BRAKING_COUNT);
        assertThat(testDataClass.getGear()).isEqualTo(DEFAULT_GEAR);
        assertThat(testDataClass.getTrafficLightState()).isEqualTo(DEFAULT_TRAFFIC_LIGHT_STATE);
        assertThat(testDataClass.getYawRate()).isEqualTo(DEFAULT_YAW_RATE);
        assertThat(testDataClass.getOperatorMarker()).isEqualTo(DEFAULT_OPERATOR_MARKER);
        assertThat(testDataClass.getTotalPitchingAngle()).isEqualTo(DEFAULT_TOTAL_PITCHING_ANGLE);
        assertThat(testDataClass.getTotalRollingAngle()).isEqualTo(DEFAULT_TOTAL_ROLLING_ANGLE);
    }

    @Test
    @Transactional
    public void getAllDataClasss() throws Exception {
        // Initialize the database
        dataClassRepository.saveAndFlush(dataClass);

        // Get all the dataClasss
        restDataClassMockMvc.perform(get("/api/dataClasss"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(dataClass.getId().intValue())))
                .andExpect(jsonPath("$.[*].distanceTravelled").value(hasItem(DEFAULT_DISTANCE_TRAVELLED.doubleValue())))
                .andExpect(jsonPath("$.[*].timeElapsed").value(hasItem(DEFAULT_TIME_ELAPSED.doubleValue())))
                .andExpect(jsonPath("$.[*].speedLimit").value(hasItem(DEFAULT_SPEED_LIMIT)))
                .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED.doubleValue())))
                .andExpect(jsonPath("$.[*].longAccelThrottle").value(hasItem(DEFAULT_LONG_ACCEL_THROTTLE.doubleValue())))
                .andExpect(jsonPath("$.[*].longAccelBrake").value(hasItem(DEFAULT_LONG_ACCEL_BRAKE.doubleValue())))
                .andExpect(jsonPath("$.[*].crashCount").value(hasItem(DEFAULT_CRASH_COUNT)))
                .andExpect(jsonPath("$.[*].steeringRate").value(hasItem(DEFAULT_STEERING_RATE.doubleValue())))
                .andExpect(jsonPath("$.[*].collisionTimeSameDir").value(hasItem(DEFAULT_COLLISION_TIME_SAME_DIR.doubleValue())))
                .andExpect(jsonPath("$.[*].distanceSameDir").value(hasItem(DEFAULT_DISTANCE_SAME_DIR.doubleValue())))
                .andExpect(jsonPath("$.[*].collisionOppDir").value(hasItem(DEFAULT_COLLISION_OPP_DIR.doubleValue())))
                .andExpect(jsonPath("$.[*].distanceOppDir").value(hasItem(DEFAULT_DISTANCE_OPP_DIR.doubleValue())))
                .andExpect(jsonPath("$.[*].longitudeAccel").value(hasItem(DEFAULT_LONGITUDE_ACCEL.doubleValue())))
                .andExpect(jsonPath("$.[*].lateralAccel").value(hasItem(DEFAULT_LATERAL_ACCEL.doubleValue())))
                .andExpect(jsonPath("$.[*].speedLimitFtSec").value(hasItem(DEFAULT_SPEED_LIMIT_FT_SEC)))
                .andExpect(jsonPath("$.[*].longitudeVelocity").value(hasItem(DEFAULT_LONGITUDE_VELOCITY.doubleValue())))
                .andExpect(jsonPath("$.[*].lateralVelocity").value(hasItem(DEFAULT_LATERAL_VELOCITY.doubleValue())))
                .andExpect(jsonPath("$.[*].lateralLanePosition").value(hasItem(DEFAULT_LATERAL_LANE_POSITION.doubleValue())))
                .andExpect(jsonPath("$.[*].vehicleCurvature").value(hasItem(DEFAULT_VEHICLE_CURVATURE.doubleValue())))
                .andExpect(jsonPath("$.[*].roadCurvature").value(hasItem(DEFAULT_ROAD_CURVATURE.doubleValue())))
                .andExpect(jsonPath("$.[*].steeringCount").value(hasItem(DEFAULT_STEERING_COUNT)))
                .andExpect(jsonPath("$.[*].throttleCount").value(hasItem(DEFAULT_THROTTLE_COUNT)))
                .andExpect(jsonPath("$.[*].brakingCount").value(hasItem(DEFAULT_BRAKING_COUNT)))
                .andExpect(jsonPath("$.[*].gear").value(hasItem(DEFAULT_GEAR)))
                .andExpect(jsonPath("$.[*].trafficLightState").value(hasItem(DEFAULT_TRAFFIC_LIGHT_STATE)))
                .andExpect(jsonPath("$.[*].yawRate").value(hasItem(DEFAULT_YAW_RATE.doubleValue())))
                .andExpect(jsonPath("$.[*].operatorMarker").value(hasItem(DEFAULT_OPERATOR_MARKER.doubleValue())))
                .andExpect(jsonPath("$.[*].totalPitchingAngle").value(hasItem(DEFAULT_TOTAL_PITCHING_ANGLE.doubleValue())))
                .andExpect(jsonPath("$.[*].totalRollingAngle").value(hasItem(DEFAULT_TOTAL_ROLLING_ANGLE.doubleValue())));
    }

    @Test
    @Transactional
    public void getDataClass() throws Exception {
        // Initialize the database
        dataClassRepository.saveAndFlush(dataClass);

        // Get the dataClass
        restDataClassMockMvc.perform(get("/api/dataClasss/{id}", dataClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(dataClass.getId().intValue()))
            .andExpect(jsonPath("$.distanceTravelled").value(DEFAULT_DISTANCE_TRAVELLED.doubleValue()))
            .andExpect(jsonPath("$.timeElapsed").value(DEFAULT_TIME_ELAPSED.doubleValue()))
            .andExpect(jsonPath("$.speedLimit").value(DEFAULT_SPEED_LIMIT))
            .andExpect(jsonPath("$.speed").value(DEFAULT_SPEED.doubleValue()))
            .andExpect(jsonPath("$.longAccelThrottle").value(DEFAULT_LONG_ACCEL_THROTTLE.doubleValue()))
            .andExpect(jsonPath("$.longAccelBrake").value(DEFAULT_LONG_ACCEL_BRAKE.doubleValue()))
            .andExpect(jsonPath("$.crashCount").value(DEFAULT_CRASH_COUNT))
            .andExpect(jsonPath("$.steeringRate").value(DEFAULT_STEERING_RATE.doubleValue()))
            .andExpect(jsonPath("$.collisionTimeSameDir").value(DEFAULT_COLLISION_TIME_SAME_DIR.doubleValue()))
            .andExpect(jsonPath("$.distanceSameDir").value(DEFAULT_DISTANCE_SAME_DIR.doubleValue()))
            .andExpect(jsonPath("$.collisionOppDir").value(DEFAULT_COLLISION_OPP_DIR.doubleValue()))
            .andExpect(jsonPath("$.distanceOppDir").value(DEFAULT_DISTANCE_OPP_DIR.doubleValue()))
            .andExpect(jsonPath("$.longitudeAccel").value(DEFAULT_LONGITUDE_ACCEL.doubleValue()))
            .andExpect(jsonPath("$.lateralAccel").value(DEFAULT_LATERAL_ACCEL.doubleValue()))
            .andExpect(jsonPath("$.speedLimitFtSec").value(DEFAULT_SPEED_LIMIT_FT_SEC))
            .andExpect(jsonPath("$.longitudeVelocity").value(DEFAULT_LONGITUDE_VELOCITY.doubleValue()))
            .andExpect(jsonPath("$.lateralVelocity").value(DEFAULT_LATERAL_VELOCITY.doubleValue()))
            .andExpect(jsonPath("$.lateralLanePosition").value(DEFAULT_LATERAL_LANE_POSITION.doubleValue()))
            .andExpect(jsonPath("$.vehicleCurvature").value(DEFAULT_VEHICLE_CURVATURE.doubleValue()))
            .andExpect(jsonPath("$.roadCurvature").value(DEFAULT_ROAD_CURVATURE.doubleValue()))
            .andExpect(jsonPath("$.steeringCount").value(DEFAULT_STEERING_COUNT))
            .andExpect(jsonPath("$.throttleCount").value(DEFAULT_THROTTLE_COUNT))
            .andExpect(jsonPath("$.brakingCount").value(DEFAULT_BRAKING_COUNT))
            .andExpect(jsonPath("$.gear").value(DEFAULT_GEAR))
            .andExpect(jsonPath("$.trafficLightState").value(DEFAULT_TRAFFIC_LIGHT_STATE))
            .andExpect(jsonPath("$.yawRate").value(DEFAULT_YAW_RATE.doubleValue()))
            .andExpect(jsonPath("$.operatorMarker").value(DEFAULT_OPERATOR_MARKER.doubleValue()))
            .andExpect(jsonPath("$.totalPitchingAngle").value(DEFAULT_TOTAL_PITCHING_ANGLE.doubleValue()))
            .andExpect(jsonPath("$.totalRollingAngle").value(DEFAULT_TOTAL_ROLLING_ANGLE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDataClass() throws Exception {
        // Get the dataClass
        restDataClassMockMvc.perform(get("/api/dataClasss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataClass() throws Exception {
        // Initialize the database
        dataClassRepository.saveAndFlush(dataClass);

		int databaseSizeBeforeUpdate = dataClassRepository.findAll().size();

        // Update the dataClass
        dataClass.setDistanceTravelled(UPDATED_DISTANCE_TRAVELLED);
        dataClass.setTimeElapsed(UPDATED_TIME_ELAPSED);
        dataClass.setSpeedLimit(UPDATED_SPEED_LIMIT);
        dataClass.setSpeed(UPDATED_SPEED);
        dataClass.setLongAccelThrottle(UPDATED_LONG_ACCEL_THROTTLE);
        dataClass.setLongAccelBrake(UPDATED_LONG_ACCEL_BRAKE);
        dataClass.setCrashCount(UPDATED_CRASH_COUNT);
        dataClass.setSteeringRate(UPDATED_STEERING_RATE);
        dataClass.setCollisionTimeSameDir(UPDATED_COLLISION_TIME_SAME_DIR);
        dataClass.setDistanceSameDir(UPDATED_DISTANCE_SAME_DIR);
        dataClass.setCollisionOppDir(UPDATED_COLLISION_OPP_DIR);
        dataClass.setDistanceOppDir(UPDATED_DISTANCE_OPP_DIR);
        dataClass.setLongitudeAccel(UPDATED_LONGITUDE_ACCEL);
        dataClass.setLateralAccel(UPDATED_LATERAL_ACCEL);
        dataClass.setSpeedLimitFtSec(UPDATED_SPEED_LIMIT_FT_SEC);
        dataClass.setLongitudeVelocity(UPDATED_LONGITUDE_VELOCITY);
        dataClass.setLateralVelocity(UPDATED_LATERAL_VELOCITY);
        dataClass.setLateralLanePosition(UPDATED_LATERAL_LANE_POSITION);
        dataClass.setVehicleCurvature(UPDATED_VEHICLE_CURVATURE);
        dataClass.setRoadCurvature(UPDATED_ROAD_CURVATURE);
        dataClass.setSteeringCount(UPDATED_STEERING_COUNT);
        dataClass.setThrottleCount(UPDATED_THROTTLE_COUNT);
        dataClass.setBrakingCount(UPDATED_BRAKING_COUNT);
        dataClass.setGear(UPDATED_GEAR);
        dataClass.setTrafficLightState(UPDATED_TRAFFIC_LIGHT_STATE);
        dataClass.setYawRate(UPDATED_YAW_RATE);
        dataClass.setOperatorMarker(UPDATED_OPERATOR_MARKER);
        dataClass.setTotalPitchingAngle(UPDATED_TOTAL_PITCHING_ANGLE);
        dataClass.setTotalRollingAngle(UPDATED_TOTAL_ROLLING_ANGLE);

        restDataClassMockMvc.perform(put("/api/dataClasss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dataClass)))
                .andExpect(status().isOk());

        // Validate the DataClass in the database
        List<DataClass> dataClasss = dataClassRepository.findAll();
        assertThat(dataClasss).hasSize(databaseSizeBeforeUpdate);
        DataClass testDataClass = dataClasss.get(dataClasss.size() - 1);
        assertThat(testDataClass.getDistanceTravelled()).isEqualTo(UPDATED_DISTANCE_TRAVELLED);
        assertThat(testDataClass.getTimeElapsed()).isEqualTo(UPDATED_TIME_ELAPSED);
        assertThat(testDataClass.getSpeedLimit()).isEqualTo(UPDATED_SPEED_LIMIT);
        assertThat(testDataClass.getSpeed()).isEqualTo(UPDATED_SPEED);
        assertThat(testDataClass.getLongAccelThrottle()).isEqualTo(UPDATED_LONG_ACCEL_THROTTLE);
        assertThat(testDataClass.getLongAccelBrake()).isEqualTo(UPDATED_LONG_ACCEL_BRAKE);
        assertThat(testDataClass.getCrashCount()).isEqualTo(UPDATED_CRASH_COUNT);
        assertThat(testDataClass.getSteeringRate()).isEqualTo(UPDATED_STEERING_RATE);
        assertThat(testDataClass.getCollisionTimeSameDir()).isEqualTo(UPDATED_COLLISION_TIME_SAME_DIR);
        assertThat(testDataClass.getDistanceSameDir()).isEqualTo(UPDATED_DISTANCE_SAME_DIR);
        assertThat(testDataClass.getCollisionOppDir()).isEqualTo(UPDATED_COLLISION_OPP_DIR);
        assertThat(testDataClass.getDistanceOppDir()).isEqualTo(UPDATED_DISTANCE_OPP_DIR);
        assertThat(testDataClass.getLongitudeAccel()).isEqualTo(UPDATED_LONGITUDE_ACCEL);
        assertThat(testDataClass.getLateralAccel()).isEqualTo(UPDATED_LATERAL_ACCEL);
        assertThat(testDataClass.getSpeedLimitFtSec()).isEqualTo(UPDATED_SPEED_LIMIT_FT_SEC);
        assertThat(testDataClass.getLongitudeVelocity()).isEqualTo(UPDATED_LONGITUDE_VELOCITY);
        assertThat(testDataClass.getLateralVelocity()).isEqualTo(UPDATED_LATERAL_VELOCITY);
        assertThat(testDataClass.getLateralLanePosition()).isEqualTo(UPDATED_LATERAL_LANE_POSITION);
        assertThat(testDataClass.getVehicleCurvature()).isEqualTo(UPDATED_VEHICLE_CURVATURE);
        assertThat(testDataClass.getRoadCurvature()).isEqualTo(UPDATED_ROAD_CURVATURE);
        assertThat(testDataClass.getSteeringCount()).isEqualTo(UPDATED_STEERING_COUNT);
        assertThat(testDataClass.getThrottleCount()).isEqualTo(UPDATED_THROTTLE_COUNT);
        assertThat(testDataClass.getBrakingCount()).isEqualTo(UPDATED_BRAKING_COUNT);
        assertThat(testDataClass.getGear()).isEqualTo(UPDATED_GEAR);
        assertThat(testDataClass.getTrafficLightState()).isEqualTo(UPDATED_TRAFFIC_LIGHT_STATE);
        assertThat(testDataClass.getYawRate()).isEqualTo(UPDATED_YAW_RATE);
        assertThat(testDataClass.getOperatorMarker()).isEqualTo(UPDATED_OPERATOR_MARKER);
        assertThat(testDataClass.getTotalPitchingAngle()).isEqualTo(UPDATED_TOTAL_PITCHING_ANGLE);
        assertThat(testDataClass.getTotalRollingAngle()).isEqualTo(UPDATED_TOTAL_ROLLING_ANGLE);
    }

    @Test
    @Transactional
    public void deleteDataClass() throws Exception {
        // Initialize the database
        dataClassRepository.saveAndFlush(dataClass);

		int databaseSizeBeforeDelete = dataClassRepository.findAll().size();

        // Get the dataClass
        restDataClassMockMvc.perform(delete("/api/dataClasss/{id}", dataClass.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DataClass> dataClasss = dataClassRepository.findAll();
        assertThat(dataClasss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
