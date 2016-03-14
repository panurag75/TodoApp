package todo.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import todo.Status;

@Stateless
public class StatusDAO implements StatusDAOLocal {
	
	private static final Logger lgr = Logger.getLogger(StatusDAO.class.getName());

	@PersistenceContext(unitName="TodoPU")
	private EntityManager em;
	
	@Override
	public Status get(final String name) {
		lgr.log(Level.FINEST, "Entry. name={0}", name);
		final TypedQuery<Status> tq = em.createNamedQuery("getByName", Status.class);
		tq.setParameter("name", name);
		Status status = tq.getSingleResult();
		lgr.log(Level.FINEST, "Entry. status={0}", status);
		return status;
	}
}
