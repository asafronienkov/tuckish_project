package project.common.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "TASK")
@XmlRootElement
public class Task extends Named implements Comparable<Task> {
	private static final long serialVersionUID = 1L;

	@Column(name = "DESCRIPTION")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "LOE")
	private int levelOfEffort;

	@ManyToOne(optional = false)
	private Project project;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "TASK_PART", joinColumns = { @JoinColumn(name = "TASK_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PART_ID", referencedColumnName = "ID") })
	private Set<Part> parts = new HashSet<Part>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getLevelOfEffort() {
		return levelOfEffort;
	}

	public void setLevelOfEffort(int levelOfEffort) {
		this.levelOfEffort = levelOfEffort;
	}

	@JsonIgnore
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@JsonIgnore
	public Set<Part> getParts() {
		return parts;
	}

	public void setParts(Set<Part> parts) {
		this.parts = parts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Task o) {
		return name.compareToIgnoreCase(o.getName());
	}
}
