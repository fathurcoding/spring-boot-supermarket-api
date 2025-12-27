package ui.ft.ccit.faculty.transaksi.barang.dto;

import ui.ft.ccit.faculty.transaksi.barang.model.Barang;

public class BarangMapper {
	
	public static Barang toEntity(CreateBarangRequest request) {
		if (request == null) return null;
		Barang barang = new Barang();
		barang.setIdBarang(request.getIdBarang());
		barang.setNama(request.getNama());
		barang.setStok(request.getStok());
		barang.setHarga(request.getHarga());
		barang.setPersenLaba(request.getPersenLaba());
		barang.setDiskon(request.getDiskon());
		barang.setIdJenisbrg(request.getIdJenisbrg());
		barang.setIdPemasok(request.getIdPemasok());
		return barang;
	}
	
	public static void updateEntity(Barang barang, UpdateBarangRequest request) {
		if (request == null || barang == null) return;
		
		if (request.getNama() != null) barang.setNama(request.getNama());
		if (request.getStok() != null) barang.setStok(request.getStok());
		if (request.getHarga() != null) barang.setHarga(request.getHarga());
		if (request.getPersenLaba() != null) barang.setPersenLaba(request.getPersenLaba());
		if (request.getDiskon() != null) barang.setDiskon(request.getDiskon());
		if (request.getIdJenisbrg() != null) barang.setIdJenisbrg(request.getIdJenisbrg());
		if (request.getIdPemasok() != null) barang.setIdPemasok(request.getIdPemasok());
	}
	
	public static BarangResponse toResponse(Barang barang) {
		if (barang == null) return null;
		return new BarangResponse(
				barang.getIdBarang(),
				barang.getNama(),
				barang.getStok(),
				barang.getHarga(),
				barang.getPersenLaba(),
				barang.getDiskon(),
				barang.getIdJenisbrg(),
				barang.getIdPemasok()
		);
	}
}
