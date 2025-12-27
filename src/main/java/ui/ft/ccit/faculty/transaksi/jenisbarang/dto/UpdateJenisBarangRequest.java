package ui.ft.ccit.faculty.transaksi.jenisbarang.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.*;

public class UpdateJenisBarangRequest {
	
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String namaJenisbrg;
	
	public UpdateJenisBarangRequest() {}
	
=======
public class UpdateJenisBarangRequest {
	private String namaJenisbrg;

>>>>>>> feature/pagination-implementation
	public String getNamaJenisbrg() { return namaJenisbrg; }
	public void setNamaJenisbrg(String namaJenisbrg) { this.namaJenisbrg = namaJenisbrg; }
}
