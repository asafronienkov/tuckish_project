package project.common.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TASK")
@XmlRootElement
public class Task extends Named {
	private static final long serialVersionUID = 1L;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "LOE")
	private int levelOfEffort;

	@ManyToOne(optional = false)
	private Project project;

	@ManyToMany
	private List<Part> parts;

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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
}
