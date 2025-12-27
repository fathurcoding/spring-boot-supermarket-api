package ui.ft.ccit.faculty.transaksi.pelanggan.view;

import ui.ft.ccit.faculty.transaksi.DataNotFoundException;

public class PelangganNotFoundException extends DataNotFoundException {
	public PelangganNotFoundException(String id) {
		super("Pelanggan", id);
	}
}
