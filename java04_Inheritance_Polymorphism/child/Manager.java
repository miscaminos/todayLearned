package com.encore.child;

import com.encore.util.MyDate;
import com.encore.parent.Person;

public class Manager extends Person {
	private String dept;
	private int salary;
	private int bonus;
	
	public Manager(String name, MyDate birthDate, String address, String dept, int salary, int bonus) {
		super(name, birthDate, address);
		this.dept = dept;
		this.salary = salary;
		this.bonus = bonus;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}

	/**
	 * @return the bonus
	 */
	public int getBonus() {
		return bonus;
	}

	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return super.toString()+","+ dept;
	}
	
	
}
