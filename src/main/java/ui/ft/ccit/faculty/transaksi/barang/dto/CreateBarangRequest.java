package ui.ft.ccit.faculty.transaksi.barang.dto;

import jakarta.validation.constraints.*;

public class CreateBarangRequest {
	@NotBlank(message = "ID barang tidak boleh kosong")
	@Pattern(regexp = "B\\d{3}", message = "ID barang harus format B###")
	private String idBarang;

	@NotBlank(message = "Nama tidak boleh kosong")
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String nama;

	@Min(value = 0, message = "Stok tidak boleh negatif")
	private Integer stok;

	@Positive(message = "Harga harus lebih dari 0")
	private Double harga;

	@Min(0)
	@Max(100)
	private Integer persenLaba;

	@Min(0)
	@Max(100)
	private Integer diskon;

	@NotNull(message = "ID Jenis Barang tidak boleh kosong")
	private Integer idJenisbrg;

	@NotBlank(message = "ID Pemasok tidak boleh kosong")
	private String idPemasok;

	// Getters and Setters
	public String getIdBarang() {
		return idBarang;
	}

	public void setIdBarang(String idBarang) {
		this.idBarang = idBarang;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Integer getStok() {
		return stok;
	}

	public void setStok(Integer stok) {
		this.stok = stok;
	}

	public Double getHarga() {
		return harga;
	}

	public void setHarga(Double harga) {
		this.harga = harga;
	}

	public Integer getPersenLaba() {
		return persenLaba;
	}

	public void setPersenLaba(Integer persenLaba) {
		this.persenLaba = persenLaba;
	}

	public Integer getDiskon() {
		return diskon;
	}

	public void setDiskon(Integer diskon) {
		this.diskon = diskon;
	}

	public Integer getIdJenisbrg() {
		return idJenisbrg;
	}

	public void setIdJenisbrg(Integer idJenisbrg) {
		this.idJenisbrg = idJenisbrg;
	}

	public String getIdPemasok() {
		return idPemasok;
	}

	public void setIdPemasok(String idPemasok) {
		this.idPemasok = idPemasok;
	}
}
