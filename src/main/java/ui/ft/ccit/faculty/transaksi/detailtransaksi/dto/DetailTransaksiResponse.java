package ui.ft.ccit.faculty.transaksi.detailtransaksi.dto;

public class DetailTransaksiResponse {
	private String kodeTransaksi;
	private String idBarang;
<<<<<<< HEAD
	private Short jumlah;
	
	public DetailTransaksiResponse() {}
	
	public DetailTransaksiResponse(String kodeTransaksi, String idBarang, Short jumlah) {
=======
	private Integer jumlah;

	public DetailTransaksiResponse(String kodeTransaksi, String idBarang, Integer jumlah) {
>>>>>>> feature/pagination-implementation
		this.kodeTransaksi = kodeTransaksi;
		this.idBarang = idBarang;
		this.jumlah = jumlah;
	}
<<<<<<< HEAD
	
	public String getKodeTransaksi() { return kodeTransaksi; }
	public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }
	
	public String getIdBarang() { return idBarang; }
	public void setIdBarang(String idBarang) { this.idBarang = idBarang; }
	
	public Short getJumlah() { return jumlah; }
	public void setJumlah(Short jumlah) { this.jumlah = jumlah; }
=======

	public String getKodeTransaksi() { return kodeTransaksi; }
	public String getIdBarang() { return idBarang; }
	public Integer getJumlah() { return jumlah; }
>>>>>>> feature/pagination-implementation
}
