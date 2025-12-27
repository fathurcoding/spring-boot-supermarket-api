package ui.ft.ccit.faculty.transaksi.pelanggan.dto;

import java.time.LocalDate;
<<<<<<< HEAD

/**
 * DTO for Pelanggan response with calculated discount rate.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class PelangganResponse {
	
=======
import java.time.Period;

public class PelangganResponse {
>>>>>>> feature/pagination-implementation
	private String idPelanggan;
	private String nama;
	private String jenisKelamin;
	private String alamat;
	private String telepon;
	private LocalDate tglLahir;
	private String jenisPelanggan;
	private Integer usia;
<<<<<<< HEAD
	private Integer discountRate; // Calculated: Silver=5%, Gold=10%
	
	public PelangganResponse() {
	}
	
	public PelangganResponse(String idPelanggan, String nama, String jenisKelamin,
	                         String alamat, String telepon, LocalDate tglLahir, String jenisPelanggan) {
=======
	private Integer discountRate;

	public PelangganResponse(String idPelanggan, String nama, String jenisKelamin, String alamat, String telepon, LocalDate tglLahir, String jenisPelanggan) {
>>>>>>> feature/pagination-implementation
		this.idPelanggan = idPelanggan;
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.alamat = alamat;
		this.telepon = telepon;
		this.tglLahir = tglLahir;
		this.jenisPelanggan = jenisPelanggan;
<<<<<<< HEAD
		this.usia = calculateAge(tglLahir);
		this.discountRate = calculateDiscountRate(jenisPelanggan);
	}
	
	private Integer calculateAge(LocalDate birthDate) {
		if (birthDate == null) return null;
		return LocalDate.now().getYear() - birthDate.getYear();
	}
	
	private Integer calculateDiscountRate(String membershipType) {
		if (membershipType == null) return 0;
		return "Gold".equals(membershipType) ? 10 : 5;
	}
	
	// Getters and Setters
	public String getIdPelanggan() { return idPelanggan; }
	public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
	
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
	
	public String getJenisPelanggan() { return jenisPelanggan; }
	public void setJenisPelanggan(String jenisPelanggan) { 
		this.jenisPelanggan = jenisPelanggan;
		this.discountRate = calculateDiscountRate(jenisPelanggan);
	}
	
	public Integer getUsia() { return usia; }
	public void setUsia(Integer usia) { this.usia = usia; }
	
	public Integer getDiscountRate() { return discountRate; }
	public void setDiscountRate(Integer discountRate) { this.discountRate = discountRate; }
=======
		this.usia = calculateUsia(tglLahir);
		this.discountRate = calculateDiscount(jenisPelanggan);
	}

	private Integer calculateUsia(LocalDate tglLahir) {
		if (tglLahir == null) return null;
		return Period.between(tglLahir, LocalDate.now()).getYears();
	}

	private Integer calculateDiscount(String jenis) {
		if ("Gold".equalsIgnoreCase(jenis)) return 10;
		if ("Silver".equalsIgnoreCase(jenis)) return 5;
		return 0;
	}

	// Getters
	public String getIdPelanggan() { return idPelanggan; }
	public String getNama() { return nama; }
	public String getJenisKelamin() { return jenisKelamin; }
	public String getAlamat() { return alamat; }
	public String getTelepon() { return telepon; }
	public LocalDate getTglLahir() { return tglLahir; }
	public String getJenisPelanggan() { return jenisPelanggan; }
	public Integer getUsia() { return usia; }
	public Integer getDiscountRate() { return discountRate; }
>>>>>>> feature/pagination-implementation
}
