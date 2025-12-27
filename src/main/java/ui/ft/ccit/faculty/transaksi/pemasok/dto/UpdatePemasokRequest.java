package ui.ft.ccit.faculty.transaksi.pemasok.dto;

import jakarta.validation.constraints.*;

public class UpdatePemasokRequest {
	private String namaPemasok;
	private String alamatPemasok;
	@Pattern(regexp = "\\d{10,15}")
	private String teleponPemasok;

	// Getters/Setters
	public String getNamaPemasok() { return namaPemasok; }
	public void setNamaPemasok(String namaPemasok) { this.namaPemasok = namaPemasok; }
	public String getAlamatPemasok() { return alamatPemasok; }
	public void setAlamatPemasok(String alamatPemasok) { this.alamatPemasok = alamatPemasok; }
	public String getTeleponPemasok() { return teleponPemasok; }
	public void setTeleponPemasok(String teleponPemasok) { this.teleponPemasok = teleponPemasok; }
}
