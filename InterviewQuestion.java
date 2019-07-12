// Question 1.
package restExcel;

import java.io.*;

public class MeaningFileReader {

    public final static String SAMPLE_XLSX_FILE_PATH = "/Users/nur/Desktop/meaning.txt";


    public static void main(String[] args) {
        restExcel.MeaningFileReader meaningFileReader = new restExcel.MeaningFileReader();

        boolean readingSuccess = meaningFileReader.doesFileExist(SAMPLE_XLSX_FILE_PATH);
        if (readingSuccess) {
            meaningFileReader.displayMeanings(SAMPLE_XLSX_FILE_PATH);
        }
    }

    public void displayMeanings(String filePath) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while (line != null) {
                // assuming lines with word and meanings are separated by -
                // and skipping if it isn't
                if (line.indexOf("-") != -1) {
                    String[] parts = line.split("-");

                    System.out.println(parts[0].trim());

                    for (String meaning : parts[1].split(",")) {
                        System.out.println(meaning.trim());
                    }
                }
                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
                ;
            }
        }
    }

    public boolean doesFileExist(String filePath) {

        boolean readingSuccess = true;
        File file = new File(filePath);
        if (!file.exists()) {
            readingSuccess = false;
            System.out.println("File doesn't exists at :" + filePath);
        }

        if (!file.canRead()) {
            System.out.println("File can not be read :" + filePath);
            readingSuccess = false;
        }
        return readingSuccess;
    }

}

// Question 2.
package WW;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.testng.Assert;
        import org.testng.annotations.Test;

        import java.util.*;
        import java.util.stream.Collectors;

public class WWTest {
    public static WebDriver driver;

    // 9. Create a method to print the number of meeting the each person(under the scheduled time) has a particular day of the week
    // e.g. printMeetings("Sun")
    public static Map<String, Long> list_of_names(By locator) {
        List<WebElement> listOfTrinners = driver.findElements(locator);

        List<String> names = new ArrayList<>();
        for (WebElement name : listOfTrinners) {
            names.add(name.getText());
        }

        Map<String, Long> couterSchedualedPerson = names.stream().collect(Collectors.groupingBy(e -> e.toString(), Collectors.counting()));
        System.out.println(couterSchedualedPerson);

        return couterSchedualedPerson;

    }

    @Test
    public void testingWW() throws InterruptedException {
        driver = new ChromeDriver();
        // 1. Navigate to https://www.weightwatchers.com/us/
        driver.get("https://www.weightwatchers.com/us/");
        driver.manage().window().maximize();

        // 2. Verify loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help”
        String expactedTitle = "WW (Weight Watchers): Weight Loss & Wellness Help";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expactedTitle, actualTitle);

        // 3. On the right corner of the page, click on “Find a Studio”
        WebElement find_a_Studio = driver.findElement(By.id("ela-menu-visitor-desktop-supplementa_find-a-studio"));
        find_a_Studio.click();
        Thread.sleep(3000);

        // 4. Verify loaded page title contains “Find WW Studios & Meetings Near You | WW USA”
        String expected_loding_page_title = "Find WW Studios & Meetings Near You | WW USA";
        String actual_loding_page_title = driver.getTitle();
        if (expected_loding_page_title.equalsIgnoreCase(actual_loding_page_title)) {
            System.out.println("Both titles are same");
        } else {
            System.out.println("No Match!");
        }

        // 5. In the search field, search for meetings for zip code: 10011
        WebElement send_Zip_Code = driver.findElement(By.id("meetingSearch"));
        send_Zip_Code.sendKeys("10011");

        WebElement enterButton = driver.findElement(By.xpath("//button[@class='btn spice-translated']"));
        enterButton.click();

        // 6. Print the title of the first result and the distance (located on the right of location title/name)
        WebElement resultText = driver.findElement(By.xpath("//span[contains(text(),'WW Studio Flatiron')]"));
        String location_name = resultText.getText();
        System.out.println("Title of the studio is: " + location_name);


        WebElement location_distance = driver.findElement(By.className("location__distance"));
        String distance = location_distance.getText();
        System.out.println("The distance of the location is: " + distance);


        // 7. Click on the first search result and then, verify displayed location name/title matches with the name of the first searched result that was clicked.
        WebElement click_on_frist_result = driver.findElement(By.xpath("//div[@id='ml-1180510']//div[@class='meeting-location__top']//a"));
        click_on_frist_result.click();

        WebElement selected_location_name = driver.findElement(By.xpath("//span[contains(text(),'WW Studio Flatiron')]"));
        String selected_location_name_text = selected_location_name.getText();
        Assert.assertEquals(location_name, selected_location_name_text);

        // 8. From this location page, print TODAY’s hours of operation (located towards the bottom of the page)
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='hours-list-item-wrapper hours-list--currentday']"));
        System.out.println("Today's hours of operation");
        for (WebElement element : elements) {
            System.out.println(element.getText());
        }

        // 9. method being called
        System.out.println("Number of meeting each person has: ");
        By locator = By.xpath("//div[@class='schedule-detailed-day-meetings-item-leader']");
        list_of_names(locator);


        driver.quit();

    }
}


// QuestIon 3.
package randomDemo;

        import java.util.ArrayList;
        import java.util.Random;
        import java.util.Scanner;

public class TestingRandom {

    Scanner scanner = new Scanner(System.in);
    public static ArrayList<Integer> randomNumbe = new ArrayList<>();

    public static void main(String[] args) {
        randomDemo.TestingRandom testingRandom = new randomDemo.TestingRandom();

        // generate 500 random numbers
        Random random = new Random();
        for (int i = 0; i <= 500; i++) {
            int numbers = random.nextInt(1000000);
            randomNumbe.add(numbers);
        }

        // Bubble Sorting random numbers
        for (int i = 0; i < randomNumbe.size() - 1; i++) {
            for (int j = 0; j < randomNumbe.size() - i - 1; j++) {
                if (randomNumbe.get(j) > randomNumbe.get(j + 1)) {

                    int temp = randomNumbe.get(j);
                    randomNumbe.set(j, (randomNumbe.get(j + 1)));
                    randomNumbe.set(j + 1, temp);
                }

            }
        }


        // printing the array list
//        for (int i = 0; i < randomNumbe.size(); i++) {
//            System.out.println(randomNumbe.get(i));
//        }

        //  calling the display method
        testingRandom.display();

    }


    public void display() {
        System.out.println("Please enter which nth lowest number you wqant to see : ");
        int number = scanner.nextInt();
        System.out.println(randomNumbe.get(number - 1));
    }

}