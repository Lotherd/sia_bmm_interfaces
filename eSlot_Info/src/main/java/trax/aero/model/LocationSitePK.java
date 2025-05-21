package trax.aero.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LOCATION_SITE database table.
 * 
 */
@Embeddable
public class LocationSitePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String location;

	private String site;

	public LocationSitePK() {
	}
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSite() {
		return this.site;
	}
	public void setSite(String site) {
		this.site = site;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LocationSitePK)) {
			return false;
		}
		LocationSitePK castOther = (LocationSitePK)other;
		return 
			this.location.equals(castOther.location)
			&& this.site.equals(castOther.site);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.location.hashCode();
		hash = hash * prime + this.site.hashCode();
		
		return hash;
	}
}