package Tests;

import Common.CommonBaseTest;
import Pages.GoogleMapsPage;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GoogleMapsTest extends CommonBaseTest {
    GoogleMapsPage mapsPage;

    @Test
    public void testGoogleMaps() throws IOException {
        mapsPage = new GoogleMapsPage(driver);
        mapsPage.clickDirectionsButton();
        mapsPage.enterStartingLocation("CBD Belapur");
        mapsPage.enterDestination("91 Springboard, Vikhroli");

        mapsPage.clickFirstRoute();

        int instructionsCount = mapsPage.getDrivingInstructionsCount();

        System.out.println("InstructionsCount :- "+instructionsCount);

        // Create a new workbook and sheet for the driving instructions
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Driving Instructions");

        // Create the header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Driving Instructions");

        // Loop through each driving instruction and add it to the sheet
        for (int i = 0; i < instructionsCount; i++) {
            String instruction ="";
            if (i == instructionsCount-1){
                instruction = mapsPage.getDrivingInstructionText("*" + i);
            }else {
                instruction = mapsPage.getDrivingInstructionText(String.valueOf(i));
            }
            Row row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(instruction);
        }

        // Write the workbook to a file
        FileOutputStream fileOut = new FileOutputStream("DrivingInstructions.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        // Take a screenshot of the screen
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("Screenshot.png"));
    }

}
