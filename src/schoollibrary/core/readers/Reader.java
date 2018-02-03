package schoollibrary.core.readers;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public abstract class Reader {
  String name;
  GregorianCalendar birthday;

  public Reader() {
    birthday = new GregorianCalendar();
  }

  public Reader(String name, int year, int month, int day) {
    setName(name);
    --month;
    birthday = new GregorianCalendar(year, month, day);
  }

  public Reader(String name, GregorianCalendar birthday) {
    setName(name);
    setBirthday(birthday);
  }

  public String getName() {
    return name;
  }

  public GregorianCalendar getBirthday() {
    return (GregorianCalendar) birthday.clone();
  }

  public void setName(String name) {
    if (!name.isEmpty()) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Name of reader can't be empty");
    }
  }

  public void setBirthday(GregorianCalendar birthday) {
    this.birthday = (GregorianCalendar) birthday.clone();
  }

  @Override
  public String toString() {
    SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
    fmt.setCalendar(birthday);
    String dateFormatted = fmt.format(birthday.getTime());
    return name + ", " + dateFormatted + "; " + this.getClass().getSimpleName();
  }
}
