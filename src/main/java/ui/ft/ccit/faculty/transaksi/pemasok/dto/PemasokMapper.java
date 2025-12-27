package ui.ft.ccit.faculty.transaksi.pemasok.dto;

import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;

public class PemasokMapper {
	
	public static Pemasok toEntity(CreatePemasokRequest request) {
		if (request == null) return null;
		Pemasok pemasok = new Pemasok();
		pemasok.setIdPemasok(request.getIdPemasok());
		pemasok.setNamaPemasok(request.getNamaPemasok());
		pemasok.setAlamatPemasok(request.getAlamatPemasok());
		pemasok.setTeleponPemasok(request.getTeleponPemasok());
		return pemasok;
	}
	
	public static void updateEntity(Pemasok pemasok, UpdatePemasokRequest request) {
		if (request == null || pemasok == null) return;
		
		if (request.getNamaPemasok() != null) pemasok.setNamaPemasok(request.getNamaPemasok());
		if (request.getAlamatPemasok() != null) pemasok.setAlamatPemasok(request.getAlamatPemasok());
		if (request.getTeleponPemasok() != null) pemasok.setTeleponPemasok(request.getTeleponPemasok());
	}
	
	public static PemasokResponse toResponse(Pemasok pemasok) {
		if (pemasok == null) return null;
		return new PemasokResponse(
				pemasok.getIdPemasok(),
				pemasok.getNamaPemasok(),
				pemasok.getAlamatPemasok(),
				pemasok.getTeleponPemasok()
		);
	}
}
