package net.texhad3op.ejb;

import java.util.List;

import javax.ejb.Local;

import net.texhad3op.ejb.base.BaseLocal;
import net.texhad3op.ejb.entities.Worker;

@Local
public interface WorkerLocal extends BaseLocal<Worker> {
	public Worker find(Object id);

	public List<Worker> findAll();

	void remove(Long id);
	
	Worker getWorker(String login, String password);	
}
