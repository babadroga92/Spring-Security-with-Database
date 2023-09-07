package com.school.SpringSecuritywithDatabase.service.csv;
import com.school.SpringSecuritywithDatabase.model.Course;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
public class CsvExportService {

    @Value("${csv.download.directory}")
    private String exportDirectory;

    public String exportToCsv(List<?> dataList, String[] headers) {
        // Check if the data list is empty
        if (dataList.isEmpty()) {
            throw new IllegalArgumentException("Data list is empty.");
        }

        try (StringWriter writer = new StringWriter()) {
            // Write CSV header
            writeCsvHeader(writer, headers);

            // Write data rows
            for (Object data : dataList) {
                writeCsvDataRow(writer, data, headers);
            }

            return writer.toString(); // Convert StringWriter content to a String
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeCsvHeader(StringWriter writer, String[] headers) throws IOException {
        writer.append(String.join(",", headers));
        writer.append("\n");
    }

    private void writeCsvDataRow(StringWriter writer, Object data, String[] headers) throws IOException {
        Course course = (Course) data;
        String[] values = new String[headers.length];
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            if ("Course ID".equals(header)) {
                values[i] = String.valueOf(course.getId());
            } else if ("Name".equals(header)) {
                values[i] = course.getName();
            }
        }
        writer.append(String.join(",", values));
        writer.append("\n");
    }

    public ResponseEntity<FileSystemResource> exportCsvFileToResponse(String filename, String csvData) {
        try {
            // Create the CSV file on the server-side with the provided data
            String filePath = exportDirectory + filename; // Replace with the actual file path
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(csvData);
            fileWriter.close();

            // Prepare the file for download
            FileSystemResource file = new FileSystemResource(filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(file);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
