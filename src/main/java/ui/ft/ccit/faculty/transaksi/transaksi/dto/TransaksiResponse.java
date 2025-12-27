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
}
