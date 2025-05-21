package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the JOURNAL_ENTRIES_EXPENDITURE database table.
 * 
 */
@Entity
@Table(name="JOURNAL_ENTRIES_EXPENDITURE")
@NamedQuery(name="JournalEntriesExpenditure.findAll", query="SELECT j FROM JournalEntriesExpenditure j")
public class JournalEntriesExpenditure implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JournalEntriesExpenditurePK id;

	@Column(name="AC_SERIES")
	private String acSeries;

	@Column(name="AC_TYPE")
	private String acType;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="EXPENDITURE_USE")
	private String expenditureUse;

	private String gl;

	@Column(name="GL_COMPANY")
	private String glCompany;

	@Column(name="GL_COST_CENTER")
	private String glCostCenter;

	@Column(name="GL_EXPENDITURE")
	private String glExpenditure;

	@Column(name="JOURNAL_DESCRIPTION")
	private String journalDescription;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	private BigDecimal notes;

	public JournalEntriesExpenditure() {
	}

	public JournalEntriesExpenditurePK getId() {
		return this.id;
	}

	public void setId(JournalEntriesExpenditurePK id) {
		this.id = id;
	}

	public String getAcSeries() {
		return this.acSeries;
	}

	public void setAcSeries(String acSeries) {
		this.acSeries = acSeries;
	}

	public String getAcType() {
		return this.acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpenditureUse() {
		return this.expenditureUse;
	}

	public void setExpenditureUse(String expenditureUse) {
		this.expenditureUse = expenditureUse;
	}

	public String getGl() {
		return this.gl;
	}

	public void setGl(String gl) {
		this.gl = gl;
	}

	public String getGlCompany() {
		return this.glCompany;
	}

	public void setGlCompany(String glCompany) {
		this.glCompany = glCompany;
	}

	public String getGlCostCenter() {
		return this.glCostCenter;
	}

	public void setGlCostCenter(String glCostCenter) {
		this.glCostCenter = glCostCenter;
	}

	public String getGlExpenditure() {
		return this.glExpenditure;
	}

	public void setGlExpenditure(String glExpenditure) {
		this.glExpenditure = glExpenditure;
	}

	public String getJournalDescription() {
		return this.journalDescription;
	}

	public void setJournalDescription(String journalDescription) {
		this.journalDescription = journalDescription;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public BigDecimal getNotes() {
		return this.notes;
	}

	public void setNotes(BigDecimal notes) {
		this.notes = notes;
	}

}