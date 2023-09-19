package com.school.SpringSecuritywithDatabase.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lowagie.text.DocumentException;
import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.Course;
import com.school.SpringSecuritywithDatabase.model.CoursesTaken;
import com.school.SpringSecuritywithDatabase.model.Student;
import com.school.SpringSecuritywithDatabase.model.registration.StudentRegistrationRequest;
import com.school.SpringSecuritywithDatabase.model.registration.StudentRegistrationService;
import com.school.SpringSecuritywithDatabase.service.StudentServiceImpl;
import com.school.SpringSecuritywithDatabase.service.csv.CsvExportService;
import com.school.SpringSecuritywithDatabase.service.pdf.UserPDFExporter;
import com.school.SpringSecuritywithDatabase.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private CsvExportService csvExportService;

    public StudentController(StudentServiceImpl studentServiceImpl, CsvExportService csvExportService) {
        this.studentServiceImpl = studentServiceImpl;
        this.csvExportService = csvExportService;
    }
@GetMapping("listOfAll/export/pdf")
public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
    response.setContentType("application/pdf");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=students_" + currentDateTime + ".pdf";
    response.setHeader(headerKey, headerValue);

    List<Student> listStudents = studentServiceImpl.findAll();

    UserPDFExporter exporter = new UserPDFExporter(listStudents);
    exporter.export(response);

}

    @PostMapping("/registration")
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student) {
        return new ResponseEntity<>(studentServiceImpl.addStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return studentServiceImpl.deleteById(id);
    }

    @GetMapping("/{id}")
    @JsonView(View.ShowMinimal.class)
    public ResponseEntity<Student> findById(@PathVariable int id) throws WrongIdException {
        return new ResponseEntity<>(studentServiceImpl.findById(id), HttpStatus.OK);
    }

        @GetMapping("/{id}/listOfCourses")
    public ResponseEntity<FileSystemResource> findAllCoursesByStudentId(@PathVariable int id) {
        List<Course> courses = studentServiceImpl.findAllCoursesByStudentId(id);
        String[] headers = {"Course ID", "Name"};
        // Use the CsvExportService to convert the JSON-like data to CSV
        String csvData = csvExportService.exportToCsv(courses, headers);
        // Specify the filename for the CSV file
        String filename = "student-courses.csv";
        // Save the CSV file to your IntelliJ project directory
        String projectPath = "csv-exports"; // Replace with your project directory path
        try {
            String filePath = projectPath + "/" + filename;
            Files.write(Paths.get(filePath), csvData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Use the exportCsvFileToResponse method to send the CSV file as a response
        ResponseEntity<FileSystemResource> responseEntity = csvExportService.exportCsvFileToResponse(filename, csvData);
        return responseEntity;
    }
    @PutMapping("/{id}/update")
    public Student updateStudentsName(@PathVariable int id, @RequestBody Student student) {
        return studentServiceImpl.updateStudentsName(id, student);
    }

    @GetMapping("/{studentId}/{professorId}/courses")
    public List<String> findByStudentAndProfessor(@PathVariable int studentId, @PathVariable int professorId) {
        return studentServiceImpl.findByStudentAndProfessor(studentId, professorId);
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

}
