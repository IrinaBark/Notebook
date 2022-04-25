package by.epam.note.bean;

import java.time.LocalDate;
import java.util.Objects;

public class Note {
	
	private String note;
	private LocalDate date;
	
	public Note(String note) {
		this.note = note;
		date = LocalDate.now();
	}

	public Note() {
		this("");
	}
    public Note (String note, LocalDate date) {
    	this.note = note;
    	this.date = date;
    }

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Note [note=" + note + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, note);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		return Objects.equals(date, other.date) && Objects.equals(note, other.note);
	}
}