package net.texhad3op.ejb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import net.texhad3op.ejb.base.BaseBean;
import net.texhad3op.ejb.entities.Caliber;
import net.texhad3op.ejb.entities.CaliberOperation;
import net.texhad3op.ejb.entities.Worker;

@Stateless(name = "CaliberEJB")
public class CaliberBean extends BaseBean<Caliber> implements CaliberLocal {

	@EJB(name = "CaliberOperationEJB")
	public CaliberOperationLocal caliberOperationLocal;

	public Caliber find(Object id) {
		return em.find(Caliber.class, id);
	}

	public List<Caliber> findAll() {
		return em.createQuery("select object(c) from Caliber as c order by c.deviceNr").getResultList();
	}

	public void remove(Long id) {
		Query query = em.createQuery("delete from Caliber as caliber where caliber.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean takeCaliber(Long id, Worker worker) {
		Caliber caliber = em.find(Caliber.class, id);
		if (caliber.getIsTaken())
			return false;
		else {
			CaliberOperation co = new CaliberOperation();
			co.setWorker(worker);
			co.setCaliber(caliber);
			co.setType(Utils.getOperationType(0));
			caliberOperationLocal.create(co);

			caliber.setLastCaliberOperationId(co.getId());
			caliber.setIsTaken(true);
			em.persist(caliber);
			return true;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void returnCaliber(Long id, Worker worker) {
		Caliber caliber = em.find(Caliber.class, id);
		CaliberOperation co = new CaliberOperation();
		co.setWorker(worker);
		co.setCaliber(caliber);
		co.setType(Utils.getOperationType(1));
		caliberOperationLocal.create(co);

		caliber.setLastCaliberOperationId(co.getId());
		caliber.setIsTaken(false);
		em.persist(caliber);
	}

	public List<Object[]> findAllCalibers(String deviceName, String measurementNominalMin, String measurementNominalMax, Worker worker) {
		
		String qry = "select co.operationtype,w.userlogin,w.id,c.istaken, c.id as caliberId,c.deviceNr"
			+ ",c.measurementNominal,c.measurementTolerance,c.minCaliber,c.maxCaliber,c.holdPlace,c.holdSubPlace,cor.caliberordertype,c.isActive,cor.workerid,co.registered"
			+ " from calibers c left join caliberoperations co on c.lastcaliberoperationid = co.id"
					+ " left join workers w on co.workerid = w.id left join caliberorders cor on (c.lastcaliberorderid = cor.id and cor.workerid = :forUserId )"
							//"  where c.isActive=true" ;
					+" where 1=1";
		if(null != deviceName) qry += " and upper(c.deviceNr) like upper('%"+deviceName+"%')";
		if(null != measurementNominalMin && null != measurementNominalMax){
			qry += " and CAST(c.measurementNominal as real) >= CAST("+measurementNominalMin+" as real) and cast(c.measurementNominal as real) <= CAST("+measurementNominalMax+" as real)";
		}
		else if(null != measurementNominalMin){
			qry += " and CAST(c.measurementNominal as real) = CAST("+measurementNominalMin+" as real)";
		}
		String orderSection = " order by c.deviceNr";
		Query query = em.createNativeQuery(
				qry+orderSection);
		query.setParameter("forUserId", worker.getId());
		return query.getResultList();

	}

	public List<Object[]> allCalibersTraffic(Caliber caliber) {
		return em
				.createNativeQuery(
						"select c.deviceNr,co.operationtype,co.registered, w.id, w.firstname from calibers c"
								+ " left join caliberoperations co on c.id = co.caliberid"
								+ " left join workers w on w.id = co.workerid where c.id = :id order by c.deviceNr", "CalibersTraffic")
				.setParameter("id", caliber.getId()).getResultList();

	}
	
	public List<CaliberChartItem> allCalibersTrafficChartItems(Long caliberId) {
		
		List<Object[]> list = em
		.createNativeQuery(
				"select c.deviceNr,co.operationtype,co.registered, w.id, w.firstname from calibers c"
						+ " left join caliberoperations co on c.id = co.caliberid"
						+ " left join workers w on w.id = co.workerid where c.id = :id order by c.deviceNr", "CalibersTraffic")
		.setParameter("id", caliberId).getResultList();
		
		
		List<CaliberChartItem> chartList = new ArrayList<CaliberChartItem>();
		for(Object[] tuple:list){
			Integer operation = (Integer) tuple[1];
			chartList.add(new CaliberChartItem((Timestamp) tuple[2], 0 == operation?true:false));			
		}

		Collections.sort(chartList, new Comparator<CaliberChartItem>() {
			public int compare(CaliberChartItem i1, CaliberChartItem i2) {
				return i1.start.compareTo(i2.start);
			}
		});

		CaliberChartItem firstItem = null;
		if (0 != list.size())
			firstItem = chartList.get(0);
		CaliberChartItem prevItem = null;
		for (CaliberChartItem item : chartList) {
			if (null != prevItem) {
				long d = item.start.getTime() - firstItem.start.getTime();
				item.diff = (int) (d / 86400000);
			} else
				item.diff = 0;
			prevItem = item;
		}
		return chartList;
	}	
	

	public List<Object[]> findAllCalibersForChecking() {
		return em.createNativeQuery(
				"select caliber.id as caliberId,caliber.nextCheckingTime as nextCheckingTime, caliber.deviceNr as deviceNr from calibers as caliber"
						+ " where CAST(caliber.nextCheckingTime AS date) <= current_date order by caliber.nextCheckingTime",
				"CalibersForChecking").getResultList();

	}
}
