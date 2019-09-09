package payroll;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="salary_for_month")
public class SalaryForMonth {
	
	@Override
	public String toString() {
		return "SalaryForMonth [id=" + id + ", month=" + month + ", employee=" + employee.getEmployeeId() + ", baseSalary=" + String.format("%.2f", baseSalary)
				+ ", travel=" + String.format("%.2f", travel) + ", food=" + String.format("%.2f", food) + ", insurance=" + String.format("%.2f", insurance) + ", finalSalary=" + String.format("%.2f", finalSalary)
				+ ", paymentStatus=" + paymentStatus + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="month", unique=false, nullable=false)
	private Date month;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Column(name="base_salary", unique=false, nullable=false)
	private Double baseSalary;
	
	@Column(name="travel_allowance", unique=false, nullable=false)
	private Double travel;
	
	@Column(name="food_allowance", unique=false, nullable=false)
	private Double food;
	
	@Column(name="insurance_allowance", unique=false, nullable=false)
	private Double insurance;
	
	@Column(name="final_salary", unique=false, nullable=false)
	private Double finalSalary;
	
	@Column(name="status", unique=false, nullable=false)
	private String paymentStatus;

	public SalaryForMonth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryForMonth(Date month, Employee employee, Double baseSalary, Double travel, Double food,
			Double insurance, Double finalSalary, String paymentStatus) {
		super();
		this.id = id;
		this.month = month;
		this.employee = employee;
		this.baseSalary = baseSalary;
		this.travel = travel;
		this.food = food;
		this.insurance = insurance;
		this.finalSalary = finalSalary;
		this.paymentStatus = paymentStatus;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null && obj instanceof SalaryForMonth) {
			SalaryForMonth sfm = (SalaryForMonth) obj;
			return month.equals(sfm.getMonth());
		}
		return false;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Double getTravel() {
		return travel;
	}

	public void setTravel(Double travel) {
		this.travel = travel;
	}

	public Double getFood() {
		return food;
	}

	public void setFood(Double food) {
		this.food = food;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public Double getFinalSalary() {
		return finalSalary;
	}

	public void setFinalSalary(Double finalSalary) {
		this.finalSalary = finalSalary;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}	
	
}
