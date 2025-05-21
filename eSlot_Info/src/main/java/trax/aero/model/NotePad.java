package trax.aero.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * The persistent class for the NOTE_PAD database table.
 * 
 */
@Entity
@Table(name="NOTE_PAD")
@NamedQuery(name="NotePad.findAll", query="SELECT n FROM NotePad n")
public class NotePad implements Serializable {
    private static final long serialVersionUID = 1L;
    private NotePadPK id;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private String notesCategory;
    private String notesText;
    private String notesTittle;
    private String printNotes;
    public NotePad() {
    }
    @EmbeddedId
    public NotePadPK getId() {
        return this.id;
    }
    public void setId(NotePadPK id) {
        this.id = id;
    }
    @Column(name="CREATED_BY")
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE")
    public Date getCreatedDate() {
        return this.createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    @Column(name="MODIFIED_BY")
    public String getModifiedBy() {
        return this.modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFIED_DATE")
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    @Column(name="NOTES_CATEGORY")
    public String getNotesCategory() {
        return this.notesCategory;
    }
    public void setNotesCategory(String notesCategory) {
        this.notesCategory = notesCategory;
    }
    @Lob
    @Column(name="NOTES_TEXT")
    public String getNotesText() {
        return this.notesText;
    }
    public void setNotesText(String notesText) {
        this.notesText = notesText;
    }
    @Column(name="NOTES_TITTLE")
    public String getNotesTittle() {
        return this.notesTittle;
    }
    public void setNotesTittle(String notesTittle) {
        this.notesTittle = notesTittle;
    }
    @Column(name="PRINT_NOTES")
    public String getPrintNotes() {
        return this.printNotes;
    }
    public void setPrintNotes(String printNotes) {
        this.printNotes = printNotes;
    }
}