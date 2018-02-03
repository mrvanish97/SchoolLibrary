package schoollibrary.core.items;

public abstract class Item {
  String name;

  Item() {
  }

  public Item(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) throws IllegalArgumentException {
    if (!name.isEmpty()) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Name of item can't be empty");
    }
  }

  @Override
  public String toString() {
    return this.name + "; " + this.getClass().getSimpleName();
  }
}
