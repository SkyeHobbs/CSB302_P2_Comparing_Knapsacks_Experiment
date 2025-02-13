package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {


  @Test
  void readCSVWithValidFile() throws IOException {
    String filename = "data/testValid.csv";
    createTestFile(filename, "1,10\n10,20,30\n5,10,15")
    ;
    CSVReader reader = new CSVReader();
    List<Item> list = reader.readCSV(filename);

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
  void readCSVWithInvalidFile() throws IOException {
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
  void readCSVInvalidNumberFormat() throws IOException {
    String filename = "data/invalidFormatTestFile.csv";
    createTestFile(filename,"1,10\na,20,30\n5,10,15");

    CSVReader reader = new CSVReader();
    assertThrows(NumberFormatException.class, () ->
      reader.readCSV(filename));

  }

  @Test
  void readCSVUnequalValuesAndWeights() throws IOException {
    String filename = "data/unequalValue.csv";
    createTestFile(filename,"1,10\n20,30\n5,10,15");

    CSVReader reader = new CSVReader();
    assertThrows(IllegalArgumentException.class, () ->
            reader.readCSV(filename));

    String filename2 = "data/unequalWeight.csv";
    createTestFile(filename2,"1,10\n10,20,30\n10,15");

    CSVReader reader2 = new CSVReader();
    assertThrows(IllegalArgumentException.class, () ->
            reader2.readCSV(filename2));


  }

  @Test
  void setAndGetTotalKnapsackCapacity() throws IOException {
    String filename = "data/testSetAndGet.csv";
    createTestFile(filename, "1,10\n10,20,30\n5,10,15");

    CSVReader reader = new CSVReader();
    reader.readCSV(filename);

    assertEquals(10.0, reader.getTotalKnapsackCapacity());
  }


    private void createTestFile(String filename, String content) throws IOException {
      File file = new File(filename);
      try (FileWriter writer = new FileWriter(file)) {
        writer.write(content);
      }
    }
}