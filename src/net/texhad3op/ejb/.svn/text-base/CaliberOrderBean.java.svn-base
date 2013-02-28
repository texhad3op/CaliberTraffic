package net.texhad3op.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import net.texhad3op.ejb.base.BaseBean;
import net.texhad3op.ejb.entities.CaliberOrder;

@Stateless(name = "CaliberOrderEJB")
public class CaliberOrderBean extends BaseBean<CaliberOrder> implements CaliberOrderLocal {

	@EJB(name = "CaliberEJB")
	public CaliberLocal caliberLocal;

	public CaliberOrder find(Object id) {
		return em.find(CaliberOrder.class, id);
	}

	public List<CaliberOrder> findAll() {
		return em.createQuery("select object(o) from CaliberOrder as o order by o.registered").getResultList();
	}

	public List<CaliberOrder> findActivesOrders() {
		return em.createQuery("select object(o) from CaliberOrder as o where o.isActive = true order by o.registered").getResultList();
	}

	public void remove(Long id) {
		Query query = em.createQuery("delete from CaliberOrder as co where co.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public void approveTakeCaliberOrder(CaliberOrder co) {
		if (caliberLocal.takeCaliber(co.getCaliber().getId(), co.getWorker())) {
			CaliberOrder caliberOrder = em.find(CaliberOrder.class, co.getId());
			caliberOrder.setIsActive(false);
			edit(caliberOrder);
		}
	}

	public void approveReturnCaliberOrder(CaliberOrder co) {
		caliberLocal.returnCaliber(co.getCaliber().getId(), co.getWorker());
		CaliberOrder caliberOrder = em.find(CaliberOrder.class, co.getId());
		caliberOrder.setIsActive(false);
		edit(caliberOrder);
	}
}
