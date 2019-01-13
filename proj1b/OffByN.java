public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char a, char b) {
        return (Math.abs(a - b) == n);
    }
}
