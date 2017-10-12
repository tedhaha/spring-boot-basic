package kr.tedchoi.www.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "sex")
	private String sex;

	@Column(name = "age")
	private String age;

	@Column(name = "parentId")
	private long parentId;

	@Column(name = "registDate")
	private Date registDate;

	@PrePersist
	void createdAt() {
		this.registDate = new Date();
	}

	public Customer() {
	}
	public Customer(String name) {
		this.name = name;
	}

	public Customer(String name, String phone, String email, String sex, String age) {
		super();
		this.name = name; 
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.age = age;
	}
	public Customer(String name, String phone, String email, String sex, String age, long parentId) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.age = age;
		this.parentId = parentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
