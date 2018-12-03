package domain;

public class Claim {

    private final int id;
    private final int inchesToLeftEdge;

    public int getInchesToLeftEdge() {
        return inchesToLeftEdge;
    }

    public int getInchesToTopEdge() {
        return inchesToTopEdge;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private final int inchesToTopEdge;
    private final int width;
    private final int height;

    private Claim(int id, int inchesToLeftEdge, int inchesToTopEdge, int width, int height) {
        this.id = id;
        this.inchesToLeftEdge = inchesToLeftEdge;
        this.inchesToTopEdge = inchesToTopEdge;
        this.width = width;
        this.height = height;
    }

    int getId() {
        return id;
    }

    public static class ClaimBuilder {

        private int id;
        private int inchesToLeftEdge;
        private int inchesToTopEdge;
        private int width;
        private int height;

        public ClaimBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ClaimBuilder inchesToLeftEdge(int inchesToLeftEdge) {
            this.inchesToLeftEdge = inchesToLeftEdge;
            return this;
        }

        public ClaimBuilder inchesToTopEdge(int inchesToTopEdge) {
            this.inchesToTopEdge = inchesToTopEdge;
            return this;
        }

        public ClaimBuilder width(int width) {
            this.width = width;
            return this;
        }

        public ClaimBuilder height(int height) {
            this.height = height;
            return this;
        }


        public Claim build() {
            Claim claim = new Claim(id, inchesToLeftEdge, inchesToTopEdge, width, height);
            return claim;
        }
    }
}
