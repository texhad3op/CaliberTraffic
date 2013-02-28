package net.texhad3op.ejb;

import java.util.List;

import javax.ejb.Local;

import net.texhad3op.ejb.base.BaseLocal;
import net.texhad3op.ejb.entities.CaliberOperation;
import net.texhad3op.ejb.entities.Worker;

@Local
public interface CaliberOperationLocal extends BaseLocal<CaliberOperation> {
	public CaliberOperation find(Object id);

	public List<CaliberOperation> findAll();

	void remove(Long id);

	List<Object[]> findAllOperations();
	List<Object[]> findAllOperationsByWorker(Worker worker);
}