package com.cc.scoot;

import org.json.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String dueDate;

	private String entry;

	private String priority;

	private int ord;

	public String getDueDate() {
		return this.dueDate;
	}

	public String getEntry() {
		return this.entry;
	}

	public Integer getId() {
		return this.id;
	}

	public int getOrder() {
		return this.ord;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrder(int order) {
		this.ord = order;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public JSONObject toJsonObject() {
		JSONObject ret = new JSONObject();
		ret.put("id", this.id);
		ret.put("entry", this.entry);
		ret.put("dueDate", this.dueDate);
		ret.put("priority", this.priority);
		ret.put("order", this.ord);
		return ret;
	}
}
