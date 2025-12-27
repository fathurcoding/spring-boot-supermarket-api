package ui.ft.ccit.faculty.transaksi.detailtransaksi.view;

import ui.ft.ccit.faculty.transaksi.DataNotFoundException;

public class DetailTransaksiNotFoundException extends DataNotFoundException {
	public DetailTransaksiNotFoundException(String kodeTransaksi, String idBarang) {
		super("Detail Transaksi", kodeTransaksi + "/" + idBarang);
	}
}
