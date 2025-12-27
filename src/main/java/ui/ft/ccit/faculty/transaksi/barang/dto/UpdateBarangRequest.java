package ui.ft.ccit.faculty.transaksi.barang.dto;

import jakarta.validation.constraints.*;

public class UpdateBarangRequest {
	
	@Size(min = 2, max = 100, message = "Nama harus antara 2-100 karakter")
	private String nama;
	
	@Min(value = 0, message = "Stok tidak boleh negatif")
	private Short stok;
	
	@Positive(message = "Harga harus lebih dari 0")
	private Double harga;
	
	@Min(value = 0, message = "Persen laba minimal 0")
	@Max(value = 100, message = "Persen laba maksimal 100")
	private Double persenLaba;
	
	@Min(value = 0, message = "Diskon minimal 0")
	@Max(value = 100, message = "Diskon maksimal 100")
	private Double diskon;
	
	private Byte idJenisbrg;
	private String idPemasok;
	
	public UpdateBarangRequest() {}
	
	// Getters and Setters
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
	
	public String getIdPemasok() { return idPemasok; }
	public void setIdPemasok(String idPemasok) { this.idPemasok = idPemasok; }
}
