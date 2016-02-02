package com.strath.visu.web.rest;

import com.strath.visu.Application;
import com.strath.visu.domain.DataUser;
import com.strath.visu.repository.DataUserRepository;

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
 * Test class for the DataUserResource REST controller.
 *
 * @see DataUserResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DataUserResourceIntTest {

    private static final String DEFAULT_PARTICIPANT_NUM = "AAAAA";
    private static final String UPDATED_PARTICIPANT_NUM = "BBBBB";

    @Inject
    private DataUserRepository dataUserRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDataUserMockMvc;

    private DataUser dataUser;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DataUserResource dataUserResource = new DataUserResource();
        ReflectionTestUtils.setField(dataUserResource, "dataUserRepository", dataUserRepository);
        this.restDataUserMockMvc = MockMvcBuilders.standaloneSetup(dataUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        dataUser = new DataUser();
        dataUser.setParticipantNum(DEFAULT_PARTICIPANT_NUM);
    }

    @Test
    @Transactional
    public void createDataUser() throws Exception {
        int databaseSizeBeforeCreate = dataUserRepository.findAll().size();

        // Create the DataUser

        restDataUserMockMvc.perform(post("/api/dataUsers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dataUser)))
                .andExpect(status().isCreated());

        // Validate the DataUser in the database
        List<DataUser> dataUsers = dataUserRepository.findAll();
        assertThat(dataUsers).hasSize(databaseSizeBeforeCreate + 1);
        DataUser testDataUser = dataUsers.get(dataUsers.size() - 1);
        assertThat(testDataUser.getParticipantNum()).isEqualTo(DEFAULT_PARTICIPANT_NUM);
    }

    @Test
    @Transactional
    public void getAllDataUsers() throws Exception {
        // Initialize the database
        dataUserRepository.saveAndFlush(dataUser);

        // Get all the dataUsers
        restDataUserMockMvc.perform(get("/api/dataUsers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(dataUser.getDataUserId().intValue())))
                .andExpect(jsonPath("$.[*].participantNum").value(hasItem(DEFAULT_PARTICIPANT_NUM.toString())));
    }

    @Test
    @Transactional
    public void getDataUser() throws Exception {
        // Initialize the database
        dataUserRepository.saveAndFlush(dataUser);

        // Get the dataUser
        restDataUserMockMvc.perform(get("/api/dataUsers/{id}", dataUser.getDataUserId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(dataUser.getDataUserId().intValue()))
            .andExpect(jsonPath("$.participantNum").value(DEFAULT_PARTICIPANT_NUM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDataUser() throws Exception {
        // Get the dataUser
        restDataUserMockMvc.perform(get("/api/dataUsers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataUser() throws Exception {
        // Initialize the database
        dataUserRepository.saveAndFlush(dataUser);

		int databaseSizeBeforeUpdate = dataUserRepository.findAll().size();

        // Update the dataUser
        dataUser.setParticipantNum(UPDATED_PARTICIPANT_NUM);

        restDataUserMockMvc.perform(put("/api/dataUsers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dataUser)))
                .andExpect(status().isOk());

        // Validate the DataUser in the database
        List<DataUser> dataUsers = dataUserRepository.findAll();
        assertThat(dataUsers).hasSize(databaseSizeBeforeUpdate);
        DataUser testDataUser = dataUsers.get(dataUsers.size() - 1);
        assertThat(testDataUser.getParticipantNum()).isEqualTo(UPDATED_PARTICIPANT_NUM);
    }

    @Test
    @Transactional
    public void deleteDataUser() throws Exception {
        // Initialize the database
        dataUserRepository.saveAndFlush(dataUser);

		int databaseSizeBeforeDelete = dataUserRepository.findAll().size();

        // Get the dataUser
        restDataUserMockMvc.perform(delete("/api/dataUsers/{id}", dataUser.getDataUserId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DataUser> dataUsers = dataUserRepository.findAll();
        assertThat(dataUsers).hasSize(databaseSizeBeforeDelete - 1);
    }
}
