import java.util.Scanner;

public class JalurPendakian {
    public static void main(String[] args) {
        // Matriks 6 Baris x 12 Kolom
        // 1 = Jalur Hijau, 0 = Jurang (Merah), 2 = Pos Istirahat
        int[][] peta = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, // Baris 0: Puncak di [0,0]
            {1, 0, 1, 1, 1, 0, 0, 2, 0, 1, 0, 1}, // Baris 1
            {1, 0, 0, 0, 1, 2, 1, 1, 1, 1, 0, 1}, // Baris 2: Pos 4 & Pos 3
            {1, 2, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1}, // Baris 3
            {1, 1, 0, 1, 1, 0, 2, 0, 0, 0, 0, 1}, // Baris 4
            {2, 1, 0, 2, 1, 0, 1, 1, 1, 1, 1, 1}  // Baris 5: Start P1 di [5,11], P2 di [5,7]
        };

        Scanner input = new Scanner(System.in);
        
        System.out.print("Tenaga : ");
        int tenaga = input.nextInt();
        input.nextLine(); // Clear buffer
        
        System.out.print("Jalur  : ");
        String jalur = input.nextLine(); 

        int baris = 5; // Posisi Start (P1)
        int kolom = 11;

        for (int i = 0; i < jalur.length(); i++) {
            char aksi = jalur.charAt(i);

            // LOGIKA ISTIRAHAT (r kecil)
            if (aksi == 'r') {
                if (peta[baris][kolom] == 2) { 
                    tenaga += 10;
                } else {
                    System.out.println("Mohon maaf, istirahat hanya diperbolehkan di Pos-pos yang tersedia");
                    return;
                }
                continue; 
            }

            // LOGIKA PERGERAKAN (KAPITAL)
            if (aksi == 'L') kolom--;
            else if (aksi == 'R') kolom++;
            else if (aksi == 'U') baris--;
            else if (aksi == 'D') baris++;

            tenaga--;

            // 1. Cek Batas Peta & Jurang
            if (baris < 0 || baris >= 6 || kolom < 0 || kolom >= 12 || peta[baris][kolom] == 0) {
                System.out.println("Jalur anda salah, anda masuk ke jurang/blank 45");
                return;
            }

            // 2. Cek Tenaga
            if (tenaga < 0) {
                System.out.println("Jalur anda benar, tapi tenaga anda tidak akan kuat, coba jalur lain atau sempatkan istirahat terlebih dahulu");
                return;
            }

            // 3. Cek Sampai Puncak Mahameru
            if (baris == 0 && kolom == 0) {
                System.out.println("Selamat Pendakian anda berhasil mencapai Puncak Mahameru, sisa tenaga anda " + tenaga);
                return;
            }
        }

        System.out.println("Pendakian berakhir di [" + baris + "," + kolom + "], anda belum sampai puncak.");
        input.close();
    }
}