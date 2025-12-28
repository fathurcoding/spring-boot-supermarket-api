package ui.ft.ccit.faculty.transaksi.pelanggan.dto;

import java.time.LocalDate;
import java.time.Period;

public class PelangganResponse {
	private String idPelanggan;
	private String nama;
	private String jenisKelamin;
	private String alamat;
	private String telepon;
	private LocalDate tglLahir;
	private String jenisPelanggan;
	private Integer usia;
	private Integer discountRate;

	public PelangganResponse(String idPelanggan, String nama, String jenisKelamin, String alamat, String telepon,
			LocalDate tglLahir, String jenisPelanggan) {
		this.idPelanggan = idPelanggan;
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.alamat = alamat;
		this.telepon = telepon;
		this.tglLahir = tglLahir;
		this.jenisPelanggan = jenisPelanggan;
		this.usia = calculateUsia(tglLahir);
		this.discountRate = calculateDiscount(jenisPelanggan);
	}

	private Integer calculateUsia(LocalDate tglLahir) {
		if (tglLahir == null)
			return null;
		return Period.between(tglLahir, LocalDate.now()).getYears();
	}

	private Integer calculateDiscount(String jenis) {
		if ("Gold".equalsIgnoreCase(jenis))
			return 10;
		if ("Silver".equalsIgnoreCase(jenis))
			return 5;
		return 0;
	}

	// Getters
	public String getIdPelanggan() {
		return idPelanggan;
	}

	public String getNama() {
		return nama;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public String getAlamat() {
		return alamat;
	}

	public String getTelepon() {
		return telepon;
	}

	public LocalDate getTglLahir() {
		return tglLahir;
	}

	public String getJenisPelanggan() {
		return jenisPelanggan;
	}

	public Integer getUsia() {
		return usia;
	}

	public Integer getDiscountRate() {
		return discountRate;
	}
}
