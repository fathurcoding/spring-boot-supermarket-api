package ui.ft.ccit.faculty.transaksi.transaksi.model;

import jakarta.persistence.*;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;

import java.time.LocalDateTime;

/**
 * Entity representing a transaction (transaksi) in the supermarket.
 * 
 * <p>
 * This entity has relationships with Karyawan and Pelanggan entities.
 * </p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Entity
@Table(name = "transaksi", indexes = {
		@Index(name = "idx_transaksi_date", columnList = "tgl_transaksi")
})
public class Transaksi {

	@Id
	@Column(name = "kode_transaksi", length = 4, nullable = false)
	private String kodeTransaksi;

	@Column(name = "tgl_transaksi", nullable = false)
	private LocalDateTime tglTransaksi;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pelanggan", nullable = false)
	private Pelanggan pelanggan;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_karyawan", nullable = false)
	private Karyawan karyawan;

	public Transaksi() {
	}

	public Transaksi(String kodeTransaksi, LocalDateTime tglTransaksi,
			Pelanggan pelanggan, Karyawan karyawan) {
		this.kodeTransaksi = kodeTransaksi;
		this.tglTransaksi = tglTransaksi;
		this.pelanggan = pelanggan;
		this.karyawan = karyawan;
	}

	// Getters and Setters

	public String getKodeTransaksi() {
		return kodeTransaksi;
	}

	public void setKodeTransaksi(String kodeTransaksi) {
		this.kodeTransaksi = kodeTransaksi;
	}

	public LocalDateTime getTglTransaksi() {
		return tglTransaksi;
	}

	public void setTglTransaksi(LocalDateTime tglTransaksi) {
		this.tglTransaksi = tglTransaksi;
	}

	public Pelanggan getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(Pelanggan pelanggan) {
		this.pelanggan = pelanggan;
	}

	public Karyawan getKaryawan() {
		return karyawan;
	}

	public void setKaryawan(Karyawan karyawan) {
		this.karyawan = karyawan;
	}
}
