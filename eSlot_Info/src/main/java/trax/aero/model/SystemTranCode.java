package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SYSTEM_TRAN_CODE database table.
 * 
 */
@Entity
@Table(name="SYSTEM_TRAN_CODE")
@NamedQuery(name="SystemTranCode.findAll", query="SELECT s FROM SystemTranCode s")
public class SystemTranCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SystemTranCodePK id;

	@Column(name="AC_MANDATORY")
	private String acMandatory;

	@Column(name="AC_MANDATORY_EX")
	private String acMandatoryEx;

	@Column(name="AC_MANDATORY_LO")
	private String acMandatoryLo;

	@Column(name="AC_MANDATORY_PO")
	private String acMandatoryPo;

	@Column(name="AC_MANDATORY_RO")
	private String acMandatoryRo;

	@Column(name="AC_MANDATORY_SV")
	private String acMandatorySv;

	@Column(name="AC_RESTRICTION")
	private String acRestriction;

	@Column(name="AC_SERIES")
	private String acSeries;

	@Column(name="AC_TYPE")
	private String acType;

	@Column(name="ACC_FLAG")
	private String accFlag;

	@Column(name="ACTION_PRIORITY")
	private BigDecimal actionPriority;

	@Column(name="AD_CATEGORY")
	private String adCategory;

	@Column(name="ALERT_CATEGORY_NO_OF")
	private BigDecimal alertCategoryNoOf;

	@Column(name="ALERT_NO_OF_REMOVAL")
	private BigDecimal alertNoOfRemoval;

	@Column(name="ALLOW_ADDITIONAL_TASK_CARD")
	private String allowAdditionalTaskCard;

	@Column(name="ALLOW_BUST")
	private String allowBust;

	@Column(name="ALLOW_DECIMAL")
	private String allowDecimal;

	@Column(name="ALLOW_NON_ROUTINE_TASK_CARD")
	private String allowNonRoutineTaskCard;

	private String approval;

	@Column(name="APPROVAL_STATEMENT_1")
	private String approvalStatement1;

	@Column(name="APPROVAL_STATEMENT_2")
	private String approvalStatement2;

	@Column(name="AUTHORIZATION_REQUIRED")
	private String authorizationRequired;

	@Column(name="AUTO_POPULATE_DEFECT")
	private String autoPopulateDefect;

	@Column(name="AUTO_RESET_ON_RO")
	private String autoResetOnRo;

	@Column(name="AUTO_SEQ_EC")
	private String autoSeqEc;

	@Column(name="AUTO_SN_PREFIX")
	private String autoSnPrefix;

	@Column(name="AVL_VOL")
	private String avlVol;

	@Column(name="BIN_COUNT_FREQUENCY")
	private String binCountFrequency;

	@Column(name="BIN_COUNT_NO_WEEKEND")
	private String binCountNoWeekend;

	@Column(name="BLOB_NO")
	private BigDecimal blobNo;

	private String cabin;

	@Column(name="CAPITAL_COST_CENTER")
	private String capitalCostCenter;

	@Column(name="CAPITAL_EXPENDITURE_MANDATORY")
	private String capitalExpenditureMandatory;

	@Column(name="CARRYING_COST_FACTOR")
	private BigDecimal carryingCostFactor;

	@Column(name="CC_REFERENCE")
	private String ccReference;

	@Column(name="CHANGE_SCHEDULE")
	private String changeSchedule;

	private BigDecimal chapter;

	@Column(name="CHAPTER_MANDATORY")
	private String chapterMandatory;

	private String chargeable;

	@Column(name="CHECKOUT_REORDER")
	private String checkoutReorder;

	@Column(name="CHEMICAL_GUIDE_BLOB")
	private BigDecimal chemicalGuideBlob;

	@Column(name="CHEMICAL_GUIDE_SHEET")
	private String chemicalGuideSheet;

	@Column(name="CLASS_DIVISION_NO")
	private String classDivisionNo;

	@Column(name="CLOSE_DEFECT")
	private String closeDefect;

	private String code;

	@Column(name="CODE_COLOR")
	private String codeColor;

	@Column(name="CODIFIED_DEFECT")
	private String codifiedDefect;

	private String company;

	@Column(name="CONFIG_DATE")
	private Date configDate;

	@Column(name="CONFIG_FLAG")
	private String configFlag;

	@Column(name="CONFIG_NUMBER")
	private BigDecimal configNumber;

	@Column(name="CONFIG_OTHER")
	private String configOther;

	@Column(name="CONSUMABLE_KIT")
	private String consumableKit;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CURRENCY_EXCHANGE")
	private BigDecimal currencyExchange;

	@Column(name="CYCLE_COUNT_BY_CATEGORY")
	private String cycleCountByCategory;

	@Column(name="CYCLE_COUNT_BY_COST_FROM")
	private BigDecimal cycleCountByCostFrom;

	@Column(name="CYCLE_COUNT_BY_COST_TO")
	private BigDecimal cycleCountByCostTo;

	@Column(name="CYCLE_COUNT_BY_ESSENTIALITY")
	private String cycleCountByEssentiality;

	@Column(name="CYCLE_COUNT_BY_USAGE_FROM")
	private BigDecimal cycleCountByUsageFrom;

	@Column(name="CYCLE_COUNT_BY_USAGE_TO")
	private BigDecimal cycleCountByUsageTo;

	@Column(name="CYCLE_COUNT_DAYS")
	private BigDecimal cycleCountDays;

	@Column(name="DEFAULT_AUTHORIZATION_ROUTING")
	private String defaultAuthorizationRouting;

	@Column(name="DEFAULT_RELIABILITY_MONITORED")
	private String defaultReliabilityMonitored;

	@Column(name="DEFAULT_SHIP_LOCATION")
	private String defaultShipLocation;

	@Column(name="DEFAULT_SKILL")
	private String defaultSkill;

	@Column(name="DEFECT_REPORT_MAREP")
	private String defectReportMarep;

	@Column(name="DEFECT_REPORT_PILOT")
	private String defectReportPilot;

	@Column(name="DEFER_CAT_AUTO_MDDR_CLOSING")
	private String deferCatAutoMddrClosing;

	@Column(name="DEFER_RESOLVE_MANDATORY")
	private String deferResolveMandatory;

	@Column(name="DEPRECIATION_GL")
	private String depreciationGl;

	@Column(name="DEPRECIATION_GL_COMPANY")
	private String depreciationGlCompany;

	@Column(name="DEPRECIATION_GL_COST_CENTER")
	private String depreciationGlCostCenter;

	@Column(name="DEPRECIATION_GL_EXPENDITURE")
	private String depreciationGlExpenditure;

	@Column(name="DISABLE_DEFER_AFTER_SAVED")
	private String disableDeferAfterSaved;

	@Column(name="DISPATCH_SYSTEM_ADVISE")
	private String dispatchSystemAdvise;

	@Column(name="DISPATCH_SYSTEM_CONFIDENCE")
	private String dispatchSystemConfidence;

	@Column(name="DISPATCH_SYSTEM_DISABLE")
	private String dispatchSystemDisable;

	@Column(name="DISPATCH_SYSTEM_PLANNED_FLAG")
	private String dispatchSystemPlannedFlag;

	@Column(name="DISPATCH_SYSTEM_PRIORITY")
	private String dispatchSystemPriority;

	@Column(name="DO_NOT_ALLOW_CONSESSION")
	private String doNotAllowConsession;

	@Column(name="DO_NOT_ALLOW_HAZMAT")
	private String doNotAllowHazmat;

	@Column(name="DO_NOT_ALLOW_RESET")
	private String doNotAllowReset;

	@Column(name="DO_NOT_ALLOW_RESET_PROD")
	private String doNotAllowResetProd;

	@Column(name="DUAL_INSPECTOR_SKILL")
	private String dualInspectorSkill;

	@Column(name="DUPLICATE_INSPECTION")
	private String duplicateInspection;

	@Column(name="EC_CATEGORY")
	private String ecCategory;

	@Column(name="EC_CLASSIFICATION_MANDATORY")
	private String ecClassificationMandatory;

	@Column(name="EC_NAME_OVERRIDE")
	private String ecNameOverride;

	@Column(name="EDITING_LEVEL")
	private String editingLevel;

	@Column(name="EQUIPMENT_REF_DESIGNATOR")
	private String equipmentRefDesignator;

	@Column(name="ESSENTIALITY_CODE_LEVEL")
	private String essentialityCodeLevel;

	private String etops;

	@Column(name="EX_COST_CENTER")
	private String exCostCenter;

	@Column(name="EX_GL")
	private String exGl;

	@Column(name="EX_GL_COMPANY")
	private String exGlCompany;

	@Column(name="EX_GL_EXPENDITURE")
	private String exGlExpenditure;

	@Column(name="EX_RO_GL")
	private String exRoGl;

	@Column(name="EX_RO_GL_COMPANY")
	private String exRoGlCompany;

	@Column(name="EX_RO_GL_COST_CENTER")
	private String exRoGlCostCenter;

	@Column(name="EX_RO_GL_EXPENDITURE")
	private String exRoGlExpenditure;

	@Column(name="EXPEDITE_TYPE")
	private String expediteType;

	private String expenditure;

	@Column(name="EXPIRATION_DAYS")
	private BigDecimal expirationDays;

	@Column(name="FA_DEPRECIATION_CODE")
	private String faDepreciationCode;

	@Column(name="FA_RESIDUAL_VALUE_PERCENT")
	private BigDecimal faResidualValuePercent;

	@Column(name="FC_OBSERVED")
	private String fcObserved;

	@Column(name="FILING_SEQUENCE")
	private BigDecimal filingSequence;

	private String flight;

	@Column(name="FLIGHT_MANDATORY")
	private String flightMandatory;

	@Column(name="FOLLOW_GRB")
	private String followGrb;

	@Column(name="FORMULATED_COUNTER")
	private String formulatedCounter;

	@Column(name="FREIGHT_FORWARDER")
	private String freightForwarder;

	private String gl;

	@Column(name="GL_2")
	private String gl2;

	@Column(name="GL_COMPANY")
	private String glCompany;

	@Column(name="GL_COMPANY_2")
	private String glCompany2;

	@Column(name="GL_COMPANY_EXPENSE")
	private String glCompanyExpense;

	@Column(name="GL_COMPANY_TAX")
	private String glCompanyTax;

	@Column(name="GL_COST_CENTER")
	private String glCostCenter;

	@Column(name="GL_COST_CENTER_2")
	private String glCostCenter2;

	@Column(name="GL_COST_CENTER_EXPENSE")
	private String glCostCenterExpense;

	@Column(name="GL_COST_CENTER_TAX")
	private String glCostCenterTax;

	@Column(name="GL_EXPENDITURE")
	private String glExpenditure;

	@Column(name="GL_EXPENDITURE_2")
	private String glExpenditure2;

	@Column(name="GL_EXPENDITURE_EXPENSE")
	private String glExpenditureExpense;

	@Column(name="GL_EXPENDITURE_TAX")
	private String glExpenditureTax;

	@Column(name="GL_EXPENSE")
	private String glExpense;

	@Column(name="GL_TAX")
	private String glTax;

	@Column(name="GROUP_SORT_ORDER")
	private BigDecimal groupSortOrder;

	@Column(name="HIGH_DOLLAR")
	private String highDollar;

	@Column(name="HIGH_DOLLAR_AMOUNT")
	private BigDecimal highDollarAmount;

	private String ifrs;

	private String image;

	private String inspector;

	@Column(name="INSPECTOR_SKILL")
	private String inspectorSkill;

	@Column(name="INSPECTOR_SKILL_LEVEL")
	private String inspectorSkillLevel;

	@Column(name="INVOICEWORKS_CAPABLE")
	private String invoiceworksCapable;

	@Column(name="INVOICEWORKS_CLASS")
	private String invoiceworksClass;

	@Column(name="INVOICEWORKS_UOM")
	private String invoiceworksUom;

	@Column(name="ISSUE_REORDER")
	private String issueReorder;

	@Column(name="LABOR_ONLY")
	private String laborOnly;

	@Column(name="LICENSE_FLAG")
	private String licenseFlag;

	@Column(name="LINK_LOGPAGE_NR")
	private String linkLogpageNr;

	@Column(name="LO_COST_CENTER")
	private String loCostCenter;

	@Column(name="LO_GL")
	private String loGl;

	@Column(name="LO_GL_COMPANY")
	private String loGlCompany;

	@Column(name="LO_GL_EXPENDITURE")
	private String loGlExpenditure;

	@Column(name="LO_RO_GL")
	private String loRoGl;

	@Column(name="LO_RO_GL_COMPANY")
	private String loRoGlCompany;

	@Column(name="LO_RO_GL_COST_CENTER")
	private String loRoGlCostCenter;

	@Column(name="LO_RO_GL_EXPENDITURE")
	private String loRoGlExpenditure;

	@Column(name="LOAD_AT_RECV")
	private String loadAtRecv;

	@Column(name="MAIN_SKILL")
	private String mainSkill;

	private String manufacture;

	private String mechanic;

	@Column(name="MECHANIC_SKILL_LEVEL")
	private String mechanicSkillLevel;

	@Column(name="MEL_CYCLES")
	private BigDecimal melCycles;

	@Column(name="MEL_DAYS")
	private BigDecimal melDays;

	@Column(name="MEL_HOURS")
	private BigDecimal melHours;

	@Column(name="MEL_PRIORITY")
	private String melPriority;

	private String mis;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name="MONTH_DEPRECIATION")
	private BigDecimal monthDepreciation;

	private BigDecimal noi;

	@Column(name="NON_INVENTORY")
	private String nonInventory;

	private BigDecimal notes;

	@Column(name="NR_EXPRESS_AUTH")
	private String nrExpressAuth;

	@Column(name="OPS_RESTRICTION")
	private String opsRestriction;

	@Column(name="OPS_RESTRICTION_DAYS")
	private BigDecimal opsRestrictionDays;

	@Column(name="OPS_SYSTEM_WO_CATEGORY")
	private String opsSystemWoCategory;

	@Column(name="OPS_SYSTEM_WO_PRIORITY_DAYS")
	private BigDecimal opsSystemWoPriorityDays;

	@Column(name="OPS_SYSTEM_WO_PRIORITY_FLAG")
	private String opsSystemWoPriorityFlag;

	@Column(name="OPS_SYSTEM_WO_PRIORITY_VALUE")
	private String opsSystemWoPriorityValue;

	@Column(name="ORDER_VENDOR_CUSTOMER")
	private String orderVendorCustomer;

	@Column(name="ORIGINATING_TASK_CARD")
	private String originatingTaskCard;

	@Column(name="PACKING_GROUP")
	private BigDecimal packingGroup;

	@Column(name="PAPER_REQUIRED")
	private String paperRequired;

	private BigDecimal paragraph;

	@Column(name="PARTS_EST_ARRIVAL_FLAG")
	private String partsEstArrivalFlag;

	private String password;

	@Column(name="PHRASE_FLAG_1")
	private String phraseFlag1;

	@Column(name="PHRASE_FLAG_2")
	private String phraseFlag2;

	@Column(name="PHRASE_FLAG_3")
	private String phraseFlag3;

	@Column(name="PHRRASE_PREFIX_1")
	private String phrrasePrefix1;

	@Column(name="PHRRASE_PREFIX_2")
	private String phrrasePrefix2;

	@Column(name="PHRRASE_PREFIX_3")
	private String phrrasePrefix3;

	@Column(name="PICKLIST_TAG_PRINT")
	private String picklistTagPrint;

	private String planned;

	@Column(name="PN_CATEGORY_INVENTORY_TYPE")
	private String pnCategoryInventoryType;

	@Column(name="PN_CATEGORY_LIST")
	private String pnCategoryList;

	@Column(name="PN_COSTING_METHOD")
	private String pnCostingMethod;

	@Column(name="PN_SELL_METHOD")
	private String pnSellMethod;

	@Column(name="PN_SELL_METHOD_MONTH")
	private BigDecimal pnSellMethodMonth;

	@Column(name="PN_TRANSACTION")
	private String pnTransaction;

	@Column(name="PRIORITY_LEVEL")
	private String priorityLevel;

	@Column(name="PROJECT_LEAD")
	private String projectLead;

	@Column(name="PROVIDED_PART")
	private String providedPart;

	private String radiocative;

	@Column(name="RATING_FLAG")
	private String ratingFlag;

	@Column(name="RECURRENT_ALERT_DEDEFECT_TYPE")
	private String recurrentAlertDedefectType;

	@Column(name="RECURRENT_ITEM_TYPE")
	private String recurrentItemType;

	@Column(name="REMOVAL_TYPE")
	private String removalType;

	private String repair;

	@Column(name="REPAIR_GENERAL")
	private String repairGeneral;

	@Column(name="REPAIR_GSE")
	private String repairGse;

	@Column(name="REPAIR_MAINTENANCE")
	private String repairMaintenance;

	@Column(name="REPLENISHMENT_COST_FACTOR")
	private BigDecimal replenishmentCostFactor;

	@Column(name="REPORTED_BY")
	private String reportedBy;

	@Column(name="RESET_SEQUENCE")
	private BigDecimal resetSequence;

	@Column(name="RESTRICT_TO_EC_AND_TASK_CARD")
	private String restrictToEcAndTaskCard;

	@Column(name="REVENUE_FLIGHT")
	private String revenueFlight;

	@Column(name="RN_EXCHANGE_FEE_PERCENT")
	private BigDecimal rnExchangeFeePercent;

	@Column(name="RO_COST_CENTER")
	private String roCostCenter;

	@Column(name="RO_GL")
	private String roGl;

	@Column(name="RO_GL_2")
	private String roGl2;

	@Column(name="RO_GL_COMPANY")
	private String roGlCompany;

	@Column(name="RO_GL_COMPANY_2")
	private String roGlCompany2;

	@Column(name="RO_GL_COST_CENTER_2")
	private String roGlCostCenter2;

	@Column(name="RO_GL_EXPENDITURE")
	private String roGlExpenditure;

	@Column(name="RO_GL_EXPENDITURE_2")
	private String roGlExpenditure2;

	@Column(name="ROS_ACTION_CODE")
	private String rosActionCode;

	@Column(name="ROS_CLASSIFICATION_CODE")
	private String rosClassificationCode;

	@Column(name="ROS_DISCREPANCY_CODE")
	private String rosDiscrepancyCode;

	@Column(name="ROS_REASON_CODE")
	private String rosReasonCode;

	@Column(name="ROS_SHIPMENT_CARRIER")
	private String rosShipmentCarrier;

	@Column(name="RW_AC")
	private String rwAc;

	@Column(name="SCHED_START_COMPLET_DATE_TIME")
	private String schedStartCompletDateTime;

	@Column(name="SCHEDULE_CONTROL_FLAG")
	private String scheduleControlFlag;

	@Column(name="SCRAP_REORDER")
	private String scrapReorder;

	@Column(name="\"SECTION\"")
	private BigDecimal section;

	@Column(name="SECTION_MANDATORY")
	private String sectionMandatory;

	@Column(name="SELECTED_CATEGORY")
	private String selectedCategory;

	@Column(name="\"SERVICE\"")
	private String service;

	@Column(name="SGM_REFERENCE")
	private String sgmReference;

	@Column(name="SHELF_LIFE_RE_CERTIFICATION")
	private String shelfLifeReCertification;

	@Column(name="SHOW_DEFECTS_ACCP")
	private String showDefectsAccp;

	@Column(name="SHOW_SDR")
	private String showSdr;

	private String skill;

	@Column(name="SO_FEE_PERCENT")
	private BigDecimal soFeePercent;

	private String software;

	@Column(name="SPEC2K_CONDITION")
	private String spec2kCondition;

	@Column(name="SPEC2K_PART_STATUS")
	private String spec2kPartStatus;

	@Column(name="SPEC2K_PRIORITY")
	private String spec2kPriority;

	@Column(name="SPEC2K_TRANSACTION_FIELD")
	private BigDecimal spec2kTransactionField;

	@Column(name="SPEC2K_UOM")
	private String spec2kUom;

	@Column(name="START_DEPRECIATION_DATE")
	private Date startDepreciationDate;

	private String status;

	@Column(name="STEP_TRANSFER_REORDER")
	private String stepTransferReorder;

	@Column(name="SUB_RISK")
	private String subRisk;

	@Column(name="SUB_RISK_2")
	private String subRisk2;

	@Column(name="SUB_RISK_3")
	private String subRisk3;

	private String supplier;

	@Column(name="SUPPLIER_GENERAL")
	private String supplierGeneral;

	@Column(name="SUPPLIER_GSE")
	private String supplierGse;

	@Column(name="SUPPLIER_MAINTENANCE")
	private String supplierMaintenance;

	@Column(name="SWO_REQUISITION")
	private String swoRequisition;

	@Column(name="SYSTEM_CODE_DESCRIPTION")
	private String systemCodeDescription;

	@Column(name="T_PED")
	private String tPed;

	@Column(name="TAG_TYPE")
	private String tagType;

	@Column(name="TAX_RATE")
	private BigDecimal taxRate;

	@Column(name="TEMPORARY_REVISION")
	private String temporaryRevision;

	@Column(name="THIRD_PARTY_WO")
	private String thirdPartyWo;

	@Column(name="TOOL_LIFE_RE_CERTIFICATION")
	private String toolLifeReCertification;

	@Column(name="TRANSFER_ORDER_REORDER")
	private String transferOrderReorder;

	@Column(name="UNIT_TYPE_ENG_APU_INDICATOR")
	private String unitTypeEngApuIndicator;

	@Column(name="UPPER_LIMIT")
	private BigDecimal upperLimit;

	private String url;

	@Column(name="USE_GL_2")
	private String useGl2;

	private String username;

	@Column(name="VACATION_CATEGORY")
	private String vacationCategory;

	@Column(name="VB_LIFE_LIMIT")
	private String vbLifeLimit;

	@Column(name="VB_LIFE_LIMIT_PN_CONTROL")
	private String vbLifeLimitPnControl;

	@Column(name="VENDOR_STATUS")
	private String vendorStatus;

	@Column(name="VOP_BASE_ON_CONDITION")
	private BigDecimal vopBaseOnCondition;

	@Column(name="WC_MINIMUN_PERCENTAGE")
	private BigDecimal wcMinimunPercentage;

	@Column(name="WIP_ACTIVE")
	private String wipActive;

	@Column(name="WO_COMPLETION_MIN")
	private BigDecimal woCompletionMin;

	@Column(name="WO_DURATION")
	private BigDecimal woDuration;

	@Column(name="WO_EXPIRATION_MIN")
	private BigDecimal woExpirationMin;

	@Column(name="WO_INI_START_MIN")
	private BigDecimal woIniStartMin;

	@Column(name="WO_LINK_FLIGHT")
	private String woLinkFlight;

	@Column(name="WO_PRIORITY")
	private String woPriority;

	@Column(name="WO_WIP_JOB_MIN")
	private BigDecimal woWipJobMin;

	@Column(name="XO_EXCHANGE_FEE_PERCENT")
	private BigDecimal xoExchangeFeePercent;

	public SystemTranCode() {
	}

	public SystemTranCodePK getId() {
		return this.id;
	}

	public void setId(SystemTranCodePK id) {
		this.id = id;
	}

	public String getAcMandatory() {
		return this.acMandatory;
	}

	public void setAcMandatory(String acMandatory) {
		this.acMandatory = acMandatory;
	}

	public String getAcMandatoryEx() {
		return this.acMandatoryEx;
	}

	public void setAcMandatoryEx(String acMandatoryEx) {
		this.acMandatoryEx = acMandatoryEx;
	}

	public String getAcMandatoryLo() {
		return this.acMandatoryLo;
	}

	public void setAcMandatoryLo(String acMandatoryLo) {
		this.acMandatoryLo = acMandatoryLo;
	}

	public String getAcMandatoryPo() {
		return this.acMandatoryPo;
	}

	public void setAcMandatoryPo(String acMandatoryPo) {
		this.acMandatoryPo = acMandatoryPo;
	}

	public String getAcMandatoryRo() {
		return this.acMandatoryRo;
	}

	public void setAcMandatoryRo(String acMandatoryRo) {
		this.acMandatoryRo = acMandatoryRo;
	}

	public String getAcMandatorySv() {
		return this.acMandatorySv;
	}

	public void setAcMandatorySv(String acMandatorySv) {
		this.acMandatorySv = acMandatorySv;
	}

	public String getAcRestriction() {
		return this.acRestriction;
	}

	public void setAcRestriction(String acRestriction) {
		this.acRestriction = acRestriction;
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

	public String getAccFlag() {
		return this.accFlag;
	}

	public void setAccFlag(String accFlag) {
		this.accFlag = accFlag;
	}

	public BigDecimal getActionPriority() {
		return this.actionPriority;
	}

	public void setActionPriority(BigDecimal actionPriority) {
		this.actionPriority = actionPriority;
	}

	public String getAdCategory() {
		return this.adCategory;
	}

	public void setAdCategory(String adCategory) {
		this.adCategory = adCategory;
	}

	public BigDecimal getAlertCategoryNoOf() {
		return this.alertCategoryNoOf;
	}

	public void setAlertCategoryNoOf(BigDecimal alertCategoryNoOf) {
		this.alertCategoryNoOf = alertCategoryNoOf;
	}

	public BigDecimal getAlertNoOfRemoval() {
		return this.alertNoOfRemoval;
	}

	public void setAlertNoOfRemoval(BigDecimal alertNoOfRemoval) {
		this.alertNoOfRemoval = alertNoOfRemoval;
	}

	public String getAllowAdditionalTaskCard() {
		return this.allowAdditionalTaskCard;
	}

	public void setAllowAdditionalTaskCard(String allowAdditionalTaskCard) {
		this.allowAdditionalTaskCard = allowAdditionalTaskCard;
	}

	public String getAllowBust() {
		return this.allowBust;
	}

	public void setAllowBust(String allowBust) {
		this.allowBust = allowBust;
	}

	public String getAllowDecimal() {
		return this.allowDecimal;
	}

	public void setAllowDecimal(String allowDecimal) {
		this.allowDecimal = allowDecimal;
	}

	public String getAllowNonRoutineTaskCard() {
		return this.allowNonRoutineTaskCard;
	}

	public void setAllowNonRoutineTaskCard(String allowNonRoutineTaskCard) {
		this.allowNonRoutineTaskCard = allowNonRoutineTaskCard;
	}

	public String getApproval() {
		return this.approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getApprovalStatement1() {
		return this.approvalStatement1;
	}

	public void setApprovalStatement1(String approvalStatement1) {
		this.approvalStatement1 = approvalStatement1;
	}

	public String getApprovalStatement2() {
		return this.approvalStatement2;
	}

	public void setApprovalStatement2(String approvalStatement2) {
		this.approvalStatement2 = approvalStatement2;
	}

	public String getAuthorizationRequired() {
		return this.authorizationRequired;
	}

	public void setAuthorizationRequired(String authorizationRequired) {
		this.authorizationRequired = authorizationRequired;
	}

	public String getAutoPopulateDefect() {
		return this.autoPopulateDefect;
	}

	public void setAutoPopulateDefect(String autoPopulateDefect) {
		this.autoPopulateDefect = autoPopulateDefect;
	}

	public String getAutoResetOnRo() {
		return this.autoResetOnRo;
	}

	public void setAutoResetOnRo(String autoResetOnRo) {
		this.autoResetOnRo = autoResetOnRo;
	}

	public String getAutoSeqEc() {
		return this.autoSeqEc;
	}

	public void setAutoSeqEc(String autoSeqEc) {
		this.autoSeqEc = autoSeqEc;
	}

	public String getAutoSnPrefix() {
		return this.autoSnPrefix;
	}

	public void setAutoSnPrefix(String autoSnPrefix) {
		this.autoSnPrefix = autoSnPrefix;
	}

	public String getAvlVol() {
		return this.avlVol;
	}

	public void setAvlVol(String avlVol) {
		this.avlVol = avlVol;
	}

	public String getBinCountFrequency() {
		return this.binCountFrequency;
	}

	public void setBinCountFrequency(String binCountFrequency) {
		this.binCountFrequency = binCountFrequency;
	}

	public String getBinCountNoWeekend() {
		return this.binCountNoWeekend;
	}

	public void setBinCountNoWeekend(String binCountNoWeekend) {
		this.binCountNoWeekend = binCountNoWeekend;
	}

	public BigDecimal getBlobNo() {
		return this.blobNo;
	}

	public void setBlobNo(BigDecimal blobNo) {
		this.blobNo = blobNo;
	}

	public String getCabin() {
		return this.cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getCapitalCostCenter() {
		return this.capitalCostCenter;
	}

	public void setCapitalCostCenter(String capitalCostCenter) {
		this.capitalCostCenter = capitalCostCenter;
	}

	public String getCapitalExpenditureMandatory() {
		return this.capitalExpenditureMandatory;
	}

	public void setCapitalExpenditureMandatory(String capitalExpenditureMandatory) {
		this.capitalExpenditureMandatory = capitalExpenditureMandatory;
	}

	public BigDecimal getCarryingCostFactor() {
		return this.carryingCostFactor;
	}

	public void setCarryingCostFactor(BigDecimal carryingCostFactor) {
		this.carryingCostFactor = carryingCostFactor;
	}

	public String getCcReference() {
		return this.ccReference;
	}

	public void setCcReference(String ccReference) {
		this.ccReference = ccReference;
	}

	public String getChangeSchedule() {
		return this.changeSchedule;
	}

	public void setChangeSchedule(String changeSchedule) {
		this.changeSchedule = changeSchedule;
	}

	public BigDecimal getChapter() {
		return this.chapter;
	}

	public void setChapter(BigDecimal chapter) {
		this.chapter = chapter;
	}

	public String getChapterMandatory() {
		return this.chapterMandatory;
	}

	public void setChapterMandatory(String chapterMandatory) {
		this.chapterMandatory = chapterMandatory;
	}

	public String getChargeable() {
		return this.chargeable;
	}

	public void setChargeable(String chargeable) {
		this.chargeable = chargeable;
	}

	public String getCheckoutReorder() {
		return this.checkoutReorder;
	}

	public void setCheckoutReorder(String checkoutReorder) {
		this.checkoutReorder = checkoutReorder;
	}

	public BigDecimal getChemicalGuideBlob() {
		return this.chemicalGuideBlob;
	}

	public void setChemicalGuideBlob(BigDecimal chemicalGuideBlob) {
		this.chemicalGuideBlob = chemicalGuideBlob;
	}

	public String getChemicalGuideSheet() {
		return this.chemicalGuideSheet;
	}

	public void setChemicalGuideSheet(String chemicalGuideSheet) {
		this.chemicalGuideSheet = chemicalGuideSheet;
	}

	public String getClassDivisionNo() {
		return this.classDivisionNo;
	}

	public void setClassDivisionNo(String classDivisionNo) {
		this.classDivisionNo = classDivisionNo;
	}

	public String getCloseDefect() {
		return this.closeDefect;
	}

	public void setCloseDefect(String closeDefect) {
		this.closeDefect = closeDefect;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeColor() {
		return this.codeColor;
	}

	public void setCodeColor(String codeColor) {
		this.codeColor = codeColor;
	}

	public String getCodifiedDefect() {
		return this.codifiedDefect;
	}

	public void setCodifiedDefect(String codifiedDefect) {
		this.codifiedDefect = codifiedDefect;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getConfigDate() {
		return this.configDate;
	}

	public void setConfigDate(Date configDate) {
		this.configDate = configDate;
	}

	public String getConfigFlag() {
		return this.configFlag;
	}

	public void setConfigFlag(String configFlag) {
		this.configFlag = configFlag;
	}

	public BigDecimal getConfigNumber() {
		return this.configNumber;
	}

	public void setConfigNumber(BigDecimal configNumber) {
		this.configNumber = configNumber;
	}

	public String getConfigOther() {
		return this.configOther;
	}

	public void setConfigOther(String configOther) {
		this.configOther = configOther;
	}

	public String getConsumableKit() {
		return this.consumableKit;
	}

	public void setConsumableKit(String consumableKit) {
		this.consumableKit = consumableKit;
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

	public BigDecimal getCurrencyExchange() {
		return this.currencyExchange;
	}

	public void setCurrencyExchange(BigDecimal currencyExchange) {
		this.currencyExchange = currencyExchange;
	}

	public String getCycleCountByCategory() {
		return this.cycleCountByCategory;
	}

	public void setCycleCountByCategory(String cycleCountByCategory) {
		this.cycleCountByCategory = cycleCountByCategory;
	}

	public BigDecimal getCycleCountByCostFrom() {
		return this.cycleCountByCostFrom;
	}

	public void setCycleCountByCostFrom(BigDecimal cycleCountByCostFrom) {
		this.cycleCountByCostFrom = cycleCountByCostFrom;
	}

	public BigDecimal getCycleCountByCostTo() {
		return this.cycleCountByCostTo;
	}

	public void setCycleCountByCostTo(BigDecimal cycleCountByCostTo) {
		this.cycleCountByCostTo = cycleCountByCostTo;
	}

	public String getCycleCountByEssentiality() {
		return this.cycleCountByEssentiality;
	}

	public void setCycleCountByEssentiality(String cycleCountByEssentiality) {
		this.cycleCountByEssentiality = cycleCountByEssentiality;
	}

	public BigDecimal getCycleCountByUsageFrom() {
		return this.cycleCountByUsageFrom;
	}

	public void setCycleCountByUsageFrom(BigDecimal cycleCountByUsageFrom) {
		this.cycleCountByUsageFrom = cycleCountByUsageFrom;
	}

	public BigDecimal getCycleCountByUsageTo() {
		return this.cycleCountByUsageTo;
	}

	public void setCycleCountByUsageTo(BigDecimal cycleCountByUsageTo) {
		this.cycleCountByUsageTo = cycleCountByUsageTo;
	}

	public BigDecimal getCycleCountDays() {
		return this.cycleCountDays;
	}

	public void setCycleCountDays(BigDecimal cycleCountDays) {
		this.cycleCountDays = cycleCountDays;
	}

	public String getDefaultAuthorizationRouting() {
		return this.defaultAuthorizationRouting;
	}

	public void setDefaultAuthorizationRouting(String defaultAuthorizationRouting) {
		this.defaultAuthorizationRouting = defaultAuthorizationRouting;
	}

	public String getDefaultReliabilityMonitored() {
		return this.defaultReliabilityMonitored;
	}

	public void setDefaultReliabilityMonitored(String defaultReliabilityMonitored) {
		this.defaultReliabilityMonitored = defaultReliabilityMonitored;
	}

	public String getDefaultShipLocation() {
		return this.defaultShipLocation;
	}

	public void setDefaultShipLocation(String defaultShipLocation) {
		this.defaultShipLocation = defaultShipLocation;
	}

	public String getDefaultSkill() {
		return this.defaultSkill;
	}

	public void setDefaultSkill(String defaultSkill) {
		this.defaultSkill = defaultSkill;
	}

	public String getDefectReportMarep() {
		return this.defectReportMarep;
	}

	public void setDefectReportMarep(String defectReportMarep) {
		this.defectReportMarep = defectReportMarep;
	}

	public String getDefectReportPilot() {
		return this.defectReportPilot;
	}

	public void setDefectReportPilot(String defectReportPilot) {
		this.defectReportPilot = defectReportPilot;
	}

	public String getDeferCatAutoMddrClosing() {
		return this.deferCatAutoMddrClosing;
	}

	public void setDeferCatAutoMddrClosing(String deferCatAutoMddrClosing) {
		this.deferCatAutoMddrClosing = deferCatAutoMddrClosing;
	}

	public String getDeferResolveMandatory() {
		return this.deferResolveMandatory;
	}

	public void setDeferResolveMandatory(String deferResolveMandatory) {
		this.deferResolveMandatory = deferResolveMandatory;
	}

	public String getDepreciationGl() {
		return this.depreciationGl;
	}

	public void setDepreciationGl(String depreciationGl) {
		this.depreciationGl = depreciationGl;
	}

	public String getDepreciationGlCompany() {
		return this.depreciationGlCompany;
	}

	public void setDepreciationGlCompany(String depreciationGlCompany) {
		this.depreciationGlCompany = depreciationGlCompany;
	}

	public String getDepreciationGlCostCenter() {
		return this.depreciationGlCostCenter;
	}

	public void setDepreciationGlCostCenter(String depreciationGlCostCenter) {
		this.depreciationGlCostCenter = depreciationGlCostCenter;
	}

	public String getDepreciationGlExpenditure() {
		return this.depreciationGlExpenditure;
	}

	public void setDepreciationGlExpenditure(String depreciationGlExpenditure) {
		this.depreciationGlExpenditure = depreciationGlExpenditure;
	}

	public String getDisableDeferAfterSaved() {
		return this.disableDeferAfterSaved;
	}

	public void setDisableDeferAfterSaved(String disableDeferAfterSaved) {
		this.disableDeferAfterSaved = disableDeferAfterSaved;
	}

	public String getDispatchSystemAdvise() {
		return this.dispatchSystemAdvise;
	}

	public void setDispatchSystemAdvise(String dispatchSystemAdvise) {
		this.dispatchSystemAdvise = dispatchSystemAdvise;
	}

	public String getDispatchSystemConfidence() {
		return this.dispatchSystemConfidence;
	}

	public void setDispatchSystemConfidence(String dispatchSystemConfidence) {
		this.dispatchSystemConfidence = dispatchSystemConfidence;
	}

	public String getDispatchSystemDisable() {
		return this.dispatchSystemDisable;
	}

	public void setDispatchSystemDisable(String dispatchSystemDisable) {
		this.dispatchSystemDisable = dispatchSystemDisable;
	}

	public String getDispatchSystemPlannedFlag() {
		return this.dispatchSystemPlannedFlag;
	}

	public void setDispatchSystemPlannedFlag(String dispatchSystemPlannedFlag) {
		this.dispatchSystemPlannedFlag = dispatchSystemPlannedFlag;
	}

	public String getDispatchSystemPriority() {
		return this.dispatchSystemPriority;
	}

	public void setDispatchSystemPriority(String dispatchSystemPriority) {
		this.dispatchSystemPriority = dispatchSystemPriority;
	}

	public String getDoNotAllowConsession() {
		return this.doNotAllowConsession;
	}

	public void setDoNotAllowConsession(String doNotAllowConsession) {
		this.doNotAllowConsession = doNotAllowConsession;
	}

	public String getDoNotAllowHazmat() {
		return this.doNotAllowHazmat;
	}

	public void setDoNotAllowHazmat(String doNotAllowHazmat) {
		this.doNotAllowHazmat = doNotAllowHazmat;
	}

	public String getDoNotAllowReset() {
		return this.doNotAllowReset;
	}

	public void setDoNotAllowReset(String doNotAllowReset) {
		this.doNotAllowReset = doNotAllowReset;
	}

	public String getDoNotAllowResetProd() {
		return this.doNotAllowResetProd;
	}

	public void setDoNotAllowResetProd(String doNotAllowResetProd) {
		this.doNotAllowResetProd = doNotAllowResetProd;
	}

	public String getDualInspectorSkill() {
		return this.dualInspectorSkill;
	}

	public void setDualInspectorSkill(String dualInspectorSkill) {
		this.dualInspectorSkill = dualInspectorSkill;
	}

	public String getDuplicateInspection() {
		return this.duplicateInspection;
	}

	public void setDuplicateInspection(String duplicateInspection) {
		this.duplicateInspection = duplicateInspection;
	}

	public String getEcCategory() {
		return this.ecCategory;
	}

	public void setEcCategory(String ecCategory) {
		this.ecCategory = ecCategory;
	}

	public String getEcClassificationMandatory() {
		return this.ecClassificationMandatory;
	}

	public void setEcClassificationMandatory(String ecClassificationMandatory) {
		this.ecClassificationMandatory = ecClassificationMandatory;
	}

	public String getEcNameOverride() {
		return this.ecNameOverride;
	}

	public void setEcNameOverride(String ecNameOverride) {
		this.ecNameOverride = ecNameOverride;
	}

	public String getEditingLevel() {
		return this.editingLevel;
	}

	public void setEditingLevel(String editingLevel) {
		this.editingLevel = editingLevel;
	}

	public String getEquipmentRefDesignator() {
		return this.equipmentRefDesignator;
	}

	public void setEquipmentRefDesignator(String equipmentRefDesignator) {
		this.equipmentRefDesignator = equipmentRefDesignator;
	}

	public String getEssentialityCodeLevel() {
		return this.essentialityCodeLevel;
	}

	public void setEssentialityCodeLevel(String essentialityCodeLevel) {
		this.essentialityCodeLevel = essentialityCodeLevel;
	}

	public String getEtops() {
		return this.etops;
	}

	public void setEtops(String etops) {
		this.etops = etops;
	}

	public String getExCostCenter() {
		return this.exCostCenter;
	}

	public void setExCostCenter(String exCostCenter) {
		this.exCostCenter = exCostCenter;
	}

	public String getExGl() {
		return this.exGl;
	}

	public void setExGl(String exGl) {
		this.exGl = exGl;
	}

	public String getExGlCompany() {
		return this.exGlCompany;
	}

	public void setExGlCompany(String exGlCompany) {
		this.exGlCompany = exGlCompany;
	}

	public String getExGlExpenditure() {
		return this.exGlExpenditure;
	}

	public void setExGlExpenditure(String exGlExpenditure) {
		this.exGlExpenditure = exGlExpenditure;
	}

	public String getExRoGl() {
		return this.exRoGl;
	}

	public void setExRoGl(String exRoGl) {
		this.exRoGl = exRoGl;
	}

	public String getExRoGlCompany() {
		return this.exRoGlCompany;
	}

	public void setExRoGlCompany(String exRoGlCompany) {
		this.exRoGlCompany = exRoGlCompany;
	}

	public String getExRoGlCostCenter() {
		return this.exRoGlCostCenter;
	}

	public void setExRoGlCostCenter(String exRoGlCostCenter) {
		this.exRoGlCostCenter = exRoGlCostCenter;
	}

	public String getExRoGlExpenditure() {
		return this.exRoGlExpenditure;
	}

	public void setExRoGlExpenditure(String exRoGlExpenditure) {
		this.exRoGlExpenditure = exRoGlExpenditure;
	}

	public String getExpediteType() {
		return this.expediteType;
	}

	public void setExpediteType(String expediteType) {
		this.expediteType = expediteType;
	}

	public String getExpenditure() {
		return this.expenditure;
	}

	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}

	public BigDecimal getExpirationDays() {
		return this.expirationDays;
	}

	public void setExpirationDays(BigDecimal expirationDays) {
		this.expirationDays = expirationDays;
	}

	public String getFaDepreciationCode() {
		return this.faDepreciationCode;
	}

	public void setFaDepreciationCode(String faDepreciationCode) {
		this.faDepreciationCode = faDepreciationCode;
	}

	public BigDecimal getFaResidualValuePercent() {
		return this.faResidualValuePercent;
	}

	public void setFaResidualValuePercent(BigDecimal faResidualValuePercent) {
		this.faResidualValuePercent = faResidualValuePercent;
	}

	public String getFcObserved() {
		return this.fcObserved;
	}

	public void setFcObserved(String fcObserved) {
		this.fcObserved = fcObserved;
	}

	public BigDecimal getFilingSequence() {
		return this.filingSequence;
	}

	public void setFilingSequence(BigDecimal filingSequence) {
		this.filingSequence = filingSequence;
	}

	public String getFlight() {
		return this.flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getFlightMandatory() {
		return this.flightMandatory;
	}

	public void setFlightMandatory(String flightMandatory) {
		this.flightMandatory = flightMandatory;
	}

	public String getFollowGrb() {
		return this.followGrb;
	}

	public void setFollowGrb(String followGrb) {
		this.followGrb = followGrb;
	}

	public String getFormulatedCounter() {
		return this.formulatedCounter;
	}

	public void setFormulatedCounter(String formulatedCounter) {
		this.formulatedCounter = formulatedCounter;
	}

	public String getFreightForwarder() {
		return this.freightForwarder;
	}

	public void setFreightForwarder(String freightForwarder) {
		this.freightForwarder = freightForwarder;
	}

	public String getGl() {
		return this.gl;
	}

	public void setGl(String gl) {
		this.gl = gl;
	}

	public String getGl2() {
		return this.gl2;
	}

	public void setGl2(String gl2) {
		this.gl2 = gl2;
	}

	public String getGlCompany() {
		return this.glCompany;
	}

	public void setGlCompany(String glCompany) {
		this.glCompany = glCompany;
	}

	public String getGlCompany2() {
		return this.glCompany2;
	}

	public void setGlCompany2(String glCompany2) {
		this.glCompany2 = glCompany2;
	}

	public String getGlCompanyExpense() {
		return this.glCompanyExpense;
	}

	public void setGlCompanyExpense(String glCompanyExpense) {
		this.glCompanyExpense = glCompanyExpense;
	}

	public String getGlCompanyTax() {
		return this.glCompanyTax;
	}

	public void setGlCompanyTax(String glCompanyTax) {
		this.glCompanyTax = glCompanyTax;
	}

	public String getGlCostCenter() {
		return this.glCostCenter;
	}

	public void setGlCostCenter(String glCostCenter) {
		this.glCostCenter = glCostCenter;
	}

	public String getGlCostCenter2() {
		return this.glCostCenter2;
	}

	public void setGlCostCenter2(String glCostCenter2) {
		this.glCostCenter2 = glCostCenter2;
	}

	public String getGlCostCenterExpense() {
		return this.glCostCenterExpense;
	}

	public void setGlCostCenterExpense(String glCostCenterExpense) {
		this.glCostCenterExpense = glCostCenterExpense;
	}

	public String getGlCostCenterTax() {
		return this.glCostCenterTax;
	}

	public void setGlCostCenterTax(String glCostCenterTax) {
		this.glCostCenterTax = glCostCenterTax;
	}

	public String getGlExpenditure() {
		return this.glExpenditure;
	}

	public void setGlExpenditure(String glExpenditure) {
		this.glExpenditure = glExpenditure;
	}

	public String getGlExpenditure2() {
		return this.glExpenditure2;
	}

	public void setGlExpenditure2(String glExpenditure2) {
		this.glExpenditure2 = glExpenditure2;
	}

	public String getGlExpenditureExpense() {
		return this.glExpenditureExpense;
	}

	public void setGlExpenditureExpense(String glExpenditureExpense) {
		this.glExpenditureExpense = glExpenditureExpense;
	}

	public String getGlExpenditureTax() {
		return this.glExpenditureTax;
	}

	public void setGlExpenditureTax(String glExpenditureTax) {
		this.glExpenditureTax = glExpenditureTax;
	}

	public String getGlExpense() {
		return this.glExpense;
	}

	public void setGlExpense(String glExpense) {
		this.glExpense = glExpense;
	}

	public String getGlTax() {
		return this.glTax;
	}

	public void setGlTax(String glTax) {
		this.glTax = glTax;
	}

	public BigDecimal getGroupSortOrder() {
		return this.groupSortOrder;
	}

	public void setGroupSortOrder(BigDecimal groupSortOrder) {
		this.groupSortOrder = groupSortOrder;
	}

	public String getHighDollar() {
		return this.highDollar;
	}

	public void setHighDollar(String highDollar) {
		this.highDollar = highDollar;
	}

	public BigDecimal getHighDollarAmount() {
		return this.highDollarAmount;
	}

	public void setHighDollarAmount(BigDecimal highDollarAmount) {
		this.highDollarAmount = highDollarAmount;
	}

	public String getIfrs() {
		return this.ifrs;
	}

	public void setIfrs(String ifrs) {
		this.ifrs = ifrs;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInspector() {
		return this.inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getInspectorSkill() {
		return this.inspectorSkill;
	}

	public void setInspectorSkill(String inspectorSkill) {
		this.inspectorSkill = inspectorSkill;
	}

	public String getInspectorSkillLevel() {
		return this.inspectorSkillLevel;
	}

	public void setInspectorSkillLevel(String inspectorSkillLevel) {
		this.inspectorSkillLevel = inspectorSkillLevel;
	}

	public String getInvoiceworksCapable() {
		return this.invoiceworksCapable;
	}

	public void setInvoiceworksCapable(String invoiceworksCapable) {
		this.invoiceworksCapable = invoiceworksCapable;
	}

	public String getInvoiceworksClass() {
		return this.invoiceworksClass;
	}

	public void setInvoiceworksClass(String invoiceworksClass) {
		this.invoiceworksClass = invoiceworksClass;
	}

	public String getInvoiceworksUom() {
		return this.invoiceworksUom;
	}

	public void setInvoiceworksUom(String invoiceworksUom) {
		this.invoiceworksUom = invoiceworksUom;
	}

	public String getIssueReorder() {
		return this.issueReorder;
	}

	public void setIssueReorder(String issueReorder) {
		this.issueReorder = issueReorder;
	}

	public String getLaborOnly() {
		return this.laborOnly;
	}

	public void setLaborOnly(String laborOnly) {
		this.laborOnly = laborOnly;
	}

	public String getLicenseFlag() {
		return this.licenseFlag;
	}

	public void setLicenseFlag(String licenseFlag) {
		this.licenseFlag = licenseFlag;
	}

	public String getLinkLogpageNr() {
		return this.linkLogpageNr;
	}

	public void setLinkLogpageNr(String linkLogpageNr) {
		this.linkLogpageNr = linkLogpageNr;
	}

	public String getLoCostCenter() {
		return this.loCostCenter;
	}

	public void setLoCostCenter(String loCostCenter) {
		this.loCostCenter = loCostCenter;
	}

	public String getLoGl() {
		return this.loGl;
	}

	public void setLoGl(String loGl) {
		this.loGl = loGl;
	}

	public String getLoGlCompany() {
		return this.loGlCompany;
	}

	public void setLoGlCompany(String loGlCompany) {
		this.loGlCompany = loGlCompany;
	}

	public String getLoGlExpenditure() {
		return this.loGlExpenditure;
	}

	public void setLoGlExpenditure(String loGlExpenditure) {
		this.loGlExpenditure = loGlExpenditure;
	}

	public String getLoRoGl() {
		return this.loRoGl;
	}

	public void setLoRoGl(String loRoGl) {
		this.loRoGl = loRoGl;
	}

	public String getLoRoGlCompany() {
		return this.loRoGlCompany;
	}

	public void setLoRoGlCompany(String loRoGlCompany) {
		this.loRoGlCompany = loRoGlCompany;
	}

	public String getLoRoGlCostCenter() {
		return this.loRoGlCostCenter;
	}

	public void setLoRoGlCostCenter(String loRoGlCostCenter) {
		this.loRoGlCostCenter = loRoGlCostCenter;
	}

	public String getLoRoGlExpenditure() {
		return this.loRoGlExpenditure;
	}

	public void setLoRoGlExpenditure(String loRoGlExpenditure) {
		this.loRoGlExpenditure = loRoGlExpenditure;
	}

	public String getLoadAtRecv() {
		return this.loadAtRecv;
	}

	public void setLoadAtRecv(String loadAtRecv) {
		this.loadAtRecv = loadAtRecv;
	}

	public String getMainSkill() {
		return this.mainSkill;
	}

	public void setMainSkill(String mainSkill) {
		this.mainSkill = mainSkill;
	}

	public String getManufacture() {
		return this.manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getMechanic() {
		return this.mechanic;
	}

	public void setMechanic(String mechanic) {
		this.mechanic = mechanic;
	}

	public String getMechanicSkillLevel() {
		return this.mechanicSkillLevel;
	}

	public void setMechanicSkillLevel(String mechanicSkillLevel) {
		this.mechanicSkillLevel = mechanicSkillLevel;
	}

	public BigDecimal getMelCycles() {
		return this.melCycles;
	}

	public void setMelCycles(BigDecimal melCycles) {
		this.melCycles = melCycles;
	}

	public BigDecimal getMelDays() {
		return this.melDays;
	}

	public void setMelDays(BigDecimal melDays) {
		this.melDays = melDays;
	}

	public BigDecimal getMelHours() {
		return this.melHours;
	}

	public void setMelHours(BigDecimal melHours) {
		this.melHours = melHours;
	}

	public String getMelPriority() {
		return this.melPriority;
	}

	public void setMelPriority(String melPriority) {
		this.melPriority = melPriority;
	}

	public String getMis() {
		return this.mis;
	}

	public void setMis(String mis) {
		this.mis = mis;
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

	public BigDecimal getMonthDepreciation() {
		return this.monthDepreciation;
	}

	public void setMonthDepreciation(BigDecimal monthDepreciation) {
		this.monthDepreciation = monthDepreciation;
	}

	public BigDecimal getNoi() {
		return this.noi;
	}

	public void setNoi(BigDecimal noi) {
		this.noi = noi;
	}

	public String getNonInventory() {
		return this.nonInventory;
	}

	public void setNonInventory(String nonInventory) {
		this.nonInventory = nonInventory;
	}

	public BigDecimal getNotes() {
		return this.notes;
	}

	public void setNotes(BigDecimal notes) {
		this.notes = notes;
	}

	public String getNrExpressAuth() {
		return this.nrExpressAuth;
	}

	public void setNrExpressAuth(String nrExpressAuth) {
		this.nrExpressAuth = nrExpressAuth;
	}

	public String getOpsRestriction() {
		return this.opsRestriction;
	}

	public void setOpsRestriction(String opsRestriction) {
		this.opsRestriction = opsRestriction;
	}

	public BigDecimal getOpsRestrictionDays() {
		return this.opsRestrictionDays;
	}

	public void setOpsRestrictionDays(BigDecimal opsRestrictionDays) {
		this.opsRestrictionDays = opsRestrictionDays;
	}

	public String getOpsSystemWoCategory() {
		return this.opsSystemWoCategory;
	}

	public void setOpsSystemWoCategory(String opsSystemWoCategory) {
		this.opsSystemWoCategory = opsSystemWoCategory;
	}

	public BigDecimal getOpsSystemWoPriorityDays() {
		return this.opsSystemWoPriorityDays;
	}

	public void setOpsSystemWoPriorityDays(BigDecimal opsSystemWoPriorityDays) {
		this.opsSystemWoPriorityDays = opsSystemWoPriorityDays;
	}

	public String getOpsSystemWoPriorityFlag() {
		return this.opsSystemWoPriorityFlag;
	}

	public void setOpsSystemWoPriorityFlag(String opsSystemWoPriorityFlag) {
		this.opsSystemWoPriorityFlag = opsSystemWoPriorityFlag;
	}

	public String getOpsSystemWoPriorityValue() {
		return this.opsSystemWoPriorityValue;
	}

	public void setOpsSystemWoPriorityValue(String opsSystemWoPriorityValue) {
		this.opsSystemWoPriorityValue = opsSystemWoPriorityValue;
	}

	public String getOrderVendorCustomer() {
		return this.orderVendorCustomer;
	}

	public void setOrderVendorCustomer(String orderVendorCustomer) {
		this.orderVendorCustomer = orderVendorCustomer;
	}

	public String getOriginatingTaskCard() {
		return this.originatingTaskCard;
	}

	public void setOriginatingTaskCard(String originatingTaskCard) {
		this.originatingTaskCard = originatingTaskCard;
	}

	public BigDecimal getPackingGroup() {
		return this.packingGroup;
	}

	public void setPackingGroup(BigDecimal packingGroup) {
		this.packingGroup = packingGroup;
	}

	public String getPaperRequired() {
		return this.paperRequired;
	}

	public void setPaperRequired(String paperRequired) {
		this.paperRequired = paperRequired;
	}

	public BigDecimal getParagraph() {
		return this.paragraph;
	}

	public void setParagraph(BigDecimal paragraph) {
		this.paragraph = paragraph;
	}

	public String getPartsEstArrivalFlag() {
		return this.partsEstArrivalFlag;
	}

	public void setPartsEstArrivalFlag(String partsEstArrivalFlag) {
		this.partsEstArrivalFlag = partsEstArrivalFlag;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhraseFlag1() {
		return this.phraseFlag1;
	}

	public void setPhraseFlag1(String phraseFlag1) {
		this.phraseFlag1 = phraseFlag1;
	}

	public String getPhraseFlag2() {
		return this.phraseFlag2;
	}

	public void setPhraseFlag2(String phraseFlag2) {
		this.phraseFlag2 = phraseFlag2;
	}

	public String getPhraseFlag3() {
		return this.phraseFlag3;
	}

	public void setPhraseFlag3(String phraseFlag3) {
		this.phraseFlag3 = phraseFlag3;
	}

	public String getPhrrasePrefix1() {
		return this.phrrasePrefix1;
	}

	public void setPhrrasePrefix1(String phrrasePrefix1) {
		this.phrrasePrefix1 = phrrasePrefix1;
	}

	public String getPhrrasePrefix2() {
		return this.phrrasePrefix2;
	}

	public void setPhrrasePrefix2(String phrrasePrefix2) {
		this.phrrasePrefix2 = phrrasePrefix2;
	}

	public String getPhrrasePrefix3() {
		return this.phrrasePrefix3;
	}

	public void setPhrrasePrefix3(String phrrasePrefix3) {
		this.phrrasePrefix3 = phrrasePrefix3;
	}

	public String getPicklistTagPrint() {
		return this.picklistTagPrint;
	}

	public void setPicklistTagPrint(String picklistTagPrint) {
		this.picklistTagPrint = picklistTagPrint;
	}

	public String getPlanned() {
		return this.planned;
	}

	public void setPlanned(String planned) {
		this.planned = planned;
	}

	public String getPnCategoryInventoryType() {
		return this.pnCategoryInventoryType;
	}

	public void setPnCategoryInventoryType(String pnCategoryInventoryType) {
		this.pnCategoryInventoryType = pnCategoryInventoryType;
	}

	public String getPnCategoryList() {
		return this.pnCategoryList;
	}

	public void setPnCategoryList(String pnCategoryList) {
		this.pnCategoryList = pnCategoryList;
	}

	public String getPnCostingMethod() {
		return this.pnCostingMethod;
	}

	public void setPnCostingMethod(String pnCostingMethod) {
		this.pnCostingMethod = pnCostingMethod;
	}

	public String getPnSellMethod() {
		return this.pnSellMethod;
	}

	public void setPnSellMethod(String pnSellMethod) {
		this.pnSellMethod = pnSellMethod;
	}

	public BigDecimal getPnSellMethodMonth() {
		return this.pnSellMethodMonth;
	}

	public void setPnSellMethodMonth(BigDecimal pnSellMethodMonth) {
		this.pnSellMethodMonth = pnSellMethodMonth;
	}

	public String getPnTransaction() {
		return this.pnTransaction;
	}

	public void setPnTransaction(String pnTransaction) {
		this.pnTransaction = pnTransaction;
	}

	public String getPriorityLevel() {
		return this.priorityLevel;
	}

	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public String getProjectLead() {
		return this.projectLead;
	}

	public void setProjectLead(String projectLead) {
		this.projectLead = projectLead;
	}

	public String getProvidedPart() {
		return this.providedPart;
	}

	public void setProvidedPart(String providedPart) {
		this.providedPart = providedPart;
	}

	public String getRadiocative() {
		return this.radiocative;
	}

	public void setRadiocative(String radiocative) {
		this.radiocative = radiocative;
	}

	public String getRatingFlag() {
		return this.ratingFlag;
	}

	public void setRatingFlag(String ratingFlag) {
		this.ratingFlag = ratingFlag;
	}

	public String getRecurrentAlertDedefectType() {
		return this.recurrentAlertDedefectType;
	}

	public void setRecurrentAlertDedefectType(String recurrentAlertDedefectType) {
		this.recurrentAlertDedefectType = recurrentAlertDedefectType;
	}

	public String getRecurrentItemType() {
		return this.recurrentItemType;
	}

	public void setRecurrentItemType(String recurrentItemType) {
		this.recurrentItemType = recurrentItemType;
	}

	public String getRemovalType() {
		return this.removalType;
	}

	public void setRemovalType(String removalType) {
		this.removalType = removalType;
	}

	public String getRepair() {
		return this.repair;
	}

	public void setRepair(String repair) {
		this.repair = repair;
	}

	public String getRepairGeneral() {
		return this.repairGeneral;
	}

	public void setRepairGeneral(String repairGeneral) {
		this.repairGeneral = repairGeneral;
	}

	public String getRepairGse() {
		return this.repairGse;
	}

	public void setRepairGse(String repairGse) {
		this.repairGse = repairGse;
	}

	public String getRepairMaintenance() {
		return this.repairMaintenance;
	}

	public void setRepairMaintenance(String repairMaintenance) {
		this.repairMaintenance = repairMaintenance;
	}

	public BigDecimal getReplenishmentCostFactor() {
		return this.replenishmentCostFactor;
	}

	public void setReplenishmentCostFactor(BigDecimal replenishmentCostFactor) {
		this.replenishmentCostFactor = replenishmentCostFactor;
	}

	public String getReportedBy() {
		return this.reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public BigDecimal getResetSequence() {
		return this.resetSequence;
	}

	public void setResetSequence(BigDecimal resetSequence) {
		this.resetSequence = resetSequence;
	}

	public String getRestrictToEcAndTaskCard() {
		return this.restrictToEcAndTaskCard;
	}

	public void setRestrictToEcAndTaskCard(String restrictToEcAndTaskCard) {
		this.restrictToEcAndTaskCard = restrictToEcAndTaskCard;
	}

	public String getRevenueFlight() {
		return this.revenueFlight;
	}

	public void setRevenueFlight(String revenueFlight) {
		this.revenueFlight = revenueFlight;
	}

	public BigDecimal getRnExchangeFeePercent() {
		return this.rnExchangeFeePercent;
	}

	public void setRnExchangeFeePercent(BigDecimal rnExchangeFeePercent) {
		this.rnExchangeFeePercent = rnExchangeFeePercent;
	}

	public String getRoCostCenter() {
		return this.roCostCenter;
	}

	public void setRoCostCenter(String roCostCenter) {
		this.roCostCenter = roCostCenter;
	}

	public String getRoGl() {
		return this.roGl;
	}

	public void setRoGl(String roGl) {
		this.roGl = roGl;
	}

	public String getRoGl2() {
		return this.roGl2;
	}

	public void setRoGl2(String roGl2) {
		this.roGl2 = roGl2;
	}

	public String getRoGlCompany() {
		return this.roGlCompany;
	}

	public void setRoGlCompany(String roGlCompany) {
		this.roGlCompany = roGlCompany;
	}

	public String getRoGlCompany2() {
		return this.roGlCompany2;
	}

	public void setRoGlCompany2(String roGlCompany2) {
		this.roGlCompany2 = roGlCompany2;
	}

	public String getRoGlCostCenter2() {
		return this.roGlCostCenter2;
	}

	public void setRoGlCostCenter2(String roGlCostCenter2) {
		this.roGlCostCenter2 = roGlCostCenter2;
	}

	public String getRoGlExpenditure() {
		return this.roGlExpenditure;
	}

	public void setRoGlExpenditure(String roGlExpenditure) {
		this.roGlExpenditure = roGlExpenditure;
	}

	public String getRoGlExpenditure2() {
		return this.roGlExpenditure2;
	}

	public void setRoGlExpenditure2(String roGlExpenditure2) {
		this.roGlExpenditure2 = roGlExpenditure2;
	}

	public String getRosActionCode() {
		return this.rosActionCode;
	}

	public void setRosActionCode(String rosActionCode) {
		this.rosActionCode = rosActionCode;
	}

	public String getRosClassificationCode() {
		return this.rosClassificationCode;
	}

	public void setRosClassificationCode(String rosClassificationCode) {
		this.rosClassificationCode = rosClassificationCode;
	}

	public String getRosDiscrepancyCode() {
		return this.rosDiscrepancyCode;
	}

	public void setRosDiscrepancyCode(String rosDiscrepancyCode) {
		this.rosDiscrepancyCode = rosDiscrepancyCode;
	}

	public String getRosReasonCode() {
		return this.rosReasonCode;
	}

	public void setRosReasonCode(String rosReasonCode) {
		this.rosReasonCode = rosReasonCode;
	}

	public String getRosShipmentCarrier() {
		return this.rosShipmentCarrier;
	}

	public void setRosShipmentCarrier(String rosShipmentCarrier) {
		this.rosShipmentCarrier = rosShipmentCarrier;
	}

	public String getRwAc() {
		return this.rwAc;
	}

	public void setRwAc(String rwAc) {
		this.rwAc = rwAc;
	}

	public String getSchedStartCompletDateTime() {
		return this.schedStartCompletDateTime;
	}

	public void setSchedStartCompletDateTime(String schedStartCompletDateTime) {
		this.schedStartCompletDateTime = schedStartCompletDateTime;
	}

	public String getScheduleControlFlag() {
		return this.scheduleControlFlag;
	}

	public void setScheduleControlFlag(String scheduleControlFlag) {
		this.scheduleControlFlag = scheduleControlFlag;
	}

	public String getScrapReorder() {
		return this.scrapReorder;
	}

	public void setScrapReorder(String scrapReorder) {
		this.scrapReorder = scrapReorder;
	}

	public BigDecimal getSection() {
		return this.section;
	}

	public void setSection(BigDecimal section) {
		this.section = section;
	}

	public String getSectionMandatory() {
		return this.sectionMandatory;
	}

	public void setSectionMandatory(String sectionMandatory) {
		this.sectionMandatory = sectionMandatory;
	}

	public String getSelectedCategory() {
		return this.selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSgmReference() {
		return this.sgmReference;
	}

	public void setSgmReference(String sgmReference) {
		this.sgmReference = sgmReference;
	}

	public String getShelfLifeReCertification() {
		return this.shelfLifeReCertification;
	}

	public void setShelfLifeReCertification(String shelfLifeReCertification) {
		this.shelfLifeReCertification = shelfLifeReCertification;
	}

	public String getShowDefectsAccp() {
		return this.showDefectsAccp;
	}

	public void setShowDefectsAccp(String showDefectsAccp) {
		this.showDefectsAccp = showDefectsAccp;
	}

	public String getShowSdr() {
		return this.showSdr;
	}

	public void setShowSdr(String showSdr) {
		this.showSdr = showSdr;
	}

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public BigDecimal getSoFeePercent() {
		return this.soFeePercent;
	}

	public void setSoFeePercent(BigDecimal soFeePercent) {
		this.soFeePercent = soFeePercent;
	}

	public String getSoftware() {
		return this.software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getSpec2kCondition() {
		return this.spec2kCondition;
	}

	public void setSpec2kCondition(String spec2kCondition) {
		this.spec2kCondition = spec2kCondition;
	}

	public String getSpec2kPartStatus() {
		return this.spec2kPartStatus;
	}

	public void setSpec2kPartStatus(String spec2kPartStatus) {
		this.spec2kPartStatus = spec2kPartStatus;
	}

	public String getSpec2kPriority() {
		return this.spec2kPriority;
	}

	public void setSpec2kPriority(String spec2kPriority) {
		this.spec2kPriority = spec2kPriority;
	}

	public BigDecimal getSpec2kTransactionField() {
		return this.spec2kTransactionField;
	}

	public void setSpec2kTransactionField(BigDecimal spec2kTransactionField) {
		this.spec2kTransactionField = spec2kTransactionField;
	}

	public String getSpec2kUom() {
		return this.spec2kUom;
	}

	public void setSpec2kUom(String spec2kUom) {
		this.spec2kUom = spec2kUom;
	}

	public Date getStartDepreciationDate() {
		return this.startDepreciationDate;
	}

	public void setStartDepreciationDate(Date startDepreciationDate) {
		this.startDepreciationDate = startDepreciationDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStepTransferReorder() {
		return this.stepTransferReorder;
	}

	public void setStepTransferReorder(String stepTransferReorder) {
		this.stepTransferReorder = stepTransferReorder;
	}

	public String getSubRisk() {
		return this.subRisk;
	}

	public void setSubRisk(String subRisk) {
		this.subRisk = subRisk;
	}

	public String getSubRisk2() {
		return this.subRisk2;
	}

	public void setSubRisk2(String subRisk2) {
		this.subRisk2 = subRisk2;
	}

	public String getSubRisk3() {
		return this.subRisk3;
	}

	public void setSubRisk3(String subRisk3) {
		this.subRisk3 = subRisk3;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierGeneral() {
		return this.supplierGeneral;
	}

	public void setSupplierGeneral(String supplierGeneral) {
		this.supplierGeneral = supplierGeneral;
	}

	public String getSupplierGse() {
		return this.supplierGse;
	}

	public void setSupplierGse(String supplierGse) {
		this.supplierGse = supplierGse;
	}

	public String getSupplierMaintenance() {
		return this.supplierMaintenance;
	}

	public void setSupplierMaintenance(String supplierMaintenance) {
		this.supplierMaintenance = supplierMaintenance;
	}

	public String getSwoRequisition() {
		return this.swoRequisition;
	}

	public void setSwoRequisition(String swoRequisition) {
		this.swoRequisition = swoRequisition;
	}

	public String getSystemCodeDescription() {
		return this.systemCodeDescription;
	}

	public void setSystemCodeDescription(String systemCodeDescription) {
		this.systemCodeDescription = systemCodeDescription;
	}

	public String getTPed() {
		return this.tPed;
	}

	public void setTPed(String tPed) {
		this.tPed = tPed;
	}

	public String getTagType() {
		return this.tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getTemporaryRevision() {
		return this.temporaryRevision;
	}

	public void setTemporaryRevision(String temporaryRevision) {
		this.temporaryRevision = temporaryRevision;
	}

	public String getThirdPartyWo() {
		return this.thirdPartyWo;
	}

	public void setThirdPartyWo(String thirdPartyWo) {
		this.thirdPartyWo = thirdPartyWo;
	}

	public String getToolLifeReCertification() {
		return this.toolLifeReCertification;
	}

	public void setToolLifeReCertification(String toolLifeReCertification) {
		this.toolLifeReCertification = toolLifeReCertification;
	}

	public String getTransferOrderReorder() {
		return this.transferOrderReorder;
	}

	public void setTransferOrderReorder(String transferOrderReorder) {
		this.transferOrderReorder = transferOrderReorder;
	}

	public String getUnitTypeEngApuIndicator() {
		return this.unitTypeEngApuIndicator;
	}

	public void setUnitTypeEngApuIndicator(String unitTypeEngApuIndicator) {
		this.unitTypeEngApuIndicator = unitTypeEngApuIndicator;
	}

	public BigDecimal getUpperLimit() {
		return this.upperLimit;
	}

	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUseGl2() {
		return this.useGl2;
	}

	public void setUseGl2(String useGl2) {
		this.useGl2 = useGl2;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVacationCategory() {
		return this.vacationCategory;
	}

	public void setVacationCategory(String vacationCategory) {
		this.vacationCategory = vacationCategory;
	}

	public String getVbLifeLimit() {
		return this.vbLifeLimit;
	}

	public void setVbLifeLimit(String vbLifeLimit) {
		this.vbLifeLimit = vbLifeLimit;
	}

	public String getVbLifeLimitPnControl() {
		return this.vbLifeLimitPnControl;
	}

	public void setVbLifeLimitPnControl(String vbLifeLimitPnControl) {
		this.vbLifeLimitPnControl = vbLifeLimitPnControl;
	}

	public String getVendorStatus() {
		return this.vendorStatus;
	}

	public void setVendorStatus(String vendorStatus) {
		this.vendorStatus = vendorStatus;
	}

	public BigDecimal getVopBaseOnCondition() {
		return this.vopBaseOnCondition;
	}

	public void setVopBaseOnCondition(BigDecimal vopBaseOnCondition) {
		this.vopBaseOnCondition = vopBaseOnCondition;
	}

	public BigDecimal getWcMinimunPercentage() {
		return this.wcMinimunPercentage;
	}

	public void setWcMinimunPercentage(BigDecimal wcMinimunPercentage) {
		this.wcMinimunPercentage = wcMinimunPercentage;
	}

	public String getWipActive() {
		return this.wipActive;
	}

	public void setWipActive(String wipActive) {
		this.wipActive = wipActive;
	}

	public BigDecimal getWoCompletionMin() {
		return this.woCompletionMin;
	}

	public void setWoCompletionMin(BigDecimal woCompletionMin) {
		this.woCompletionMin = woCompletionMin;
	}

	public BigDecimal getWoDuration() {
		return this.woDuration;
	}

	public void setWoDuration(BigDecimal woDuration) {
		this.woDuration = woDuration;
	}

	public BigDecimal getWoExpirationMin() {
		return this.woExpirationMin;
	}

	public void setWoExpirationMin(BigDecimal woExpirationMin) {
		this.woExpirationMin = woExpirationMin;
	}

	public BigDecimal getWoIniStartMin() {
		return this.woIniStartMin;
	}

	public void setWoIniStartMin(BigDecimal woIniStartMin) {
		this.woIniStartMin = woIniStartMin;
	}

	public String getWoLinkFlight() {
		return this.woLinkFlight;
	}

	public void setWoLinkFlight(String woLinkFlight) {
		this.woLinkFlight = woLinkFlight;
	}

	public String getWoPriority() {
		return this.woPriority;
	}

	public void setWoPriority(String woPriority) {
		this.woPriority = woPriority;
	}

	public BigDecimal getWoWipJobMin() {
		return this.woWipJobMin;
	}

	public void setWoWipJobMin(BigDecimal woWipJobMin) {
		this.woWipJobMin = woWipJobMin;
	}

	public BigDecimal getXoExchangeFeePercent() {
		return this.xoExchangeFeePercent;
	}

	public void setXoExchangeFeePercent(BigDecimal xoExchangeFeePercent) {
		this.xoExchangeFeePercent = xoExchangeFeePercent;
	}

}