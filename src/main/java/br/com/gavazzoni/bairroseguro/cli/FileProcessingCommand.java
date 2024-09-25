package br.com.gavazzoni.bairroseguro.cli;

import br.com.gavazzoni.bairroseguro.model.Crime;
import br.com.gavazzoni.bairroseguro.service.CrimeService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

@ShellComponent
public class FileProcessingCommand {

    private final CrimeService crimeService;

    @Autowired
    public FileProcessingCommand(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @ShellMethod(key = "load-files", value = "Load files from a directory")
    public String loadFiles(@ShellOption String directoryPath) {
        System.out.println("Loading files from " + directoryPath);

        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return "The provided path is not a valid directory.";
        }

        File[] files = dir.listFiles((_, name) -> name.endsWith(".xlsx"));
        if (files == null || files.length == 0) {
            return "No Excel files found in the directory.";
        }

        for (File file : files) {
            try (FileInputStream fis = new FileInputStream(file)) {
                Workbook workbook = WorkbookFactory.create(fis);
                Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue;

                    Crime crime = new Crime();

                    crime.setBouYear((int) row.getCell(0).getNumericCellValue());
                    crime.setExternalId(row.getCell(1).getStringCellValue());
                    crime.setYear((int) row.getCell(2).getNumericCellValue());
                    crime.setMonth(this.convertMonthStringToInt(row.getCell(3).getStringCellValue()));
                    crime.setDay((int) row.getCell(4).getNumericCellValue());
                    crime.setDayOfWeek(row.getCell(5).getStringCellValue());
                    crime.setHour((int) row.getCell(6).getNumericCellValue());
                    crime.setAisp(row.getCell(7).getStringCellValue());
                    crime.setMunicipality(row.getCell(8).getStringCellValue());
                    crime.setNeighborhood(row.getCell(9).getStringCellValue());
                    crime.setNatureType(row.getCell(10).getStringCellValue());
                    crime.setNature(row.getCell(11).getStringCellValue());
                    crime.setEnvironment(row.getCell(12).getStringCellValue());

                    crimeService.save(crime);
                }

                System.out.println("Processed file: " + file.getName());
            } catch (Exception e) {
                System.out.println("An error occurred while processing the file: " + file.getName() + ", Error: " + e.getMessage());
            }
        }

        return "Finished";
    }

    public int convertMonthStringToInt(String month) {
        return switch (month) {
            case "jan" -> 1;
            case "fev" -> 2;
            case "mar" -> 3;
            case "abr" -> 4;
            case "mai" -> 5;
            case "jun" -> 6;
            case "jul" -> 7;
            case "ago" -> 8;
            case "set" -> 9;
            case "out" -> 10;
            case "nov" -> 11;
            case "dev" -> 12;
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }
}