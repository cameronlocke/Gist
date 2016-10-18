package com.thegist.domain;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "overload",name = "review")
public class Review {

	@ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "application_id", nullable = false)
    private Application applicationId;

	@ManyToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "organisation_id", nullable = false)
    private Organisation assignedTo;

	@ManyToOne
    @JoinColumn(name = "actioned_by", referencedColumnName = "user_id")
    private User actionedBy;

	@Column(name = "assigned_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar assignedOn;

	@Column(name = "actioned_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar actionedOn;

	@Column(name = "review_status")
    private Integer reviewStatus;

	@Column(name = "last_reminder")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar lastReminder;

	public Application getApplicationId() {
        return applicationId;
    }

	public void setApplicationId(Application applicationId) {
        this.applicationId = applicationId;
    }

	public Organisation getAssignedTo() {
        return assignedTo;
    }

	public void setAssignedTo(Organisation assignedTo) {
        this.assignedTo = assignedTo;
    }

	public User getActionedBy() {
        return actionedBy;
    }

	public void setActionedBy(User actionedBy) {
        this.actionedBy = actionedBy;
    }

	public Calendar getAssignedOn() {
        return assignedOn;
    }

	public void setAssignedOn(Calendar assignedOn) {
        this.assignedOn = assignedOn;
    }

	public Calendar getActionedOn() {
        return actionedOn;
    }

	public void setActionedOn(Calendar actionedOn) {
        this.actionedOn = actionedOn;
    }

	public Integer getReviewStatus() {
        return reviewStatus;
    }

	public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

	public Calendar getLastReminder() {
        return lastReminder;
    }

	public void setLastReminder(Calendar lastReminder) {
        this.lastReminder = lastReminder;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Integer reviewId;

	public Integer getReviewId() {
        return this.reviewId;
    }

	public void setReviewId(Integer id) {
        this.reviewId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationId", "assignedTo", "actionedBy").toString();
    }
}
