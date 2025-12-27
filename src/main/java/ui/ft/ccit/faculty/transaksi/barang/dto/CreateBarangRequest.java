package ui.ft.ccit.faculty.transaksi.barang.dto;

import jakarta.validation.constraints.*;

public class CreateBarangRequest {
<<<<<<< HEAD
	
	@NotBlank(message = "ID barang tidak boleh kosong")
	@Size(max = 4, message = "ID barang maksimal 4 karakter")
	@Pattern(regexp = "B\\d{3}", message = "ID barang harus format B### (contoh: B001)")
	private String idBarang;
	
	@NotBlank(message = "Nama barang tidak boleh kosong")
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String nama;
	
	@NotNull(message = "Stok tidak boleh kosong")
	@Min(value = 0, message = "Stok tidak boleh negatif")
	private Short stok;
	
	@NotNull(message = "Harga tidak boleh kosong")
	@Positive(message = "Harga harus lebih dari 0")
	private Double harga;
	
	@NotNull(message = "Persen laba tidak boleh kosong")
	@Min(value = 0, message = "Persen laba minimal 0")
	@Max(value = 100, message = "Persen laba maksimal 100")
	private Double persenLaba;
	
	@NotNull(message = "Diskon tidak boleh kosong")
	@Min(value = 0, message = "Diskon minimal 0")
	@Max(value = 100, message = "Diskon maksimal 100")
	private Double diskon;
	
	@NotNull(message = "ID jenis barang tidak boleh kosong")
	private Byte idJenisbrg;
	
	@NotBlank(message = "ID pemasok tidak boleh kosong")
	private String idPemasok;
	
	public CreateBarangRequest() {}
	
	// Getters and Setters
	public String getIdBarang() { return idBarang; }
	public void setIdBarang(String idBarang) { this.idBarang = idBarang; }
	
	public String getNama() { return nama; }
	public void setNama(String nama) { this.nama = nama; }
	
	public Short getStok() { return stok; }
	public void setStok(Short stok) { this.stok = stok; }
	
	public Double getHarga() { return harga; }
	public void setHarga(Double harga) { this.harga = harga; }
	
	public Double getPersenLaba() { return persenLaba; }
	public void setPersenLaba(Double persenLaba) { this.persenLaba = persenLaba; }
	
	public Double getDiskon() { return diskon; }
	public void setDiskon(Double diskon) { this.diskon = diskon; }
	
	public Byte getIdJenisbrg() { return idJenisbrg; }
	public void setIdJenisbrg(Byte idJenisbrg) { this.idJenisbrg = idJenisbrg; }
	
=======
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

	@Min(0) @Max(100)
	private Integer persenLaba;

	@Min(0) @Max(100)
	private Integer diskon;

	@NotNull(message = "ID Jenis Barang tidak boleh kosong")
	private Integer idJenisbrg;

	@NotBlank(message = "ID Pemasok tidak boleh kosong")
	private String idPemasok;

	// Getters and Setters
	public String getIdBarang() { return idBarang; }
	public void setIdBarang(String idBarang) { this.idBarang = idBarang; }
	public String getNama() { return nama; }
	public void setNama(String nama) { this.nama = nama; }
	public Integer getStok() { return stok; }
	public void setStok(Integer stok) { this.stok = stok; }
	public Double getHarga() { return harga; }
	public void setHarga(Double harga) { this.harga = harga; }
	public Integer getPersenLaba() { return persenLaba; }
	public void setPersenLaba(Integer persenLaba) { this.persenLaba = persenLaba; }
	public Integer getDiskon() { return diskon; }
	public void setDiskon(Integer diskon) { this.diskon = diskon; }
	public Integer getIdJenisbrg() { return idJenisbrg; }
	public void setIdJenisbrg(Integer idJenisbrg) { this.idJenisbrg = idJenisbrg; }
>>>>>>> feature/pagination-implementation
	public String getIdPemasok() { return idPemasok; }
	public void setIdPemasok(String idPemasok) { this.idPemasok = idPemasok; }
}
