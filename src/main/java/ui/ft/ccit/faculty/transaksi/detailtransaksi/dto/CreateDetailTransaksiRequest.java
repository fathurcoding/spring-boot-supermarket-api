package ui.ft.ccit.faculty.transaksi.detailtransaksi.dto;

import jakarta.validation.constraints.*;

public class CreateDetailTransaksiRequest {
<<<<<<< HEAD
	
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
=======
	@NotBlank
	private String kodeTransaksi;
	@NotBlank
	private String idBarang;
	@Min(1)
	private Integer jumlah;

	public String getKodeTransaksi() { return kodeTransaksi; }
	public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }
	public String getIdBarang() { return idBarang; }
	public void setIdBarang(String idBarang) { this.idBarang = idBarang; }
	public Integer getJumlah() { return jumlah; }
	public void setJumlah(Integer jumlah) { this.jumlah = jumlah; }
>>>>>>> feature/pagination-implementation
}
