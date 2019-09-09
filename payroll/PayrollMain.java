package payroll;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PayrollMain {
	
	private static SessionFactory factory;
	
	public static void main(String[] args) {
		factory = new Configuration().configure().buildSessionFactory();
		
		Salary[] sals = new Salary[3];
		sals[0] = new Salary(Double.valueOf(1000), Double.valueOf(10), Double.valueOf(10), Double.valueOf(10));
		sals[1] = new Salary(Double.valueOf(2000), Double.valueOf(15), Double.valueOf(5), Double.valueOf(20));
		sals[2] = new Salary(Double.valueOf(3000), Double.valueOf(15), Double.valueOf(15), Double.valueOf(15));
		
		Employee[] emps = new Employee[6];
		Account[] accs = new Account[6];
		for(int i = 0; i<6; i++) {
			emps[i] = new Employee("Bob" + (i+1), "Bob" + (i+1) + "@bob.com" , 12345678 + i, sals[i%3],
					accs[i] = new Account("Savings", "BobBank" , 12345+i, "BOBBANK00" + i, emps[i]), 
					Double.valueOf(500*i), true);
		}
		
		Session session = factory.openSession();
		
		
/*1 Insert 5 records for employees and 3 salary types */	
		for (Salary sal : sals) {
			System.out.println("Inserted Salary ID: " + addSalary(session, sal));
		}
		
		for (Employee emp : emps) {
			System.out.println("Inserted Employee ID: " + addEmployee(session, emp));
		}
		
		System.out.println("\n\n");
		
/*2 Calculate the salary of the month of 092019 for all employees other than id 2 */	
		List<Employee> empList = fetchEmployee(session);
		for (Employee employee : empList) {
			if (employee.getEmployeeId().equals(2)) continue;
			calculateSalary(employee, Date.valueOf("2019-09-30"), "paid");
			updateEmployee(session, employee);
		}

		System.out.println("\n\n");
/*3 Employee id with 3 is awarded a bonus in terms of over pay hike of 150 */	
		Employee emp = fetchEmployee(session, 3);
		emp.setOverpay(emp.getOverpay() + 150);
		updateEmployee(session, emp);		
		
		System.out.println("\n\n");
/*4 Calculate the salary of the month of 102019 for all employees */	
		empList = fetchEmployee(session);
		for (Employee employee : empList) {
			calculateSalary(employee, Date.valueOf("2019-10-31"), "paid");
			updateEmployee(session, employee);
		}

		System.out.println("\n\n");
/*5 Employee with id 1 has left the organisation calculate his pending amount if any on 21/10/2019 */
		emp = fetchEmployee(session, 1);
		emp.setIsActive(false);
		Set<SalaryForMonth> salsFM = emp.getSalaryForMonth();
		SalaryForMonth sfm = null;
		for (SalaryForMonth salaryForMonth : salsFM) {
			if (salaryForMonth.getMonth().equals(Date.valueOf("2019-10-31"))){
				salaryForMonth.setPaymentStatus("cancelled");
				break;
			}	
		}
		sfm = emp.getSalaryForMonth(Date.valueOf("2019-10-21"), "paid", 21, 31);
		emp.getSalaryForMonth().add(sfm);
		updateEmployee(session, emp);
		
		System.out.println("\n\n");
/*6 Company had some great profits and is revising the base pay by 10% */	
		List<Salary> salList = fetchSalary(session);
		for (Salary sal: salList) {
			sal.setBaseSalary(1.1 * sal.getBaseSalary());
			updateSalary(session, sal);
		}
		
		System.out.println("\n\n");
/*7 Calculate the salary of the month of 112019 for all employees */	
		empList = fetchEmployee(session);
		for (Employee employee : empList) {
			calculateSalary(employee, Date.valueOf("2019-11-30"), "paid");
			updateEmployee(session, employee);
		}
		
		System.out.println("\n\n");
/*8 Two employees with id 4 and 6 have changed their account details, please update them for salary processing. */
		emp = fetchEmployee(session, 4);
		Account acc = emp.getAccount();
		acc.setAccountNumber(987654);
		acc.setBankName("NewBank");
		acc.setBranchCode("NEWBANK01");
		acc.setType("Checking");
		updateEmployee(session, emp);
		
		emp = fetchEmployee(session, 6);
		acc = emp.getAccount();
		acc.setAccountNumber(456789);
		acc.setBankName("NewBank");
		acc.setBranchCode("NEWBANK01");
		acc.setType("Checking");
		updateEmployee(session, emp);
		
		System.out.println("\n\n");
/*9 Calculate the salary of the month of 122019 for all employees */	
		empList = fetchEmployee(session);
		for (Employee employee : empList) {
			calculateSalary(employee, Date.valueOf("2019-12-31"), "paid");
			updateEmployee(session, employee);
		}
		
		System.out.println("\n\n");
/*10 Print the entire salaries paid out to all employees for the quarter ending on 31/12/2019. */
		
		List<SalaryForMonth> salfm = fetchSalaryForMonth(session, Date.valueOf("2019-10-01"), Date.valueOf("2019-12-31"));
		for (SalaryForMonth salaryForMonth : salfm) {
			System.out.println(salaryForMonth);
		}
		
		session.close();
	}
	

	public static Integer addSalary(Session session,Salary s){
		return  (Integer)session.save(s);
	}
	
	public static Salary fetchSalary(Session session, Integer id) {
		return session.get(Salary.class, id);
	}
	
	public static void updateSalary(Session session, Salary s) {
		Transaction tx = session.beginTransaction();
	
		try{
			
			session.update(s);
			session.flush();
			tx.commit();
		}catch(Exception e){
		
			tx.rollback();
			e.printStackTrace();
		}finally{
			try {
				tx.rollback();
			}catch(Exception e) {
				
			}
		}
	}
	
	public static List<Salary> fetchSalary(Session session){
		return session.createQuery("From Salary").list();
	}
	
	public static Integer addEmployee(Session session,Employee e){
		return  (Integer)session.save(e);
	}
	
	public static Employee fetchEmployee(Session session, Integer id) {
		return session.get(Employee.class, id);
	}
	
	public static List<Employee> fetchEmployee(Session session){
		return session.createQuery("From Employee").list();
	}
	
	public static void updateEmployee(Session session, Employee e) {
		Transaction tx = session.beginTransaction();
	
		try{
			
			session.update(e);
			session.flush();
			tx.commit();
		}catch(Exception err){
		
			tx.rollback();
			err.printStackTrace();
		}finally{
			try {
				tx.rollback();
			}catch(Exception err) {
				
			}
		}
	}
	
	public static void calculateSalary(Employee emp, Date date, String status) {
		if (emp.getIsActive()) {
			emp.getSalaryForMonth().add(emp.getSalaryForMonth(date, status));
		}
	}
	
	public static void calculateSalary(Employee emp, Date date, String status, Integer days, Integer daysThisMonth) {
		if (emp.getIsActive()) {
			emp.getSalaryForMonth().add(emp.getSalaryForMonth(date, status, days, daysThisMonth));
		}
	}
	
	public static Integer addSalaryForMonth(Session session, SalaryForMonth s) {
		return (Integer) session.save(s);
	}
	
	public static List<SalaryForMonth> fetchSalaryForMonth(Session session) {
		return session.createQuery("From SalaryForMonth").list();
	}
	
	public static List<SalaryForMonth> fetchSalaryForMonth(Session session, Date start, Date end) {
		return session.createQuery("From SalaryForMonth WHERE month BETWEEN '" + start.toString() + "' AND '" + end.toString() + "'").list();
	}
	

}
