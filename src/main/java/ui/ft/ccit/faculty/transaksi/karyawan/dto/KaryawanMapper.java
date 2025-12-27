package ui.ft.ccit.faculty.transaksi.karyawan.dto;

import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;

/**
 * Mapper utility for converting between Karyawan entity and DTOs.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class KaryawanMapper {
	
	/**
	 * Convert CreateKaryawanRequest to Karyawan entity
	 */
	public static Karyawan toEntity(CreateKaryawanRequest request) {
		if (request == null) return null;
		
		return new Karyawan(
				request.getIdKaryawan(),
				request.getNama(),
				request.getJenisKelamin(),
				request.getAlamat(),
				request.getTelepon(),
				request.getTglLahir(),
				request.getGaji()
		);
	}
	
	/**
	 * Update existing Karyawan entity from UpdateKaryawanRequest
	 */
	public static void updateEntity(Karyawan karyawan, UpdateKaryawanRequest request) {
		if (request == null || karyawan == null) return;
		
		if (request.getNama() != null) {
			karyawan.setNama(request.getNama());
		}
		if (request.getJenisKelamin() != null) {
			karyawan.setJenisKelamin(request.getJenisKelamin());
		}
		if (request.getAlamat() != null) {
			karyawan.setAlamat(request.getAlamat());
		}
		if (request.getTelepon() != null) {
			karyawan.setTelepon(request.getTelepon());
		}
		if (request.getTglLahir() != null) {
			karyawan.setTglLahir(request.getTglLahir());
		}
		if (request.getGaji() != null) {
			karyawan.setGaji(request.getGaji());
		}
	}
	
	/**
	 * Convert Karyawan entity to KaryawanResponse
	 */
	public static KaryawanResponse toResponse(Karyawan karyawan) {
		if (karyawan == null) return null;
		
		return new KaryawanResponse(
				karyawan.getIdKaryawan(),
				karyawan.getNama(),
				karyawan.getJenisKelamin(),
				karyawan.getAlamat(),
				karyawan.getTelepon(),
				karyawan.getTglLahir(),
				karyawan.getGaji()
		);
	}
}
