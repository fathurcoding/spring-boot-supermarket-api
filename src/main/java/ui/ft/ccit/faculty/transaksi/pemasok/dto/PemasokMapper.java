package ui.ft.ccit.faculty.transaksi.pemasok.dto;

import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;

public class PemasokMapper {

	public static Pemasok toEntity(CreatePemasokRequest request) {
		if (request == null)
			return null;
		// Pemasok constructor: idPemasok, namaPemasok, alamat, telepon, email
		return new Pemasok(
				request.getIdPemasok(),
				request.getNamaPemasok(),
				request.getAlamatPemasok(),
				request.getTeleponPemasok(),
				(String) null // email
		);
	}

	public static void updateEntity(Pemasok pemasok, UpdatePemasokRequest request) {
		if (request == null || pemasok == null)
			return;

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
		if (pemasok == null)
			return null;
		return new PemasokResponse(
				pemasok.getIdPemasok(),
				pemasok.getNamaPemasok(),
				pemasok.getAlamat(),
				pemasok.getTelepon());
	}
}
