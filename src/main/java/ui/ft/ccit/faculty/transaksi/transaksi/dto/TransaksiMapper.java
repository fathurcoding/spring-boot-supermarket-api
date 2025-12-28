package ui.ft.ccit.faculty.transaksi.transaksi.dto;

import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;

public class TransaksiMapper {

	public static Transaksi toEntity(CreateTransaksiRequest request) {
		if (request == null)
			return null;
		Transaksi t = new Transaksi();
		t.setKodeTransaksi(request.getKodeTransaksi());
		t.setTglTransaksi(request.getTglTransaksi());

		// Set relations via ID-only objects
		if (request.getIdPelanggan() != null) {
			Pelanggan p = new Pelanggan();
			p.setIdPelanggan(request.getIdPelanggan());
			t.setPelanggan(p);
		}

		if (request.getIdKaryawan() != null) {
			Karyawan k = new Karyawan();
			k.setIdKaryawan(request.getIdKaryawan());
			t.setKaryawan(k);
		}

		return t;
	}

	public static TransaksiResponse toResponse(Transaksi t, java.math.BigDecimal totalHarga) {
		if (t == null)
			return null;

		String idPelanggan = (t.getPelanggan() != null) ? t.getPelanggan().getIdPelanggan() : null;
		String idKaryawan = (t.getKaryawan() != null) ? t.getKaryawan().getIdKaryawan() : null;

		return new TransaksiResponse(
				t.getKodeTransaksi(),
				t.getTglTransaksi(),
				idPelanggan,
				idKaryawan,
				totalHarga);
	}
}
