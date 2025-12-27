package ui.ft.ccit.faculty.transaksi.jenisbarang.dto;

import jakarta.validation.constraints.*;

public class UpdateJenisBarangRequest {
	
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String namaJenisbrg;
	
	public UpdateJenisBarangRequest() {}
	
	public String getNamaJenisbrg() { return namaJenisbrg; }
	public void setNamaJenisbrg(String namaJenisbrg) { this.namaJenisbrg = namaJenisbrg; }
}
