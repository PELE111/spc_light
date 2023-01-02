import java.io.IOException;
import java.util.Scanner;

    public class Main2 {

        static Row[] rows;
        static Scanner scanner;
        static int colorN;
        static int[] R;
        static int[] G;
        static int[] B;
        static float[] colorSize;
        static float[] colorX;

        public void setShift(){
            int l,r;
            System.out.println("Left shift: ");
            l=scanner.nextInt();
            System.out.println("Right shift: ");
            r= scanner.nextInt();
            for(int i=0;i<6;i++){
                rows[i].lShift=i*l/5;
                rows[i].rShift=(5-i)*r/5;
            }
        }



        public static void main(String[] args) throws IOException, InterruptedException {
            Main2 main2 = new Main2();

            rows = new Row[6];

            rows[0] = new Row(17,0,0);
            rows[1] = new Row(17,0,0);
            rows[2] = new Row(17,0,0);
            rows[3] = new Row(16,0,0);
            rows[4] = new Row(17,0,0);
            rows[5] = new Row(16,0,0);

            for (int i = 0; i < 6; i++) {

                for (int k = 0; k < 21; k++) {
                    rows[i].switches[k] = new Switch();
                }
            }



            scanner = new Scanner(System.in);
            System.out.println("1. Poziomo\n2. Pionowo\n3. Na skos");
            int x;
            x = scanner.nextInt();

            System.out.print("Ile kolorow? ");
            colorN = scanner.nextInt();
            R =new int[colorN];
            G =new int[colorN];
            B =new int[colorN];
            colorX=new float[colorN];

            for(int i=0;i<colorN;i++){
                System.out.println("Color " + (i+1));
                System.out.print("Size: ");
                colorX[i]=scanner.nextFloat();
                System.out.print("Red: ");
                R[i]=scanner.nextInt();
                System.out.print("Green: ");
                G[i]= scanner.nextInt();
                System.out.print("Blue: ");
                B[i]=scanner.nextInt();
            }

            colorSize = new float[colorN-1];



            switch (x){
                case 1 : main2.horizontail();
                break;
                case 2 : main2.vertical();
                break;
                case 3:
                    main2.setShift();
                    main2.diagonal();
                default : break;
            }

            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            for (int i = 0; i < 6; i++) {
                for (int k = 0; k < rows[i].size; k++) {
                    if(i== 5 && k==rows[5].size-1) rows[i].switches[k].print(true);
                    else rows[i].switches[k].print(false);
                }
            }
            scanner.next();
        }

        void diagonal(){
            for (int i = 0; i < 6; i++) {
                int sizeI=0;
                for(int k=0;k<colorN-1;k++){
                    colorSize[k]=0;
                }
                for (int k=0;k<(rows[i].accSize-1+rows[i].lShift+rows[i].rShift);k++){
                    colorSize[sizeI]++;
                    sizeI=(sizeI+1)%(colorN-1);
                }

                //int colorSize = rows[i].accSize/(colorN-1)+1;
                int keyI=0;
                float j=rows[i].lShift;
                for(int k=0;k<colorN-1;k++){
                    float r = R[k];
                    float g = G[k];
                    float b = B[k];
                    int colorI=0;
                    float Rinc = (R[k+1] - R[k]) ;
                    float Ginc = (G[k+1] - G[k]) ;
                    float Binc = (B[k+1] - B[k]) ;

                    for(;j<colorSize[k];j++){
                        //if(i==5 && keyI==3) j+=2;

                        //if(i==5 && keyI==3) j+=2;

                        float colorDif=colorX[k+1]-colorX[k];
                        if(colorDif<-1) colorDif=-1;
                        else if(colorDif >1) colorDif =1;

                        r = R[k] + (Rinc * j / (colorSize[k]) * (1 +((colorSize[k]-1-j)/colorSize[k]) * colorDif ) );
                        g = G[k] + (Ginc * j / (colorSize[k]) * (1 +((colorSize[k]-1-j)/colorSize[k]) * colorDif ) );
                        b = B[k] + (Binc * j / (colorSize[k]) * (1 +((colorSize[k]-1-j)/colorSize[k]) * colorDif ) );


                        rows[i].switches[keyI].red = (int) r;
                        rows[i].switches[keyI].green = (int) g;
                        rows[i].switches[keyI].blue = (int) b;
                        keyI++;

                        if(i==0 && keyI==1) j++;
                        if(i==4 && keyI==12) j+=2;
                        if(i==4 && keyI==1) j++;
                        if(i==5 && keyI==4) j+=3;
                        if(i==5 && keyI==3) j+=2;

                        if(keyI==20) break;
                    }
                    j-=colorSize[k];
                }
                rows[i].switches[keyI].red = R[colorN-1];
                rows[i].switches[keyI].green = G[colorN-1];
                rows[i].switches[keyI].blue = B[colorN-1];
            }
        }

        void horizontail(){
            for (int i = 0; i < 6; i++) {
                int sizeI=0;
                for(int k=0;k<colorN-1;k++){
                    colorSize[k]=0;
                }
                for (int k=0;k<rows[i].accSize-1;k++){
                    colorSize[sizeI]++;
                    sizeI=(sizeI+1)%(colorN-1);
                }

                //int colorSize = rows[i].accSize/(colorN-1)+1;
                int keyI=0;
                int j=0;
                for(int k=0;k<colorN-1;k++){
                    float r = R[k];
                    float g = G[k];
                    float b = B[k];
                    int colorI=0;

                    float Rinc = (R[k+1] - R[k]) ;
                    float Ginc = (G[k+1] - G[k]) ;
                    float Binc = (B[k+1] - B[k]) ;

                    /*
                    float Rinc = (R[k+1] - R[k]) / colorSize[k];
                    float Ginc = (G[k+1] - G[k]) / colorSize[k];
                    float Binc = (B[k+1] - B[k]) / colorSize[k];

                     */

                    for(;j<colorSize[k];j++){
                        //if(i==5 && keyI==3) j+=2;

                        //if(i==5 && keyI==3) j+=2;

                        float colorDif=colorX[k+1]-colorX[k];
                        if(colorDif<-1) colorDif=-1;
                        else if(colorDif >1) colorDif =1;

                        r = R[k] + (Rinc * j / (colorSize[k]) * (1 +((colorSize[k]-1-j)/colorSize[k]) * colorDif ) );
                        g = G[k] + (Ginc * j / (colorSize[k]) * (1 +((colorSize[k]-1-j)/colorSize[k]) * colorDif ) );
                        b = B[k] + (Binc * j / (colorSize[k]) * (1 +((colorSize[k]-1-j)/colorSize[k]) * colorDif ) );

                        /*
                        r += (Rinc* ( (1 + colorDif) + (-2*colorDif*j / (colorSize[k]-1) )));
                        g += (Ginc* ( (1 + colorDif) + (-2*colorDif*j / (colorSize[k]-1) )));
                        b += (Binc* ( (1 + colorDif) + (-2*colorDif*j / (colorSize[k]-1) )));

                         */

                        rows[i].switches[keyI].red = (int) r;
                        rows[i].switches[keyI].green = (int) g;
                        rows[i].switches[keyI].blue = (int) b;




                        keyI++;

                        if(i==0 && keyI==1) j++;
                        if(i==4 && keyI==12) j+=2;
                        if(i==4 && keyI==1) j++;
                        if(i==5 && keyI==4) j+=3;
                        if(i==5 && keyI==3) j+=2;

                        //if(keyI==21) break;
                    }
                    j-=colorSize[k];
                }
                rows[i].switches[keyI].red = R[colorN-1];
                rows[i].switches[keyI].green = G[colorN-1];
                rows[i].switches[keyI].blue = B[colorN-1];
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
