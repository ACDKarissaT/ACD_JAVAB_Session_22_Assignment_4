package payroll;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="salary")
public class Salary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="base_salary", unique=false, nullable = false)
	private Double baseSalary;
	
	@Column(name="travel_allowance", unique=false, nullable = false)
	private Double travelAllowance;
	
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name="food_allowance", unique=false, nullable= false)
	private Double foodAllowance;
	
	@Column(name="insurance", unique=false, nullable=false)
	private Double insurance;

	@Override
	public String toString() {
		return "Salary [id=" + id + ", baseSalary=" + baseSalary + ", travelAllowance=" + travelAllowance
				+ ", foodAllowance=" + foodAllowance + ", insurance=" + insurance + "]";
	}

	public Salary(Double baseSalary, Double travelAllowance, Double foodAllowance, Double insurance) {
		super();
		this.baseSalary = baseSalary;
		this.travelAllowance = travelAllowance;
		this.foodAllowance = foodAllowance;
		this.insurance = insurance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Double getTravelAllowance() {
		return travelAllowance;
	}

	public void setTravelAllowance(Double travelAllowance) {
		this.travelAllowance = travelAllowance;
	}

	public Double getFoodAllowance() {
		return foodAllowance;
	}

	public void setFoodAllowance(Double foodAllowance) {
		this.foodAllowance = foodAllowance;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}
	
	
	
}
