package umkm;

public class JenisBarang1 extends Barang {
    private String jenis;

    public JenisBarang1(String nama, double harga, int stok, String jenis) {
        super(nama, harga, stok);
        this.jenis = jenis;
    }

    // Getter dan Setter khusus untuk atribut jenis

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
