package com.frame.test;

import java.math.BigDecimal;
import java.sql.Date;


/**
 * 
 * @author lzx
 * @version 1.0
 * @create_date 下午9:21:25
 */
public class Account {

	private Integer id;
	private BigDecimal money;
	private long act_no;
	private Date time;
	private String name;
	private boolean statue;
	private int age;
	private Float f;
	private Long act;
	
	public Long getAct() {
		return act;
	}
	public void setAct(Long act) {
		this.act = act;
	}
	public Float getF() {
		return f;
	}
	public void setF(Float f) {
		this.f = f;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public long getAct_no() {
		return act_no;
	}
	public void setAct_no(long act_no) {
		this.act_no = act_no;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatue() {
		return statue;
	}
	public void setStatue(boolean statue) {
		this.statue = statue;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
