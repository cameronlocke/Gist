package com.thegist.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "travel_conditions")
public class TravelConditions {

	@ManyToOne
    @JoinColumn(name = "condition_id", referencedColumnName = "condition_id")
    private Condition conditionId;

	@ManyToOne
    @JoinColumn(name = "permit_id", referencedColumnName = "permit_id", nullable = false)
    private Permit permitId;

	@Column(name = "conditions_seq")
    @NotNull
    private Integer conditionsSeq;

	@Column(name = "free_text_condition", length = 255)
    private String freeTextCondition;

	public Condition getConditionId() {
        return conditionId;
    }

	public void setConditionId(Condition conditionId) {
        this.conditionId = conditionId;
    }

	public Permit getPermitId() {
        return permitId;
    }

	public void setPermitId(Permit permitId) {
        this.permitId = permitId;
    }

	public Integer getConditionsSeq() {
        return conditionsSeq;
    }

	public void setConditionsSeq(Integer conditionsSeq) {
        this.conditionsSeq = conditionsSeq;
    }

	public String getFreeTextCondition() {
        return freeTextCondition;
    }

	public void setFreeTextCondition(String freeTextCondition) {
        this.freeTextCondition = freeTextCondition;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "travel_conditions_id")
    private Integer travelConditionsId;

	public Integer getTravelConditionsId() {
        return this.travelConditionsId;
    }

	public void setTravelConditionsId(Integer id) {
        this.travelConditionsId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("conditionId", "permitId").toString();
    }
}
