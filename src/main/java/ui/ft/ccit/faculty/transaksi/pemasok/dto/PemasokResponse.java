package ui.ft.ccit.faculty.transaksi.pemasok.dto;

public class PemasokResponse {
	private String idPemasok;
	private String namaPemasok;
	private String alamatPemasok;
	private String teleponPemasok;

	public PemasokResponse(String idPemasok, String namaPemasok, String alamatPemasok, String teleponPemasok) {
		this.idPemasok = idPemasok;
		this.namaPemasok = namaPemasok;
		this.alamatPemasok = alamatPemasok;
		this.teleponPemasok = teleponPemasok;
	}

	public String getIdPemasok() { return idPemasok; }
	public String getNamaPemasok() { return namaPemasok; }
	public String getAlamatPemasok() { return alamatPemasok; }
	public String getTeleponPemasok() { return teleponPemasok; }
}
