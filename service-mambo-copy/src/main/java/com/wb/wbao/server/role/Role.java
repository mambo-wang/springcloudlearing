package com.wb.wbao.server.role;

import com.h3c.common.utils.Constant;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_role")
public class Role implements Serializable {

    public static final String ROLE_ADMIN = "ADMIN";

    public static final String ROLE_USER = "USER";

    private static final long serialVersionUID = -2179792193919468821L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AVAILABLE")
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
