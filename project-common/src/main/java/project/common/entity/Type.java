package project.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TYPE")
@XmlRootElement
public class Type extends Named implements Comparable<Type> {
	private static final long serialVersionUID = 1L;

	public Type() {
	}

	public Type(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Type o) {
		return name.compareToIgnoreCase(o.getName());
	}
}
