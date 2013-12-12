package project.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A basic model class that contains a name property
 */
@MappedSuperclass
@XmlRootElement
public class Named extends Base {
	private static final long serialVersionUID = 1L;
	private String name;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
