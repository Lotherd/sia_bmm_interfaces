package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the JOURNAL_ENTRIES_EXPENDITURE database table.
 * 
 */
@Embeddable
public class JournalEntriesExpenditurePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String category;

	@Column(name="CATEGORY_CODE")
	private String categoryCode;

	@Column(name="\"CLASS\"")
	private String class_;

	@Column(name="\"TRANSACTION\"")
	private String transaction;

	public JournalEntriesExpenditurePK() {
	}
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryCode() {
		return this.categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getClass_() {
		return this.class_;
	}
	public void setClass_(String class_) {
		this.class_ = class_;
	}
	public String getTransaction() {
		return this.transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JournalEntriesExpenditurePK)) {
			return false;
		}
		JournalEntriesExpenditurePK castOther = (JournalEntriesExpenditurePK)other;
		return 
			this.category.equals(castOther.category)
			&& this.categoryCode.equals(castOther.categoryCode)
			&& this.class_.equals(castOther.class_)
			&& this.transaction.equals(castOther.transaction);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.category.hashCode();
		hash = hash * prime + this.categoryCode.hashCode();
		hash = hash * prime + this.class_.hashCode();
		hash = hash * prime + this.transaction.hashCode();
		
		return hash;
	}
}