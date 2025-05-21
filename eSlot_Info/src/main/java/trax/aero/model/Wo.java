package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the WO database table.
 * 
 */
@Entity
@NamedQuery(name="Wo.findAll", query="SELECT w FROM Wo w")
public class Wo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long wo;

	private String ac;

	@Column(name="AC_SERIES")
	private String acSeries;

	@Column(name="AC_SN")
	private String acSn;

	@Column(name="AC_TYPE")
	private String acType;

	@Column(name="ACTUAL_START_DATE")
	private Date actualStartDate;

	@Column(name="ACTUAL_START_HOUR")
	private BigDecimal actualStartHour;

	@Column(name="ACTUAL_START_MINUTE")
	private BigDecimal actualStartMinute;

	@Column(name="AMP_MS")
	private String ampMs;

	@Column(name="AMP_REV_DATE")
	private Date ampRevDate;

	@Column(name="AMP_REVISION")
	private String ampRevision;

	@Column(name="AMP_TEMP_REV")
	private String ampTempRev;

	private String approval;

	@Column(name="APPROVED_CERTIFICATE")
	private String approvedCertificate;

	@Column(name="\"AUTHORIZATION\"")
	private String authorization;

	@Column(name="AUTHORIZATION_BY")
	private String authorizationBy;

	@Column(name="AUTHORIZATION_DATE")
	private Date authorizationDate;

	@Column(name="BLOB_NO")
	private BigDecimal blobNo;

	@Column(name="CLOSED_BY")
	private String closedBy;

	@Column(name="CLOSED_DATE")
	private Date closedDate;

	@Column(name="COMPLETED_BY")
	private String completedBy;

	@Column(name="COMPLETION_DATE")
	private Date completionDate;

	@Column(name="COMPLETION_TIME")
	private Date completionTime;

	private String cosl;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CUSTOMER_WO")
	private String customerWo;

	@Column(name="DAMAGE_INDICATOR")
	private String damageIndicator;

	private String defect;

	@Column(name="DEFECT_AUTO_WO")
	private String defectAutoWo;

	@Column(name="DEFECT_ITEM")
	private BigDecimal defectItem;

	@Column(name="DEFECT_TYPE")
	private String defectType;

	@Column(name="DOCUMENT_NO")
	private BigDecimal documentNo;

	@Column(name="EXCLUDE_MH_PLANNER")
	private String excludeMhPlanner;

	private String expenditure;

	@Column(name="EXTERNAL_REFERENCE")
	private String externalReference;

	@Column(name="FIELD_TRIP_INDICATOR")
	private String fieldTripIndicator;

	private String flight;

	@Column(name="FLIGHT_ID")
	private BigDecimal flightId;

	@Column(name="FORM_NO")
	private BigDecimal formNo;

	@Column(name="GE_MODIFIED_DATE")
	private Date geModifiedDate;

	@Column(name="GE_TRANSFERRED_DATE")
	private Date geTransferredDate;

	@Column(name="GL_COMPANY")
	private String glCompany;

	@Column(name="IN_PROGRESS")
	private String inProgress;

	@Column(name="INSURANCE_CLAIM")
	private String insuranceClaim;

	@Column(name="INSURANCE_CLAIM_NUMBER")
	private String insuranceClaimNumber;

	@Column(name="INTERFACE_CREATED_DATE")
	private Date interfaceCreatedDate;

	@Column(name="INTERFACE_MODIFIED_DATE")
	private Date interfaceModifiedDate;

	@Column(name="INTERFACE_TRANSFER_BY")
	private String interfaceTransferBy;

	@Column(name="INTERFACE_TRANSFER_DATE")
	private Date interfaceTransferDate;

	private String location;

	@Column(name="LONG_TERM_EVENT")
	private String longTermEvent;

	@Column(name="MANUFACTURE_ORDER")
	private String manufactureOrder;

	@Column(name="MM_REVISION")
	private String mmRevision;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name="\"MODULE\"")
	private String module;

	@Column(name="MTC_ALERT")
	private String mtcAlert;

	@Column(name="MTC_CONTACTED")
	private String mtcContacted;

	@Column(name="NH_WO")
	private BigDecimal nhWo;

	private BigDecimal notes;

	@Column(name="NOTES_FORM1")
	private BigDecimal notesForm1;

	@Column(name="NR_ALLOW")
	private String nrAllow;

	@Column(name="NR_CONTROL")
	private BigDecimal nrControl;

	@Column(name="NR_REQ_ITEM")
	private String nrReqItem;

	@Column(name="OPS_LINE")
	private String opsLine;

	@Column(name="ORDER_TYPE")
	private String orderType;

	@Column(name="PACK_PAGES")
	private BigDecimal packPages;

	@Column(name="PAPER_CHECKED")
	private String paperChecked;

	@Column(name="PAPER_CHECKED_BY")
	private String paperCheckedBy;

	@Column(name="PAPER_CHECKED_DATE")
	private Date paperCheckedDate;

	@Column(name="PAPER_STORE_BIN")
	private String paperStoreBin;

	@Column(name="PAPER_STORE_LOCATION")
	private String paperStoreLocation;

	private String pn;

	@Column(name="PN_CONDITION")
	private String pnCondition;

	@Column(name="PN_DESCRIPTION")
	private String pnDescription;

	@Column(name="PN_QTY")
	private BigDecimal pnQty;

	@Column(name="PN_SN")
	private String pnSn;

	@Column(name="POSTCOMPLETED_BY")
	private String postcompletedBy;

	@Column(name="POSTCOMPLETED_DATE")
	private Date postcompletedDate;

	@Column(name="PREVIOUS_INTERFACE_TRANSACTION")
	private BigDecimal previousInterfaceTransaction;

	private String priority;

	private String project;

	@Column(name="RELEASE_FOR_AUTHORIZATION")
	private String releaseForAuthorization;

	@Column(name="RELEASE_FOR_AUTHORIZATION_ON")
	private Date releaseForAuthorizationOn;

	@Column(name="RESTRICT_ACTUAL")
	private String restrictActual;

	@Column(name="SABRE_MESSAGE_NUMBER")
	private BigDecimal sabreMessageNumber;

	@Column(name="SCHEDULE_COMPLETION_DATE")
	private Date scheduleCompletionDate;

	@Column(name="SCHEDULE_COMPLETION_HOUR")
	private BigDecimal scheduleCompletionHour;

	@Column(name="SCHEDULE_COMPLETION_MINUTE")
	private BigDecimal scheduleCompletionMinute;

	@Column(name="SCHEDULE_ORG_COMPLETION_DATE")
	private Date scheduleOrgCompletionDate;

	@Column(name="SCHEDULE_ORG_COMPLETION_HOUR")
	private BigDecimal scheduleOrgCompletionHour;

	@Column(name="SCHEDULE_ORG_COMPLETION_MINUTE")
	private BigDecimal scheduleOrgCompletionMinute;

	@Column(name="SCHEDULE_START_DATE")
	private Date scheduleStartDate;

	@Column(name="SCHEDULE_START_HOUR")
	private BigDecimal scheduleStartHour;

	@Column(name="SCHEDULE_START_MINUTE")
	private BigDecimal scheduleStartMinute;

	@Column(name="SCHEDULE_TIMESTAMP")
	private Date scheduleTimestamp;

	@Column(name="SHELF_LIFE_EXPIRATION")
	private Date shelfLifeExpiration;

	private String site;

	private String status;

	@Column(name="STATUS_PRIOR_TO_HD")
	private String statusPriorToHd;

	@Column(name="TASK_CARD_NUMBERING_SYSTEM")
	private BigDecimal taskCardNumberingSystem;

	@Column(name="TEMPLATE_ID")
	private BigDecimal templateId;

	@Column(name="THIRD_PARTY_WO")
	private String thirdPartyWo;

	private String vendor;

	@Column(name="WARRANTY_INDICATOR")
	private String warrantyIndicator;

	@Column(name="WO_CATEGORY")
	private String woCategory;

	@Column(name="WO_CLOSING")
	private String woClosing;

	@Column(name="WO_CLOSING_STATUS")
	private String woClosingStatus;

	@Column(name="WO_DESCRIPTION")
	private String woDescription;

	@Column(name="WO_INITIAL_INSTALL")
	private String woInitialInstall;

	@Column(name="WO_INPROGRESS_STATUS")
	private String woInprogressStatus;

	@Column(name="WO_INPROGRESS_STATUS_BY")
	private String woInprogressStatusBy;

	@Column(name="WO_INPROGRESS_STATUS_DATE")
	private Date woInprogressStatusDate;

	@Column(name="WORK_STARTED")
	private String workStarted;

	public Wo() {
	}

	public long getWo() {
		return this.wo;
	}

	public void setWo(long wo) {
		this.wo = wo;
	}

	public String getAc() {
		return this.ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getAcSeries() {
		return this.acSeries;
	}

	public void setAcSeries(String acSeries) {
		this.acSeries = acSeries;
	}

	public String getAcSn() {
		return this.acSn;
	}

	public void setAcSn(String acSn) {
		this.acSn = acSn;
	}

	public String getAcType() {
		return this.acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public Date getActualStartDate() {
		return this.actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public BigDecimal getActualStartHour() {
		return this.actualStartHour;
	}

	public void setActualStartHour(BigDecimal actualStartHour) {
		this.actualStartHour = actualStartHour;
	}

	public BigDecimal getActualStartMinute() {
		return this.actualStartMinute;
	}

	public void setActualStartMinute(BigDecimal actualStartMinute) {
		this.actualStartMinute = actualStartMinute;
	}

	public String getAmpMs() {
		return this.ampMs;
	}

	public void setAmpMs(String ampMs) {
		this.ampMs = ampMs;
	}

	public Date getAmpRevDate() {
		return this.ampRevDate;
	}

	public void setAmpRevDate(Date ampRevDate) {
		this.ampRevDate = ampRevDate;
	}

	public String getAmpRevision() {
		return this.ampRevision;
	}

	public void setAmpRevision(String ampRevision) {
		this.ampRevision = ampRevision;
	}

	public String getAmpTempRev() {
		return this.ampTempRev;
	}

	public void setAmpTempRev(String ampTempRev) {
		this.ampTempRev = ampTempRev;
	}

	public String getApproval() {
		return this.approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getApprovedCertificate() {
		return this.approvedCertificate;
	}

	public void setApprovedCertificate(String approvedCertificate) {
		this.approvedCertificate = approvedCertificate;
	}

	public String getAuthorization() {
		return this.authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getAuthorizationBy() {
		return this.authorizationBy;
	}

	public void setAuthorizationBy(String authorizationBy) {
		this.authorizationBy = authorizationBy;
	}

	public Date getAuthorizationDate() {
		return this.authorizationDate;
	}

	public void setAuthorizationDate(Date authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	public BigDecimal getBlobNo() {
		return this.blobNo;
	}

	public void setBlobNo(BigDecimal blobNo) {
		this.blobNo = blobNo;
	}

	public String getClosedBy() {
		return this.closedBy;
	}

	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	public Date getClosedDate() {
		return this.closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getCompletedBy() {
		return this.completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Date getCompletionTime() {
		return this.completionTime;
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}

	public String getCosl() {
		return this.cosl;
	}

	public void setCosl(String cosl) {
		this.cosl = cosl;
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

	public String getCustomerWo() {
		return this.customerWo;
	}

	public void setCustomerWo(String customerWo) {
		this.customerWo = customerWo;
	}

	public String getDamageIndicator() {
		return this.damageIndicator;
	}

	public void setDamageIndicator(String damageIndicator) {
		this.damageIndicator = damageIndicator;
	}

	public String getDefect() {
		return this.defect;
	}

	public void setDefect(String defect) {
		this.defect = defect;
	}

	public String getDefectAutoWo() {
		return this.defectAutoWo;
	}

	public void setDefectAutoWo(String defectAutoWo) {
		this.defectAutoWo = defectAutoWo;
	}

	public BigDecimal getDefectItem() {
		return this.defectItem;
	}

	public void setDefectItem(BigDecimal defectItem) {
		this.defectItem = defectItem;
	}

	public String getDefectType() {
		return this.defectType;
	}

	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}

	public BigDecimal getDocumentNo() {
		return this.documentNo;
	}

	public void setDocumentNo(BigDecimal documentNo) {
		this.documentNo = documentNo;
	}

	public String getExcludeMhPlanner() {
		return this.excludeMhPlanner;
	}

	public void setExcludeMhPlanner(String excludeMhPlanner) {
		this.excludeMhPlanner = excludeMhPlanner;
	}

	public String getExpenditure() {
		return this.expenditure;
	}

	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}

	public String getExternalReference() {
		return this.externalReference;
	}

	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}

	public String getFieldTripIndicator() {
		return this.fieldTripIndicator;
	}

	public void setFieldTripIndicator(String fieldTripIndicator) {
		this.fieldTripIndicator = fieldTripIndicator;
	}

	public String getFlight() {
		return this.flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public BigDecimal getFlightId() {
		return this.flightId;
	}

	public void setFlightId(BigDecimal flightId) {
		this.flightId = flightId;
	}

	public BigDecimal getFormNo() {
		return this.formNo;
	}

	public void setFormNo(BigDecimal formNo) {
		this.formNo = formNo;
	}

	public Date getGeModifiedDate() {
		return this.geModifiedDate;
	}

	public void setGeModifiedDate(Date geModifiedDate) {
		this.geModifiedDate = geModifiedDate;
	}

	public Date getGeTransferredDate() {
		return this.geTransferredDate;
	}

	public void setGeTransferredDate(Date geTransferredDate) {
		this.geTransferredDate = geTransferredDate;
	}

	public String getGlCompany() {
		return this.glCompany;
	}

	public void setGlCompany(String glCompany) {
		this.glCompany = glCompany;
	}

	public String getInProgress() {
		return this.inProgress;
	}

	public void setInProgress(String inProgress) {
		this.inProgress = inProgress;
	}

	public String getInsuranceClaim() {
		return this.insuranceClaim;
	}

	public void setInsuranceClaim(String insuranceClaim) {
		this.insuranceClaim = insuranceClaim;
	}

	public String getInsuranceClaimNumber() {
		return this.insuranceClaimNumber;
	}

	public void setInsuranceClaimNumber(String insuranceClaimNumber) {
		this.insuranceClaimNumber = insuranceClaimNumber;
	}

	public Date getInterfaceCreatedDate() {
		return this.interfaceCreatedDate;
	}

	public void setInterfaceCreatedDate(Date interfaceCreatedDate) {
		this.interfaceCreatedDate = interfaceCreatedDate;
	}

	public Date getInterfaceModifiedDate() {
		return this.interfaceModifiedDate;
	}

	public void setInterfaceModifiedDate(Date interfaceModifiedDate) {
		this.interfaceModifiedDate = interfaceModifiedDate;
	}

	public String getInterfaceTransferBy() {
		return this.interfaceTransferBy;
	}

	public void setInterfaceTransferBy(String interfaceTransferBy) {
		this.interfaceTransferBy = interfaceTransferBy;
	}

	public Date getInterfaceTransferDate() {
		return this.interfaceTransferDate;
	}

	public void setInterfaceTransferDate(Date interfaceTransferDate) {
		this.interfaceTransferDate = interfaceTransferDate;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongTermEvent() {
		return this.longTermEvent;
	}

	public void setLongTermEvent(String longTermEvent) {
		this.longTermEvent = longTermEvent;
	}

	public String getManufactureOrder() {
		return this.manufactureOrder;
	}

	public void setManufactureOrder(String manufactureOrder) {
		this.manufactureOrder = manufactureOrder;
	}

	public String getMmRevision() {
		return this.mmRevision;
	}

	public void setMmRevision(String mmRevision) {
		this.mmRevision = mmRevision;
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

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMtcAlert() {
		return this.mtcAlert;
	}

	public void setMtcAlert(String mtcAlert) {
		this.mtcAlert = mtcAlert;
	}

	public String getMtcContacted() {
		return this.mtcContacted;
	}

	public void setMtcContacted(String mtcContacted) {
		this.mtcContacted = mtcContacted;
	}

	public BigDecimal getNhWo() {
		return this.nhWo;
	}

	public void setNhWo(BigDecimal nhWo) {
		this.nhWo = nhWo;
	}

	public BigDecimal getNotes() {
		return this.notes;
	}

	public void setNotes(BigDecimal notes) {
		this.notes = notes;
	}

	public BigDecimal getNotesForm1() {
		return this.notesForm1;
	}

	public void setNotesForm1(BigDecimal notesForm1) {
		this.notesForm1 = notesForm1;
	}

	public String getNrAllow() {
		return this.nrAllow;
	}

	public void setNrAllow(String nrAllow) {
		this.nrAllow = nrAllow;
	}

	public BigDecimal getNrControl() {
		return this.nrControl;
	}

	public void setNrControl(BigDecimal nrControl) {
		this.nrControl = nrControl;
	}

	public String getNrReqItem() {
		return this.nrReqItem;
	}

	public void setNrReqItem(String nrReqItem) {
		this.nrReqItem = nrReqItem;
	}

	public String getOpsLine() {
		return this.opsLine;
	}

	public void setOpsLine(String opsLine) {
		this.opsLine = opsLine;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getPackPages() {
		return this.packPages;
	}

	public void setPackPages(BigDecimal packPages) {
		this.packPages = packPages;
	}

	public String getPaperChecked() {
		return this.paperChecked;
	}

	public void setPaperChecked(String paperChecked) {
		this.paperChecked = paperChecked;
	}

	public String getPaperCheckedBy() {
		return this.paperCheckedBy;
	}

	public void setPaperCheckedBy(String paperCheckedBy) {
		this.paperCheckedBy = paperCheckedBy;
	}

	public Date getPaperCheckedDate() {
		return this.paperCheckedDate;
	}

	public void setPaperCheckedDate(Date paperCheckedDate) {
		this.paperCheckedDate = paperCheckedDate;
	}

	public String getPaperStoreBin() {
		return this.paperStoreBin;
	}

	public void setPaperStoreBin(String paperStoreBin) {
		this.paperStoreBin = paperStoreBin;
	}

	public String getPaperStoreLocation() {
		return this.paperStoreLocation;
	}

	public void setPaperStoreLocation(String paperStoreLocation) {
		this.paperStoreLocation = paperStoreLocation;
	}

	public String getPn() {
		return this.pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public String getPnCondition() {
		return this.pnCondition;
	}

	public void setPnCondition(String pnCondition) {
		this.pnCondition = pnCondition;
	}

	public String getPnDescription() {
		return this.pnDescription;
	}

	public void setPnDescription(String pnDescription) {
		this.pnDescription = pnDescription;
	}

	public BigDecimal getPnQty() {
		return this.pnQty;
	}

	public void setPnQty(BigDecimal pnQty) {
		this.pnQty = pnQty;
	}

	public String getPnSn() {
		return this.pnSn;
	}

	public void setPnSn(String pnSn) {
		this.pnSn = pnSn;
	}

	public String getPostcompletedBy() {
		return this.postcompletedBy;
	}

	public void setPostcompletedBy(String postcompletedBy) {
		this.postcompletedBy = postcompletedBy;
	}

	public Date getPostcompletedDate() {
		return this.postcompletedDate;
	}

	public void setPostcompletedDate(Date postcompletedDate) {
		this.postcompletedDate = postcompletedDate;
	}

	public BigDecimal getPreviousInterfaceTransaction() {
		return this.previousInterfaceTransaction;
	}

	public void setPreviousInterfaceTransaction(BigDecimal previousInterfaceTransaction) {
		this.previousInterfaceTransaction = previousInterfaceTransaction;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getReleaseForAuthorization() {
		return this.releaseForAuthorization;
	}

	public void setReleaseForAuthorization(String releaseForAuthorization) {
		this.releaseForAuthorization = releaseForAuthorization;
	}

	public Date getReleaseForAuthorizationOn() {
		return this.releaseForAuthorizationOn;
	}

	public void setReleaseForAuthorizationOn(Date releaseForAuthorizationOn) {
		this.releaseForAuthorizationOn = releaseForAuthorizationOn;
	}

	public String getRestrictActual() {
		return this.restrictActual;
	}

	public void setRestrictActual(String restrictActual) {
		this.restrictActual = restrictActual;
	}

	public BigDecimal getSabreMessageNumber() {
		return this.sabreMessageNumber;
	}

	public void setSabreMessageNumber(BigDecimal sabreMessageNumber) {
		this.sabreMessageNumber = sabreMessageNumber;
	}

	public Date getScheduleCompletionDate() {
		return this.scheduleCompletionDate;
	}

	public void setScheduleCompletionDate(Date scheduleCompletionDate) {
		this.scheduleCompletionDate = scheduleCompletionDate;
	}

	public BigDecimal getScheduleCompletionHour() {
		return this.scheduleCompletionHour;
	}

	public void setScheduleCompletionHour(BigDecimal scheduleCompletionHour) {
		this.scheduleCompletionHour = scheduleCompletionHour;
	}

	public BigDecimal getScheduleCompletionMinute() {
		return this.scheduleCompletionMinute;
	}

	public void setScheduleCompletionMinute(BigDecimal scheduleCompletionMinute) {
		this.scheduleCompletionMinute = scheduleCompletionMinute;
	}

	public Date getScheduleOrgCompletionDate() {
		return this.scheduleOrgCompletionDate;
	}

	public void setScheduleOrgCompletionDate(Date scheduleOrgCompletionDate) {
		this.scheduleOrgCompletionDate = scheduleOrgCompletionDate;
	}

	public BigDecimal getScheduleOrgCompletionHour() {
		return this.scheduleOrgCompletionHour;
	}

	public void setScheduleOrgCompletionHour(BigDecimal scheduleOrgCompletionHour) {
		this.scheduleOrgCompletionHour = scheduleOrgCompletionHour;
	}

	public BigDecimal getScheduleOrgCompletionMinute() {
		return this.scheduleOrgCompletionMinute;
	}

	public void setScheduleOrgCompletionMinute(BigDecimal scheduleOrgCompletionMinute) {
		this.scheduleOrgCompletionMinute = scheduleOrgCompletionMinute;
	}

	public Date getScheduleStartDate() {
		return this.scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public BigDecimal getScheduleStartHour() {
		return this.scheduleStartHour;
	}

	public void setScheduleStartHour(BigDecimal scheduleStartHour) {
		this.scheduleStartHour = scheduleStartHour;
	}

	public BigDecimal getScheduleStartMinute() {
		return this.scheduleStartMinute;
	}

	public void setScheduleStartMinute(BigDecimal scheduleStartMinute) {
		this.scheduleStartMinute = scheduleStartMinute;
	}

	public Date getScheduleTimestamp() {
		return this.scheduleTimestamp;
	}

	public void setScheduleTimestamp(Date scheduleTimestamp) {
		this.scheduleTimestamp = scheduleTimestamp;
	}

	public Date getShelfLifeExpiration() {
		return this.shelfLifeExpiration;
	}

	public void setShelfLifeExpiration(Date shelfLifeExpiration) {
		this.shelfLifeExpiration = shelfLifeExpiration;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusPriorToHd() {
		return this.statusPriorToHd;
	}

	public void setStatusPriorToHd(String statusPriorToHd) {
		this.statusPriorToHd = statusPriorToHd;
	}

	public BigDecimal getTaskCardNumberingSystem() {
		return this.taskCardNumberingSystem;
	}

	public void setTaskCardNumberingSystem(BigDecimal taskCardNumberingSystem) {
		this.taskCardNumberingSystem = taskCardNumberingSystem;
	}

	public BigDecimal getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(BigDecimal templateId) {
		this.templateId = templateId;
	}

	public String getThirdPartyWo() {
		return this.thirdPartyWo;
	}

	public void setThirdPartyWo(String thirdPartyWo) {
		this.thirdPartyWo = thirdPartyWo;
	}

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getWarrantyIndicator() {
		return this.warrantyIndicator;
	}

	public void setWarrantyIndicator(String warrantyIndicator) {
		this.warrantyIndicator = warrantyIndicator;
	}

	public String getWoCategory() {
		return this.woCategory;
	}

	public void setWoCategory(String woCategory) {
		this.woCategory = woCategory;
	}

	public String getWoClosing() {
		return this.woClosing;
	}

	public void setWoClosing(String woClosing) {
		this.woClosing = woClosing;
	}

	public String getWoClosingStatus() {
		return this.woClosingStatus;
	}

	public void setWoClosingStatus(String woClosingStatus) {
		this.woClosingStatus = woClosingStatus;
	}

	public String getWoDescription() {
		return this.woDescription;
	}

	public void setWoDescription(String woDescription) {
		this.woDescription = woDescription;
	}

	public String getWoInitialInstall() {
		return this.woInitialInstall;
	}

	public void setWoInitialInstall(String woInitialInstall) {
		this.woInitialInstall = woInitialInstall;
	}

	public String getWoInprogressStatus() {
		return this.woInprogressStatus;
	}

	public void setWoInprogressStatus(String woInprogressStatus) {
		this.woInprogressStatus = woInprogressStatus;
	}

	public String getWoInprogressStatusBy() {
		return this.woInprogressStatusBy;
	}

	public void setWoInprogressStatusBy(String woInprogressStatusBy) {
		this.woInprogressStatusBy = woInprogressStatusBy;
	}

	public Date getWoInprogressStatusDate() {
		return this.woInprogressStatusDate;
	}

	public void setWoInprogressStatusDate(Date woInprogressStatusDate) {
		this.woInprogressStatusDate = woInprogressStatusDate;
	}

	public String getWorkStarted() {
		return this.workStarted;
	}

	public void setWorkStarted(String workStarted) {
		this.workStarted = workStarted;
	}

}