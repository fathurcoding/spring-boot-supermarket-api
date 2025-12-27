package ui.ft.ccit.faculty.transaksi.jenisbarang.dto;

import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;

public class JenisBarangMapper {
	
	public static JenisBarang toEntity(CreateJenisBarangRequest request) {
		if (request == null) return null;
		JenisBarang jenis = new JenisBarang(request.getIdJenisbrg(), request.getNamaJenisbrg());
		return jenis;
	}
	
	public static void updateEntity(JenisBarang jenis, UpdateJenisBarangRequest request) {
		if (request == null || jenis == null) return;
		if (request.getNamaJenisbrg() != null) {
			jenis.setNamaJenis(request.getNamaJenisbrg());
		}
	}
	
	public static JenisBarangResponse toResponse(JenisBarang jenis) {
		if (jenis == null) return null;
		return new JenisBarangResponse(jenis.getIdJenisBarang(), jenis.getNamaJenis());
	}
}
