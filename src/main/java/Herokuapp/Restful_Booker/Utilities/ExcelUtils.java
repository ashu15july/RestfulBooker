package Herokuapp.Restful_Booker.Utilities;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {

	private static Workbook workbook;
	private static Sheet worksheet;
	private static Cell cell;
	private static int rows;
	private String testCaseName;


	public ExcelUtils(String workSheetName, String testCaseName) throws Exception{
		System.out.println("workSheetName name is : "+workSheetName);
		System.out.println("testCaseName name is : "+testCaseName);
		FileInputStream fis = new FileInputStream(FrameworkConstants.DATA_FILE_PATH);
		workbook = new XSSFWorkbook(fis);

		worksheet = workbook.getSheet(workSheetName);

		System.out.println("Sheet name is : "+ worksheet.getSheetName());
		this.testCaseName = testCaseName;
		
	}


	public Object[][] getTestdata() throws Exception {   

		String[][] tabArray = null;

		try {

			int startRow = 1;

			int startCol = 0;

			int ci,cj;

			int totalRows = worksheet.getLastRowNum();
			//System.out.println("Total rows are "+totalRows);

			int totalCols = worksheet.getRow(0).getLastCellNum();
			//System.out.println("Total cols are "+totalCols);

			tabArray=new String[totalRows][totalCols];

			ci=0;

			for (int i=startRow;i<=totalRows;i++, ci++) {           	   

				cj=0;

				for (int j=startCol;j<totalCols;j++, cj++){

					tabArray[ci][cj]=getCellData(i,j);

					System.out.println(tabArray[ci][cj]);  

				}

			}

		}

		catch (FileNotFoundException e){

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e){

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return(tabArray);

	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try{

			cell = worksheet.getRow(RowNum).getCell(ColNum);

			String CellData = cell.getStringCellValue();
			//System.out.println(CellData);

			return CellData;

		}catch (Exception e){

			System.out.println(e.getMessage());

			throw (e);

		}

	}

}
