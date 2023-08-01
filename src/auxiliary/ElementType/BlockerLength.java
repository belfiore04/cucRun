package auxiliary.ElementType;

import auxiliary.CommonUtils;

public enum BlockerLength {
    SHORT(80),
    MEDIUM(150),
    LONG(230);
    private int length;
    private int width = 40;
    BlockerLength(int length){
        this.length = length;
    }
    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public static BlockerLength getRandomLength(){
        int i = CommonUtils.getRandomNumBetween(0,2);
        for(BlockerLength blockerLength : BlockerLength.values()){
            if (blockerLength.ordinal() == i)
                return blockerLength;
        }
        return null;
    }
}
