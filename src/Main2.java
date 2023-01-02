import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

    public class Main2 {

        static Row[] rows;
        static Scanner scanner;
        static int colorN;
        static int[] R;
        static int[] G;
        static int[] B;
        static float[] colorSize;
        static float[] colorSizeMultiplier;
        static float[] colorX;
        static FileReader fileReader;
        static boolean file=false;
        static String path=new File("").getAbsolutePath();


        public Main2() throws FileNotFoundException {
        }

        public void setShift(){
            int l,r;
            System.out.println("Left shift: ");
            l=scanner.nextInt();
            if (file) System.out.print(l);
            System.out.println("Right shift: ");
            r= scanner.nextInt();
            if (file) System.out.print(r);
            for(int i=0;i<6;i++){
                rows[i].lShift=i*l/5;
                rows[i].rShift=(5-i)*r/5;
            }
        }



        public static void main(String[] args) throws IOException, InterruptedException {
            Main2 main2 = new Main2();

            path = path.concat("\\config.txt");
            rows = new Row[6];
            for (int i = 0; i < 6; i++) {
                rows[i] = new Row();
            }

            /*
            rows[0] = new Row(17,0,0);
            rows[1] = new Row(18,0,0);
            rows[2] = new Row(17,0,0);
            rows[3] = new Row(17,0,0);
            rows[4] = new Row(18,0,0);
            rows[5] = new Row(16,0,0);

             */

            for (int i = 0; i < 6; i++) {

                for (int k = 0; k < 21; k++) {
                    rows[i].switches[k] = new Switch();
                }
            }


            scanner = new Scanner(System.in);
            System.out.println("1. Poziomo\n2. Pionowo\n3. Na skos\n4. Z pliku");
            int x;
            x = scanner.nextInt();



            if (x == 4) {
                //File file1 = new File("D:/studia/Inzyneirka/spc_light/src/config.txt");
                File file1 = new File(path);
                System.out.println(path);

                scanner = new Scanner(file1);
                file = true;
                //fileReader = new FileReader("D:\\studia\\Inzyneirka\\spc_light\\src\\config.txt");
                x = scanner.nextInt();

            }
            System.out.print("Ile kolorow? ");
            colorN = scanner.nextInt();
            colorX = new float[colorN];
            colorSize = new float[colorN - 1];
            colorSizeMultiplier = new float[colorN - 1];
            R = new int[colorN];
            G = new int[colorN];
            B = new int[colorN];


            for (int i = 0; i < colorN; i++) {
                System.out.println("Color " + (i + 1));
                System.out.print("Size: ");
                colorX[i] = scanner.nextFloat();
                if (file) System.out.print(colorX[i]);
                System.out.print("Red: ");
                R[i] = scanner.nextInt();
                if (file) System.out.print(R[i]);
                System.out.print("Green: ");
                G[i] = scanner.nextInt();
                if (file) System.out.print(G[i]);
                System.out.print("Blue: ");
                B[i] = scanner.nextInt();
                if (file) System.out.print(B[i]);
            }


            for (int i = 0; i < colorN - 1; i++) {
                System.out.print("Zone " + (i + 1) + "size: ");
                colorSizeMultiplier[i] = scanner.nextFloat();
            }


            switch (x) {
                case 1:
                    main2.horizontail();
                    break;
                case 2:
                    main2.vertical();
                    break;
                case 3:
                    main2.setShift();
                    main2.horizontail();
                    break;
                default:
                    break;
            }

            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            for (int i = 0; i < 6; i++) {
                for (int k = 0; k < rows[i].size; k++) {
                    if (i == 5 && k == rows[5].size - 1) rows[i].switches[k].print(true);
                    else rows[i].switches[k].print(false);
                }
            }
            scanner = new Scanner(System.in);
            scanner.next();
        }

        void horizontail(){


            for (int i = 0; i < 6; i++) {
                float fullZoneSize=0;
                for (int k = 0; k < colorN - 1; k++) {

                    colorSize[k] = (Row.accSize+rows[i].lShift+rows[i].rShift) / (float) (colorN - 1) * colorSizeMultiplier[k];
                    fullZoneSize += colorSize[k];
                }

                float x = fullZoneSize / (Row.accSize+rows[i].lShift+rows[i].rShift);
                for (int k = 0; k < colorN - 1; k++) {
                    colorSize[k] /= x;
                    System.out.println("Color size " + k + ": " + colorSize[k]);
                }

                int keyI = 0;
                float j=rows[i].lShift;
                for (int k = 0; k < colorN - 1; k++) {

                    float r = R[k];
                    float g = G[k];
                    float b = B[k];
                    int colorI = 0;

                    float Rinc = (R[k + 1] - R[k]);
                    float Ginc = (G[k + 1] - G[k]);
                    float Binc = (B[k + 1] - B[k]);

                    /*
                    float Rinc = (R[k+1] - R[k]) / colorSize[k];
                    float Ginc = (G[k+1] - G[k]) / colorSize[k];
                    float Binc = (B[k+1] - B[k]) / colorSize[k];

                     */

                    for (; j < colorSize[k]; j++) {
                        //if(i==5 && keyI==3) j+=2;

                        //if(i==5 && keyI==3) j+=2;

                        float colorDif = colorX[k + 1] - colorX[k];
                        if (colorDif < -1) colorDif = -1;
                        else if (colorDif > 1) colorDif = 1;

                        /*
                        r = R[k] + (Rinc * j / (colorSize[k]));
                        g = G[k] + (Ginc * j / (colorSize[k]));
                        b = B[k] + (Binc * j / (colorSize[k]));

                         */
                        float j1 = j;
                        if (i == 0) {
                            if (keyI == 1) j++;
                            else if (keyI == 5) j += 0.5;
                            else if (keyI == 9) j += 0.5;
                        } else if (i == 1) {
                            if (keyI == 13) j += 0.5;
                            else if (keyI == 14) j += 0.5;
                        } else if (i == 2) {
                            if (keyI == 0) j += 0.25;
                            else if (keyI == 1) j += 0.25;
                            else if (keyI == 13) j += 0.25;
                            else if (keyI == 14) j += 0.25;
                        } else if (i == 3) {
                            if (keyI == 0) j += 0.4;
                            else if (keyI == 1) j += 0.4;
                            else if (keyI == 12) j += 0.6;
                            else if (keyI == 13) j += 0.6;
                        } else if (i == 4) {
                            if (keyI == 0) j += 0.55;
                            else if (keyI == 1) j += 0.55;
                            else if (keyI == 11) j += 0.95;
                            else if (keyI == 12) j += 1.95;
                        } else if (i == 5) {
                            if (keyI == 3) j += 2.52;
                            else if (keyI == 4) j += 2.8;
                            else if (keyI < 8 ) j += 0.28;

                        }
                        if(j >= colorSize[k]) {
                            j=j1 ;
                            break;
                        }

                        r = R[k] + (Rinc * j / (colorSize[k]) * (1 + ((colorSize[k] - 1 - j) / colorSize[k]) * colorDif));
                        g = G[k] + (Ginc * j / (colorSize[k]) * (1 + ((colorSize[k] - 1 - j) / colorSize[k]) * colorDif));
                        b = B[k] + (Binc * j / (colorSize[k]) * (1 + ((colorSize[k] - 1 - j) / colorSize[k]) * colorDif));


                        /*
                        r += (Rinc* ( (1 + colorDif) + (-2*colorDif*j / (colorSize[k]-1) )));
                        g += (Ginc* ( (1 + colorDif) + (-2*colorDif*j / (colorSize[k]-1) )));
                        b += (Binc* ( (1 + colorDif) + (-2*colorDif*j / (colorSize[k]-1) )));

                         */



                        if(r<0) r=0;
                        if(g<0) g=0;
                        if(b<0) b=0;
                        if(r>255) r=255;
                        if(g>255) g=255;
                        if(b>255) b=255;
                        rows[i].switches[keyI].red = (int) (r);
                        rows[i].switches[keyI].green = (int) (g);
                        rows[i].switches[keyI].blue = (int) (b);

                        keyI++;
                        if (keyI == 21) break;

                    }
                    System.out.println("j " + j);
                    j -= colorSize[k];
                    System.out.println("j " + j);
                }
                if(keyI<21){
                    rows[i].switches[keyI].red = R[colorN-1];
                    rows[i].switches[keyI].green = G[colorN-1];
                    rows[i].switches[keyI].blue = B[colorN-1];
                }
            }
        }

        void vertical(){
            int sizeI=0;
            for(int k=0;k<colorN-1;k++){
                colorSize[k]=0;
            }
            for (int k=0;k<5;k++){
                colorSize[sizeI]++;
                sizeI=(sizeI+1)%(colorN-1);
            }
            int keyI=0;
            int j=0;
            for(int k=0;k<colorN-1;k++){
                int r = R[k];
                int g = G[k];
                int b = B[k];
                int colorI=0;
                float Rinc = (R[k+1] - R[k]) / colorSize[k];
                float Ginc = (G[k+1] - G[k]) / colorSize[k];
                float Binc = (B[k+1] - B[k]) / colorSize[k];

                for(;j<colorSize[k];j++){
                    for(int i=0;i<21;i++){
                        rows[keyI].switches[i].red = (int) (r+j*Rinc);
                        rows[keyI].switches[i].green = (int) (g+j*Ginc);
                        rows[keyI].switches[i].blue = (int) (b+j*Binc);
                    }
                    keyI++;
                }
                j-=colorSize[k];
            }
            for(int i=0;i<21;i++){
                rows[keyI].switches[i].red = R[colorN-1];
                rows[keyI].switches[i].green = G[colorN-1];
                rows[keyI].switches[i].blue = B[colorN-1];
            }
        }



    }
