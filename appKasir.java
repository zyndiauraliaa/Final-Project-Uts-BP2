package umkm;

import java.util.Scanner;

public class appKasir {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        Barang[] daftarBarang = new Barang[10];

        // Proses login
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("===== Login =====");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            loggedIn = login.login(username, password);

            if (!loggedIn) {
                System.out.println("Login gagal. Mohon periksa kembali username dan password anda.");
            }
        }

        // Jika login berhasil, tampilkan menu utama
        while (true) {
            System.out.println("\n======= Menu Utama =======");
            System.out.println("| 1. Master Barang       |");
            System.out.println("| 2. Transaksi Penjualan |");
            System.out.println("| 3. Keluar              |");
            System.out.println("==========================");
            System.out.print("Pilih menu (1-3): ");
            int pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    MasterBarangMenu menuBarang = new MasterBarangMenu(scanner, daftarBarang);
                    menuBarang.displayMenu();
                    break;

                case 2:
                    TransaksiPenjualanMenu  transaksiPenjualan = new TransaksiPenjualanMenu(scanner, null, daftarBarang);
                    transaksiPenjualan.displayMenu();
                    break;

                case 3:
                    System.out.println("Terima kasih sudah menggunakan aplikasi ini!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
