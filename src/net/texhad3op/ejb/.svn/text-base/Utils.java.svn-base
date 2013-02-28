package net.texhad3op.ejb;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.texhad3op.ejb.entities.CaliberOrderType;
import net.texhad3op.ejb.entities.OperationType;
import net.texhad3op.ejb.entities.WorkerType;

public class Utils {

	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("lt"));

	public static List<WorkerType> workerTypes;
	public static List<OperationType> operationTypes;
	public static List<CaliberOrderType> caliberOrderTypes;
	static {
		workerTypes = new ArrayList<WorkerType>() {
			{
				add(new WorkerType(0, "Darbuotojas"));
				add(new WorkerType(1, "Administratorius"));
				add(new WorkerType(2, "Direktorius"));
			}
		};
		operationTypes = new ArrayList<OperationType>() {
			{
				add(new OperationType(0, "Paimtas"));
				add(new OperationType(1, "Gražintas"));
			}
		};
		caliberOrderTypes = new ArrayList<CaliberOrderType>() {
			{
				add(new CaliberOrderType(0, "Paimti"));
				add(new CaliberOrderType(1, "Gražinti"));
			}
		};
	}

	public static WorkerType getWorkerType(int type) {
		return workerTypes.get(type);
	}

	public static OperationType getOperationType(int type) {
		return operationTypes.get(type);
	}

	public static CaliberOrderType getCaliberOrderType(int type) {
		return caliberOrderTypes.get(type);
	}

	public static String formatDate(Timestamp ts) {
		return null == ts?"":formatter.format(ts);
	}
}
