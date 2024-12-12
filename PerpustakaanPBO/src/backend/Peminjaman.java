/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author Admin
 */

import backend.DBHelper;
import backend.Buku;
import backend.Anggota;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Peminjaman {
    private int idPeminjaman;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPinjam;
    private String tanggalKembali;

    public Peminjaman() {
    }

    public Peminjaman(Anggota anggota, Buku buku, String tanggalPinjam, String tanggalKembali) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() { 
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
    
    public Peminjaman getById(int id) {
        Peminjaman peminjaman = new Peminjaman();
        String sql = "SELECT p.*, a.nama as anggota, b.judul as buku "
                + "FROM peminjaman p LEFT JOIN anggota a ON p.idanggota = a.idanggota "
                + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
                + "WHERE p.idpeminjaman = '" + id + "'";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("anggota"));
                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("buku"));
                peminjaman.setTanggalPinjam(rs.getString("tanggal_pinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggal_kembali"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peminjaman;
    }   

    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> listPeminjaman = new ArrayList();
        String sql = "SELECT p.*, a.nama as anggota, b.judul as buku "
                + "FROM peminjaman p LEFT JOIN anggota a ON p.idanggota = a.idanggota "
                + "LEFT JOIN buku b ON p.idbuku = b.idbuku";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("anggota"));
                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("buku"));
                peminjaman.setTanggalPinjam(rs.getString("tanggal_pinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggal_kembali"));

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }

    public ArrayList<Peminjaman> search(String keyword) {
        ArrayList<Peminjaman> listPeminjaman = new ArrayList();
        String sql = "SELECT p.*, a.nama as anggota, b.judul as buku "
                + "FROM peminjaman p LEFT JOIN anggota a ON p.idanggota = a.idanggota "
                + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
                + "WHERE a.nama LIKE '%" + keyword + "%' "
                + "OR b.judul LIKE '%" + keyword + "%' "
                + "OR p.tanggal_pinjam LIKE '%" + keyword + "%' "
                + "OR p.tanggal_kembali LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("anggota"));
                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("buku"));
                peminjaman.setTanggalPinjam(rs.getString("tanggal_pinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggal_kembali"));

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }

    public void save() {
        if (getById(idPeminjaman).getIdPeminjaman() == 0) {
            String SQL = "INSERT INTO peminjaman (idanggota, idbuku, tanggal_pinjam, tanggal_kembali) VALUES("
                    + this.getAnggota().getIdanggota() + ", "
                    + this.getBuku().getIdbuku() + ", "
                    + "'" + this.tanggalPinjam + "', "
                    + "'" + this.tanggalKembali + "'"
                    + ")";
            this.idPeminjaman = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE peminjaman SET "
                    + "idanggota = " + this.getAnggota().getIdanggota() + ", "
                    + "idbuku = " + this.getBuku().getIdbuku() + ", "
                    + "tanggal_pinjam = '" + this.tanggalPinjam + "', "
                    + "tanggal_kembali = '" + this.tanggalKembali + "' "
                    + "WHERE idpeminjaman = '" + this.idPeminjaman + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    public void delete() {
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = '" + this.idPeminjaman + "'";
        DBHelper.executeQuery(SQL);
    }
}
