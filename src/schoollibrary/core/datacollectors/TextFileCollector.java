package schoollibrary.core.datacollectors;

import schoollibrary.core.items.*;
import schoollibrary.core.librarymodel.Library;
import schoollibrary.core.readers.Pupil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TextFileCollector implements DataCollector {
  private static TextFileCollector ourInstance = new TextFileCollector();

  public static TextFileCollector getInstance() {
    return ourInstance;
  }

  private TextFileCollector() {
  }

  @Override
  public Library makeLibrary() throws IOException, ParseException {
    Path path = Paths.get("input.txt");
    Stream<String> lines = Files.lines(path);
    int stage = 0;
    final ArrayList<String> strings = new ArrayList<>();
    lines.forEach(strings::add);
    strings.forEach(System.out::println);
    Library library = new Library();
    ArrayList<Item> items = new ArrayList<>();
    for (String s : strings) {
      switch (stage) {
        case 0:
          if (s.equals("---")) {
            ++stage;
            library = new Library(items);
          } else if (Pattern.compile("^.+; (Book|Article|Journal|Newspaper)$",
              Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(s).matches()) {
            String[] parts = s.split("; ");
            if (parts[1].toUpperCase().equals(TypeOfItem.BOOK.toString())) {
              items.add(new Book(parts[0]));
            } else if (parts[1].toUpperCase().equals(TypeOfItem.ARTICLE.toString())) {
              items.add(new Article(parts[0]));
            } else if (parts[1].toUpperCase().equals(TypeOfItem.JOURNAL.toString())) {
              items.add(new Journal(parts[0]));
            } else if (parts[1].toUpperCase().equals(TypeOfItem.NEWSPAPER.toString())) {
              items.add(new Newspaper(parts[0]));
            }
          } else {
            throw new IllegalArgumentException("Incorrect data");
          }
          break;
        case 1:
          if (Pattern.compile("^.+: .+:( \\d+,)*( \\d+){0,1}( )*$").matcher(s).matches()) {
            String[] parts = s.split(": ");
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            Date date = df.parse(parts[1]);
            GregorianCalendar birthday = new GregorianCalendar();
            birthday.setTime(date);
            Pupil pupil = new Pupil(parts[0], birthday);
            library.addReader(pupil);
            if (parts.length == 3) {
              String[] history = parts[2].split(", ");
              for (String h : history) {
                library.addItemToReadersHistory(items.get(Integer.parseInt(h)), pupil);
              }
            }
          } else {
            throw new IllegalArgumentException("Incorrect data");
          }
          break;
        default:
          break;
      }
    }
    return library;
  }
}
