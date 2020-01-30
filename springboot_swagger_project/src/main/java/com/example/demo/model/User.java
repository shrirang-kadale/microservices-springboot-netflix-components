/**
 * 
 */
package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author skadale
 *
 */

public class User {

	public User(Integer userId, String name, Integer age) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
	}

    @ApiModelProperty(notes = "The database generated product ID")
	private Integer userId;
    
    @ApiModelProperty(notes = "The database generated product ID")
	private String name;
    
    @ApiModelProperty(notes = "The database generated product ID")
	private Integer age;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", age=" + age + "]";
	}
	
	
	
	
}
