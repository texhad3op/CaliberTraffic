package net.texhad3op.ejb;

import java.util.List;

import javax.ejb.Local;

import net.texhad3op.ejb.base.BaseLocal;
import net.texhad3op.ejb.entities.Caliber;
import net.texhad3op.ejb.entities.Worker;

@Local
public interface CaliberLocal extends BaseLocal<Caliber> {
	public Caliber find(Object id);

	public List<Caliber> findAll();

	void remove(Long id);

	boolean takeCaliber(Long id, Worker worker);
	void returnCaliber(Long id, Worker worker);	
	
	List<Object[]> findAllCalibers(String deviceName, String measurementNominalMin, String measurementNominalMax, Worker worker);	
	List<Object[]> allCalibersTraffic(Caliber caliber);	
	List<Object[]> findAllCalibersForChecking();
	List<CaliberChartItem> allCalibersTrafficChartItems(Long caliberId);
}