package com.example.demo;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Student;
import com.example.demo.service.IStudentService;
import com.itextpdf.text.Element;

 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;



@Controller
@RequestMapping("/student")
public class MainController {
    // @RequestMapping mặc định phương thức là get

    // @GetMapping => thể hiện việc lấy dữ liệu, trả về status code 200
    // @PostMapping => thể hiện việc thêm dữ liệu, trả về status code 201
    // @PatchMapping => cập nhật 1 phần dữ liệu, 200
    // @DeleteMapping => xóa dữ liệu, 200

    // status code 2xx: thành công
    // status code 4xx: lỗi của người dùng
    // status code 5xx: lỗi của máy chủ

    // @PutMapping => cập nhật dưới dạng thay thế dữ liệu, 200, nên dùng patch
    @Autowired
    IStudentService studentServicelmpl;

    @GetMapping
    public String getAll(Student student, Model model) {
        List<Student> studentList = studentServicelmpl.findAll();
        model.addAttribute("student", studentList);
        return "student";
    }

    @GetMapping("/add")
    public String create() {
        return "add-student";
    }

    // /student/add
    @PostMapping("/add")
    public String create(@ModelAttribute("student") Student student) {
        System.out.println(student.getName());
        student = studentServicelmpl.create(student);
        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Optional<Student> optional = studentServicelmpl.findById(id);
        Student student = optional.get();
        model.addAttribute("student", student);
        return "update-student";
    }

    // /student/update/44464646
    @PostMapping("/update")
    public String update(@ModelAttribute("student") Student student) {
        student = studentServicelmpl.update(student);
        return "redirect:/student";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentServicelmpl.delete(id);
        return "redirect:/student";
    }

    @RequestMapping(value = "/pdfview", method = RequestMethod.GET)
    public ResponseEntity<Byte[]> download() throws DocumentException, MalformedURLException, IOException {
        // chỉ định nơi lưu
        List<Student> studentList = studentServicelmpl.findAll();
        String path = "/student.pdf";
        File file = new File(path);
        file.getParentFile().mkdir();
        // khởi tạo IText
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        // design NAME
                Paragraph herader = new Paragraph("Danh Sách Sinh Viên");
                herader.setAlignment(Element.ALIGN_CENTER);
            document.add(herader);
                Paragraph username = new Paragraph("Nguyễn Quang Thanh");
                username.setAlignment(Element.ALIGN_CENTER);
            document.add(username);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E đ.MM.yyy 'at'hh:mm:ss  ");
                Paragraph dateTime = new Paragraph(simpleDateFormat.format(new java.util.Date()));
                dateTime.setAlignment(Element.ALIGN_RIGHT);
            document.add(dateTime);
                Image logo = Image.getInstance("./src/main/resources/templates/logo_IVS.png");
                logo.setAlignment(Element.ALIGN_RIGHT);
            document.add(logo);
            // Table heraderInfo = new Table(2);
            // heraderInfo.addCell(null, 1, 1);
        // Design table header
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 80F, 130F, 130F, 130F });
        table.setSpacingBefore(10);
        // disign table header
        PdfPCell cell = new PdfPCell(new Paragraph(new
         Chunk("UTF-8")));
        //Color color = WebColors.getRGBColor("silver");
        cell.setPadding(4);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(13);
        font1.setColor(Color.black);
        
        font.setSize(18);
        font.setColor(Color.BLUE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Age", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Adress", font));
        table.addCell(cell);
        
        //student data
        for(Student student : studentList)
        {
            cell.setPhrase(new Phrase("SV-"+student.getId(), font1));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(student.getName(), font1));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(""+student.getAge(), font1));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(student.getAddress(), font1));
            table.addCell(cell);
        }
        document.add(table);
        document.close();
        byte[] contens =  Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename =  "student.pdf";
        headers.setContentDispositionFormData(filename,filename);
        headers.setCacheControl("must-revalidate, post- check=0, pre-check=0");
        ResponseEntity response =  new ResponseEntity<>(contens, headers,HttpStatus.OK);
        return response;
    }

    @PostMapping("")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
