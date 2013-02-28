package net.texhad3op.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import net.texhad3op.ejb.base.BaseBean;
import net.texhad3op.ejb.entities.CaliberOperation;
import net.texhad3op.ejb.entities.Worker;

@Stateless(name = "CaliberOperationEJB")
public class CaliberOperationBean extends BaseBean<CaliberOperation> implements CaliberOperationLocal {

	public CaliberOperation find(Object id) {
		return em.find(CaliberOperation.class, id);
	}

	public List<CaliberOperation> findAll() {
		return em.createQuery("select object(co) from CaliberOperation as co").getResultList();
	}

	public void remove(Long id) {
		Query query = em.createQuery("delete from CaliberOperation as caliberOperation where caliberOperation.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public List<Object[]> findAllOperations() {
		return em.createNativeQuery(
				"select co.operationtype,w.userlogin,w.id,c.deviceNr,c.istaken, c.id as caliberId"
						+ " from calibers c left join caliberoperations co on co.caliberid = c.id"
						+ " left join workers w on co.workerid = w.id order by co.registered", "OperationsWithWorkers").getResultList();

	}

	public List<Object[]> findAllOperationsByWorker(Worker worker) {
		return em
				.createNativeQuery(
						"select co.operationtype,co.registered,c.deviceNr"
								+ " from caliberoperations co inner join workers w on co.workerid = w.id"
								+ " left join calibers c on co.caliberid = c.id where w.id = :workerId order by co.registered",
						"OperationsByWorker").setParameter("workerId", worker.getId()).getResultList();

	}
	
	

}
