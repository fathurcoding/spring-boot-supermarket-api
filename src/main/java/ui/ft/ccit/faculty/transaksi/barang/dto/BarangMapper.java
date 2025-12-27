package ui.ft.ccit.faculty.transaksi.barang.dto;

import ui.ft.ccit.faculty.transaksi.barang.model.Barang;

public class BarangMapper {
	
	public static Barang toEntity(CreateBarangRequest request) {
		if (request == null) return null;
		Barang barang = new Barang();
		barang.setIdBarang(request.getIdBarang());
		barang.setNama(request.getNama());
		
		if (request.getStok() != null) {
			barang.setStok(request.getStok().shortValue());
		}
		
		if (request.getHarga() != null) {
			barang.setHarga(request.getHarga());
		}
		
		if (request.getPersenLaba() != null) {
			barang.setPersenLaba(request.getPersenLaba().doubleValue());
		}
		
		if (request.getDiskon() != null) {
			barang.setDiskon(request.getDiskon().doubleValue());
		}
		
		barang.setIdJenisBarang(request.getIdJenisbrg());
		barang.setIdPemasok(request.getIdPemasok());
		return barang;
	}
	
	public static void updateEntity(Barang barang, UpdateBarangRequest request) {
		if (request == null || barang == null) return;
		
		if (request.getNama() != null) barang.setNama(request.getNama());
		
		if (request.getStok() != null) {
			barang.setStok(request.getStok().shortValue());
		}
		
		if (request.getHarga() != null) {
			barang.setHarga(request.getHarga());
		}
		
		if (request.getPersenLaba() != null) {
			barang.setPersenLaba(request.getPersenLaba().doubleValue());
		}
		
		if (request.getDiskon() != null) {
			barang.setDiskon(request.getDiskon().doubleValue());
		}
		
		if (request.getIdJenisbrg() != null) barang.setIdJenisBarang(request.getIdJenisbrg());
		if (request.getIdPemasok() != null) barang.setIdPemasok(request.getIdPemasok());
	}
	
	public static BarangResponse toResponse(Barang barang) {
		if (barang == null) return null;
		
		// Handle potential nulls for primitives if not using wrapper classes in DTO or Entity
		Integer stok = (barang.getStok() != null) ? barang.getStok().intValue() : null;
		Integer persenLaba = (barang.getPersenLaba() != null) ? barang.getPersenLaba().intValue() : null;
		Integer diskon = (barang.getDiskon() != null) ? barang.getDiskon().intValue() : null;

		return new BarangResponse(
				barang.getIdBarang(),
				barang.getNama(),
				stok,
				barang.getHarga(),
				persenLaba,
				diskon,
				barang.getIdJenisBarang(),
				barang.getIdPemasok()
		);
	}
}
