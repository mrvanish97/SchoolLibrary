package schoollibrary.core.datacollectors;

import schoollibrary.core.librarymodel.Library;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface DataCollector {
  Library makeLibrary() throws IOException, ParseException;
}