import java.util.Scanner;

public class matriks {
    int IdxMax = 10;
    //Atribut
    int Brs;
    int Kol;
    float[][] Mat = new float[IdxMax][IdxMax];

    //Scanner
    Scanner keyboard = new Scanner(System.in);

    matriks(){
    /* Konstruktor matriks, membuat matriks berukuran IdxMax x IdxMax
    dan mengatur semua nilai dari entri matriks bernilai 0*/
        int i,j;

        for (i = 1; i < IdxMax; i++){
            for (j = 1; j < IdxMax; j++){
                this.Mat[i][j] = 0;
            }
        }

        Brs = 0;
        Kol = 0;
    }

    void bacaUkuranMatriks(){
    //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
        System.out.print("Masukan banyaknya baris : ");
        Brs = keyboard.nextInt();
        System.out.print("Masukan banyaknya kolom : ");
        Kol = keyboard.nextInt();
    }

    void bacaMatriks(){
    //Membaca ukuran lalu membaca inputan user untuk membentuk sebuah matriks
        int i,j;

        bacaUkuranMatriks();

        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = keyboard.nextFloat();
            }
        }
    }

    void tulisMatriks(){
    //Menampilkan isi matriks ke layar
        int i,j;

        for (i = 1; i <= Brs; i++){
            for (j = 1; j < Kol; j++){
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println(this.Mat[i][Kol]);
        }
    }

    void tukarBaris(int i, int j){
    // Menukar baris ke i dengan baris ke j suatu matriks

        int k;
        float temp;

        for (k = 1;k <= Kol;k++){
            temp = this.Mat[j][k];
            this.Mat[j][k] = this.Mat[i][k];
            this.Mat[i][k] = temp;
        }
    }

    boolean isKolNol(int j, int i){
    //true bila pada kolom j dimulai dari baris i sampai Brs = 0
        boolean cek;

        cek = true;
        if (j >= Kol){
            cek = false;
        } else {
            while (cek & i <= Brs){
                if (this.Mat[i][j] != 0){
                    cek = false;
                } else {
                    i += 1;
                }
            }
        }

        return cek;
    }

    int indeksTakNol(int j, int i){
    // Mengeluarkan indeks pertama yang tidak bernilai 0 dari matriks pada kolom j
    // dimulai dari baris ke i
        int k = i;
        boolean cek = true;

        while (cek & k <= Brs){
            if (this.Mat[k][j] != 0){
                cek = false;
            } else {
                k += 1;
            }
        }
        return k;
    }

    void buatLeadingPoint(int i, int j){
    //Membuat M[i][j] leading point bernilai 1 di baris i
        int k;
        float factor;

        factor = this.Mat[i][j];
        for (k = 1;k <= Kol;k++){
            this.Mat[i][k] = this.Mat[i][k]/factor;
        }
    }

    void buatKolomNolBawah(int j, int i){
    //Pivot di M[i][j]
    //Membuat kolom j nol dimulai dari baris ke i + 1
        int k = i+1;
        int l;
        float factor;

        while (k <= Brs){
            factor = this.Mat[k][j];
            for (l = 1;l <= Kol;l++){
                this.Mat[k][l] = this.Mat[k][l] - factor*this.Mat[i][l];
            }
            k += 1;
        }
    }

    void Gauss(){
    //Merubah matriks menjadi Row-Echelon-Form
        int i,j,k;
        float temp;
        i = 1;
        j = 1;

        while (i <= Brs & j < Kol){
            while (isKolNol(j,i)){
                j += 1;
            }

            if (j < Kol){
                tukarBaris(i,indeksTakNol(j,i));
                buatLeadingPoint(i,j);
                buatKolomNolBawah(j,i);

                i += 1;
                j += 1;
            }
        }

        for (i = 1;i <= Brs; i++){
            for (j = 1;j <= Kol;j++){
                if (this.Mat[i][j] == -0){
                    this.Mat[i][j] = 0;
                }
            }
        }
    }

    void SolusiGauss(){
        GaussJordan();
        SolusiGaussJordan();
    }

    boolean isNolSemua(int i){
    //Mengembalikan true jika dalam baris tersebut 0 semua kecuali kolom augmented
        boolean cek = true;
        int k = 1;

        while (cek & k < Kol){
            if (this.Mat[i][k] == 0){
                k += 1;
            } else {
                cek = false;
            }
        }

        return cek;
    }

    int indeksPivot(int i){
    //Mengembalikan indeks pivot point pada baris i
    //Dengan asumsi bukan baris yang berisi 0 semua (isNolSemua = false)
        int k = 1;
        boolean cek = true;

        while (cek & k < Kol){
            if (this.Mat[i][k] == 0){
                k += 1;
            } else {
                cek = false;
            }
        } //
        return k;
    }

    void buatKolomNolAtas(int i, int j){
    // Membuat kolom j berisi nol semua diatas indeks i
        int k = i - 1;
        int l;
        float factor;

        while (k >= 1){
            factor = this.Mat[k][j];
            for (l = 1;l <= Kol;l++){
                this.Mat[k][l] = this.Mat[k][l] - factor*this.Mat[i][l];
            }
            k -= 1;
        }

    }

    void GaussJordan(){
        int i = Brs;
        int j;

        Gauss();
        while (i >= 1){
            while (isNolSemua(i)){
                i -= 1;
            }
            j = indeksPivot(i);
            buatKolomNolAtas(i,j);

            i -= 1;
        }
    }

    boolean isBarisNol(int i){
    //Menghasilkan true jika baris i bukan augmented isinya 0 semua
        int k = 1;
        boolean cek = true;

        while (cek & k < Kol){
            if (this.Mat[i][k] == 0){
                k += 1;
            } else {
                cek = false;
            }
        }

        return cek;
    }

    void SolusiGaussJordan(){
        int k = 1;
        int i = Brs;
        int j;
        boolean lanjut = true;

        while (i >= 1 & lanjut){
            if (isBarisNol(i) & this.Mat[i][Kol] != 0){
                lanjut = false;
                System.out.println("Tidak ada Solusi!");
            } else if (isBarisNol(i) & this.Mat[i][Kol] == 0){
                i -= 1;
            } else {
                j = indeksPivot(i);
                System.out.print("x" + j + " = " + this.Mat[i][Kol]);
                for (k = j + 1;k < Kol;k++){
                    if (this.Mat[i][k] != 0){
                        System.out.print(" -(" + this.Mat[i][k] + ")x" + k);
                    }
                }
                System.out.println();
                i -= 1;
            }

        }

    }

}
