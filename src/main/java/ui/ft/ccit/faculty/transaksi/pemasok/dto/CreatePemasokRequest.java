package ui.ft.ccit.faculty.transaksi.pemasok.dto;

import jakarta.validation.constraints.*;

public class CreatePemasokRequest {
	
	@NotBlank(message = "ID pemasok tidak boleh kosong")
	@Size(max = 4, message = "ID pemasok maksimal 4 karakter")
	@Pattern(regexp = "S\\d{3}", message = "ID pemasok harus format S### (contoh: S001)")
	private String idPemasok;
	
	@NotBlank(message = "Nama pemasok tidak boleh kosong")
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String namaPemasok;
	
	@NotBlank(message = "Alamat tidak boleh kosong")
	@Size(max = 255, message = "Alamat maksimal 255 karakter")
	private String alamatPemasok;
	
	@NotBlank(message = "Telepon tidak boleh kosong")
	@Pattern(regexp = "\\d{10,15}", message = "Telepon harus 10-15 digit angka")
	private String teleponPemasok;
	
	public CreatePemasokRequest() {}
	
	public String getIdPemasok() { return idPemasok; }
	public void setIdPemasok(String idPemasok) { this.idPemasok = idPemasok; }
	
	public String getNamaPemasok() { return namaPemasok; }
	public void setNamaPemasok(String namaPemasok) { this.namaPemasok = namaPemasok; }
	
	public String getAlamatPemasok() { return alamatPemasok; }
	public void setAlamatPemasok(String alamatPemasok) { this.alamatPemasok = alamatPemasok; }
	
	public String getTeleponPemasok() { return teleponPemasok; }
	public void setTeleponPemasok(String teleponPemasok) { this.teleponPemasok = teleponPemasok; }
}
