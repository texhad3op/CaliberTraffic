package net.texhad3op.ejb;

import java.util.List;

import javax.ejb.Local;

import net.texhad3op.ejb.base.BaseLocal;
import net.texhad3op.ejb.entities.CaliberOrder;

@Local
public interface CaliberOrderLocal extends BaseLocal<CaliberOrder> {
	public CaliberOrder find(Object id);

	public List<CaliberOrder> findAll();

	public List<CaliberOrder>  findActivesOrders();
	void remove(Long id);
	void approveTakeCaliberOrder(CaliberOrder co);	
	void approveReturnCaliberOrder(CaliberOrder co);	
}
