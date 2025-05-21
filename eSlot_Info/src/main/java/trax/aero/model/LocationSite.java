package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LOCATION_SITE database table.
 * 
 */
@Entity
@Table(name="LOCATION_SITE")
@NamedQuery(name="LocationSite.findAll", query="SELECT l FROM LocationSite l")
public class LocationSite implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LocationSitePK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DIMENSION_HEIGHT")
	private BigDecimal dimensionHeight;

	@Column(name="DIMENSION_LENGTH")
	private BigDecimal dimensionLength;

	@Column(name="DIMENSION_WIDTH")
	private BigDecimal dimensionWidth;

	@Column(name="LONG_TERM_SITE")
	private String longTermSite;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	private BigDecimal notes;

	@Column(name="OPS_SYSTEM_HANGAR_FLAG")
	private String opsSystemHangarFlag;

	@Column(name="PREVIOUS_SITE")
	private String previousSite;

	@Column(name="SEQ_NO")
	private BigDecimal seqNo;

	@Column(name="SITE_DESCRIPTION")
	private String siteDescription;

	@Column(name="SITE_GROUP")
	private String siteGroup;

	@Column(name="SITE_TYPE")
	private String siteType;

	private String status;

	private BigDecimal xpos;

	private BigDecimal ypos;

	public LocationSite() {
	}

	public LocationSitePK getId() {
		return this.id;
	}

	public void setId(LocationSitePK id) {
		this.id = id;
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

	public BigDecimal getDimensionHeight() {
		return this.dimensionHeight;
	}

	public void setDimensionHeight(BigDecimal dimensionHeight) {
		this.dimensionHeight = dimensionHeight;
	}

	public BigDecimal getDimensionLength() {
		return this.dimensionLength;
	}

	public void setDimensionLength(BigDecimal dimensionLength) {
		this.dimensionLength = dimensionLength;
	}

	public BigDecimal getDimensionWidth() {
		return this.dimensionWidth;
	}

	public void setDimensionWidth(BigDecimal dimensionWidth) {
		this.dimensionWidth = dimensionWidth;
	}

	public String getLongTermSite() {
		return this.longTermSite;
	}

	public void setLongTermSite(String longTermSite) {
		this.longTermSite = longTermSite;
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

	public String getOpsSystemHangarFlag() {
		return this.opsSystemHangarFlag;
	}

	public void setOpsSystemHangarFlag(String opsSystemHangarFlag) {
		this.opsSystemHangarFlag = opsSystemHangarFlag;
	}

	public String getPreviousSite() {
		return this.previousSite;
	}

	public void setPreviousSite(String previousSite) {
		this.previousSite = previousSite;
	}

	public BigDecimal getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

	public String getSiteDescription() {
		return this.siteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public String getSiteGroup() {
		return this.siteGroup;
	}

	public void setSiteGroup(String siteGroup) {
		this.siteGroup = siteGroup;
	}

	public String getSiteType() {
		return this.siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getXpos() {
		return this.xpos;
	}

	public void setXpos(BigDecimal xpos) {
		this.xpos = xpos;
	}

	public BigDecimal getYpos() {
		return this.ypos;
	}

	public void setYpos(BigDecimal ypos) {
		this.ypos = ypos;
	}

}