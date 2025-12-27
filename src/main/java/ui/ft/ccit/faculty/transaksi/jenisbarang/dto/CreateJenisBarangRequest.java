package ui.ft.ccit.faculty.transaksi.jenisbarang.dto;

import jakarta.validation.constraints.*;

/**
 * DTO for creating JenisBarang.
 */
public class CreateJenisBarangRequest {
	
	@NotNull(message = "ID jenis barang tidak boleh kosong")
	@Min(value = 100, message = "ID jenis barang minimal 100")
	@Max(value = 999, message = "ID jenis barang maksimal 999")
	private Byte idJenisbrg;
	
	@NotBlank(message = "Nama jenis barang tidak boleh kosong")
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String namaJenisbrg;
	
	public CreateJenisBarangRequest() {}
	
	public Byte getIdJenisbrg() { return idJenisbrg; }
	public void setIdJenisbrg(Byte idJenisbrg) { this.idJenisbrg = idJenisbrg; }
	
	public String getNamaJenisbrg() { return namaJenisbrg; }
	public void setNamaJenisbrg(String namaJenisbrg) { this.namaJenisbrg = namaJenisbrg; }
}
