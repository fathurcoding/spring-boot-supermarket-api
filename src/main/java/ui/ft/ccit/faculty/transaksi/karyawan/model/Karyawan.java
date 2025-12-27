package ui.ft.ccit.faculty.transaksi.karyawan.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity representing an employee (karyawan) in the supermarket.
 * 
 * <p>This entity maps to the 'karyawan' table in the database and contains
 * employee information including personal details and salary.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Entity
@Table(name = "karyawan")
public class Karyawan {
	
	@Id
	@Column(name = "id_karyawan", length = 4, nullable = false)
	private String idKaryawan;
	
	@Column(name = "nama", length = 20, nullable = false)
	private String nama;
	
	@Column(name = "jenis_kelamin", length = 1, nullable = false)
	private String jenisKelamin = "L";
	
	@Column(name = "alamat", length = 50, nullable = false)
	private String alamat;
	
	@Column(name = "telepon", length = 15)
	private String telepon;
	
	@Column(name = "tgl_lahir", nullable = false)
	private LocalDate tglLahir;
	
	@Column(name = "gaji", nullable = false)
	private Double gaji;
	
	/**
	 * Default constructor required by JPA.
	 */
	public Karyawan() {
	}
	
	/**
	 * Constructor with all fields.
	 * 
	 * @param idKaryawan employee ID (4 characters)
	 * @param nama employee name
	 * @param jenisKelamin gender ('L' for male, 'P' for female)
	 * @param alamat address
	 * @param telepon phone number
	 * @param tglLahir birth date
	 * @param gaji salary
	 */
	public Karyawan(String idKaryawan, String nama, String jenisKelamin, 
	                String alamat, String telepon, LocalDate tglLahir, Double gaji) {
		this.idKaryawan = idKaryawan;
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.alamat = alamat;
		this.telepon = telepon;
		this.tglLahir = tglLahir;
		this.gaji = gaji;
	}
	
	// Getters and Setters
	
	public String getIdKaryawan() {
		return idKaryawan;
	}
	
	public void setIdKaryawan(String idKaryawan) {
		this.idKaryawan = idKaryawan;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	
	public String getAlamat() {
		return alamat;
	}
	
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public String getTelepon() {
		return telepon;
	}
	
	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}
	
	public LocalDate getTglLahir() {
		return tglLahir;
	}
	
	public void setTglLahir(LocalDate tglLahir) {
		this.tglLahir = tglLahir;
	}
	
	public Double getGaji() {
		return gaji;
	}
	
	public void setGaji(Double gaji) {
		this.gaji = gaji;
	}
}
