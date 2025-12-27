package ui.ft.ccit.faculty.transaksi.jenisbarang.dto;

public class JenisBarangResponse {
	private Byte idJenisbrg;
	private String namaJenisbrg;
	
	public JenisBarangResponse() {}
	
	public JenisBarangResponse(Byte idJenisbrg, String namaJenisbrg) {
		this.idJenisbrg = idJenisbrg;
		this.namaJenisbrg = namaJenisbrg;
	}
	
	public Byte getIdJenisbrg() { return idJenisbrg; }
	public void setIdJenisbrg(Byte idJenisbrg) { this.idJenisbrg = idJenisbrg; }
	
	public String getNamaJenisbrg() { return namaJenisbrg; }
	public void setNamaJenisbrg(String namaJenisbrg) { this.namaJenisbrg = namaJenisbrg; }
}
