package ui.ft.ccit.faculty.transaksi.detailtransaksi.dto;

import jakarta.validation.constraints.*;

public class CreateDetailTransaksiRequest {
	
	@NotBlank(message = "Kode transaksi tidak boleh kosong")
	private String kodeTransaksi;
	
	@NotBlank(message = "ID barang tidak boleh kosong")
	private String idBarang;
	
	@NotNull(message = "Jumlah tidak boleh kosong")
	@Min(value = 1, message = "Jumlah minimal 1")
	@Max(value = 32767, message = "Jumlah maksimal 32767")
	private Short jumlah;
	
	public CreateDetailTransaksiRequest() {}
	
	public String getKodeTransaksi() { return kodeTransaksi; }
	public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }
	
	public String getIdBarang() { return idBarang; }
	public void setIdBarang(String idBarang) { this.idBarang = idBarang; }
	
	public Short getJumlah() { return jumlah; }
	public void setJumlah(Short jumlah) { this.jumlah = jumlah; }
}
