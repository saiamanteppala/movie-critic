package com.javapoint.entities;








import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table

public class User  {

	    @Id
	    @Column(name = "user_id")
	  @GeneratedValue(strategy = GenerationType.AUTO)
	   
	    private int user_id;

	    private String first_name;
	    private String last_name;
	    private String gender;
	    private int age;
	    private String contact_number;
	    private String email;
	    private String user_name;
	    private String password;
	    
	    
	    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id")}, 
	    inverseJoinColumns = { @JoinColumn(name = "role_id")})
	    			
	    
	  
	    private Collection<Role> roles;
	    
		
	
		
		
		public Collection<Role> getRoles() {
			return roles;
		}
		public User(String first_name, String last_name, String gender, int age, String contact_number,
				String email, String user_name, Collection<Role> list) {
			super();
			this.user_id = user_id;
			this.first_name = first_name;
			this.last_name = last_name;
			this.gender = gender;
			this.age = age;
			this.contact_number = contact_number;
			this.email = email;
			this.user_name = user_name;
			this.password = password;
			this.roles = roles;
		}
		
		
		
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public void setRoles(Collection<Role> roles) {
			this.roles = roles;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getContact_number() {
			return contact_number;
		}
		public void setContact_number(String contact_number) {
			this.contact_number = contact_number;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
}