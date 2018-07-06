package learning;

public class Enums {

    public static void main(String[] args) {
        // Enums
        System.out.println(SUITE.valueOf("HEART"));
        System.out.println(SUITE.HEART.getSuite(0));
    }
}


enum SUITE {
    HEART(1),
    SPADE(2),
    CLUB(3),
    DIAMOND(4);

    int value;

    SUITE(int i) {
        this.value = i;
    }

    public SUITE getSuite(int i) {
        switch (i) {
            case 0: return SUITE.HEART;
        }
        return SUITE.CLUB;
    }
}
