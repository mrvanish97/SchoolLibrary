package schoollibrary.core.readers;

import java.util.GregorianCalendar;

public class Pupil extends Reader {
  public Pupil() {
    super();
  }

  public Pupil(String name, int year, int month, int day) {
    super(name, year, month, day);
  }

  public Pupil(String name, GregorianCalendar birthday) {
    super(name, birthday);
  }
}