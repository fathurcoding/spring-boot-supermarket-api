package ui.ft.ccit.faculty.transaksi.transaksi.view;

import ui.ft.ccit.faculty.transaksi.DataNotFoundException;

public class TransaksiNotFoundException extends DataNotFoundException {
	public TransaksiNotFoundException(String kode) {
		super("Transaksi", kode);
	}
}
