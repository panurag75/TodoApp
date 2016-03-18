package todo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="Status.getByName", query="select s from Status s where s.name=:name")
public class Status implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long statusId;
	
	private String name;
	
	@OneToMany(mappedBy="status")
	private List<TodoItem> todoItems;

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(final long statusId) {
		this.statusId = statusId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Status: statusId=" + statusId + ", name=" + name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Status) {
			final Status s = (Status)obj;
			return s.statusId == statusId;//tbd: compare other fields also
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int)statusId;//tbd: include other fields also in calculation
	}
}
