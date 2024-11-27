/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.PhieuMuonSach;
import model.PhieuMuonSachTable;
import util.ChuanHoa;

/**
 *
 * @author admin
 */
public class PhieuMuonSachDAO extends DAO{

    public PhieuMuonSachDAO() {
        super();
    }
    
    public String timTenDG(String maDG) {
        String sql = "select hoTen from people where maDG = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maDG);
            ResultSet res = ps.executeQuery();
            while(res.next()) {
                String tenDG = res.getString(1);
                return tenDG;
            } 
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "-1";
        }
        return "-1";
    }
    
    public String timtenSach(String maSach) {
        String sql = "select tenSach from books where maSach = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maSach);
            ResultSet res = ps.executeQuery();
            while(res.next()) {
                String tenDG = res.getString(1);
                return tenDG;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "-1";
        }
        return "-1";
    }
    
    public boolean addPhieuMuonSach(PhieuMuonSach phieu) {
        String sql = "insert into borrow(maDG, maSach, ngayMuon, soLuong, tinhTrang) values (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phieu.getMaDG());
            ps.setString(2, phieu.getMaSach());
            String date = ChuanHoa.chuanHoaNgayThang(phieu.getNgayMuon());
            ps.setString(3, date);
            ps.setInt(4, phieu.getSoLuong());
            ps.setInt(5, phieu.getTinhTrang());
            
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
    
    public void traSach(int maMuon) {
        String sql = "update borrow set tinhTrang = 0 where maMuon = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maMuon);
            ps.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
     public void deletePhieuMuonSach(int maMuon) {
        String sql = "delete from borrow where maMuon = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maMuon);
            ps.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    
    public int getSoLuongDocGiaMuonSach() {
        String sql = "select COUNT(distinct maDG) as soLuong from borrow group by maDG";
        try {
            int sum = 0;
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(sql);
            while(set.next()) {
                sum += Integer.parseInt(set.getString("soLuong"));
            }
            return sum;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public DefaultTableModel getListPhieu() {
        String sql = "select * from borrow";
        String[] col = {"Mã mượn", "Mã ĐG", "Mã sách", "Họ tên", "Tên sách", "Ngày mượn", "Số lượng", "Tình trạng"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        try {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                Vector vecto = new Vector();
                vecto.add(res.getInt(1));
                vecto.add(res.getString(2));
                vecto.add(res.getString(3));
                String hoTen = timTenDG(res.getString(2));
                vecto.add(hoTen);
                String tenSach = timtenSach(res.getString(3));
                vecto.add(tenSach);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date = formatter.format(res.getDate(4));
                vecto.add(date);
                vecto.add(res.getInt(5));
                String tinhTrang = "";
                if(res.getInt(6) == 1)
                    tinhTrang += "Đang mượn";
                else
                    tinhTrang += "Đã trả";
                vecto.add(tinhTrang);
                model.addRow(vecto);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return model;
    }
    
    public ArrayList<PhieuMuonSachTable> getAllPhieuMuon() {
        String sql = "select * from borrow";
        ArrayList<PhieuMuonSachTable> arr = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                String tinhTrang = "";
                String hoTen = timTenDG(res.getString(2));
                String tenSach = timtenSach(res.getString(3));
                if(res.getInt(6) == 1)
                    tinhTrang = "Đang mượn";
                else
                    tinhTrang = "Đã trả";
                
                PhieuMuonSachTable pm = new PhieuMuonSachTable(res.getInt(1), res.getString(2), res.getString(3), hoTen, tenSach,res.getString(4), res.getInt(5), tinhTrang);
                arr.add(pm);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return arr;
    }
}
