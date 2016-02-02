package com.strath.visu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Route.
 */
@Entity
@Table(name = "route")
public class Route implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1129778111635045351L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "route_id")
    private Long route_id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;
    
    @ManyToOne
    @JoinColumn(name = "data_user_id")
    private DataUser dataUser;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DataClass> dataClasses = new ArrayList<>();

    public Long getRouteId() {
        return route_id;
    }

    public void setRouteId(Long id) {
        this.route_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataUser getDataUser() {
        return dataUser;
    }

    public void setDataUser(DataUser dataUser) {
        this.dataUser = dataUser;
    }

    public List<DataClass> getDataClasses() {
        return dataClasses;
    }

    public void setDataClasses(List<DataClass> dataClasss) {
        this.dataClasses = dataClasss;
    }
    
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void addChild(DataClass dataClass) {
    	dataClass.setParent(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        return Objects.equals(route_id, route.route_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(route_id);
    }

    @Override
    public String toString() {
        return "Route{" +
            "id=" + route_id +
            ", name='" + name + "'" +
            '}';
    }
}
