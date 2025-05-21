package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the AC_MASTER database table.
 * 
 */
@Entity
@Table(name="AC_MASTER")
@NamedQuery(name="AcMaster.findAll", query="SELECT a FROM AcMaster a")
public class AcMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String ac;

	@Column(name="AC_BLOCK_HOURS")
	private BigDecimal acBlockHours;

	@Column(name="AC_BLOCK_MINUTES")
	private BigDecimal acBlockMinutes;

	@Column(name="AC_BRAKE_CYCLES")
	private BigDecimal acBrakeCycles;

	@Column(name="AC_COMPONENT_WARRANTY_CYCLES")
	private BigDecimal acComponentWarrantyCycles;

	@Column(name="AC_COMPONENT_WARRANTY_DAYS")
	private BigDecimal acComponentWarrantyDays;

	@Column(name="AC_COMPONENT_WARRANTY_HOURS")
	private BigDecimal acComponentWarrantyHours;

	@Column(name="AC_COMPONENT_WARRANTY_VENDOR")
	private String acComponentWarrantyVendor;

	@Column(name="AC_CYCLES")
	private BigDecimal acCycles;

	@Column(name="AC_DEFECT_ITEM_OUT_OF_SERVICE")
	private BigDecimal acDefectItemOutOfService;

	@Column(name="AC_DEFECT_OUT_OF_SERIVE")
	private String acDefectOutOfSerive;

	@Column(name="AC_DEFECT_TYPE_OUT_SERVICE")
	private String acDefectTypeOutService;

	@Column(name="AC_DESCRIPTION")
	private String acDescription;

	@Column(name="AC_FLIGHT_HOURS")
	private BigDecimal acFlightHours;

	@Column(name="AC_FLIGHT_MINUTES")
	private BigDecimal acFlightMinutes;

	@Column(name="AC_FLIGHT_STATUS")
	private String acFlightStatus;

	@Lob
	@Column(name="AC_NOTES")
	private String acNotes;

	@Lob
	@Column(name="AC_NOTES_AUX")
	private String acNotesAux;

	@Column(name="AC_OUT_OF_SERVICE_NOTES")
	private BigDecimal acOutOfServiceNotes;

	@Column(name="AC_OUT_OF_SVR_ADVICED_DATE")
	private Date acOutOfSvrAdvicedDate;

	@Column(name="AC_OUT_OF_SVR_ADVICED_HOUR")
	private BigDecimal acOutOfSvrAdvicedHour;

	@Column(name="AC_OUT_OF_SVR_ADVICED_MINUTE")
	private BigDecimal acOutOfSvrAdvicedMinute;

	@Column(name="AC_OWNERSHIP_CONTROL")
	private String acOwnershipControl;

	@Column(name="AC_PLANNING_FAST_UPLOAD")
	private String acPlanningFastUpload;

	@Column(name="AC_POSITION")
	private String acPosition;

	@Column(name="AC_RIN")
	private BigDecimal acRin;

	@Column(name="AC_SCHEDULE_IN_SERVICE_DATE")
	private Date acScheduleInServiceDate;

	@Column(name="AC_SCHEDULE_IN_SERVICE_HOUR")
	private BigDecimal acScheduleInServiceHour;

	@Column(name="AC_SCHEDULE_IN_SERVICE_MINUTE")
	private BigDecimal acScheduleInServiceMinute;

	@Column(name="AC_SCHEDULE_OUT_SERVICE_DATE")
	private Date acScheduleOutServiceDate;

	@Column(name="AC_SCHEDULE_OUT_SERVICE_HOUR")
	private BigDecimal acScheduleOutServiceHour;

	@Column(name="AC_SCHEDULE_OUT_SERVICE_MINUTE")
	private BigDecimal acScheduleOutServiceMinute;

	@Column(name="AC_SERIES")
	private String acSeries;

	@Column(name="AC_SN")
	private String acSn;

	@Column(name="AC_SORT_ORDER")
	private String acSortOrder;

	@Column(name="AC_TEAR_DOWN")
	private String acTearDown;

	@Column(name="AC_TYPE")
	private String acType;

	@Column(name="AC_UNDER_WARRANTY")
	private String acUnderWarranty;

	@Column(name="AC_WARRANTY_CYCLES")
	private BigDecimal acWarrantyCycles;

	@Column(name="AC_WARRANTY_DAYS")
	private BigDecimal acWarrantyDays;

	@Column(name="AC_WARRANTY_HOURS")
	private BigDecimal acWarrantyHours;

	@Column(name="AC_WARRANTY_VENDOR")
	private String acWarrantyVendor;

	@Column(name="AC_WEIGHT_CONFIG")
	private String acWeightConfig;

	@Column(name="ACTUAL_FLIGHT_DATE_CONTROL")
	private Date actualFlightDateControl;

	@Column(name="AMM_DESIGNATOR")
	private String ammDesignator;

	@Column(name="AMP_OMP")
	private String ampOmp;

	@Column(name="BASIC_EMPTY_WEIGHT")
	private BigDecimal basicEmptyWeight;

	@Column(name="BASIC_EMPTY_WEIGHT_UNITS")
	private String basicEmptyWeightUnits;

	@Column(name="BASIC_NUMBER")
	private String basicNumber;

	@Column(name="BLOB_NO")
	private BigDecimal blobNo;

	@Column(name="BUILD_SPEC")
	private String buildSpec;

	@Column(name="CAT_RATING")
	private String catRating;

	@Column(name="CERT_OF_AIRWORTH_DATE")
	private Date certOfAirworthDate;

	@Column(name="CIVIL_AUTHORITY")
	private String civilAuthority;

	@Column(name="CONCESSION_LIMIT")
	private BigDecimal concessionLimit;

	@Column(name="CONCESSION_LIMIT_MAX_CYCLES")
	private BigDecimal concessionLimitMaxCycles;

	@Column(name="CONCESSION_LIMIT_MAX_DAYS")
	private BigDecimal concessionLimitMaxDays;

	@Column(name="CONCESSION_LIMIT_MAX_HOURS")
	private BigDecimal concessionLimitMaxHours;

	@Column(name="CONCESSION_LIMIT_MAX_RIN")
	private BigDecimal concessionLimitMaxRin;

	@Column(name="CONCESSION_LIMIT_OVERRIDE_FLAG")
	private String concessionLimitOverrideFlag;

	private String configuration;

	@Column(name="CONFIGURATION_1_CODE")
	private String configuration1Code;

	@Column(name="CONFIGURATION_1_NO_SEATS")
	private BigDecimal configuration1NoSeats;

	@Column(name="CONFIGURATION_2_CODE")
	private String configuration2Code;

	@Column(name="CONFIGURATION_2_NO_SEATS")
	private BigDecimal configuration2NoSeats;

	@Column(name="CONFIGURATION_3_CODE")
	private String configuration3Code;

	@Column(name="CONFIGURATION_3_NO_SEATS")
	private BigDecimal configuration3NoSeats;

	@Column(name="CONFIGURATION_4_CODE")
	private String configuration4Code;

	@Column(name="CONFIGURATION_4_NO_SEATS")
	private BigDecimal configuration4NoSeats;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String customer;

	@Column(name="DATE_ON_OPS_SPEC")
	private Date dateOnOpsSpec;

	@Column(name="DEFECT_AUTO_BILLING")
	private String defectAutoBilling;

	@Column(name="DEFECT_AUTO_CONTRACT_NUMBER")
	private BigDecimal defectAutoContractNumber;

	@Column(name="DELIVERED_ENGINE_NO_1")
	private String deliveredEngineNo1;

	@Column(name="DELIVERED_ENGINE_NO_2")
	private String deliveredEngineNo2;

	@Column(name="DELIVERY_DATE")
	private Date deliveryDate;

	@Column(name="DELIVERY_POSITION")
	private BigDecimal deliveryPosition;

	@Column(name="DOCUMENT_NO")
	private BigDecimal documentNo;

	@Column(name="EFFECTIVITY_CODE")
	private String effectivityCode;

	@Column(name="ENGINE_MODEL")
	private String engineModel;

	@Column(name="ENGINE1_NG_CYCLES")
	private BigDecimal engine1NgCycles;

	@Column(name="ENGINE1_NP_CYCLES")
	private BigDecimal engine1NpCycles;

	@Column(name="ENGINE1_OEI")
	private BigDecimal engine1Oei;

	@Column(name="ENGINE1_STARTS")
	private BigDecimal engine1Starts;

	@Column(name="ENGINE2_NG_CYCLES")
	private BigDecimal engine2NgCycles;

	@Column(name="ENGINE2_NP_CYCLES")
	private BigDecimal engine2NpCycles;

	@Column(name="ENGINE2_OEI")
	private BigDecimal engine2Oei;

	@Column(name="ENGINE2_STARTS")
	private BigDecimal engine2Starts;

	@Column(name="ENHANCED_CABIN")
	private String enhancedCabin;

	@Column(name="EOL_DATE")
	private Date eolDate;

	@Column(name="EOL_PLANNING_CYCLES")
	private BigDecimal eolPlanningCycles;

	@Column(name="EOL_PLANNING_DAYS")
	private BigDecimal eolPlanningDays;

	@Column(name="EOL_PLANNING_HOURS")
	private BigDecimal eolPlanningHours;

	private String etops;

	@Column(name="ETOPS_DOWNGRADE")
	private String etopsDowngrade;

	@Column(name="ETOPS_DOWNGRADE_DATE")
	private Date etopsDowngradeDate;

	@Column(name="ETOPS_DOWNGRADE_TRANSACTION")
	private BigDecimal etopsDowngradeTransaction;

	@Column(name="ETOPS_DOWNGRADE_USER")
	private String etopsDowngradeUser;

	@Column(name="ETOPS_MINUTES")
	private BigDecimal etopsMinutes;

	@Column(name="EXCLUDE_FROM_MXPLAN")
	private String excludeFromMxplan;

	@Column(name="FIRST_REVENUE_DATE")
	private Date firstRevenueDate;

	@Column(name="FLEET_AGE")
	private String fleetAge;

	private String flight;

	@Column(name="FLIGHT_STATUS")
	private String flightStatus;

	@Column(name="FOP_AC_SERIES")
	private String fopAcSeries;

	@Column(name="FOP_AC_TYPE")
	private String fopAcType;

	@Column(name="FORM_NO")
	private BigDecimal formNo;

	@Column(name="FREIGHTER_PAX_CARGO")
	private String freighterPaxCargo;

	@Column(name="FUEL_CAPACITY")
	private BigDecimal fuelCapacity;

	@Column(name="FUEL_CAPACITY_UNITS")
	private String fuelCapacityUnits;

	@Column(name="GL_COMPANY")
	private String glCompany;

	@Column(name="GL_COST_CENTER")
	private String glCostCenter;

	@Column(name="GRB_ENGINE_1")
	private BigDecimal grbEngine1;

	@Column(name="GRB_ENGINE_2")
	private BigDecimal grbEngine2;

	private String hub;

	private String ife;

	@Column(name="INSURANCE_CERTIFICATE")
	private String insuranceCertificate;

	@Column(name="INSURANCE_COMPANY")
	private String insuranceCompany;

	@Column(name="INSURANCE_EXPIRY")
	private Date insuranceExpiry;

	private String ipc;

	@Column(name="LAST_AC_REGISTRATION")
	private String lastAcRegistration;

	@Column(name="LAST_AC_REGISTRATION_CHG_BY")
	private String lastAcRegistrationChgBy;

	@Column(name="LAST_AC_REGISTRATION_CHG_ON")
	private Date lastAcRegistrationChgOn;

	@Column(name="LAST_AC_REGISTRATION_DATE")
	private Date lastAcRegistrationDate;

	@Column(name="LAST_DATE_FLIGHT_LOG_APPLIED")
	private Date lastDateFlightLogApplied;

	@Column(name="LHT_AIRBORNE")
	private String lhtAirborne;

	@Column(name="LICENSE_CODE")
	private String licenseCode;

	@Column(name="LINE_NO")
	private String lineNo;

	private String livery;

	@Column(name="LOGBOOK_NO")
	private BigDecimal logbookNo;

	private String manufacture;

	@Column(name="MANUFACTURE_DATE")
	private Date manufactureDate;

	@Column(name="MAX_CRUISING_ALT")
	private String maxCruisingAlt;

	@Column(name="MAX_LANDING_WEIGHT")
	private BigDecimal maxLandingWeight;

	@Column(name="MAX_LANDING_WEIGHT_UNITS")
	private String maxLandingWeightUnits;

	@Column(name="MAX_TAXI_WEIGHT")
	private BigDecimal maxTaxiWeight;

	@Column(name="MAX_TAXI_WEIGHT_UNITS")
	private String maxTaxiWeightUnits;

	@Column(name="MAX_TO_UNITS")
	private String maxToUnits;

	@Column(name="MAX_TO_WEIGHT")
	private BigDecimal maxToWeight;

	@Column(name="MAX_ZERO_FUEL_WEIGHT")
	private BigDecimal maxZeroFuelWeight;

	@Column(name="MAX_ZERO_FUEL_WEIGHT_UNITS")
	private String maxZeroFuelWeightUnits;

	@Column(name="MAXIMUN_TRUST")
	private String maximunTrust;

	@Column(name="MM_EFFECTIVITY")
	private String mmEffectivity;

	private String mmo;

	@Column(name="MODE_S")
	private String modeS;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	private BigDecimal notes;

	@Column(name="NUMBER_OF_SEAT_1")
	private BigDecimal numberOfSeat1;

	@Column(name="NUMBER_OF_SEAT_2")
	private BigDecimal numberOfSeat2;

	@Column(name="NUMBER_OF_SEAT_3")
	private BigDecimal numberOfSeat3;

	@Column(name="NUMBER_OF_SEAT_4")
	private BigDecimal numberOfSeat4;

	@Column(name="NUMBER_OF_SEAT_TYPE_1")
	private String numberOfSeatType1;

	@Column(name="NUMBER_OF_SEAT_TYPE_2")
	private String numberOfSeatType2;

	@Column(name="NUMBER_OF_SEAT_TYPE_3")
	private String numberOfSeatType3;

	@Column(name="NUMBER_OF_SEAT_TYPE_4")
	private String numberOfSeatType4;

	@Column(name="OPERATING_EMPTY_WEIGHT")
	private BigDecimal operatingEmptyWeight;

	@Column(name="OPERATING_EMPTY_WEIGHT_UNITS")
	private String operatingEmptyWeightUnits;

	private String operator;

	@Column(name="OPERATOR_DATE")
	private Date operatorDate;

	@Column(name="ORIGINAL_OPERATOR")
	private String originalOperator;

	@Column(name="OVER_NIGHT_SITE")
	private String overNightSite;

	@Column(name="OVER_NIGHT_STATION")
	private String overNightStation;

	private String owner;

	@Column(name="OWNER_DATE")
	private Date ownerDate;

	@Column(name="PDSC_EXPIRE_DATE")
	private Date pdscExpireDate;

	private String planner;

	@Column(name="PREVIOUS_AC")
	private String previousAc;

	@Column(name="PREVIOUS_OVER_NIGHT_STATION")
	private String previousOverNightStation;

	private String priority;

	@Column(name="\"RANGE\"")
	private BigDecimal range;

	@Column(name="RON_HOURS")
	private BigDecimal ronHours;

	@Column(name="ROTOR_COUNTER1")
	private BigDecimal rotorCounter1;

	@Column(name="ROTOR_COUNTER2")
	private BigDecimal rotorCounter2;

	@Column(name="ROTOR_COUNTER3")
	private BigDecimal rotorCounter3;

	@Column(name="ROTOR_COUNTER4")
	private BigDecimal rotorCounter4;

	@Column(name="ROTOR_COUNTER5")
	private BigDecimal rotorCounter5;

	private String rvsm;

	@Column(name="RVSM_FLIGHT_LEVEL_END")
	private BigDecimal rvsmFlightLevelEnd;

	@Column(name="RVSM_FLIGHT_LEVEL_START")
	private BigDecimal rvsmFlightLevelStart;

	private String sampling;

	private String selcal;

	@Column(name="SERVICE_DATE")
	private Date serviceDate;

	@Column(name="SHORT_FIELD_PERFORMANCE")
	private String shortFieldPerformance;

	@Column(name="SPEC2K_AC")
	private String spec2kAc;

	private String status;

	@Column(name="T_PED")
	private String tPed;

	@Column(name="TOTAL_AC_TIME_DELIVERY_CYCLES")
	private BigDecimal totalAcTimeDeliveryCycles;

	@Column(name="TOTAL_AC_TIME_DELIVERY_HOURS")
	private BigDecimal totalAcTimeDeliveryHours;

	@Column(name="TOTAL_AC_TIME_DELIVERY_MINUTES")
	private BigDecimal totalAcTimeDeliveryMinutes;

	@Column(name="TOTAL_HOIST_CYCLES")
	private BigDecimal totalHoistCycles;

	@Column(name="TOTAL_HOIST_HOURS")
	private BigDecimal totalHoistHours;

	@Column(name="TOTAL_HOIST_MINUTES")
	private BigDecimal totalHoistMinutes;

	@Column(name="TOTAL_HOIST_SECONDS")
	private BigDecimal totalHoistSeconds;

	@Column(name="TOTAL_HOOK_CYCLES")
	private BigDecimal totalHookCycles;

	@Column(name="TOTAL_HOOK_HOURS")
	private BigDecimal totalHookHours;

	@Column(name="TOTAL_HOOK_MINUTES")
	private BigDecimal totalHookMinutes;

	@Column(name="TOTAL_LANDINGS")
	private BigDecimal totalLandings;

	@Column(name="TOTAL_MOMENT")
	private BigDecimal totalMoment;

	@Column(name="TOTAL_MOMENT_UNITS")
	private String totalMomentUnits;

	@Column(name="VARIABLE_ENGINEERING_NO")
	private String variableEngineeringNo;

	private String winglet;

	@Column(name="WO_CATEGORY")
	private String woCategory;

	@Column(name="WO_DURATION")
	private BigDecimal woDuration;

	@Column(name="WO_EXPENDITURE_CODE")
	private String woExpenditureCode;

	@Column(name="WO_START_TIME")
	private BigDecimal woStartTime;

	@Column(name="WO_START_TIME_MINUTE")
	private BigDecimal woStartTimeMinute;

	public AcMaster() {
	}

	public String getAc() {
		return this.ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public BigDecimal getAcBlockHours() {
		return this.acBlockHours;
	}

	public void setAcBlockHours(BigDecimal acBlockHours) {
		this.acBlockHours = acBlockHours;
	}

	public BigDecimal getAcBlockMinutes() {
		return this.acBlockMinutes;
	}

	public void setAcBlockMinutes(BigDecimal acBlockMinutes) {
		this.acBlockMinutes = acBlockMinutes;
	}

	public BigDecimal getAcBrakeCycles() {
		return this.acBrakeCycles;
	}

	public void setAcBrakeCycles(BigDecimal acBrakeCycles) {
		this.acBrakeCycles = acBrakeCycles;
	}

	public BigDecimal getAcComponentWarrantyCycles() {
		return this.acComponentWarrantyCycles;
	}

	public void setAcComponentWarrantyCycles(BigDecimal acComponentWarrantyCycles) {
		this.acComponentWarrantyCycles = acComponentWarrantyCycles;
	}

	public BigDecimal getAcComponentWarrantyDays() {
		return this.acComponentWarrantyDays;
	}

	public void setAcComponentWarrantyDays(BigDecimal acComponentWarrantyDays) {
		this.acComponentWarrantyDays = acComponentWarrantyDays;
	}

	public BigDecimal getAcComponentWarrantyHours() {
		return this.acComponentWarrantyHours;
	}

	public void setAcComponentWarrantyHours(BigDecimal acComponentWarrantyHours) {
		this.acComponentWarrantyHours = acComponentWarrantyHours;
	}

	public String getAcComponentWarrantyVendor() {
		return this.acComponentWarrantyVendor;
	}

	public void setAcComponentWarrantyVendor(String acComponentWarrantyVendor) {
		this.acComponentWarrantyVendor = acComponentWarrantyVendor;
	}

	public BigDecimal getAcCycles() {
		return this.acCycles;
	}

	public void setAcCycles(BigDecimal acCycles) {
		this.acCycles = acCycles;
	}

	public BigDecimal getAcDefectItemOutOfService() {
		return this.acDefectItemOutOfService;
	}

	public void setAcDefectItemOutOfService(BigDecimal acDefectItemOutOfService) {
		this.acDefectItemOutOfService = acDefectItemOutOfService;
	}

	public String getAcDefectOutOfSerive() {
		return this.acDefectOutOfSerive;
	}

	public void setAcDefectOutOfSerive(String acDefectOutOfSerive) {
		this.acDefectOutOfSerive = acDefectOutOfSerive;
	}

	public String getAcDefectTypeOutService() {
		return this.acDefectTypeOutService;
	}

	public void setAcDefectTypeOutService(String acDefectTypeOutService) {
		this.acDefectTypeOutService = acDefectTypeOutService;
	}

	public String getAcDescription() {
		return this.acDescription;
	}

	public void setAcDescription(String acDescription) {
		this.acDescription = acDescription;
	}

	public BigDecimal getAcFlightHours() {
		return this.acFlightHours;
	}

	public void setAcFlightHours(BigDecimal acFlightHours) {
		this.acFlightHours = acFlightHours;
	}

	public BigDecimal getAcFlightMinutes() {
		return this.acFlightMinutes;
	}

	public void setAcFlightMinutes(BigDecimal acFlightMinutes) {
		this.acFlightMinutes = acFlightMinutes;
	}

	public String getAcFlightStatus() {
		return this.acFlightStatus;
	}

	public void setAcFlightStatus(String acFlightStatus) {
		this.acFlightStatus = acFlightStatus;
	}

	public String getAcNotes() {
		return this.acNotes;
	}

	public void setAcNotes(String acNotes) {
		this.acNotes = acNotes;
	}

	public String getAcNotesAux() {
		return this.acNotesAux;
	}

	public void setAcNotesAux(String acNotesAux) {
		this.acNotesAux = acNotesAux;
	}

	public BigDecimal getAcOutOfServiceNotes() {
		return this.acOutOfServiceNotes;
	}

	public void setAcOutOfServiceNotes(BigDecimal acOutOfServiceNotes) {
		this.acOutOfServiceNotes = acOutOfServiceNotes;
	}

	public Date getAcOutOfSvrAdvicedDate() {
		return this.acOutOfSvrAdvicedDate;
	}

	public void setAcOutOfSvrAdvicedDate(Date acOutOfSvrAdvicedDate) {
		this.acOutOfSvrAdvicedDate = acOutOfSvrAdvicedDate;
	}

	public BigDecimal getAcOutOfSvrAdvicedHour() {
		return this.acOutOfSvrAdvicedHour;
	}

	public void setAcOutOfSvrAdvicedHour(BigDecimal acOutOfSvrAdvicedHour) {
		this.acOutOfSvrAdvicedHour = acOutOfSvrAdvicedHour;
	}

	public BigDecimal getAcOutOfSvrAdvicedMinute() {
		return this.acOutOfSvrAdvicedMinute;
	}

	public void setAcOutOfSvrAdvicedMinute(BigDecimal acOutOfSvrAdvicedMinute) {
		this.acOutOfSvrAdvicedMinute = acOutOfSvrAdvicedMinute;
	}

	public String getAcOwnershipControl() {
		return this.acOwnershipControl;
	}

	public void setAcOwnershipControl(String acOwnershipControl) {
		this.acOwnershipControl = acOwnershipControl;
	}

	public String getAcPlanningFastUpload() {
		return this.acPlanningFastUpload;
	}

	public void setAcPlanningFastUpload(String acPlanningFastUpload) {
		this.acPlanningFastUpload = acPlanningFastUpload;
	}

	public String getAcPosition() {
		return this.acPosition;
	}

	public void setAcPosition(String acPosition) {
		this.acPosition = acPosition;
	}

	public BigDecimal getAcRin() {
		return this.acRin;
	}

	public void setAcRin(BigDecimal acRin) {
		this.acRin = acRin;
	}

	public Date getAcScheduleInServiceDate() {
		return this.acScheduleInServiceDate;
	}

	public void setAcScheduleInServiceDate(Date acScheduleInServiceDate) {
		this.acScheduleInServiceDate = acScheduleInServiceDate;
	}

	public BigDecimal getAcScheduleInServiceHour() {
		return this.acScheduleInServiceHour;
	}

	public void setAcScheduleInServiceHour(BigDecimal acScheduleInServiceHour) {
		this.acScheduleInServiceHour = acScheduleInServiceHour;
	}

	public BigDecimal getAcScheduleInServiceMinute() {
		return this.acScheduleInServiceMinute;
	}

	public void setAcScheduleInServiceMinute(BigDecimal acScheduleInServiceMinute) {
		this.acScheduleInServiceMinute = acScheduleInServiceMinute;
	}

	public Date getAcScheduleOutServiceDate() {
		return this.acScheduleOutServiceDate;
	}

	public void setAcScheduleOutServiceDate(Date acScheduleOutServiceDate) {
		this.acScheduleOutServiceDate = acScheduleOutServiceDate;
	}

	public BigDecimal getAcScheduleOutServiceHour() {
		return this.acScheduleOutServiceHour;
	}

	public void setAcScheduleOutServiceHour(BigDecimal acScheduleOutServiceHour) {
		this.acScheduleOutServiceHour = acScheduleOutServiceHour;
	}

	public BigDecimal getAcScheduleOutServiceMinute() {
		return this.acScheduleOutServiceMinute;
	}

	public void setAcScheduleOutServiceMinute(BigDecimal acScheduleOutServiceMinute) {
		this.acScheduleOutServiceMinute = acScheduleOutServiceMinute;
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

	public String getAcSortOrder() {
		return this.acSortOrder;
	}

	public void setAcSortOrder(String acSortOrder) {
		this.acSortOrder = acSortOrder;
	}

	public String getAcTearDown() {
		return this.acTearDown;
	}

	public void setAcTearDown(String acTearDown) {
		this.acTearDown = acTearDown;
	}

	public String getAcType() {
		return this.acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getAcUnderWarranty() {
		return this.acUnderWarranty;
	}

	public void setAcUnderWarranty(String acUnderWarranty) {
		this.acUnderWarranty = acUnderWarranty;
	}

	public BigDecimal getAcWarrantyCycles() {
		return this.acWarrantyCycles;
	}

	public void setAcWarrantyCycles(BigDecimal acWarrantyCycles) {
		this.acWarrantyCycles = acWarrantyCycles;
	}

	public BigDecimal getAcWarrantyDays() {
		return this.acWarrantyDays;
	}

	public void setAcWarrantyDays(BigDecimal acWarrantyDays) {
		this.acWarrantyDays = acWarrantyDays;
	}

	public BigDecimal getAcWarrantyHours() {
		return this.acWarrantyHours;
	}

	public void setAcWarrantyHours(BigDecimal acWarrantyHours) {
		this.acWarrantyHours = acWarrantyHours;
	}

	public String getAcWarrantyVendor() {
		return this.acWarrantyVendor;
	}

	public void setAcWarrantyVendor(String acWarrantyVendor) {
		this.acWarrantyVendor = acWarrantyVendor;
	}

	public String getAcWeightConfig() {
		return this.acWeightConfig;
	}

	public void setAcWeightConfig(String acWeightConfig) {
		this.acWeightConfig = acWeightConfig;
	}

	public Date getActualFlightDateControl() {
		return this.actualFlightDateControl;
	}

	public void setActualFlightDateControl(Date actualFlightDateControl) {
		this.actualFlightDateControl = actualFlightDateControl;
	}

	public String getAmmDesignator() {
		return this.ammDesignator;
	}

	public void setAmmDesignator(String ammDesignator) {
		this.ammDesignator = ammDesignator;
	}

	public String getAmpOmp() {
		return this.ampOmp;
	}

	public void setAmpOmp(String ampOmp) {
		this.ampOmp = ampOmp;
	}

	public BigDecimal getBasicEmptyWeight() {
		return this.basicEmptyWeight;
	}

	public void setBasicEmptyWeight(BigDecimal basicEmptyWeight) {
		this.basicEmptyWeight = basicEmptyWeight;
	}

	public String getBasicEmptyWeightUnits() {
		return this.basicEmptyWeightUnits;
	}

	public void setBasicEmptyWeightUnits(String basicEmptyWeightUnits) {
		this.basicEmptyWeightUnits = basicEmptyWeightUnits;
	}

	public String getBasicNumber() {
		return this.basicNumber;
	}

	public void setBasicNumber(String basicNumber) {
		this.basicNumber = basicNumber;
	}

	public BigDecimal getBlobNo() {
		return this.blobNo;
	}

	public void setBlobNo(BigDecimal blobNo) {
		this.blobNo = blobNo;
	}

	public String getBuildSpec() {
		return this.buildSpec;
	}

	public void setBuildSpec(String buildSpec) {
		this.buildSpec = buildSpec;
	}

	public String getCatRating() {
		return this.catRating;
	}

	public void setCatRating(String catRating) {
		this.catRating = catRating;
	}

	public Date getCertOfAirworthDate() {
		return this.certOfAirworthDate;
	}

	public void setCertOfAirworthDate(Date certOfAirworthDate) {
		this.certOfAirworthDate = certOfAirworthDate;
	}

	public String getCivilAuthority() {
		return this.civilAuthority;
	}

	public void setCivilAuthority(String civilAuthority) {
		this.civilAuthority = civilAuthority;
	}

	public BigDecimal getConcessionLimit() {
		return this.concessionLimit;
	}

	public void setConcessionLimit(BigDecimal concessionLimit) {
		this.concessionLimit = concessionLimit;
	}

	public BigDecimal getConcessionLimitMaxCycles() {
		return this.concessionLimitMaxCycles;
	}

	public void setConcessionLimitMaxCycles(BigDecimal concessionLimitMaxCycles) {
		this.concessionLimitMaxCycles = concessionLimitMaxCycles;
	}

	public BigDecimal getConcessionLimitMaxDays() {
		return this.concessionLimitMaxDays;
	}

	public void setConcessionLimitMaxDays(BigDecimal concessionLimitMaxDays) {
		this.concessionLimitMaxDays = concessionLimitMaxDays;
	}

	public BigDecimal getConcessionLimitMaxHours() {
		return this.concessionLimitMaxHours;
	}

	public void setConcessionLimitMaxHours(BigDecimal concessionLimitMaxHours) {
		this.concessionLimitMaxHours = concessionLimitMaxHours;
	}

	public BigDecimal getConcessionLimitMaxRin() {
		return this.concessionLimitMaxRin;
	}

	public void setConcessionLimitMaxRin(BigDecimal concessionLimitMaxRin) {
		this.concessionLimitMaxRin = concessionLimitMaxRin;
	}

	public String getConcessionLimitOverrideFlag() {
		return this.concessionLimitOverrideFlag;
	}

	public void setConcessionLimitOverrideFlag(String concessionLimitOverrideFlag) {
		this.concessionLimitOverrideFlag = concessionLimitOverrideFlag;
	}

	public String getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getConfiguration1Code() {
		return this.configuration1Code;
	}

	public void setConfiguration1Code(String configuration1Code) {
		this.configuration1Code = configuration1Code;
	}

	public BigDecimal getConfiguration1NoSeats() {
		return this.configuration1NoSeats;
	}

	public void setConfiguration1NoSeats(BigDecimal configuration1NoSeats) {
		this.configuration1NoSeats = configuration1NoSeats;
	}

	public String getConfiguration2Code() {
		return this.configuration2Code;
	}

	public void setConfiguration2Code(String configuration2Code) {
		this.configuration2Code = configuration2Code;
	}

	public BigDecimal getConfiguration2NoSeats() {
		return this.configuration2NoSeats;
	}

	public void setConfiguration2NoSeats(BigDecimal configuration2NoSeats) {
		this.configuration2NoSeats = configuration2NoSeats;
	}

	public String getConfiguration3Code() {
		return this.configuration3Code;
	}

	public void setConfiguration3Code(String configuration3Code) {
		this.configuration3Code = configuration3Code;
	}

	public BigDecimal getConfiguration3NoSeats() {
		return this.configuration3NoSeats;
	}

	public void setConfiguration3NoSeats(BigDecimal configuration3NoSeats) {
		this.configuration3NoSeats = configuration3NoSeats;
	}

	public String getConfiguration4Code() {
		return this.configuration4Code;
	}

	public void setConfiguration4Code(String configuration4Code) {
		this.configuration4Code = configuration4Code;
	}

	public BigDecimal getConfiguration4NoSeats() {
		return this.configuration4NoSeats;
	}

	public void setConfiguration4NoSeats(BigDecimal configuration4NoSeats) {
		this.configuration4NoSeats = configuration4NoSeats;
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

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getDateOnOpsSpec() {
		return this.dateOnOpsSpec;
	}

	public void setDateOnOpsSpec(Date dateOnOpsSpec) {
		this.dateOnOpsSpec = dateOnOpsSpec;
	}

	public String getDefectAutoBilling() {
		return this.defectAutoBilling;
	}

	public void setDefectAutoBilling(String defectAutoBilling) {
		this.defectAutoBilling = defectAutoBilling;
	}

	public BigDecimal getDefectAutoContractNumber() {
		return this.defectAutoContractNumber;
	}

	public void setDefectAutoContractNumber(BigDecimal defectAutoContractNumber) {
		this.defectAutoContractNumber = defectAutoContractNumber;
	}

	public String getDeliveredEngineNo1() {
		return this.deliveredEngineNo1;
	}

	public void setDeliveredEngineNo1(String deliveredEngineNo1) {
		this.deliveredEngineNo1 = deliveredEngineNo1;
	}

	public String getDeliveredEngineNo2() {
		return this.deliveredEngineNo2;
	}

	public void setDeliveredEngineNo2(String deliveredEngineNo2) {
		this.deliveredEngineNo2 = deliveredEngineNo2;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getDeliveryPosition() {
		return this.deliveryPosition;
	}

	public void setDeliveryPosition(BigDecimal deliveryPosition) {
		this.deliveryPosition = deliveryPosition;
	}

	public BigDecimal getDocumentNo() {
		return this.documentNo;
	}

	public void setDocumentNo(BigDecimal documentNo) {
		this.documentNo = documentNo;
	}

	public String getEffectivityCode() {
		return this.effectivityCode;
	}

	public void setEffectivityCode(String effectivityCode) {
		this.effectivityCode = effectivityCode;
	}

	public String getEngineModel() {
		return this.engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}

	public BigDecimal getEngine1NgCycles() {
		return this.engine1NgCycles;
	}

	public void setEngine1NgCycles(BigDecimal engine1NgCycles) {
		this.engine1NgCycles = engine1NgCycles;
	}

	public BigDecimal getEngine1NpCycles() {
		return this.engine1NpCycles;
	}

	public void setEngine1NpCycles(BigDecimal engine1NpCycles) {
		this.engine1NpCycles = engine1NpCycles;
	}

	public BigDecimal getEngine1Oei() {
		return this.engine1Oei;
	}

	public void setEngine1Oei(BigDecimal engine1Oei) {
		this.engine1Oei = engine1Oei;
	}

	public BigDecimal getEngine1Starts() {
		return this.engine1Starts;
	}

	public void setEngine1Starts(BigDecimal engine1Starts) {
		this.engine1Starts = engine1Starts;
	}

	public BigDecimal getEngine2NgCycles() {
		return this.engine2NgCycles;
	}

	public void setEngine2NgCycles(BigDecimal engine2NgCycles) {
		this.engine2NgCycles = engine2NgCycles;
	}

	public BigDecimal getEngine2NpCycles() {
		return this.engine2NpCycles;
	}

	public void setEngine2NpCycles(BigDecimal engine2NpCycles) {
		this.engine2NpCycles = engine2NpCycles;
	}

	public BigDecimal getEngine2Oei() {
		return this.engine2Oei;
	}

	public void setEngine2Oei(BigDecimal engine2Oei) {
		this.engine2Oei = engine2Oei;
	}

	public BigDecimal getEngine2Starts() {
		return this.engine2Starts;
	}

	public void setEngine2Starts(BigDecimal engine2Starts) {
		this.engine2Starts = engine2Starts;
	}

	public String getEnhancedCabin() {
		return this.enhancedCabin;
	}

	public void setEnhancedCabin(String enhancedCabin) {
		this.enhancedCabin = enhancedCabin;
	}

	public Date getEolDate() {
		return this.eolDate;
	}

	public void setEolDate(Date eolDate) {
		this.eolDate = eolDate;
	}

	public BigDecimal getEolPlanningCycles() {
		return this.eolPlanningCycles;
	}

	public void setEolPlanningCycles(BigDecimal eolPlanningCycles) {
		this.eolPlanningCycles = eolPlanningCycles;
	}

	public BigDecimal getEolPlanningDays() {
		return this.eolPlanningDays;
	}

	public void setEolPlanningDays(BigDecimal eolPlanningDays) {
		this.eolPlanningDays = eolPlanningDays;
	}

	public BigDecimal getEolPlanningHours() {
		return this.eolPlanningHours;
	}

	public void setEolPlanningHours(BigDecimal eolPlanningHours) {
		this.eolPlanningHours = eolPlanningHours;
	}

	public String getEtops() {
		return this.etops;
	}

	public void setEtops(String etops) {
		this.etops = etops;
	}

	public String getEtopsDowngrade() {
		return this.etopsDowngrade;
	}

	public void setEtopsDowngrade(String etopsDowngrade) {
		this.etopsDowngrade = etopsDowngrade;
	}

	public Date getEtopsDowngradeDate() {
		return this.etopsDowngradeDate;
	}

	public void setEtopsDowngradeDate(Date etopsDowngradeDate) {
		this.etopsDowngradeDate = etopsDowngradeDate;
	}

	public BigDecimal getEtopsDowngradeTransaction() {
		return this.etopsDowngradeTransaction;
	}

	public void setEtopsDowngradeTransaction(BigDecimal etopsDowngradeTransaction) {
		this.etopsDowngradeTransaction = etopsDowngradeTransaction;
	}

	public String getEtopsDowngradeUser() {
		return this.etopsDowngradeUser;
	}

	public void setEtopsDowngradeUser(String etopsDowngradeUser) {
		this.etopsDowngradeUser = etopsDowngradeUser;
	}

	public BigDecimal getEtopsMinutes() {
		return this.etopsMinutes;
	}

	public void setEtopsMinutes(BigDecimal etopsMinutes) {
		this.etopsMinutes = etopsMinutes;
	}

	public String getExcludeFromMxplan() {
		return this.excludeFromMxplan;
	}

	public void setExcludeFromMxplan(String excludeFromMxplan) {
		this.excludeFromMxplan = excludeFromMxplan;
	}

	public Date getFirstRevenueDate() {
		return this.firstRevenueDate;
	}

	public void setFirstRevenueDate(Date firstRevenueDate) {
		this.firstRevenueDate = firstRevenueDate;
	}

	public String getFleetAge() {
		return this.fleetAge;
	}

	public void setFleetAge(String fleetAge) {
		this.fleetAge = fleetAge;
	}

	public String getFlight() {
		return this.flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getFlightStatus() {
		return this.flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}

	public String getFopAcSeries() {
		return this.fopAcSeries;
	}

	public void setFopAcSeries(String fopAcSeries) {
		this.fopAcSeries = fopAcSeries;
	}

	public String getFopAcType() {
		return this.fopAcType;
	}

	public void setFopAcType(String fopAcType) {
		this.fopAcType = fopAcType;
	}

	public BigDecimal getFormNo() {
		return this.formNo;
	}

	public void setFormNo(BigDecimal formNo) {
		this.formNo = formNo;
	}

	public String getFreighterPaxCargo() {
		return this.freighterPaxCargo;
	}

	public void setFreighterPaxCargo(String freighterPaxCargo) {
		this.freighterPaxCargo = freighterPaxCargo;
	}

	public BigDecimal getFuelCapacity() {
		return this.fuelCapacity;
	}

	public void setFuelCapacity(BigDecimal fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

	public String getFuelCapacityUnits() {
		return this.fuelCapacityUnits;
	}

	public void setFuelCapacityUnits(String fuelCapacityUnits) {
		this.fuelCapacityUnits = fuelCapacityUnits;
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

	public BigDecimal getGrbEngine1() {
		return this.grbEngine1;
	}

	public void setGrbEngine1(BigDecimal grbEngine1) {
		this.grbEngine1 = grbEngine1;
	}

	public BigDecimal getGrbEngine2() {
		return this.grbEngine2;
	}

	public void setGrbEngine2(BigDecimal grbEngine2) {
		this.grbEngine2 = grbEngine2;
	}

	public String getHub() {
		return this.hub;
	}

	public void setHub(String hub) {
		this.hub = hub;
	}

	public String getIfe() {
		return this.ife;
	}

	public void setIfe(String ife) {
		this.ife = ife;
	}

	public String getInsuranceCertificate() {
		return this.insuranceCertificate;
	}

	public void setInsuranceCertificate(String insuranceCertificate) {
		this.insuranceCertificate = insuranceCertificate;
	}

	public String getInsuranceCompany() {
		return this.insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public Date getInsuranceExpiry() {
		return this.insuranceExpiry;
	}

	public void setInsuranceExpiry(Date insuranceExpiry) {
		this.insuranceExpiry = insuranceExpiry;
	}

	public String getIpc() {
		return this.ipc;
	}

	public void setIpc(String ipc) {
		this.ipc = ipc;
	}

	public String getLastAcRegistration() {
		return this.lastAcRegistration;
	}

	public void setLastAcRegistration(String lastAcRegistration) {
		this.lastAcRegistration = lastAcRegistration;
	}

	public String getLastAcRegistrationChgBy() {
		return this.lastAcRegistrationChgBy;
	}

	public void setLastAcRegistrationChgBy(String lastAcRegistrationChgBy) {
		this.lastAcRegistrationChgBy = lastAcRegistrationChgBy;
	}

	public Date getLastAcRegistrationChgOn() {
		return this.lastAcRegistrationChgOn;
	}

	public void setLastAcRegistrationChgOn(Date lastAcRegistrationChgOn) {
		this.lastAcRegistrationChgOn = lastAcRegistrationChgOn;
	}

	public Date getLastAcRegistrationDate() {
		return this.lastAcRegistrationDate;
	}

	public void setLastAcRegistrationDate(Date lastAcRegistrationDate) {
		this.lastAcRegistrationDate = lastAcRegistrationDate;
	}

	public Date getLastDateFlightLogApplied() {
		return this.lastDateFlightLogApplied;
	}

	public void setLastDateFlightLogApplied(Date lastDateFlightLogApplied) {
		this.lastDateFlightLogApplied = lastDateFlightLogApplied;
	}

	public String getLhtAirborne() {
		return this.lhtAirborne;
	}

	public void setLhtAirborne(String lhtAirborne) {
		this.lhtAirborne = lhtAirborne;
	}

	public String getLicenseCode() {
		return this.licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getLivery() {
		return this.livery;
	}

	public void setLivery(String livery) {
		this.livery = livery;
	}

	public BigDecimal getLogbookNo() {
		return this.logbookNo;
	}

	public void setLogbookNo(BigDecimal logbookNo) {
		this.logbookNo = logbookNo;
	}

	public String getManufacture() {
		return this.manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public Date getManufactureDate() {
		return this.manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public String getMaxCruisingAlt() {
		return this.maxCruisingAlt;
	}

	public void setMaxCruisingAlt(String maxCruisingAlt) {
		this.maxCruisingAlt = maxCruisingAlt;
	}

	public BigDecimal getMaxLandingWeight() {
		return this.maxLandingWeight;
	}

	public void setMaxLandingWeight(BigDecimal maxLandingWeight) {
		this.maxLandingWeight = maxLandingWeight;
	}

	public String getMaxLandingWeightUnits() {
		return this.maxLandingWeightUnits;
	}

	public void setMaxLandingWeightUnits(String maxLandingWeightUnits) {
		this.maxLandingWeightUnits = maxLandingWeightUnits;
	}

	public BigDecimal getMaxTaxiWeight() {
		return this.maxTaxiWeight;
	}

	public void setMaxTaxiWeight(BigDecimal maxTaxiWeight) {
		this.maxTaxiWeight = maxTaxiWeight;
	}

	public String getMaxTaxiWeightUnits() {
		return this.maxTaxiWeightUnits;
	}

	public void setMaxTaxiWeightUnits(String maxTaxiWeightUnits) {
		this.maxTaxiWeightUnits = maxTaxiWeightUnits;
	}

	public String getMaxToUnits() {
		return this.maxToUnits;
	}

	public void setMaxToUnits(String maxToUnits) {
		this.maxToUnits = maxToUnits;
	}

	public BigDecimal getMaxToWeight() {
		return this.maxToWeight;
	}

	public void setMaxToWeight(BigDecimal maxToWeight) {
		this.maxToWeight = maxToWeight;
	}

	public BigDecimal getMaxZeroFuelWeight() {
		return this.maxZeroFuelWeight;
	}

	public void setMaxZeroFuelWeight(BigDecimal maxZeroFuelWeight) {
		this.maxZeroFuelWeight = maxZeroFuelWeight;
	}

	public String getMaxZeroFuelWeightUnits() {
		return this.maxZeroFuelWeightUnits;
	}

	public void setMaxZeroFuelWeightUnits(String maxZeroFuelWeightUnits) {
		this.maxZeroFuelWeightUnits = maxZeroFuelWeightUnits;
	}

	public String getMaximunTrust() {
		return this.maximunTrust;
	}

	public void setMaximunTrust(String maximunTrust) {
		this.maximunTrust = maximunTrust;
	}

	public String getMmEffectivity() {
		return this.mmEffectivity;
	}

	public void setMmEffectivity(String mmEffectivity) {
		this.mmEffectivity = mmEffectivity;
	}

	public String getMmo() {
		return this.mmo;
	}

	public void setMmo(String mmo) {
		this.mmo = mmo;
	}

	public String getModeS() {
		return this.modeS;
	}

	public void setModeS(String modeS) {
		this.modeS = modeS;
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

	public BigDecimal getNumberOfSeat1() {
		return this.numberOfSeat1;
	}

	public void setNumberOfSeat1(BigDecimal numberOfSeat1) {
		this.numberOfSeat1 = numberOfSeat1;
	}

	public BigDecimal getNumberOfSeat2() {
		return this.numberOfSeat2;
	}

	public void setNumberOfSeat2(BigDecimal numberOfSeat2) {
		this.numberOfSeat2 = numberOfSeat2;
	}

	public BigDecimal getNumberOfSeat3() {
		return this.numberOfSeat3;
	}

	public void setNumberOfSeat3(BigDecimal numberOfSeat3) {
		this.numberOfSeat3 = numberOfSeat3;
	}

	public BigDecimal getNumberOfSeat4() {
		return this.numberOfSeat4;
	}

	public void setNumberOfSeat4(BigDecimal numberOfSeat4) {
		this.numberOfSeat4 = numberOfSeat4;
	}

	public String getNumberOfSeatType1() {
		return this.numberOfSeatType1;
	}

	public void setNumberOfSeatType1(String numberOfSeatType1) {
		this.numberOfSeatType1 = numberOfSeatType1;
	}

	public String getNumberOfSeatType2() {
		return this.numberOfSeatType2;
	}

	public void setNumberOfSeatType2(String numberOfSeatType2) {
		this.numberOfSeatType2 = numberOfSeatType2;
	}

	public String getNumberOfSeatType3() {
		return this.numberOfSeatType3;
	}

	public void setNumberOfSeatType3(String numberOfSeatType3) {
		this.numberOfSeatType3 = numberOfSeatType3;
	}

	public String getNumberOfSeatType4() {
		return this.numberOfSeatType4;
	}

	public void setNumberOfSeatType4(String numberOfSeatType4) {
		this.numberOfSeatType4 = numberOfSeatType4;
	}

	public BigDecimal getOperatingEmptyWeight() {
		return this.operatingEmptyWeight;
	}

	public void setOperatingEmptyWeight(BigDecimal operatingEmptyWeight) {
		this.operatingEmptyWeight = operatingEmptyWeight;
	}

	public String getOperatingEmptyWeightUnits() {
		return this.operatingEmptyWeightUnits;
	}

	public void setOperatingEmptyWeightUnits(String operatingEmptyWeightUnits) {
		this.operatingEmptyWeightUnits = operatingEmptyWeightUnits;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperatorDate() {
		return this.operatorDate;
	}

	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}

	public String getOriginalOperator() {
		return this.originalOperator;
	}

	public void setOriginalOperator(String originalOperator) {
		this.originalOperator = originalOperator;
	}

	public String getOverNightSite() {
		return this.overNightSite;
	}

	public void setOverNightSite(String overNightSite) {
		this.overNightSite = overNightSite;
	}

	public String getOverNightStation() {
		return this.overNightStation;
	}

	public void setOverNightStation(String overNightStation) {
		this.overNightStation = overNightStation;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getOwnerDate() {
		return this.ownerDate;
	}

	public void setOwnerDate(Date ownerDate) {
		this.ownerDate = ownerDate;
	}

	public Date getPdscExpireDate() {
		return this.pdscExpireDate;
	}

	public void setPdscExpireDate(Date pdscExpireDate) {
		this.pdscExpireDate = pdscExpireDate;
	}

	public String getPlanner() {
		return this.planner;
	}

	public void setPlanner(String planner) {
		this.planner = planner;
	}

	public String getPreviousAc() {
		return this.previousAc;
	}

	public void setPreviousAc(String previousAc) {
		this.previousAc = previousAc;
	}

	public String getPreviousOverNightStation() {
		return this.previousOverNightStation;
	}

	public void setPreviousOverNightStation(String previousOverNightStation) {
		this.previousOverNightStation = previousOverNightStation;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public BigDecimal getRange() {
		return this.range;
	}

	public void setRange(BigDecimal range) {
		this.range = range;
	}

	public BigDecimal getRonHours() {
		return this.ronHours;
	}

	public void setRonHours(BigDecimal ronHours) {
		this.ronHours = ronHours;
	}

	public BigDecimal getRotorCounter1() {
		return this.rotorCounter1;
	}

	public void setRotorCounter1(BigDecimal rotorCounter1) {
		this.rotorCounter1 = rotorCounter1;
	}

	public BigDecimal getRotorCounter2() {
		return this.rotorCounter2;
	}

	public void setRotorCounter2(BigDecimal rotorCounter2) {
		this.rotorCounter2 = rotorCounter2;
	}

	public BigDecimal getRotorCounter3() {
		return this.rotorCounter3;
	}

	public void setRotorCounter3(BigDecimal rotorCounter3) {
		this.rotorCounter3 = rotorCounter3;
	}

	public BigDecimal getRotorCounter4() {
		return this.rotorCounter4;
	}

	public void setRotorCounter4(BigDecimal rotorCounter4) {
		this.rotorCounter4 = rotorCounter4;
	}

	public BigDecimal getRotorCounter5() {
		return this.rotorCounter5;
	}

	public void setRotorCounter5(BigDecimal rotorCounter5) {
		this.rotorCounter5 = rotorCounter5;
	}

	public String getRvsm() {
		return this.rvsm;
	}

	public void setRvsm(String rvsm) {
		this.rvsm = rvsm;
	}

	public BigDecimal getRvsmFlightLevelEnd() {
		return this.rvsmFlightLevelEnd;
	}

	public void setRvsmFlightLevelEnd(BigDecimal rvsmFlightLevelEnd) {
		this.rvsmFlightLevelEnd = rvsmFlightLevelEnd;
	}

	public BigDecimal getRvsmFlightLevelStart() {
		return this.rvsmFlightLevelStart;
	}

	public void setRvsmFlightLevelStart(BigDecimal rvsmFlightLevelStart) {
		this.rvsmFlightLevelStart = rvsmFlightLevelStart;
	}

	public String getSampling() {
		return this.sampling;
	}

	public void setSampling(String sampling) {
		this.sampling = sampling;
	}

	public String getSelcal() {
		return this.selcal;
	}

	public void setSelcal(String selcal) {
		this.selcal = selcal;
	}

	public Date getServiceDate() {
		return this.serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getShortFieldPerformance() {
		return this.shortFieldPerformance;
	}

	public void setShortFieldPerformance(String shortFieldPerformance) {
		this.shortFieldPerformance = shortFieldPerformance;
	}

	public String getSpec2kAc() {
		return this.spec2kAc;
	}

	public void setSpec2kAc(String spec2kAc) {
		this.spec2kAc = spec2kAc;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTPed() {
		return this.tPed;
	}

	public void setTPed(String tPed) {
		this.tPed = tPed;
	}

	public BigDecimal getTotalAcTimeDeliveryCycles() {
		return this.totalAcTimeDeliveryCycles;
	}

	public void setTotalAcTimeDeliveryCycles(BigDecimal totalAcTimeDeliveryCycles) {
		this.totalAcTimeDeliveryCycles = totalAcTimeDeliveryCycles;
	}

	public BigDecimal getTotalAcTimeDeliveryHours() {
		return this.totalAcTimeDeliveryHours;
	}

	public void setTotalAcTimeDeliveryHours(BigDecimal totalAcTimeDeliveryHours) {
		this.totalAcTimeDeliveryHours = totalAcTimeDeliveryHours;
	}

	public BigDecimal getTotalAcTimeDeliveryMinutes() {
		return this.totalAcTimeDeliveryMinutes;
	}

	public void setTotalAcTimeDeliveryMinutes(BigDecimal totalAcTimeDeliveryMinutes) {
		this.totalAcTimeDeliveryMinutes = totalAcTimeDeliveryMinutes;
	}

	public BigDecimal getTotalHoistCycles() {
		return this.totalHoistCycles;
	}

	public void setTotalHoistCycles(BigDecimal totalHoistCycles) {
		this.totalHoistCycles = totalHoistCycles;
	}

	public BigDecimal getTotalHoistHours() {
		return this.totalHoistHours;
	}

	public void setTotalHoistHours(BigDecimal totalHoistHours) {
		this.totalHoistHours = totalHoistHours;
	}

	public BigDecimal getTotalHoistMinutes() {
		return this.totalHoistMinutes;
	}

	public void setTotalHoistMinutes(BigDecimal totalHoistMinutes) {
		this.totalHoistMinutes = totalHoistMinutes;
	}

	public BigDecimal getTotalHoistSeconds() {
		return this.totalHoistSeconds;
	}

	public void setTotalHoistSeconds(BigDecimal totalHoistSeconds) {
		this.totalHoistSeconds = totalHoistSeconds;
	}

	public BigDecimal getTotalHookCycles() {
		return this.totalHookCycles;
	}

	public void setTotalHookCycles(BigDecimal totalHookCycles) {
		this.totalHookCycles = totalHookCycles;
	}

	public BigDecimal getTotalHookHours() {
		return this.totalHookHours;
	}

	public void setTotalHookHours(BigDecimal totalHookHours) {
		this.totalHookHours = totalHookHours;
	}

	public BigDecimal getTotalHookMinutes() {
		return this.totalHookMinutes;
	}

	public void setTotalHookMinutes(BigDecimal totalHookMinutes) {
		this.totalHookMinutes = totalHookMinutes;
	}

	public BigDecimal getTotalLandings() {
		return this.totalLandings;
	}

	public void setTotalLandings(BigDecimal totalLandings) {
		this.totalLandings = totalLandings;
	}

	public BigDecimal getTotalMoment() {
		return this.totalMoment;
	}

	public void setTotalMoment(BigDecimal totalMoment) {
		this.totalMoment = totalMoment;
	}

	public String getTotalMomentUnits() {
		return this.totalMomentUnits;
	}

	public void setTotalMomentUnits(String totalMomentUnits) {
		this.totalMomentUnits = totalMomentUnits;
	}

	public String getVariableEngineeringNo() {
		return this.variableEngineeringNo;
	}

	public void setVariableEngineeringNo(String variableEngineeringNo) {
		this.variableEngineeringNo = variableEngineeringNo;
	}

	public String getWinglet() {
		return this.winglet;
	}

	public void setWinglet(String winglet) {
		this.winglet = winglet;
	}

	public String getWoCategory() {
		return this.woCategory;
	}

	public void setWoCategory(String woCategory) {
		this.woCategory = woCategory;
	}

	public BigDecimal getWoDuration() {
		return this.woDuration;
	}

	public void setWoDuration(BigDecimal woDuration) {
		this.woDuration = woDuration;
	}

	public String getWoExpenditureCode() {
		return this.woExpenditureCode;
	}

	public void setWoExpenditureCode(String woExpenditureCode) {
		this.woExpenditureCode = woExpenditureCode;
	}

	public BigDecimal getWoStartTime() {
		return this.woStartTime;
	}

	public void setWoStartTime(BigDecimal woStartTime) {
		this.woStartTime = woStartTime;
	}

	public BigDecimal getWoStartTimeMinute() {
		return this.woStartTimeMinute;
	}

	public void setWoStartTimeMinute(BigDecimal woStartTimeMinute) {
		this.woStartTimeMinute = woStartTimeMinute;
	}

}