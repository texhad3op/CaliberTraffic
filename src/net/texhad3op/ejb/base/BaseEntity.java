package net.texhad3op.ejb.base;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class BaseEntity implements Serializable{
	public abstract Timestamp getRegistered();

	public abstract void setRegistered(Timestamp registered);
}
