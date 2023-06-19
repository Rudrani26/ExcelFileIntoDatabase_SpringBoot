package com.example.ExcelFile.Service;

import com.example.ExcelFile.Entity.Excel;
import com.example.ExcelFile.Helper.ExcelHelper;
import com.example.ExcelFile.Repository.ExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    private ExcelRepository excelRepository;
    public void save(MultipartFile file){

        try {
            List<Excel> excels = ExcelHelper.exceltoList(file.getInputStream());


            excelRepository.saveAll(excels);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public long getTotalRecords(){
        long TotalRecords = excelRepository.count() + ExcelHelper.getTotalFails();
        return (TotalRecords);
    }


    public List<String> getValidationErrors(){
        return ExcelHelper.getValidationErrors();
    }

    public List<Excel> getExcelErrorList(){
        return ExcelHelper.getExcelErrorList();
    }

    public long getTotalSuccess(){
        return excelRepository.count();
    }

    public int getTotalFails(){
        return ExcelHelper.getTotalFails();
    }

}



