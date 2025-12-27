package ui.ft.ccit.faculty.transaksi.karyawan.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * DTO for creating a new Karyawan.
 * 
 * <p>Contains validation rules for employee creation.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class CreateKaryawanRequest {
	
	@NotBlank(message = "ID karyawan tidak boleh kosong")
	@Size(max = 4, message = "ID karyawan maksimal 4 karakter")
	@Pattern(regexp = "K\\d{3}", message = "ID karyawan harus format K### (contoh: K001)")
	private String idKaryawan;
	
	@NotBlank(message = "Nama karyawan tidak boleh kosong")
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String nama;
	
	@NotBlank(message = "Jenis kelamin tidak boleh kosong")
	@Pattern(regexp = "[LP]", message = "Jenis kelamin harus L (Laki-laki) atau P (Perempuan)")
	private String jenisKelamin;
	
	@NotBlank(message = "Alamat tidak boleh kosong")
	@Size(max = 255, message = "Alamat maksimal 255 karakter")
	private String alamat;
	
	@NotBlank(message = "Telepon tidak boleh kosong")
	@Pattern(regexp = "\\d{10,15}", message = "Telepon harus 10-15 digit angka")
	private String telepon;
	
	@NotNull(message = "Tanggal lahir tidak boleh kosong")
	@Past(message = "Tanggal lahir harus di masa lalu")
	private LocalDate tglLahir;
	
	@NotNull(message = "Gaji tidak boleh kosong")
	@Positive(message = "Gaji harus lebih dari 0")
	@DecimalMin(value = "1000000", message = "Gaji minimum Rp 1.000.000")
	private Double gaji;
	
	// Constructors
	public CreateKaryawanRequest() {
	}
	
	public CreateKaryawanRequest(String idKaryawan, String nama, String jenisKelamin, 
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
	public String getIdKaryawan() { return idKaryawan; }
	public void setIdKaryawan(String idKaryawan) { this.idKaryawan = idKaryawan; }
	
	public String getNama() { return nama; }
	public void setNama(String nama) { this.nama = nama; }
	
	public String getJenisKelamin() { return jenisKelamin; }
	public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
	
	public String getAlamat() { return alamat; }
	public void setAlamat(String alamat) { this.alamat = alamat; }
	
	public String getTelepon() { return telepon; }
	public void setTelepon(String telepon) { this.telepon = telepon; }
	
	public LocalDate getTglLahir() { return tglLahir; }
	public void setTglLahir(LocalDate tglLahir) { this.tglLahir = tglLahir; }
	
	public Double getGaji() { return gaji; }
	public void setGaji(Double gaji) { this.gaji = gaji; }
}
