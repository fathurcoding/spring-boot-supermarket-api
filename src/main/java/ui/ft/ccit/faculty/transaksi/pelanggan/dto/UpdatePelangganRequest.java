package ui.ft.ccit.faculty.transaksi.pelanggan.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

<<<<<<< HEAD
/**
 * DTO for updating Pelanggan.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class UpdatePelangganRequest {
	
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String nama;
	
	@Pattern(regexp = "[LP]", message = "Jenis kelamin harus L atau P")
	private String jenisKelamin;
	
	@Size(max = 255, message = "Alamat maksimal 255 karakter")
	private String alamat;
	
	@Pattern(regexp = "\\d{10,15}", message = "Telepon harus 10-15 digit angka")
	private String telepon;
	
	@Past(message = "Tanggal lahir harus di masa lalu")
	private LocalDate tglLahir;
	
	@Pattern(regexp = "(Silver|Gold)", message = "Jenis pelanggan harus Silver atau Gold")
	private String jenisPelanggan;
	
	public UpdatePelangganRequest() {
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
	
=======
public class UpdatePelangganRequest {
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String nama;

	@Pattern(regexp = "[LP]", message = "Jenis kelamin harus L atau P")
	private String jenisKelamin;

	private String alamat;

	@Pattern(regexp = "\\d{10,15}", message = "Telepon harus 10-15 digit angka")
	private String telepon;

	@Past(message = "Tanggal lahir harus di masa lalu")
	private LocalDate tglLahir;

	@Pattern(regexp = "(Silver|Gold)", message = "Jenis pelanggan harus Silver atau Gold")
	private String jenisPelanggan;

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
>>>>>>> feature/pagination-implementation
	public String getJenisPelanggan() { return jenisPelanggan; }
	public void setJenisPelanggan(String jenisPelanggan) { this.jenisPelanggan = jenisPelanggan; }
}
