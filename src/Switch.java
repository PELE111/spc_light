public class Switch {
    int alpha = 255;
    int blue = 0;
    int green = 0;
    int red = 0;

    void print(boolean last){
        System.out.println("{");
        System.out.println("\"Alpha\" : " + alpha + ",");
        System.out.println("\"Blue\" : " + blue + ",");
        System.out.println("\"Green\" : " + green + ",");
        System.out.println("\"Red\" : " + red);
        if(!last) System.out.println("},");
        else System.out.println("}");
}

}
