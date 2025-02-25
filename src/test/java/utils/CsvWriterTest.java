package utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvWriterTest {

  private static CsvWriter writer;
  private static String name;
  private static int total;
  private static int num;
  private static int cap;
  private static List<Item> items;

  @BeforeAll
  static void setUp() {
    Item item = new Item(1, 20);
    Item item2 = new Item(6, 50);
    items = new ArrayList<>();
    items.add(item);
    items.add(item2);
    name = "filename.csv";
    total = 70;
    num = 1;
    cap = 80;

    writer = new CsvWriter(name);
  }

  @Test
  void testWriteResults() throws IOException {
    writer.writeResults(items, total, num, cap);

    File file = new File(name);
    assertTrue(file.exists());

    BufferedReader reader = new BufferedReader(new FileReader(file));
    List<String> lines = new ArrayList<>();
    String line;

    while ((line = reader.readLine()) != null) {
      lines.add(line);
    }
    reader.close();

    assertEquals("1,80", lines.get(0));
    assertEquals("20,50", lines.get(1));
    assertEquals("1,6", lines.get(2));
    assertEquals(total, Double.parseDouble(lines.get(3)));

  }

  @Test
  void testWriteInputFile() throws IOException {
    writer.writeInputFile(items, num, cap);

    File file = new File(name);
    assertTrue(file.exists());

    BufferedReader reader = new BufferedReader(new FileReader(file));
    List<String> lines = new ArrayList<>();
    String line;

    while ((line = reader.readLine()) != null) {
      lines.add(line);
    }
    reader.close();

    assertEquals("1,80", lines.get(0));
    assertEquals("20,50", lines.get(1));
    assertEquals("1,6", lines.get(2));
  }

  @Test
  void getAndSetPath() {
    assertEquals("filename.csv", writer.getPath());
  }
}