package umkm;

public class JenisBarang2 extends Barang {
    private String keterangan;

    public JenisBarang2(String nama, double harga, int stok, String keterangan) {
        super(nama, harga, stok);
        this.keterangan = keterangan;
    }

    // Getter dan Setter khusus untuk atribut keterangan

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}