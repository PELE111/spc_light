public class Row {
    Switch[] switches = new Switch[21];
    int size = 21;
    int accSize = 21;
    int lShift=0;
    int rShift=0;

    Row(int accSize, int l, int r){
        this.accSize=accSize;
        this.lShift=l;
        this.rShift=r;
    }
}
