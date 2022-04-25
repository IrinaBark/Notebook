package by.epam.note.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.epam.note.bean.Note;
import by.epam.note.bean.Notebook;
import by.epam.note.util.NotebookUtil;

public class NotebookLogic {

	public Notebook search(LocalDate date, Notebook noteB) {

		Notebook result;
		List<Note> notesByDate = new ArrayList<Note>();

		for (Note n : noteB.getNotebook()) {
			if (n.getDate().equals(date)) {
				notesByDate.add(n);
			}
		}
		result = new Notebook(notesByDate);
		return result;
	}

	public Notebook search(String content, Notebook noteB) {

		Notebook result;
		List<Note> notesByContent = new ArrayList<Note>();
		boolean notFound = true;
		String note;

		content = content.toLowerCase();

		for (Note n : noteB.getNotebook()) {
			note = n.getNote().toLowerCase();
			if (note.contains(content)) {
				notFound = false;
				notesByContent.add(n);
			}
		}
		if (notFound) {
			System.out.println("Запись с искомым содержимым не найдена!");
		}
		result = new Notebook(notesByContent);
		return result;
	}

	public void delete(Notebook noteB) {
		if (noteB.getNotebook().size() == 0) {
			System.out.println("Блокнот пуст!");
		} else {
			noteB.getNotebook().remove(noteB.getNotebook().size() - 1);
		}
	}

	public void delete(String content, Notebook noteB) {
		String note;
		content = content.toLowerCase();
		for (int i = 0; i < noteB.getNotebook().size(); i++) {
			note = noteB.getNotebook().get(i).getNote().toLowerCase();
			if (note.contains(content)) {
				noteB.getNotebook().remove(i);
			}
		}
	}

	public void delete(LocalDate date, Notebook noteB) {
		for (int i = 0; i < noteB.getNotebook().size(); i++) {
			if (noteB.getNotebook().get(i).getDate().equals(date)) {
				noteB.getNotebook().remove(i);
			}
		}
	}

	public void createNewNote(Notebook notebook, String fileName) {

		String splitter = "#";
		File file = new File(fileName);
		System.out.println("Введите текст для записи >");

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		Note newNote = new Note(sc.nextLine() + splitter);
		notebook.getNotebook().add(newNote);
		NotebookUtil.writeToFile(file, newNote.getNote());

	}

	public void overwriteFile(File fileNote, Notebook notebook) {
		try (FileWriter fileWriter = new FileWriter(fileNote, false)) {
			fileWriter.write("");
			fileWriter.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try (FileWriter fileWriter = new FileWriter(fileNote, true)) {
			for (Note n : notebook.getNotebook()) {
				fileWriter.write(n.getNote());
				fileWriter.flush();
			}
			fileWriter.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
