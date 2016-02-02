package com.strath.visu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DataUser.
 */
@Entity
@Table(name = "data_user")
public class DataUser implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8114625490931234858L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "data_user_id")
    private Long data_user_id;

	@Column(name = "name")
    private String name;
	
    @Column(name = "participant_num")
    private String participantNum;

    @OneToMany(mappedBy = "dataClasses", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Route> routes = new HashSet<>();

    public Long getDataUserId() {
        return data_user_id;
    }

    public void setDataUserId(Long id) {
        this.data_user_id = id;
    }

    public String getParticipantNum() {
        return participantNum;
    }

    public void setParticipantNum(String participantNum) {
        this.participantNum = participantNum;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataUser dataUser = (DataUser) o;
        return Objects.equals(data_user_id, dataUser.data_user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data_user_id);
    }

    @Override
    public String toString() {
        return "DataUser{" +
            "id=" + data_user_id +
            ", participantNum='" + participantNum + "'" +
            '}';
    }
}
