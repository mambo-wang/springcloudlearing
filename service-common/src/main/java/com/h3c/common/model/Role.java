package com.h3c.common.model;

import com.h3c.common.utils.Constant;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = -2179792193919468821L;
    private Long id;

    private String role;

    private String description;

    private Integer available = Constant.AVAILABLE_FALSE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
