package ui.ft.ccit.faculty.transaksi.pemasok.dto;

import jakarta.validation.constraints.*;

public class UpdatePemasokRequest {
<<<<<<< HEAD
	
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String namaPemasok;
	
	@Size(max = 255, message = "Alamat maksimal 255 karakter")
	private String alamatPemasok;
	
	@Pattern(regexp = "\\d{10,15}", message = "Telepon harus 10-15 digit angka")
	private String teleponPemasok;
	
	public UpdatePemasokRequest() {}
	
	public String getNamaPemasok() { return namaPemasok; }
	public void setNamaPemasok(String namaPemasok) { this.namaPemasok = namaPemasok; }
	
	public String getAlamatPemasok() { return alamatPemasok; }
	public void setAlamatPemasok(String alamatPemasok) { this.alamatPemasok = alamatPemasok; }
	
=======
	private String namaPemasok;
	private String alamatPemasok;
	@Pattern(regexp = "\\d{10,15}")
	private String teleponPemasok;

	// Getters/Setters
	public String getNamaPemasok() { return namaPemasok; }
	public void setNamaPemasok(String namaPemasok) { this.namaPemasok = namaPemasok; }
	public String getAlamatPemasok() { return alamatPemasok; }
	public void setAlamatPemasok(String alamatPemasok) { this.alamatPemasok = alamatPemasok; }
>>>>>>> feature/pagination-implementation
	public String getTeleponPemasok() { return teleponPemasok; }
	public void setTeleponPemasok(String teleponPemasok) { this.teleponPemasok = teleponPemasok; }
}
