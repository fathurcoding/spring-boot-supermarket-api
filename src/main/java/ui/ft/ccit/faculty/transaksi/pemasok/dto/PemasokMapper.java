package ui.ft.ccit.faculty.transaksi.pemasok.dto;

import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;

public class PemasokMapper {
	
	public static Pemasok toEntity(CreatePemasokRequest request) {
		if (request == null) return null;
		// Pemasok constructor: idPemasok, namaPemasok, alamat, telepon, email
		return new Pemasok(
				request.getIdPemasok(),
				request.getNamaPemasok(),
				request.getAlamatPemasok(), // DTO field -> Entity constructor arg for alamat
				request.getTeleponPemasok(), // DTO field -> Entity constructor arg for telepon
				null // email
		);
	}
	
	public static void updateEntity(Pemasok pemasok, UpdatePemasokRequest request) {
		if (request == null || pemasok == null) return;
		
		if (request.getNamaPemasok() != null) {
			pemasok.setNamaPemasok(request.getNamaPemasok());
		}
		if (request.getAlamatPemasok() != null) {
			pemasok.setAlamat(request.getAlamatPemasok());
		}
		if (request.getTeleponPemasok() != null) {
			pemasok.setTelepon(request.getTeleponPemasok());
		}
	}
	
	public static PemasokResponse toResponse(Pemasok pemasok) {
		if (pemasok == null) return null;
		// PemasokResponse constructor: idPemasok, namaPemasok, alamatPemasok, teleponPemasok
		return new PemasokResponse(
				pemasok.getIdPemasok(),
				pemasok.getNamaPemasok(),
				pemasok.getAlamat(),   // Entity field -> Response DTO field
				pemasok.getTelepon()   // Entity field -> Response DTO field
		);
	}
}
