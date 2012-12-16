package org.phstudy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Travel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String fromWhere;
	private String destTo;
	public String getFromWhere() {
		return fromWhere;
	}
	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}
	public String getDestTo() {
		return destTo;
	}
	public void setDestTo(String destTo) {
		this.destTo = destTo;
	}


}
