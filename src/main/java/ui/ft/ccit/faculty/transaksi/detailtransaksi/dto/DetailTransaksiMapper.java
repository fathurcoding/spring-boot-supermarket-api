package ui.ft.ccit.faculty.transaksi.detailtransaksi.dto;

import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;

public class DetailTransaksiMapper {

	public static DetailTransaksi toEntity(CreateDetailTransaksiRequest request) {
		if (request == null) return null;
		DetailTransaksiId id = new DetailTransaksiId(request.getKodeTransaksi(), request.getIdBarang());
		DetailTransaksi dt = new DetailTransaksi();
		dt.setId(id);
		dt.setJumlah(request.getJumlah().shortValue()); // Convert Integer to Short
		return dt;
	}

	public static DetailTransaksiResponse toResponse(DetailTransaksi dt) {
		if (dt == null || dt.getId() == null) return null;
		return new DetailTransaksiResponse(
			dt.getId().getKodeTransaksi(),
			dt.getId().getIdBarang(),
			(int) dt.getJumlah() // Convert Short to Integer
		);
	}
}
