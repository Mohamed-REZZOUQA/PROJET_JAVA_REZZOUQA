public enum Direction {
    NONE(0), NORTH(2),SOUTH(0),EAST(3),WEST(1);
    private int frameLineNumber;

    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}
