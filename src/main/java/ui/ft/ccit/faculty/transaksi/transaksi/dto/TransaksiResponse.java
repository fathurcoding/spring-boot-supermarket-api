package ui.ft.ccit.faculty.transaksi.transaksi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaksiResponse {
	private String kodeTransaksi;
	private LocalDateTime tglTransaksi;
	private String idPelanggan;
	private String idKaryawan;
	private BigDecimal totalHarga;

	public TransaksiResponse(String kodeTransaksi, LocalDateTime tglTransaksi, String idPelanggan, String idKaryawan,
			BigDecimal totalHarga) {
		this.kodeTransaksi = kodeTransaksi;
		this.tglTransaksi = tglTransaksi;
		this.idPelanggan = idPelanggan;
		this.idKaryawan = idKaryawan;
		this.totalHarga = totalHarga;
	}

	public String getKodeTransaksi() {
		return kodeTransaksi;
	}

	public LocalDateTime getTglTransaksi() {
		return tglTransaksi;
	}

	public String getIdPelanggan() {
		return idPelanggan;
	}

	public String getIdKaryawan() {
		return idKaryawan;
	}

	public BigDecimal getTotalHarga() {
		return totalHarga;
	}

	public void setTotalHarga(BigDecimal totalHarga) {
		this.totalHarga = totalHarga;
	}
}
