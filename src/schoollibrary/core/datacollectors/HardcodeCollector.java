package schoollibrary.core.datacollectors;

import schoollibrary.core.items.*;
import schoollibrary.core.librarymodel.Library;
import schoollibrary.core.readers.Pupil;
import schoollibrary.core.readers.Reader;

import java.util.Arrays;

public class HardcodeCollector implements DataCollector {
  private static HardcodeCollector ourInstance = new HardcodeCollector();

  public static HardcodeCollector getInstance() {
    return ourInstance;
  }

  private HardcodeCollector() {
  }

  @Override
  public Library makeLibrary() {
    Item[] items = {
        new Journal("Wall Street Journal"),
        new Book("War and Peace"),
        new Book("Being and Time"),
        new Journal("Popular Mechanics"),
        new Article("Ito Calculus"),
        new Journal("Hands"),
        new Book("Fahrenheit 451"),
        new Journal("Cosmopolitan"),
        new Newspaper("Komsomol'skaya Truth"),
        new Newspaper("Belarus Today"),
        new Article("Introduction to Queueing Theory"),
        new Newspaper("1001 Crosswords")
    };
    System.out.println("Adding to the library following items: ");
    for (Item item : items) {
      System.out.println(item);
    }

    Reader[] readers = {
        new Pupil("Vasia", 1998, 1, 17),
        new Pupil("Valery", 1998, 1, 17),
        new Pupil("Lisa", 1999, 12, 1),
        new Pupil("Alex", 1997, 3, 10),
        new Pupil("Victor", 1997, 10, 3),
        new Pupil("Stasis", 1997, 5, 26),
        new Pupil("Ivan", 1998, 4, 6),
        new Pupil("Svetlana", 2000, 3, 2),
        new Pupil("Anastasia", 1998, 4, 26),
        new Pupil("Olga", 1998, 1, 17)
    };
    System.out.println("\nAdding to the library following readers: ");
    for (Reader reader : readers) {
      System.out.println(reader);
    }

    Library library = new Library(items, readers);
    library.addItemToReadersHistory(items[2], readers[0]);
    library.addItemToReadersHistory(items[7], readers[0]);
    System.out.println("\n" + stringHelper(readers[0], items[2], items[7]));

    library.addItemToReadersHistory(items[3], readers[1]);
    library.addItemToReadersHistory(items[5], readers[1]);
    System.out.println(stringHelper(readers[1], items[3], items[5]));

    System.out.println(stringHelper(readers[2]));

    library.addItemToReadersHistory(items[1], readers[3]);
    library.addItemToReadersHistory(items[4], readers[3]);
    library.addItemToReadersHistory(items[11], readers[3]);
    System.out.println(stringHelper(readers[3], items[1], items[4], items[11]));

    library.addItemToReadersHistory(items[2], readers[4]);
    library.addItemToReadersHistory(items[3], readers[4]);
    library.addItemToReadersHistory(items[10], readers[4]);
    System.out.println(stringHelper(readers[4], items[2], items[3], items[10]));

    library.addItemToReadersHistory(items[6], readers[5]);
    library.addItemToReadersHistory(items[7], readers[5]);
    System.out.println(stringHelper(readers[5], items[6], items[7]));

    library.addItemToReadersHistory(items[6], readers[6]);
    library.addItemToReadersHistory(items[7], readers[6]);
    System.out.println(stringHelper(readers[6], items[6], items[7]));

    library.addItemToReadersHistory(items[8], readers[7]);
    System.out.println(stringHelper(readers[7], items[8]));

    System.out.println(stringHelper(readers[7]));
    System.out.println(stringHelper(readers[8]));

    return library;
  }

  private String stringHelper(Reader reader, Item... items) {
    StringBuilder stringBuilder = new StringBuilder(reader.getName() + " read ");
    if (items.length != 0) {
      for (int i = 0; i < items.length; ++i) {
        stringBuilder.append(items[i].getName());
        if (i != items.length - 1) {
          stringBuilder.append(", ");
        }
      }
    } else {
      stringBuilder.append("nothing");
    }
    return stringBuilder.toString();
  }
}
