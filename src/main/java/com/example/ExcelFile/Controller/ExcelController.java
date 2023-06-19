package com.example.ExcelFile.Controller;


import com.example.ExcelFile.Entity.Excel;
import com.example.ExcelFile.Helper.ExcelHelper;
import com.example.ExcelFile.Service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/uploadFile")
    public String upload(@RequestParam("fileUpload") MultipartFile file,RedirectAttributes redirectAttributes) {
        if (ExcelHelper.checkExcelFormat(file)) {
            excelService.save(file);
            List<String> errors = excelService.getValidationErrors();
            List<Excel> ListErrors = excelService.getExcelErrorList();
            int ListErrorsLength = excelService.getTotalFails();
            long excelListLength = excelService.getTotalSuccess();
            long totalRecords = excelService.getTotalRecords();


            redirectAttributes.addFlashAttribute("ListErrorsLength",ListErrorsLength);
            redirectAttributes.addFlashAttribute("excelListLength",excelListLength);
            redirectAttributes.addFlashAttribute("errors",errors);
            redirectAttributes.addFlashAttribute("ListErrors",ListErrors);
            redirectAttributes.addFlashAttribute("totalRecords", totalRecords);


            return "redirect:/";
        }
        else {
            String fileType = "Upload an Excel file";
            redirectAttributes.addFlashAttribute("fileType",fileType);
            return "redirect:/";
        }
    }
}


//            model.addAttribute("ListErrorsLength",ListErrorsLength);
//            model.addAttribute("excelListLength",excelListLength);
//            model.addAttribute("errors",errors);
//            model.addAttribute("ListErrors",ListErrors);
//            model.addAttribute("totalRecords", totalRecords);

//    @PostMapping("/uploadFile")
//    public String upload(@RequestParam("fileUpload") MultipartFile file) {
//        if (ExcelHelper.checkExcelFormat(file)) {
//            excelService.save(file);
//                return "success";
//            } else {
//            return "error";
//        }
//    }

//    @PostMapping("/uploadFile")
//    public String upload(@RequestParam("fileUpload") MultipartFile file, Model model) {
//        if (ExcelHelper.checkExcelFormat(file)) {
//            List<String> validationErrors = excelService.save(file);
//            if (validationErrors.isEmpty()) {
//                return "success";
//            } else {
//                model.addAttribute("validationErrors", validationErrors);
//                return "success";
//            }
//        } else {
//            return "error";
//        }
//    }






















//
//package com.example.ExcelFile.Controller;
//
//        import com.example.ExcelFile.Entity.Excel;
//        import com.example.ExcelFile.Helper.ExcelHelper;
//        import com.example.ExcelFile.Service.ExcelService;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.http.HttpStatus;
//        import org.springframework.http.ResponseEntity;
//        import org.springframework.stereotype.Controller;
//        import org.springframework.ui.Model;
//        import org.springframework.web.bind.annotation.GetMapping;
//        import org.springframework.web.bind.annotation.PostMapping;
//        import org.springframework.web.bind.annotation.RequestParam;
//        import org.springframework.web.multipart.MultipartFile;
//        import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//        import java.io.InputStream;
//        import java.util.List;
//        import java.util.Map;
//
//@Controller
//public class ExcelController {
//
//    @Autowired
//    private ExcelService excelService;
//
//    @GetMapping("/")
//    public String home(){
//        return "Index";
//    }
//
//    @PostMapping("/uploadFile")
//    public ResponseEntity<?> upload(@RequestParam("fileUpload")MultipartFile file){
//        if(ExcelHelper.checkExcelFormat(file)){
//            excelService.save(file);
//            return ResponseEntity.status(HttpStatus.OK).body("Success");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
//    }
//
//}
