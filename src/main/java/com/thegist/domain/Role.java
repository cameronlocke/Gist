package com.thegist.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "role")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;

	public Integer getRoleId() {
        return this.roleId;
    }

	public void setRoleId(Integer id) {
        this.roleId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("users").toString();
    }

	@ManyToMany
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "role_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) })
    private Set<User> users;

	@Column(name = "authorisation", length = 255)
    private String authorisation;

	@Column(name = "role_function", length = 64)
    private String roleFunction;

	public Set<User> getUsers() {
        return users;
    }

	public void setUsers(Set<User> users) {
        this.users = users;
    }

	public String getAuthorisation() {
        return authorisation;
    }

	public void setAuthorisation(String authorisation) {
        this.authorisation = authorisation;
    }

	public String getRoleFunction() {
        return roleFunction;
    }

	public void setRoleFunction(String roleFunction) {
        this.roleFunction = roleFunction;
    }
}
