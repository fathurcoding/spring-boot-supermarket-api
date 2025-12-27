package ui.ft.ccit.faculty.transaksi.jenisbarang.dto;

import jakarta.validation.constraints.*;

public class CreateJenisBarangRequest {
	@NotNull(message = "ID jenis barang tidak boleh kosong")
	@Min(100) @Max(999)
	private Integer idJenisbrg;

	@NotBlank(message = "Nama jenis barang tidak boleh kosong")
	private String namaJenisbrg;

	// Getters/Setters
	public Integer getIdJenisbrg() { return idJenisbrg; }
	public void setIdJenisbrg(Integer idJenisbrg) { this.idJenisbrg = idJenisbrg; }
	public String getNamaJenisbrg() { return namaJenisbrg; }
	public void setNamaJenisbrg(String namaJenisbrg) { this.namaJenisbrg = namaJenisbrg; }
}
