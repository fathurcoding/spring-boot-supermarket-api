package ui.ft.ccit.faculty.transaksi.karyawan.dto;

import java.time.LocalDate;

/**
 * DTO for Karyawan response.
 * 
 * <p>Used to return employee data to clients, hiding internal entity structure.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class KaryawanResponse {
	
	private String idKaryawan;
	private String nama;
	private String jenisKelamin;
	private String alamat;
	private String telepon;
	private LocalDate tglLahir;
	private Double gaji;
	private Integer usia; // Calculated field
	
	// Constructors
	public KaryawanResponse() {
	}
	
	public KaryawanResponse(String idKaryawan, String nama, String jenisKelamin,
	                        String alamat, String telepon, LocalDate tglLahir, Double gaji) {
		this.idKaryawan = idKaryawan;
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.alamat = alamat;
		this.telepon = telepon;
		this.tglLahir = tglLahir;
		this.gaji = gaji;
		this.usia = calculateAge(tglLahir);
	}
	
	private Integer calculateAge(LocalDate birthDate) {
		if (birthDate == null) return null;
		return LocalDate.now().getYear() - birthDate.getYear();
	}
	
	// Getters and Setters
	public String getIdKaryawan() { return idKaryawan; }
	public void setIdKaryawan(String idKaryawan) { this.idKaryawan = idKaryawan; }
	
	public String getNama() { return nama; }
	public void setNama(String nama) { this.nama = nama; }
	
	public String getJenisKelamin() { return jenisKelamin; }
	public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
	
	public String getAlamat() { return alamat; }
	public void setAlamat(String alamat) { this.alamat = alamat; }
	
	public String getTelepon() { return telepon; }
	public void setTelepon(String telepon) { this.telepon = telepon; }
	
	public LocalDate getTglLahir() { return tglLahir; }
	public void setTglLahir(LocalDate tglLahir) { 
		this.tglLahir = tglLahir;
		this.usia = calculateAge(tglLahir);
	}
	
	public Double getGaji() { return gaji; }
	public void setGaji(Double gaji) { this.gaji = gaji; }
	
	public Integer getUsia() { return usia; }
	public void setUsia(Integer usia) { this.usia = usia; }
}
