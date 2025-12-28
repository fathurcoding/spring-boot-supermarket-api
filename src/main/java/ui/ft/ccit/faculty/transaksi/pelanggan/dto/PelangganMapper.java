package ui.ft.ccit.faculty.transaksi.pelanggan.dto;

import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;

/**
 * Mapper for Pelanggan entity and DTOs.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class PelangganMapper {

	public static Pelanggan toEntity(CreatePelangganRequest request) {
		if (request == null)
			return null;
		Pelanggan pelanggan = new Pelanggan();
		pelanggan.setIdPelanggan(request.getIdPelanggan());
		pelanggan.setNama(request.getNama());
		pelanggan.setJenisKelamin(request.getJenisKelamin());
		pelanggan.setAlamat(request.getAlamat());
		pelanggan.setTelepon(request.getTelepon());
		pelanggan.setTglLahir(request.getTglLahir());
		pelanggan.setJenisPelanggan(request.getJenisPelanggan());
		return pelanggan;
	}

	public static void updateEntity(Pelanggan pelanggan, UpdatePelangganRequest request) {
		if (request == null || pelanggan == null)
			return;

		if (request.getNama() != null)
			pelanggan.setNama(request.getNama());
		if (request.getJenisKelamin() != null)
			pelanggan.setJenisKelamin(request.getJenisKelamin());
		if (request.getAlamat() != null)
			pelanggan.setAlamat(request.getAlamat());
		if (request.getTelepon() != null)
			pelanggan.setTelepon(request.getTelepon());
		if (request.getTglLahir() != null)
			pelanggan.setTglLahir(request.getTglLahir());
		if (request.getJenisPelanggan() != null)
			pelanggan.setJenisPelanggan(request.getJenisPelanggan());
	}

	public static PelangganResponse toResponse(Pelanggan pelanggan) {
		if (pelanggan == null)
			return null;
		return new PelangganResponse(
				pelanggan.getIdPelanggan(),
				pelanggan.getNama(),
				pelanggan.getJenisKelamin(),
				pelanggan.getAlamat(),
				pelanggan.getTelepon(),
				pelanggan.getTglLahir(),
				pelanggan.getJenisPelanggan());
	}
}
