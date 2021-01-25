package com.bank.common;

public class Status<T> {
	
	private int status;
	
	private T Entity;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getEntity() {
		return Entity;
	}

	public void setEntity(T entity) {
		Entity = entity;
	}

	public Status(T entity) {
		super();
		Entity = entity;
		if(null != entity) {
			this.status = 200;
		}else {
			this.status = 404;
		}
	}

	public Status() {
		super();
	}

}
