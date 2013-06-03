package com.uiobjects.testbeans;

import java.util.Date;



public class Ticket {

	
	private long id;
	private MonthlyTicket parent;
	private Customer customer;
	private Month month;
	private Date deadline;
	private Date completed;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public MonthlyTicket getParent() {
		return parent;
	}
	public void setParent(MonthlyTicket parent) {
		this.parent = parent;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setMonth(Month month) {
		this.month = month;
	}
	
	public Month getMonth() {
		return month;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public Date getDeadline() {
		return deadline;
	}
	
	public void setCompleted(Date completed) {
		this.completed = completed;
	}
	
	public Date getCompleted() {
		return completed;
	}
	
	
}
