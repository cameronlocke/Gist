package com.thegist.domain;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.TypeDef;

import com.thegist.utils.HstoreUserType;

@Entity
@Table(schema = "overload",name = "collection")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Collection {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

	@Column(name = "collection")
    @NotNull
    private HashMap<String, String> collection = new HashMap<String, String>();
	
	public Integer getId() {
        return this.id;
    }

	public void setId(Integer id) {
        this.id = id;
    }

	public HashMap<String, String> getLookup() {
        return collection;
    }

	public void setLookup(HashMap<String, String> collection) {
        this.collection = collection;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
