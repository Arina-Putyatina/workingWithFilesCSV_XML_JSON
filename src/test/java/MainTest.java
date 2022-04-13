import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main sut;

    @BeforeEach
    public void init() {
        sut = new Main();
    }

    @Test
    void parseCSV_validArgument_success() {

        //arrange
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee(1,"John", "Smith", "USA",25));
        expected.add(new Employee(2,"Inav", "Petrov", "RU",23));

        //act
        List<Employee> result = sut.parseCSV(columnMapping, fileName);

        //assert
        assertEquals(result, expected);
    }

    @Test
    void listToJson_validArgument_success() {

        //arrange
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"John", "Smith", "USA",25));

        String expected = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  }\n" +
                "]";

        //act
        String result = sut.listToJson(list);

        //assert
        assertEquals(result, expected);
    }

   @ParameterizedTest
   @MethodSource("source")
//   @ValueSource(strings = { "data.json", "data6.json" })
    public void readString(String fileName, String expected) {
       //act
       String result = sut.readString(fileName);

       //assert
       assertEquals(result, expected);
   }

   public static Stream<Arguments> source() {
        String string1 = "[  {    \"id\": 1,    \"firstName\": \"John\",    \"lastName\": \"Smith\",    \"country\": \"USA\",    \"age\": 25  },  {    \"id\": 2,    \"firstName\": \"Inav\",    \"lastName\": \"Petrov\",    \"country\": \"RU\",    \"age\": 23  }]";
        String string2 = null;
        return Stream.of(Arguments.of("data.json", string1), Arguments.of("data5.json", string2));
   }
}