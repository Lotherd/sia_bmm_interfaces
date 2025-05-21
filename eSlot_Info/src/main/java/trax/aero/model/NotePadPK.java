package trax.aero.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * The primary key class for the NOTE_PAD database table.
 * 
 */
@Embeddable
public class NotePadPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;
    private long notes;
    private long notesLine;
    public NotePadPK() {
    }
    public NotePadPK(long notes, long notesLine)
    {
        super();
        this.notes = notes;
        this.notesLine = notesLine;
    }
    public long getNotes() {
        return this.notes;
    }
    public void setNotes(long notes) {
        this.notes = notes;
    }
    @Column(name="NOTES_LINE")
    public long getNotesLine() {
        return this.notesLine;
    }
    public void setNotesLine(long notesLine) {
        this.notesLine = notesLine;
    }
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotePadPK)) {
            return false;
        }
        NotePadPK castOther = (NotePadPK)other;
        return
            (this.notes == castOther.notes)
            && (this.notesLine == castOther.notesLine);
    }
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.notes ^ (this.notes >>> 32)));
        hash = hash * prime + ((int) (this.notesLine ^ (this.notesLine >>> 32)));
        return hash;
    }
}
