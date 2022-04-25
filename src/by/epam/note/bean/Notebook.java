package by.epam.note.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Notebook {

	private List<Note> notebook = new ArrayList<Note>();
	private int initialCapacity;

	public Notebook(List<Note> notebook) {
		this.notebook.addAll(notebook);
	}

	public Notebook(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	public Notebook() {
		this(new ArrayList<Note>());
	}

	public List<Note> getNotebook() {
		return notebook;
	}

	public void setNotebook(List<Note> notebook) {
		this.notebook = notebook;
	}

	public int getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	@Override
	public String toString() {
		return "Notebook [notebook=" + notebook + ", initialCapacity=" + initialCapacity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(initialCapacity, notebook);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notebook other = (Notebook) obj;
		return initialCapacity == other.initialCapacity && Objects.equals(notebook, other.notebook);
	}

}





