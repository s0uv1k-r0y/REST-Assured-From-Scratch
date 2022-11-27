package RESTAssuredFromScratch.CourseAndLocation.AccessDataUsingDifferentMethods.DifferentWaysToAccessData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class AccessUserDataUsingExcel {

    @Test(description = "To get the exact cell value")
    public void getData() throws IOException {
        String excelPath = "C:\\Users\\v1nz\\WorkSpace\\IBM-FST Training\\Training & Assignments\\RestAssured\\src\\test\\java\\RESTAssuredFromScratch\\CourseAndLocation\\AccessDataUsingDifferentMethods\\Resources\\LocationWorkbook.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheet("Sheet1");
        XSSFRow row = sheet1.getRow(1);
        System.out.println(row.getCell(1).getStringCellValue());
    }

    @Test
    public void getMultipleDataViaExcel() throws IOException {

        ArrayList<String> data = getDataFromExcel("name");
        for (int i = 0; i < data.size(); i++)
            System.out.println(data.get(i));
    }

    private static ArrayList<String> getDataFromExcel(String keyValue) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();

        // fileInputStream argument
        FileInputStream fis = new FileInputStream("C:\\Users\\v1nz\\WorkSpace\\IBM-FST Training\\Training & Assignments\\RestAssured\\src\\test\\java\\RESTAssuredFromScratch\\CourseAndLocation\\AccessDataUsingDifferentMethods\\Resources\\LocationWorkbook.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                // Identify Testcase column by scanning the entire 1st row
                Iterator<Row> rows = sheet.iterator();  //sheet is collection of rows
                Row firstRow = rows.next();
                Iterator<Cell> ce = firstRow.cellIterator();    //row is collection of cells
                int k = 0, column = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("Key")) {
                        //desired column
                        column = k;
                    }
                    k++;
                }
                System.out.println(column);

                //once row is identified scan entire key column to identify name row
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(keyValue)) {
                        //after row is captured pull all the row data
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING)
                                arrayList.add(c.getStringCellValue());
                            else
                                arrayList.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
