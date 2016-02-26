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
import javax.persistence.OneToOne;
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
    private Long route_id;

    @Column(name = "name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;
    
    @OneToOne
    @JoinColumn(name = "average_of")
    private Type avgType;
    
    @ManyToOne
    @JoinColumn(name = "data_user_id")
    private DataUser dataUser;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
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
    
    public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getAvgType() {
		return avgType;
	}

	public void setAvgType(Type avgType) {
		this.avgType = avgType;
	}

	public void addChild(DataClass dataClass) {
    	dataClass.setParent(this);
    }
	
	public void addChildType(Type type) {
		type.setAverageRoute(this);
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
