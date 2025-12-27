<<<<<<< HEAD
package ui.ft.ccit.faculty.transaksi.

transaksi.dto;

import java.time.LocalDate;

public class TransaksiResponse {
	private String kodeTransaksi;
	private LocalDate tglTransaksi;
	private String idKaryawan;
	private String idPelanggan;
	
	public TransaksiResponse() {}
	
	public TransaksiResponse(String kodeTransaksi, LocalDate tglTransaksi, String idKaryawan, String idPelanggan) {
		this.kodeTransaksi = kodeTransaksi;
		this.tglTransaksi = tglTransaksi;
		this.idKaryawan = idKaryawan;
		this.idPelanggan = idPelanggan;
	}
	
	public String getKodeTransaksi() { return kodeTransaksi; }
	public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }
	
	public LocalDate getTglTransaksi() { return tglTransaksi; }
	public void setTglTransaksi(LocalDate tglTransaksi) { this.tglTransaksi = tglTransaksi; }
	
	public String getIdKaryawan() { return idKaryawan; }
	public void setIdKaryawan(String idKaryawan) { this.idKaryawan = idKaryawan; }
	
	public String getIdPelanggan() { return idPelanggan; }
	public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
=======
package ui.ft.ccit.faculty.transaksi.transaksi.dto;

import java.time.LocalDateTime;

public class TransaksiResponse {
	private String kodeTransaksi;
	private LocalDateTime tglTransaksi;
	private String idPelanggan;
	private String idKaryawan;

	public TransaksiResponse(String kodeTransaksi, LocalDateTime tglTransaksi, String idPelanggan, String idKaryawan) {
		this.kodeTransaksi = kodeTransaksi;
		this.tglTransaksi = tglTransaksi;
		this.idPelanggan = idPelanggan;
		this.idKaryawan = idKaryawan;
	}

	public String getKodeTransaksi() { return kodeTransaksi; }
	public LocalDateTime getTglTransaksi() { return tglTransaksi; }
	public String getIdPelanggan() { return idPelanggan; }
	public String getIdKaryawan() { return idKaryawan; }
>>>>>>> feature/pagination-implementation
}
