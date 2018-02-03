package schoollibrary.core.librarymodel;

import javafx.util.Pair;
import schoollibrary.core.items.Item;
import schoollibrary.core.items.TypeOfItem;
import schoollibrary.core.readers.Reader;

import java.text.SimpleDateFormat;
import java.util.*;

public class Library {
  HashSet<Item> items;
  HashMap<Reader, HashSet<Item>> readers;

  public Library() {
    items = new HashSet<>();
    readers = new HashMap<>();
  }

  public Library(Item[] items, Reader[] readers) {
    this.items = new HashSet<>(Arrays.asList(items));
    this.readers = new HashMap<>();
    for (Reader reader : readers) {
      this.readers.put(reader, new HashSet<>());
    }
  }

  public Library(Item[] items) {
    this.items = new HashSet<>(Arrays.asList(items));
    this.readers = new HashMap<>();
  }

  public Library(ArrayList<Item> items) {
    this.items = new HashSet<>(items);
    this.readers = new HashMap<>();
  }

  public void addItemToReadersHistory(Item item, Reader reader) {
    if (items.contains(item) && readers.containsKey(reader)) {
      HashSet<Item> tempSet = readers.get(reader);
      tempSet.add(item);
      readers.put(reader, tempSet);
    } else {
      throw new NoSuchElementException("There's no such reader or item in library");
    }
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void addReader(Reader reader) {
    readers.put(reader, new HashSet<>());
  }

  public String getListOfItemsAsString(TypeOfItem type) {
    StringBuilder stringBuilder = new StringBuilder();
    boolean hasElement = false;
    for (Item item : items) {
      if (item.getClass().getSimpleName().toUpperCase().equals(type.name().toUpperCase())) {
        hasElement = true;
        stringBuilder.append(item.toString() + "\n");
      }
    }
    String r;
    if (hasElement) {
      r = stringBuilder.toString();
    } else {
      r = "List is empty";
    }
    return r;
  }

  public String secondTaskString() {
    ArrayList<Pair<String, Integer>> listOfReaders = new ArrayList<>();
    readers.forEach((k, v) -> {
      if (v.size() > 1) {
        listOfReaders.add(new Pair<>(k.getName(), v.size()));
      }
    });
    Collections.sort(listOfReaders, Comparator.comparing(Pair::getValue));
    StringBuilder stringBuilder = new StringBuilder();
    for (Pair<String, Integer> p : listOfReaders) {
      if (p.getValue() <= 2) {
        stringBuilder.append(p.getKey() + ": ");
      }
      stringBuilder.append(p.getValue() + "\n");
    }
    String r = stringBuilder.toString();
    if (r.isEmpty()) {
      r = "Report is empty";
    }
    return r;
  }

  public String thirdTaskString() {
    ArrayList<Pair<Reader, Integer>> listOfReaders = new ArrayList<>();
    readers.forEach((k, v) -> {
      if (v.size() <= 2) {
        listOfReaders.add(new Pair<>(k, v.size()));
      }
    });
    Collections.sort(listOfReaders, (o1, o2) -> {
      int birthdayCompare = o1.getKey().getBirthday().compareTo(o2.getKey().getBirthday());
      if (birthdayCompare != 0) {
        return birthdayCompare;
      } else {
        return o2.getValue().compareTo(o1.getValue());
      }
    });
    StringBuilder stringBuilder = new StringBuilder();
    SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
    for (Pair<Reader, Integer> p : listOfReaders) {
      if (p.getValue() >= 2) {
        stringBuilder.append(p.getKey().getName() + ", ");
      }
      fmt.setCalendar(p.getKey().getBirthday());
      stringBuilder.append(fmt.format(p.getKey().getBirthday().getTime()) + ": ");
      stringBuilder.append(p.getValue() + "\n");
    }
    String r = stringBuilder.toString();
    if (r.isEmpty()) {
      r = "Report is empty";
    }
    return r;
  }
}
