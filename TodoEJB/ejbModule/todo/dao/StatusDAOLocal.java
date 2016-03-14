package todo.dao;

import javax.ejb.Local;

import todo.Status;

@Local
public interface StatusDAOLocal {
	/**
	 * This method is for returning Status object whose name is the given name.
	 * @param name
	 * @return
	 */
	public Status get(String name);
}
