package ui.ft.ccit.faculty.transaksi.detailtransaksi.dto;

import jakarta.validation.constraints.*;

public class CreateDetailTransaksiRequest {
	@NotBlank
	private String kodeTransaksi;
	@NotBlank
	private String idBarang;
	@Min(1)
	private Integer jumlah;

	public String getKodeTransaksi() {
		return kodeTransaksi;
	}

	public void setKodeTransaksi(String kodeTransaksi) {
		this.kodeTransaksi = kodeTransaksi;
	}

	public String getIdBarang() {
		return idBarang;
	}

	public void setIdBarang(String idBarang) {
		this.idBarang = idBarang;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}
}
