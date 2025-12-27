package ui.ft.ccit.faculty.transaksi.pelanggan.dto;

import jakarta.validation.constraints.*;
<<<<<<< HEAD

import java.time.LocalDate;

/**
 * DTO for creating a new Pelanggan.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class CreatePelangganRequest {
	
	@NotBlank(message = "ID pelanggan tidak boleh kosong")
	@Size(max = 4, message = "ID pelanggan maksimal 4 karakter")
	@Pattern(regexp = "P\\d{3}", message = "ID pelanggan harus format P### (contoh: P001)")
	private String idPelanggan;
	
	@NotBlank(message = "Nama pelanggan tidak boleh kosong")
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
	
	@NotBlank(message = "Jenis pelanggan tidak boleh kosong")
	@Pattern(regexp = "(Silver|Gold)", message = "Jenis pelanggan harus Silver atau Gold")
	private String jenisPelanggan;
	
	// Constructors
	public CreatePelangganRequest() {
	}
	
	// Getters and Setters
	public String getIdPelanggan() { return idPelanggan; }
	public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
	
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
import java.time.LocalDate;

public class CreatePelangganRequest {
	@NotBlank(message = "ID pelanggan tidak boleh kosong")
	@Pattern(regexp = "P\\d{3}", message = "ID pelanggan harus format P###")
	private String idPelanggan;

	@NotBlank(message = "Nama tidak boleh kosong")
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String nama;

	@Pattern(regexp = "[LP]", message = "Jenis kelamin harus L atau P")
	private String jenisKelamin;

	@NotBlank(message = "Alamat tidak boleh kosong")
	private String alamat;

	@Pattern(regexp = "\\d{10,15}", message = "Telepon harus 10-15 digit angka")
	private String telepon;

	@Past(message = "Tanggal lahir harus di masa lalu")
	private LocalDate tglLahir;

	@Pattern(regexp = "(Silver|Gold)", message = "Jenis pelanggan harus Silver atau Gold")
	private String jenisPelanggan;

	// Getters and Setters
	public String getIdPelanggan() { return idPelanggan; }
	public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
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
