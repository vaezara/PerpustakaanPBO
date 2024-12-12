/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author Admin
 */
import java.sql.*;
import java.util.ArrayList;

public class Pegawai {
    private int idpegawai;
    private String nama;
    private String alamat;
    private String notelp;
    private String jabatan;
    private String username;
    private String password;

    public Pegawai() {
    }

    public Pegawai(String nama, String alamat, String notelp, String jabatan, String username, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.notelp = notelp;
        this.jabatan = jabatan;
        this.username = username;
        this.password = password;
    }

    public int getIdpegawai() {
        return idpegawai;
    }

    public void setIdpegawai(int idpegawai) {
        this.idpegawai = idpegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public Pegawai getById(int id) {
        Pegawai pgw = new Pegawai();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai " + " WHERE idpegawai = '" + id + "'");
        
        try {
            while(rs.next()) {
                pgw = new Pegawai();
                pgw.setIdpegawai(rs.getInt("idpegawai"));
                pgw.setNama(rs.getString("nama"));
                pgw.setAlamat(rs.getString("alamat"));
                pgw.setNotelp(rs.getString("notelp"));
                pgw.setJabatan(rs.getString("jabatan"));
                pgw.setUsername(rs.getString("username"));
                pgw.setPassword(rs.getString("password"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pgw;
    }
    
    public void save() {
        if(getById(idpegawai).getIdpegawai() == 0) {
            String SQL = "INSERT INTO pegawai (nama, alamat, notelp, jabatan, username, password) VALUES ("
            + "     '" + this.nama + "', "
            + "     '" + this.alamat + "', "
            + "     '" + this.notelp + "', "
            + "     '" + this.jabatan + "', "
            + "     '" + this.username + "',"
            + "     '" + this.password + "')";

            this.idpegawai = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE pegawai SET "
            + "nama = '" + this.nama + "', "
            + "alamat = '" + this.alamat + "', "
            + "notelp = '" + this.notelp + "', "
            + "jabatan = '" + this.jabatan + "', "
            + "username = '" + this.username + "', "
            + "password = '" + this.password + "', "
            + "WHERE idpegawai = '" + this.idpegawai + "'";

            
            DBHelper.executeQuery(SQL);           
        }
    }
    
    public ArrayList<Pegawai> getAll() {
        ArrayList<Pegawai> ListPegawai = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai");
        
        try {
            while (rs.next()) {
                Pegawai pgw = new Pegawai();
                pgw.setIdpegawai(rs.getInt("idpegawai"));
                pgw.setNama(rs.getString("nama"));
                pgw.setAlamat(rs.getString("alamat"));
                pgw.setNotelp(rs.getString("notelp"));
                pgw.setJabatan(rs.getString("jabatan"));
                pgw.setUsername(rs.getString("username"));
                pgw.setPassword(rs.getString("password"));

                
                ListPegawai.add(pgw);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPegawai;
    }
    
//    public ArrayList<Pegawai> search(String keyword) {
//        ArrayList<Pegawai> ListPegawai = new ArrayList();
//        
//        String sql = "SELECT * FROM pegawai WHERE "
//                        + "     username LIKE '%" + keyword + "%'";
//        ResultSet rs= DBHelper.selectQuery(sql);
//        
//        try {
//            while(rs.next()) {
//                Pegawai pgw = new Pegawai();
//                pgw.setIdpegawai(rs.getInt("idpegawai"));
//                pgw.setUsername(rs.getString("username"));
//                
//                ListPegawai.add(pgw);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ListPegawai;
//    }
    
    public ArrayList<Pegawai> search(String username) {
        ArrayList<Pegawai> list = new ArrayList<>();
        String sql = "SELECT * FROM pegawai WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/namadatabase", "username", "password");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Pegawai pgw = new Pegawai();
                pgw.setIdpegawai(rs.getInt("idpegawai"));
                pgw.setNama(rs.getString("nama"));
                pgw.setAlamat(rs.getString("alamat"));
                pgw.setNotelp(rs.getString("notelp"));
                pgw.setJabatan(rs.getString("jabatan"));
                pgw.setUsername(rs.getString("username"));
                list.add(pgw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public void delete() {
        String SQL = "DELETE FROM pegawai WHERE idpegawai = '" + this.idpegawai + "'";
        DBHelper.executeQuery(SQL);
    } 

    public boolean isUsernameExists(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
