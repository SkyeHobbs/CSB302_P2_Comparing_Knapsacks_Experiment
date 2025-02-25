package utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {


  @Test
  void readCsvWithValidFile() throws IOException {
    String filename = "data/testValid.csv";
    createTestFile(filename, "1,10\n10,20,30\n5,10,15")
    ;
    CsvReader reader = new CsvReader();
    List<Item> list = reader.readCsv(filename).getItems();

    assertNotNull(list);
    assertEquals(3, list.size());

    Item item1 = list.getFirst();
    assertEquals(5, item1.getWeight());
    assertEquals(10, item1.getValue());

    Item item2 = list.get(1);
    assertEquals(10, item2.getWeight());
    assertEquals(20, item2.getValue());

    Item item3 = list.get(2);
    assertEquals(15, item3.getWeight());
    assertEquals(30, item3.getValue());

  }

  @Test
  void readCsvWithInvalidFile() throws IOException {
    String filename = "data/nonexistentTestFile.csv";

    assertThrows(FileNotFoundException.class, () -> {
      try {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        scanner.close();
      } catch (FileNotFoundException e) {
        throw e;
      }
    });
  }

  @Test
  void readCsvInvalidNumberFormat() throws IOException {
    String filename = "data/invalidFormatTestFile.csv";
    createTestFile(filename,"1,10\na,20,30\n5,10,15");

    CsvReader reader = new CsvReader();
    assertThrows(NumberFormatException.class, () ->
      reader.readCsv(filename));

  }

  @Test
  void readCsvUnequalValuesAndWeights() throws IOException {
    String filename = "data/unequalValue.csv";
    createTestFile(filename,"1,10\n20,30\n5,10,15");

    CsvReader reader = new CsvReader();
    assertThrows(IllegalArgumentException.class, () ->
            reader.readCsv(filename));

    String filename2 = "data/unequalWeight.csv";
    createTestFile(filename2,"1,10\n10,20,30\n10,15");

    CsvReader reader2 = new CsvReader();
    assertThrows(IllegalArgumentException.class, () ->
            reader2.readCsv(filename2));


  }

  @Test
  void setAndGetTotalKnapsackCapacity() throws IOException {
    String filename = "data/testSetAndGet.csv";
    createTestFile(filename, "1,10\n10,20,30\n5,10,15");

    CsvReader reader = new CsvReader();
    reader.readCsv(filename);

    assertEquals(10.0, reader.getTotalKnapsackCapacity());
  }


    private void createTestFile(String filename, String content) throws IOException {
      File file = new File(filename);
      try (FileWriter writer = new FileWriter(file)) {
        writer.write(content);
      }
    }
}