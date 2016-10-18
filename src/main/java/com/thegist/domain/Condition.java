package com.thegist.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "condition")
public class Condition {

	@OneToMany(mappedBy = "conditionId")
    private Set<TravelConditions> travelConditionss;

	@Column(name = "condition", length = 255)
    private String condition;

	public Set<TravelConditions> getTravelConditionss() {
        return travelConditionss;
    }

	public void setTravelConditionss(Set<TravelConditions> travelConditionss) {
        this.travelConditionss = travelConditionss;
    }

	public String getCondition() {
        return condition;
    }

	public void setCondition(String condition) {
        this.condition = condition;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "condition_id")
    private Integer conditionId;

	public Integer getConditionId() {
        return this.conditionId;
    }

	public void setConditionId(Integer id) {
        this.conditionId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("travelConditionss").toString();
    }
}
