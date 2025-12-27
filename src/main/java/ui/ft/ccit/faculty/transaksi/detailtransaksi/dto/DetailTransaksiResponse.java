package ui.ft.ccit.faculty.transaksi.detailtransaksi.dto;

public class DetailTransaksiResponse {
	private String kodeTransaksi;
	private String idBarang;
	private Integer jumlah;

	public DetailTransaksiResponse(String kodeTransaksi, String idBarang, Integer jumlah) {
		this.kodeTransaksi = kodeTransaksi;
		this.idBarang = idBarang;
		this.jumlah = jumlah;
	}

	public String getKodeTransaksi() { return kodeTransaksi; }
	public String getIdBarang() { return idBarang; }
	public Integer getJumlah() { return jumlah; }
}
