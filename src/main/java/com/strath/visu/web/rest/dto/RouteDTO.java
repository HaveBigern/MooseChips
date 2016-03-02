package com.strath.visu.web.rest.dto;

import java.util.List;

import com.strath.visu.domain.DataClass;
import com.strath.visu.domain.DataUser;
import com.strath.visu.domain.Route;
import com.strath.visu.domain.Type;

public class RouteDTO {

    private Long route_id;

    private String name;

    private Type type;

    private DataUser dataUser;

    private List<DataClass> dataClasses;

    public RouteDTO(Route route) {
		this(route.getRouteId(), route.getName(), route.getType(), route.getDataUser(),
				route.getDataClasses());
	}

	public RouteDTO(Long routeId, String name, Type type, DataUser dataUser, List<DataClass> dataClasses) {
		this.route_id = routeId;
		this.name = name;
		this.type = type;
		this.dataUser = dataUser;
		this.dataClasses = dataClasses;
	}

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

}
