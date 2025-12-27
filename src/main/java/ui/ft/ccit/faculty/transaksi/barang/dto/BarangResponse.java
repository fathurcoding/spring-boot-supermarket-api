package ui.ft.ccit.faculty.transaksi.barang.dto;

public class BarangResponse {
	private String idBarang;
	private String nama;
	private Short stok;
	private Double harga;
	private Double persenLaba;
	private Double diskon;
	private Byte idJenisbrg;
	private String idPemasok;
	private Double hargaJual; // Calculated: harga + (harga * persenLaba / 100) - (harga * diskon / 100)
	
	public BarangResponse() {}
	
	public BarangResponse(String idBarang, String nama, Short stok, Double harga, 
	                      Double persenLaba, Double diskon, Byte idJenisbrg, String idPemasok) {
		this.idBarang = idBarang;
		this.nama = nama;
		this.stok = stok;
		this.harga = harga;
		this.persenLaba = persenLaba;
		this.diskon = diskon;
		this.idJenisbrg = idJenisbrg;
		this.idPemasok = idPemasok;
		this.hargaJual = calculateHargaJual(harga, persenLaba, diskon);
	}
	
	private Double calculateHargaJual(Double harga, Double persenLaba, Double diskon) {
		if (harga == null) return null;
		double hargaDenganLaba = harga + (harga * (persenLaba != null ? persenLaba : 0) / 100);
		return hargaDenganLaba - (hargaDenganLaba * (diskon != null ? diskon : 0) / 100);
	}
	
	// Getters and Setters
	public String getIdBarang() { return idBarang; }
	public void setIdBarang(String idBarang) { this.idBarang = idBarang; }
	
	public String getNama() { return nama; }
	public void setNama(String nama) { this.nama = nama; }
	
	public Short getStok() { return stok; }
	public void setStok(Short stok) { this.stok = stok; }
	
	public Double getHarga() { return harga; }
	public void setHarga(Double harga) { 
		this.harga = harga;
		this.hargaJual = calculateHargaJual(harga, persenLaba, diskon);
	}
	
	public Double getPersenLaba() { return persenLaba; }
	public void setPersenLaba(Double persenLaba) { 
		this.persenLaba = persenLaba;
		this.hargaJual = calculateHargaJual(harga, persenLaba, diskon);
	}
	
	public Double getDiskon() { return diskon; }
	public void setDiskon(Double diskon) { 
		this.diskon = diskon;
		this.hargaJual = calculateHargaJual(harga, persenLaba, diskon);
	}
	
	public Byte getIdJenisbrg() { return idJenisbrg; }
	public void setIdJenisbrg(Byte idJenisbrg) { this.idJenisbrg = idJenisbrg; }
	
	public String getIdPemasok() { return idPemasok; }
	public void setIdPemasok(String idPemasok) { this.idPemasok = idPemasok; }
	
	public Double getHargaJual() { return hargaJual; }
	public void setHargaJual(Double hargaJual) { this.hargaJual = hargaJual; }
}
