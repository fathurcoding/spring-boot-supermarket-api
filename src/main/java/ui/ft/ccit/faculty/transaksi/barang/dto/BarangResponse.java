package ui.ft.ccit.faculty.transaksi.barang.dto;

public class BarangResponse {
	private String idBarang;
	private String nama;
	private Integer stok;
	private Double harga;
	private Integer persenLaba;
	private Integer diskon;
	private Integer idJenisbrg;
	private String idPemasok;
	private Double hargaJual;

	public BarangResponse(String idBarang, String nama, Integer stok, Double harga, Integer persenLaba, Integer diskon, Integer idJenisbrg, String idPemasok) {
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

	private Double calculateHargaJual(Double harga, Integer persenLaba, Integer diskon) {
		if (harga == null) return 0.0;
		double price = harga;
		if (persenLaba != null) price += price * persenLaba / 100.0;
		if (diskon != null) price -= price * diskon / 100.0;
		return price;
	}

	// Getters
	public String getIdBarang() { return idBarang; }
	public String getNama() { return nama; }
	public Integer getStok() { return stok; }
	public Double getHarga() { return harga; }
	public Integer getPersenLaba() { return persenLaba; }
	public Integer getDiskon() { return diskon; }
	public Integer getIdJenisbrg() { return idJenisbrg; }
	public String getIdPemasok() { return idPemasok; }
	public Double getHargaJual() { return hargaJual; }
}
