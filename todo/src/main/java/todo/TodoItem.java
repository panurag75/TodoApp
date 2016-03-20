package todo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="todoitem")
@NamedQueries(
{@NamedQuery(name="TodoItem.findAll", query="select t from TodoItem t"),
@NamedQuery(name="TodoItem.getActive", 
query="select t from TodoItem t where t.status <> :statusCompleted or t.status is null"),
@NamedQuery(name="TodoItem.getBySummary", 
query="select t from TodoItem t where t.summary = :summary"),
@NamedQuery(name="TodoItem.getCount", 
query="select Count(*) from todoitem")
})
public class TodoItem implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String summary;

	private String description;

	@ManyToOne
	@JoinColumn(name="statusId", referencedColumnName="statusId")
	private Status status;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	private Date updatedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDateTime;

	public void setId(final long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(final Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getDueDateTime() {
		return dueDateTime;
	}

	public void setDueDateTime(final Date dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}
	
	@Override
	public String toString() {
		return "# TodoItem: id=" + id + ", summary=" + summary;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TodoItem) {
			final TodoItem ti = (TodoItem)obj;
			return ti.id == id;//tbd: compare other fields also
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int)id;//tbd: include other fields also in calculation
	}
}
