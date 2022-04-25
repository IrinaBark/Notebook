package by.epam.note.main;

import java.io.File;

import by.epam.note.bean.Notebook;
import by.epam.note.logic.NotebookLogic;
import by.epam.note.util.NotebookUtil;
import by.epam.note.view.NotebookView;

public class Main {

	public static void main(String[] args) {

		String fileName = "notebook.txt";

		File file = new File(fileName);

		Notebook nb = new Notebook();
		boolean isOn = true;

		start();

		while (isOn) {
			switch (selectOption()) {
			case 1: {
				NotebookLogic notebookLog = new NotebookLogic();
				notebookLog.createNewNote(nb, fileName);
			}
				break;
			case 2: {
				NotebookLogic nbl = new NotebookLogic();
				Notebook noteB = nbl.search(NotebookUtil.getDate(), nb);
				NotebookView nbv = new NotebookView();
				nbv.print(noteB);
			}
				break;
			case 3: {
				NotebookLogic nblog = new NotebookLogic();
				Notebook noteB1 = nblog.search(NotebookUtil.getContent(), nb);
				NotebookView nbv1 = new NotebookView();
				nbv1.print(noteB1);
			}
				break;
			case 4: {
				NotebookLogic nblog2 = new NotebookLogic();
				nblog2.delete(nb);
				nblog2.overwriteFile(file, nb);
			}
				break;
			case 5: {
				NotebookLogic nblog3 = new NotebookLogic();
				nblog3.delete(NotebookUtil.getDate(), nb);
				nblog3.overwriteFile(file, nb);
			}
				break;
			case 6: {
				NotebookLogic nblog4 = new NotebookLogic();
				nblog4.delete(NotebookUtil.getContent(), nb);
				nblog4.overwriteFile(file, nb);
			}
				break;
			case 7: {
				NotebookUtil.printFile(file);
			}
				break;
			case 8: {
				NotebookUtil.deleteFileContent(file);
			}
				break;
			case 9: {
				System.out.println("Всего доброго!");
				isOn = false;
			 }
		   }
		}
	}

	public static void start() {
		System.out.println("Добро пожаловать в приложение Блокнот! ");
	}

	public static int selectOption() {
		int number;
		System.out.println(System.lineSeparator() + "Для продолжения работы введите соответствующую цифру: "
				+ System.lineSeparator() + "1 - создать новую запись; "
				+ System.lineSeparator() + "2 - посмотреть записи по дате;"
				+ System.lineSeparator() + "3 - найти запись по содержимому; "
				+ System.lineSeparator() + "4 - удалить последнюю запись;"
				+ System.lineSeparator() + "5 - удалить запись по дате;"
				+ System.lineSeparator() + "6 - удалить запись по содержимому; "
				+ System.lineSeparator() + "7 - посмотреть содержимое файла; "
				+ System.lineSeparator() + "8 - удалить содержимое файла; "
				+ System.lineSeparator() + "9 - выход."
				+ System.lineSeparator() + " >");
		
		number = NotebookUtil.getInt();
		return number;
	}

}
