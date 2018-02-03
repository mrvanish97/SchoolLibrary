package schoollibrary.core.ui;

import schoollibrary.core.datacollectors.HardcodeCollector;
import schoollibrary.core.datacollectors.TextFileCollector;
import schoollibrary.core.items.TypeOfItem;
import schoollibrary.core.librarymodel.Library;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class ConsoleUI implements UI {
  private static ConsoleUI ourInstance = new ConsoleUI();

  public static ConsoleUI getInstance() {
    return ourInstance;
  }

  private ConsoleUI() {
  }

  @Override
  public void show() {
    System.out.println("Choice input method:\n1 -- hardcoded data\n2 -- input.txt in root folder");
    Scanner in = new Scanner(System.in);
    boolean flag = true;
    Library library = new Library();
    while (flag) {
      int method = in.nextInt();
      switch (method) {
        case 1:
          library = HardcodeCollector.getInstance().makeLibrary();
          flag = false;
          break;
        case 2:
          try {
            library = TextFileCollector.getInstance().makeLibrary();
            flag = false;
          } catch (IOException e) {
            e.printStackTrace();
          } catch (ParseException e) {
            e.printStackTrace();
          }
        default:
          break;
      }
    }
    System.out.println("\nFirts task:\n" + library.getListOfItemsAsString(TypeOfItem.JOURNAL));
    System.out.println("\nSecond task:\n" + library.secondTaskString());
    System.out.println("\nThird task:\n" + library.thirdTaskString());
  }
}
