package ui.ft.ccit.faculty.transaksi.transaksi.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CreateTransaksiRequest {
	@NotBlank(message = "Kode transaksi tidak boleh kosong")
	@Pattern(regexp = "J\\d{3}")
	private String kodeTransaksi;

	@NotNull
	private LocalDate tglTransaksi;

	@NotBlank
	private String idPelanggan;

	@NotBlank
	private String idKaryawan;

	// Getters/Setters
	public String getKodeTransaksi() { return kodeTransaksi; }
	public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }
	public LocalDate getTglTransaksi() { return tglTransaksi; }
	public void setTglTransaksi(LocalDate tglTransaksi) { this.tglTransaksi = tglTransaksi; }
	public String getIdPelanggan() { return idPelanggan; }
	public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
	public String getIdKaryawan() { return idKaryawan; }
	public void setIdKaryawan(String idKaryawan) { this.idKaryawan = idKaryawan; }
}
