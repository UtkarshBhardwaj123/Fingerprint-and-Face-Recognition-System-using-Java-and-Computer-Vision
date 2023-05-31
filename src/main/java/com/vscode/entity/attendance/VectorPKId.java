package com.vscode.entity.attendance;

import java.io.Serializable;
import java.util.Objects;

public class VectorPKId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8100193176209170015L;
	private String id;

	public VectorPKId() {

	}

	public VectorPKId(String id, int index) {
		super();
		this.id = id;
		this.index = index;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, index);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VectorPKId other = (VectorPKId) obj;
		return Objects.equals(id, other.id) && index == other.index;
	}

	private int index;

}
