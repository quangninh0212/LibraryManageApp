/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.DAO.con;
import model.DocGia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class DocGiaDAO extends DAO{

    public DocGiaDAO() {
        super();
    }
    
    public boolean addDocGia(DocGia s) {
        String sql = "insert into people values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getMaDG());
            ps.setString(2, s.getHoTen());
            ps.setString(3, s.getGioiTinh());
            ps.setString(4, s.getNgaySinh());
            ps.setString(5, s.getDoiTuong());
            
            int cnt = ps.executeUpdate();
            if(cnt != 0)
                return true;
            else
                return false;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void deleteDocGia(String maDG) {
        String sql = "delete from people where maDG = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maDG);
            ps.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public DefaultTableModel getListDocGia() {
        String sql = "select * from people";
        String[] col = {"Mã ĐG", "Họ tên", "Giới tính", "Ngày sinh", "Đối tượng"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        try {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                Vector vecto = new Vector();
                vecto.add(res.getString(1));
                vecto.add(res.getString(2));
                vecto.add(res.getString(3));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date = formatter.format(res.getDate(4));
                vecto.add(date);
                vecto.add(res.getString(5));
                model.addRow(vecto);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return model;
    }
    
    public ArrayList<DocGia> getAllDocGia() {
        String sql = "select * from people";
        ArrayList<DocGia> arr = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                DocGia dg = new DocGia(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                arr.add(dg);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return arr;
    }
    
//    public int getCountDG() {
//        
//    }

}