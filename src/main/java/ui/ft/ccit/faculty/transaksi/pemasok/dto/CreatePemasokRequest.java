package ui.ft.ccit.faculty.transaksi.pemasok.dto;

import jakarta.validation.constraints.*;

public class CreatePemasokRequest {
	@NotBlank(message = "ID pemasok tidak boleh kosong")
	@Pattern(regexp = "S\\d{3}", message = "ID pemasok harus format S###")
	private String idPemasok;

	@NotBlank(message = "Nama pemasok tidak boleh kosong")
	private String namaPemasok;

	@NotBlank
	private String alamatPemasok;

	@Pattern(regexp = "\\d{10,15}")
	private String teleponPemasok;

	// Getters/Setters
	public String getIdPemasok() {
		return idPemasok;
	}

	public void setIdPemasok(String idPemasok) {
		this.idPemasok = idPemasok;
	}

	public String getNamaPemasok() {
		return namaPemasok;
	}

	public void setNamaPemasok(String namaPemasok) {
		this.namaPemasok = namaPemasok;
	}

	public String getAlamatPemasok() {
		return alamatPemasok;
	}

	public void setAlamatPemasok(String alamatPemasok) {
		this.alamatPemasok = alamatPemasok;
	}

	public String getTeleponPemasok() {
		return teleponPemasok;
	}

	public void setTeleponPemasok(String teleponPemasok) {
		this.teleponPemasok = teleponPemasok;
	}
}
