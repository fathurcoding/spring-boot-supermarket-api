package ui.ft.ccit.faculty.transaksi.karyawan.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * DTO for updating an existing Karyawan.
 * 
 * <p>ID cannot be changed. All other fields are optional for partial updates.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class UpdateKaryawanRequest {
	
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String nama;
	
	@Pattern(regexp = "[LP]", message = "Jenis kelamin harus L (Laki-laki) atau P (Perempuan)")
	private String jenisKelamin;
	
	@Size(max = 255, message = "Alamat maksimal 255 karakter")
	private String alamat;
	
	@Pattern(regexp = "\\d{10,15}", message = "Telepon harus 10-15 digit angka")
	private String telepon;
	
	@Past(message = "Tanggal lahir harus di masa lalu")
	private LocalDate tglLahir;
	
	@Positive(message = "Gaji harus lebih dari 0")
	@DecimalMin(value = "1000000", message = "Gaji minimum Rp 1.000.000")
	private Double gaji;
	
	// Constructors
	public UpdateKaryawanRequest() {
	}
	
	// Getters and Setters
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
