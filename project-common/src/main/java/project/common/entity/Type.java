package project.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TYPE")
@XmlRootElement
public class Type extends Named {
	private static final long serialVersionUID = 1L;

	public Type() {
	}

	public Type(String name) {
		this.name = name;
	}
}
