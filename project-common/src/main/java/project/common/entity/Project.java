package project.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PROJECT")
@XmlRootElement
public class Project extends Named {
	private static final long serialVersionUID = 1L;

	@Column(name = "TYPE")
	private String projectType;

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
}
