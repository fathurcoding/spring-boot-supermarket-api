package ui.ft.ccit.faculty.transaksi.jenisbarang.dto;

public class JenisBarangResponse {
	private Integer idJenisbrg;
	private String namaJenisbrg;

	public JenisBarangResponse(Integer idJenisbrg, String namaJenisbrg) {
		this.idJenisbrg = idJenisbrg;
		this.namaJenisbrg = namaJenisbrg;
	}

	public Integer getIdJenisbrg() {
		return idJenisbrg;
	}

	public String getNamaJenisbrg() {
		return namaJenisbrg;
	}
}
