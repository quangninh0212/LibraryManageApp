/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author admin
 */
public class NgayThangNam {
    public static String getDate() {
         // Tạo một đối tượng JDateChooser
        JDateChooser dateChooser = new JDateChooser();
        
        // Lấy ngày hiện tại
        Date currentDate = new Date();
        dateChooser.setDate(currentDate); // Cập nhật ngày vào JDateChooser

        // Định dạng ngày theo kiểu dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(dateChooser.getDate());
        
        return formattedDate;
    }
}
