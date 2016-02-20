package com.njit.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 实验室
 * @author rongxin
 *
 */


public class Laboratory implements Serializable{

	private Long id;
	private String name;
	private int avail;     //可用座位
//	private Set<Experiment> exps = new HashSet<Experiment>();
	private String description;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAvail() {
		return avail;
	}
	public void setAvail(int avail) {
		this.avail = avail;
	}
//	public Set<Experiment> getExps() {
//		return exps;
//	}
//	public void setExps(Set<Experiment> exps) {
//		this.exps = exps;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
