package project.common.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PART")
@XmlRootElement
public class Part extends Named {
	private static final long serialVersionUID = 1L;

	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Column(name = "NUMBER")
	private String number;

	@Column(name = "COST")
	private float cost;

	@Column(name = "WEIGHT")
	private float weight;

	@ManyToOne(optional = false)
	private Type type;

	@ManyToMany(mappedBy = "parts")
	private List<Task> tasks;

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
