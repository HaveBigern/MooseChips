package com.strath.visu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Type.
 */
@Entity
@Table(name = "type")
public class Type implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4790703734911287083L;

    @Column(name = "name")
    private String name;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    private Long typeId;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private Set<Route> routes = new HashSet<>();
    
    @OneToOne(mappedBy = "avgType" , cascade = CascadeType.ALL)
    @JoinColumn(name = "avg_route")
    @JsonIgnore
    private Route averageRoute;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long id) {
        this.typeId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

	public Route getAverageRoute() {
		return averageRoute;
	}

	public void setAverageRoute(Route averageRoute) {
		this.averageRoute = averageRoute;
	}
	
	public void addChild(Route route) {
		route.setAvgType(this);
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Type type = (Type) o;
        return Objects.equals(typeId, type.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(typeId);
    }

    @Override
    public String toString() {
        return "Type{" +
            "id=" + typeId +
            ", name='" + name + "'" +
            '}';
    }
}
