package ui.ft.ccit.faculty.transaksi.detailtransaksi.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;

public class DetailTransaksiAlreadyExistsException extends DataAlreadyExistsException {
	public DetailTransaksiAlreadyExistsException(String kodeTransaksi, String idBarang) {
		super("Detail Transaksi", kodeTransaksi + "/" + idBarang);
	}
}
