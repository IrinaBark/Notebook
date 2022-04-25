package by.epam.note.view;

import by.epam.note.bean.Note;
import by.epam.note.bean.Notebook;

public class NotebookView {
	public void print(Notebook noteB) {
		for (Note n : noteB.getNotebook()) {
			System.out.println(n);
		}
	}
}
