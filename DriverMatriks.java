import java.util.Scanner;

class DriverMatriks {
    static int pilihan1 = 0; //Menu utama
    static int pilihan2 = 0; //Metode penyelesaian menuSPL
    static int pilihan3 = 0; //Pilihan input
    static int pilihan4 = 0; //pilihan output
    static int n; //Ukuran baris matriks
    static int m; //Ukuran kolom matriks

    static Scanner keyboard = new Scanner(System.in);

    public static void main (String[] args){

        matriks M = new matriks();

        tampilMenu();
        if (pilihan1 == 1){
            menuSPL();
            if (pilihan2 == 1){ //Gauss
                pilihanInput();
                if (pilihan3 == 1){ //keyboard
                    M.bacaMatriks();
                    M.tulisMatriks();
                    M.Gauss();
                    System.out.println();
                    M.tulisMatriks();

                }
            } else if (pilihan2 == 2){
                pilihanInput();
                if (pilihan3 == 1){
                    M.bacaMatriks();
                    M.tulisMatriks();
                }
            }

        } else if (pilihan1 == 2){
            pilihanInput();
            if (pilihan3 == 1){
                M.bacaMatriks();
                System.out.println("Isi Matriks : ");
                M.tulisMatriks();
            }
        } else if (pilihan1 == 3){
            System.out.println("Terima Kasih");
        }



    }

    static void tampilMenu(){
        System.out.println("MENU");
        System.out.println();
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Interpolasi Polinom");
        System.out.println("3. Keluar\n");
        pilihan1 = keyboard.nextInt();
    }

    static void menuSPL(){
        System.out.println("Silahkan pilih metode yang ingin digunakan : ");
        System.out.println();
        System.out.println("1. Metode eleminasi Gauss");
        System.out.println("2. Metode eleminasi Gauss-Jordan\n");
        pilihan2 = keyboard.nextInt();
    }

    static void pilihanInput(){
        System.out.println("Input dari ...\n");
        System.out.println("1. Keyboard");
        System.out.println("2. File eksternal\n");
        pilihan3 = keyboard.nextInt();
    }

}
