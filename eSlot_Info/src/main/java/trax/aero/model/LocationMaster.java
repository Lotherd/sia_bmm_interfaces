package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LOCATION_MASTER database table.
 * 
 */
@Entity
@Table(name="LOCATION_MASTER")
@NamedQuery(name="LocationMaster.findAll", query="SELECT l FROM LocationMaster l")
public class LocationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String location;

	@Column(name="ALLOWED_MAINTENANCE_LOCATION")
	private String allowedMaintenanceLocation;

	@Column(name="AOG_FACILITY")
	private String aogFacility;

	@Column(name="AUTOMATED_LOCATION")
	private String automatedLocation;

	@Column(name="AUTOMATED_REMOVAL_LOCATION")
	private String automatedRemovalLocation;

	@Column(name="AUTOMATED_WAREHOUSE")
	private String automatedWarehouse;

	@Column(name="AUTOMATED_WAREHOUSE_BIN")
	private String automatedWarehouseBin;

	@Column(name="BILL_TO_LOCATION")
	private String billToLocation;

	@Column(name="BLOB_NO")
	private BigDecimal blobNo;

	@Column(name="BONDED_WAREHOUSE")
	private String bondedWarehouse;

	private String category;

	@Column(name="CENTROLLIZED_INVENTORY")
	private String centrollizedInventory;

	@Column(name="CHECK_L")
	private String checkL;

	@Column(name="CLEARING_HOUSE_LOCATION")
	private String clearingHouseLocation;

	private BigDecimal contact;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DAY_HOUR")
	private BigDecimal dayHour;

	@Column(name="DAY_MINUTE")
	private BigDecimal dayMinute;

	@Column(name="DAY_RATE")
	private BigDecimal dayRate;

	@Column(name="DAY1_RATE")
	private BigDecimal day1Rate;

	@Column(name="DAY2_RATE")
	private BigDecimal day2Rate;

	@Column(name="DAY3_RATE")
	private BigDecimal day3Rate;

	@Column(name="DAY4_RATE")
	private BigDecimal day4Rate;

	@Column(name="DAY5_RATE")
	private BigDecimal day5Rate;

	@Column(name="DAY6_RATE")
	private BigDecimal day6Rate;

	@Column(name="DAY7_RATE")
	private BigDecimal day7Rate;

	@Column(name="DEFAULT_RI_BIN")
	private String defaultRiBin;

	@Column(name="EX_TAX_PERCENT")
	private BigDecimal exTaxPercent;

	@Column(name="FINANCIAL_SYSTEM_LOCATION")
	private String financialSystemLocation;

	@Column(name="FROM_HOUR")
	private BigDecimal fromHour;

	@Column(name="GSE_STATION")
	private String gseStation;

	private String hub;

	@Column(name="INTL_LOC")
	private String intlLoc;

	private String inventory;

	@Column(name="INVENTORY_PROVIDER")
	private String inventoryProvider;

	@Column(name="INVENTORY_QUARANTINE")
	private String inventoryQuarantine;

	private String ipum;

	@Column(name="\"LHT ADDRESS CODE\"")
	private String lht_address_code;

	@Column(name="LHT_ADDRESS_CODE")
	private String lhtAddressCode;

	@Column(name="LHT_STATION")
	private String lhtStation;

	private String library;

	private String line;

	@Column(name="LO_TAX_PERCENT")
	private BigDecimal loTaxPercent;

	@Column(name="LOAD_TO_POINT")
	private BigDecimal loadToPoint;

	@Column(name="LOCATION_DESCRIPTION")
	private String locationDescription;

	@Column(name="LOCATION_PARTS_SELECTED")
	private String locationPartsSelected;

	@Column(name="MAIL_ADDRESS_1")
	private String mailAddress1;

	@Column(name="MAIL_ADDRESS_2")
	private String mailAddress2;

	@Column(name="MAIL_CELL")
	private String mailCell;

	@Column(name="MAIL_CITY")
	private String mailCity;

	@Column(name="MAIL_COUNTRY")
	private String mailCountry;

	@Column(name="MAIL_EMAIL")
	private String mailEmail;

	@Column(name="MAIL_FAX")
	private String mailFax;

	@Column(name="MAIL_MAIN_CONTACT")
	private String mailMainContact;

	@Column(name="MAIL_PHONE")
	private String mailPhone;

	@Column(name="MAIL_POST")
	private String mailPost;

	@Column(name="MAIL_SITA")
	private String mailSita;

	@Column(name="MAIL_STATE")
	private String mailState;

	@Column(name="MAIN_LOCATION")
	private String mainLocation;

	@Column(name="MAINTENANCE_FACILITY")
	private String maintenanceFacility;

	@Column(name="MAINTENANCE_PROVIDER")
	private String maintenanceProvider;

	@Column(name="MANHOURS_FACTOR")
	private BigDecimal manhoursFactor;

	@Column(name="MASTER_LOCATION")
	private String masterLocation;

	@Column(name="MASTER_LOCATION_SEQUENCE")
	private BigDecimal masterLocationSequence;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	private String mpum;

	@Column(name="NIGHT_HOUR")
	private BigDecimal nightHour;

	@Column(name="NIGHT_MINUTE")
	private BigDecimal nightMinute;

	@Column(name="NIGHT_RATE")
	private BigDecimal nightRate;

	private BigDecimal notes;

	@Column(name="OVER_NIGHT")
	private String overNight;

	@Column(name="PO_TAX_PERCENT")
	private BigDecimal poTaxPercent;

	@Column(name="QUARANTINE_RELATED_BIN")
	private String quarantineRelatedBin;

	@Column(name="QUARANTINE_RELATED_LOCATION")
	private String quarantineRelatedLocation;

	@Column(name="QUARANTINE_US_CODE")
	private String quarantineUsCode;

	@Column(name="RATE_PERCENTAGE")
	private BigDecimal ratePercentage;

	private String region;

	@Column(name="RELATED_MAIN_WAREHOUSE")
	private String relatedMainWarehouse;

	@Column(name="REQUISITION_PRIORITY")
	private String requisitionPriority;

	@Column(name="REQUISITION_PRIORITY_2")
	private String requisitionPriority2;

	@Column(name="REQUISITION_USER1")
	private String requisitionUser1;

	@Column(name="REQUISITION_USER2")
	private String requisitionUser2;

	@Column(name="REQUISITION_USER3")
	private String requisitionUser3;

	@Column(name="REQUISITION_USER4")
	private String requisitionUser4;

	@Column(name="RO_TAX_PERCENT")
	private BigDecimal roTaxPercent;

	@Column(name="SHIPPING_ADDRESS_1")
	private String shippingAddress1;

	@Column(name="SHIPPING_ADDRESS_2")
	private String shippingAddress2;

	@Column(name="SHIPPING_CELL")
	private String shippingCell;

	@Column(name="SHIPPING_CITY")
	private String shippingCity;

	@Column(name="SHIPPING_COUNRTY")
	private String shippingCounrty;

	@Column(name="SHIPPING_EMAIL")
	private String shippingEmail;

	@Column(name="SHIPPING_FAX")
	private String shippingFax;

	@Column(name="SHIPPING_MAIN_CONTACT")
	private String shippingMainContact;

	@Column(name="SHIPPING_PHONE")
	private String shippingPhone;

	@Column(name="SHIPPING_POST")
	private String shippingPost;

	@Column(name="SHIPPING_SITA")
	private String shippingSita;

	@Column(name="SHIPPING_STATE")
	private String shippingState;

	@Column(name="SPARES_ON_PLANNING")
	private String sparesOnPlanning;

	@Column(name="SPEC2K_ROS_LOCATION")
	private String spec2kRosLocation;

	private String station;

	@Column(name="STATION_CODE")
	private String stationCode;

	@Column(name="SV_TAX_PERCENT")
	private BigDecimal svTaxPercent;

	@Column(name="TIME_ZONE_NAME")
	private String timeZoneName;

	@Column(name="TO_HOUR")
	private BigDecimal toHour;

	private String training;

	private String turn;

	@Column(name="UNPAVED_RUNAWAY")
	private String unpavedRunaway;

	private String visa;

	public LocationMaster() {
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAllowedMaintenanceLocation() {
		return this.allowedMaintenanceLocation;
	}

	public void setAllowedMaintenanceLocation(String allowedMaintenanceLocation) {
		this.allowedMaintenanceLocation = allowedMaintenanceLocation;
	}

	public String getAogFacility() {
		return this.aogFacility;
	}

	public void setAogFacility(String aogFacility) {
		this.aogFacility = aogFacility;
	}

	public String getAutomatedLocation() {
		return this.automatedLocation;
	}

	public void setAutomatedLocation(String automatedLocation) {
		this.automatedLocation = automatedLocation;
	}

	public String getAutomatedRemovalLocation() {
		return this.automatedRemovalLocation;
	}

	public void setAutomatedRemovalLocation(String automatedRemovalLocation) {
		this.automatedRemovalLocation = automatedRemovalLocation;
	}

	public String getAutomatedWarehouse() {
		return this.automatedWarehouse;
	}

	public void setAutomatedWarehouse(String automatedWarehouse) {
		this.automatedWarehouse = automatedWarehouse;
	}

	public String getAutomatedWarehouseBin() {
		return this.automatedWarehouseBin;
	}

	public void setAutomatedWarehouseBin(String automatedWarehouseBin) {
		this.automatedWarehouseBin = automatedWarehouseBin;
	}

	public String getBillToLocation() {
		return this.billToLocation;
	}

	public void setBillToLocation(String billToLocation) {
		this.billToLocation = billToLocation;
	}

	public BigDecimal getBlobNo() {
		return this.blobNo;
	}

	public void setBlobNo(BigDecimal blobNo) {
		this.blobNo = blobNo;
	}

	public String getBondedWarehouse() {
		return this.bondedWarehouse;
	}

	public void setBondedWarehouse(String bondedWarehouse) {
		this.bondedWarehouse = bondedWarehouse;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCentrollizedInventory() {
		return this.centrollizedInventory;
	}

	public void setCentrollizedInventory(String centrollizedInventory) {
		this.centrollizedInventory = centrollizedInventory;
	}

	public String getCheckL() {
		return this.checkL;
	}

	public void setCheckL(String checkL) {
		this.checkL = checkL;
	}

	public String getClearingHouseLocation() {
		return this.clearingHouseLocation;
	}

	public void setClearingHouseLocation(String clearingHouseLocation) {
		this.clearingHouseLocation = clearingHouseLocation;
	}

	public BigDecimal getContact() {
		return this.contact;
	}

	public void setContact(BigDecimal contact) {
		this.contact = contact;
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

	public BigDecimal getDayHour() {
		return this.dayHour;
	}

	public void setDayHour(BigDecimal dayHour) {
		this.dayHour = dayHour;
	}

	public BigDecimal getDayMinute() {
		return this.dayMinute;
	}

	public void setDayMinute(BigDecimal dayMinute) {
		this.dayMinute = dayMinute;
	}

	public BigDecimal getDayRate() {
		return this.dayRate;
	}

	public void setDayRate(BigDecimal dayRate) {
		this.dayRate = dayRate;
	}

	public BigDecimal getDay1Rate() {
		return this.day1Rate;
	}

	public void setDay1Rate(BigDecimal day1Rate) {
		this.day1Rate = day1Rate;
	}

	public BigDecimal getDay2Rate() {
		return this.day2Rate;
	}

	public void setDay2Rate(BigDecimal day2Rate) {
		this.day2Rate = day2Rate;
	}

	public BigDecimal getDay3Rate() {
		return this.day3Rate;
	}

	public void setDay3Rate(BigDecimal day3Rate) {
		this.day3Rate = day3Rate;
	}

	public BigDecimal getDay4Rate() {
		return this.day4Rate;
	}

	public void setDay4Rate(BigDecimal day4Rate) {
		this.day4Rate = day4Rate;
	}

	public BigDecimal getDay5Rate() {
		return this.day5Rate;
	}

	public void setDay5Rate(BigDecimal day5Rate) {
		this.day5Rate = day5Rate;
	}

	public BigDecimal getDay6Rate() {
		return this.day6Rate;
	}

	public void setDay6Rate(BigDecimal day6Rate) {
		this.day6Rate = day6Rate;
	}

	public BigDecimal getDay7Rate() {
		return this.day7Rate;
	}

	public void setDay7Rate(BigDecimal day7Rate) {
		this.day7Rate = day7Rate;
	}

	public String getDefaultRiBin() {
		return this.defaultRiBin;
	}

	public void setDefaultRiBin(String defaultRiBin) {
		this.defaultRiBin = defaultRiBin;
	}

	public BigDecimal getExTaxPercent() {
		return this.exTaxPercent;
	}

	public void setExTaxPercent(BigDecimal exTaxPercent) {
		this.exTaxPercent = exTaxPercent;
	}

	public String getFinancialSystemLocation() {
		return this.financialSystemLocation;
	}

	public void setFinancialSystemLocation(String financialSystemLocation) {
		this.financialSystemLocation = financialSystemLocation;
	}

	public BigDecimal getFromHour() {
		return this.fromHour;
	}

	public void setFromHour(BigDecimal fromHour) {
		this.fromHour = fromHour;
	}

	public String getGseStation() {
		return this.gseStation;
	}

	public void setGseStation(String gseStation) {
		this.gseStation = gseStation;
	}

	public String getHub() {
		return this.hub;
	}

	public void setHub(String hub) {
		this.hub = hub;
	}

	public String getIntlLoc() {
		return this.intlLoc;
	}

	public void setIntlLoc(String intlLoc) {
		this.intlLoc = intlLoc;
	}

	public String getInventory() {
		return this.inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getInventoryProvider() {
		return this.inventoryProvider;
	}

	public void setInventoryProvider(String inventoryProvider) {
		this.inventoryProvider = inventoryProvider;
	}

	public String getInventoryQuarantine() {
		return this.inventoryQuarantine;
	}

	public void setInventoryQuarantine(String inventoryQuarantine) {
		this.inventoryQuarantine = inventoryQuarantine;
	}

	public String getIpum() {
		return this.ipum;
	}

	public void setIpum(String ipum) {
		this.ipum = ipum;
	}

	public String getLht_address_code() {
		return this.lht_address_code;
	}

	public void setLht_address_code(String lht_address_code) {
		this.lht_address_code = lht_address_code;
	}

	public String getLhtAddressCode() {
		return this.lhtAddressCode;
	}

	public void setLhtAddressCode(String lhtAddressCode) {
		this.lhtAddressCode = lhtAddressCode;
	}

	public String getLhtStation() {
		return this.lhtStation;
	}

	public void setLhtStation(String lhtStation) {
		this.lhtStation = lhtStation;
	}

	public String getLibrary() {
		return this.library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getLine() {
		return this.line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public BigDecimal getLoTaxPercent() {
		return this.loTaxPercent;
	}

	public void setLoTaxPercent(BigDecimal loTaxPercent) {
		this.loTaxPercent = loTaxPercent;
	}

	public BigDecimal getLoadToPoint() {
		return this.loadToPoint;
	}

	public void setLoadToPoint(BigDecimal loadToPoint) {
		this.loadToPoint = loadToPoint;
	}

	public String getLocationDescription() {
		return this.locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getLocationPartsSelected() {
		return this.locationPartsSelected;
	}

	public void setLocationPartsSelected(String locationPartsSelected) {
		this.locationPartsSelected = locationPartsSelected;
	}

	public String getMailAddress1() {
		return this.mailAddress1;
	}

	public void setMailAddress1(String mailAddress1) {
		this.mailAddress1 = mailAddress1;
	}

	public String getMailAddress2() {
		return this.mailAddress2;
	}

	public void setMailAddress2(String mailAddress2) {
		this.mailAddress2 = mailAddress2;
	}

	public String getMailCell() {
		return this.mailCell;
	}

	public void setMailCell(String mailCell) {
		this.mailCell = mailCell;
	}

	public String getMailCity() {
		return this.mailCity;
	}

	public void setMailCity(String mailCity) {
		this.mailCity = mailCity;
	}

	public String getMailCountry() {
		return this.mailCountry;
	}

	public void setMailCountry(String mailCountry) {
		this.mailCountry = mailCountry;
	}

	public String getMailEmail() {
		return this.mailEmail;
	}

	public void setMailEmail(String mailEmail) {
		this.mailEmail = mailEmail;
	}

	public String getMailFax() {
		return this.mailFax;
	}

	public void setMailFax(String mailFax) {
		this.mailFax = mailFax;
	}

	public String getMailMainContact() {
		return this.mailMainContact;
	}

	public void setMailMainContact(String mailMainContact) {
		this.mailMainContact = mailMainContact;
	}

	public String getMailPhone() {
		return this.mailPhone;
	}

	public void setMailPhone(String mailPhone) {
		this.mailPhone = mailPhone;
	}

	public String getMailPost() {
		return this.mailPost;
	}

	public void setMailPost(String mailPost) {
		this.mailPost = mailPost;
	}

	public String getMailSita() {
		return this.mailSita;
	}

	public void setMailSita(String mailSita) {
		this.mailSita = mailSita;
	}

	public String getMailState() {
		return this.mailState;
	}

	public void setMailState(String mailState) {
		this.mailState = mailState;
	}

	public String getMainLocation() {
		return this.mainLocation;
	}

	public void setMainLocation(String mainLocation) {
		this.mainLocation = mainLocation;
	}

	public String getMaintenanceFacility() {
		return this.maintenanceFacility;
	}

	public void setMaintenanceFacility(String maintenanceFacility) {
		this.maintenanceFacility = maintenanceFacility;
	}

	public String getMaintenanceProvider() {
		return this.maintenanceProvider;
	}

	public void setMaintenanceProvider(String maintenanceProvider) {
		this.maintenanceProvider = maintenanceProvider;
	}

	public BigDecimal getManhoursFactor() {
		return this.manhoursFactor;
	}

	public void setManhoursFactor(BigDecimal manhoursFactor) {
		this.manhoursFactor = manhoursFactor;
	}

	public String getMasterLocation() {
		return this.masterLocation;
	}

	public void setMasterLocation(String masterLocation) {
		this.masterLocation = masterLocation;
	}

	public BigDecimal getMasterLocationSequence() {
		return this.masterLocationSequence;
	}

	public void setMasterLocationSequence(BigDecimal masterLocationSequence) {
		this.masterLocationSequence = masterLocationSequence;
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

	public String getMpum() {
		return this.mpum;
	}

	public void setMpum(String mpum) {
		this.mpum = mpum;
	}

	public BigDecimal getNightHour() {
		return this.nightHour;
	}

	public void setNightHour(BigDecimal nightHour) {
		this.nightHour = nightHour;
	}

	public BigDecimal getNightMinute() {
		return this.nightMinute;
	}

	public void setNightMinute(BigDecimal nightMinute) {
		this.nightMinute = nightMinute;
	}

	public BigDecimal getNightRate() {
		return this.nightRate;
	}

	public void setNightRate(BigDecimal nightRate) {
		this.nightRate = nightRate;
	}

	public BigDecimal getNotes() {
		return this.notes;
	}

	public void setNotes(BigDecimal notes) {
		this.notes = notes;
	}

	public String getOverNight() {
		return this.overNight;
	}

	public void setOverNight(String overNight) {
		this.overNight = overNight;
	}

	public BigDecimal getPoTaxPercent() {
		return this.poTaxPercent;
	}

	public void setPoTaxPercent(BigDecimal poTaxPercent) {
		this.poTaxPercent = poTaxPercent;
	}

	public String getQuarantineRelatedBin() {
		return this.quarantineRelatedBin;
	}

	public void setQuarantineRelatedBin(String quarantineRelatedBin) {
		this.quarantineRelatedBin = quarantineRelatedBin;
	}

	public String getQuarantineRelatedLocation() {
		return this.quarantineRelatedLocation;
	}

	public void setQuarantineRelatedLocation(String quarantineRelatedLocation) {
		this.quarantineRelatedLocation = quarantineRelatedLocation;
	}

	public String getQuarantineUsCode() {
		return this.quarantineUsCode;
	}

	public void setQuarantineUsCode(String quarantineUsCode) {
		this.quarantineUsCode = quarantineUsCode;
	}

	public BigDecimal getRatePercentage() {
		return this.ratePercentage;
	}

	public void setRatePercentage(BigDecimal ratePercentage) {
		this.ratePercentage = ratePercentage;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRelatedMainWarehouse() {
		return this.relatedMainWarehouse;
	}

	public void setRelatedMainWarehouse(String relatedMainWarehouse) {
		this.relatedMainWarehouse = relatedMainWarehouse;
	}

	public String getRequisitionPriority() {
		return this.requisitionPriority;
	}

	public void setRequisitionPriority(String requisitionPriority) {
		this.requisitionPriority = requisitionPriority;
	}

	public String getRequisitionPriority2() {
		return this.requisitionPriority2;
	}

	public void setRequisitionPriority2(String requisitionPriority2) {
		this.requisitionPriority2 = requisitionPriority2;
	}

	public String getRequisitionUser1() {
		return this.requisitionUser1;
	}

	public void setRequisitionUser1(String requisitionUser1) {
		this.requisitionUser1 = requisitionUser1;
	}

	public String getRequisitionUser2() {
		return this.requisitionUser2;
	}

	public void setRequisitionUser2(String requisitionUser2) {
		this.requisitionUser2 = requisitionUser2;
	}

	public String getRequisitionUser3() {
		return this.requisitionUser3;
	}

	public void setRequisitionUser3(String requisitionUser3) {
		this.requisitionUser3 = requisitionUser3;
	}

	public String getRequisitionUser4() {
		return this.requisitionUser4;
	}

	public void setRequisitionUser4(String requisitionUser4) {
		this.requisitionUser4 = requisitionUser4;
	}

	public BigDecimal getRoTaxPercent() {
		return this.roTaxPercent;
	}

	public void setRoTaxPercent(BigDecimal roTaxPercent) {
		this.roTaxPercent = roTaxPercent;
	}

	public String getShippingAddress1() {
		return this.shippingAddress1;
	}

	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}

	public String getShippingAddress2() {
		return this.shippingAddress2;
	}

	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}

	public String getShippingCell() {
		return this.shippingCell;
	}

	public void setShippingCell(String shippingCell) {
		this.shippingCell = shippingCell;
	}

	public String getShippingCity() {
		return this.shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingCounrty() {
		return this.shippingCounrty;
	}

	public void setShippingCounrty(String shippingCounrty) {
		this.shippingCounrty = shippingCounrty;
	}

	public String getShippingEmail() {
		return this.shippingEmail;
	}

	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}

	public String getShippingFax() {
		return this.shippingFax;
	}

	public void setShippingFax(String shippingFax) {
		this.shippingFax = shippingFax;
	}

	public String getShippingMainContact() {
		return this.shippingMainContact;
	}

	public void setShippingMainContact(String shippingMainContact) {
		this.shippingMainContact = shippingMainContact;
	}

	public String getShippingPhone() {
		return this.shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getShippingPost() {
		return this.shippingPost;
	}

	public void setShippingPost(String shippingPost) {
		this.shippingPost = shippingPost;
	}

	public String getShippingSita() {
		return this.shippingSita;
	}

	public void setShippingSita(String shippingSita) {
		this.shippingSita = shippingSita;
	}

	public String getShippingState() {
		return this.shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getSparesOnPlanning() {
		return this.sparesOnPlanning;
	}

	public void setSparesOnPlanning(String sparesOnPlanning) {
		this.sparesOnPlanning = sparesOnPlanning;
	}

	public String getSpec2kRosLocation() {
		return this.spec2kRosLocation;
	}

	public void setSpec2kRosLocation(String spec2kRosLocation) {
		this.spec2kRosLocation = spec2kRosLocation;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStationCode() {
		return this.stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public BigDecimal getSvTaxPercent() {
		return this.svTaxPercent;
	}

	public void setSvTaxPercent(BigDecimal svTaxPercent) {
		this.svTaxPercent = svTaxPercent;
	}

	public String getTimeZoneName() {
		return this.timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public BigDecimal getToHour() {
		return this.toHour;
	}

	public void setToHour(BigDecimal toHour) {
		this.toHour = toHour;
	}

	public String getTraining() {
		return this.training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getTurn() {
		return this.turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public String getUnpavedRunaway() {
		return this.unpavedRunaway;
	}

	public void setUnpavedRunaway(String unpavedRunaway) {
		this.unpavedRunaway = unpavedRunaway;
	}

	public String getVisa() {
		return this.visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

}