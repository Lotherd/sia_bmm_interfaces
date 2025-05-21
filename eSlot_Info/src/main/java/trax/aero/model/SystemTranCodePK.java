package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SYSTEM_TRAN_CODE database table.
 * 
 */
@Embeddable
public class SystemTranCodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SYSTEM_TRANSACTION")
	private String systemTransaction;

	@Column(name="SYSTEM_CODE")
	private String systemCode;

	@Column(name="SYSTEM_TRAN_CODE_SUB")
	private String systemTranCodeSub;

	public SystemTranCodePK() {
	}
	public String getSystemTransaction() {
		return this.systemTransaction;
	}
	public void setSystemTransaction(String systemTransaction) {
		this.systemTransaction = systemTransaction;
	}
	public String getSystemCode() {
		return this.systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getSystemTranCodeSub() {
		return this.systemTranCodeSub;
	}
	public void setSystemTranCodeSub(String systemTranCodeSub) {
		this.systemTranCodeSub = systemTranCodeSub;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SystemTranCodePK)) {
			return false;
		}
		SystemTranCodePK castOther = (SystemTranCodePK)other;
		return 
			this.systemTransaction.equals(castOther.systemTransaction)
			&& this.systemCode.equals(castOther.systemCode)
			&& this.systemTranCodeSub.equals(castOther.systemTranCodeSub);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.systemTransaction.hashCode();
		hash = hash * prime + this.systemCode.hashCode();
		hash = hash * prime + this.systemTranCodeSub.hashCode();
		
		return hash;
	}
}