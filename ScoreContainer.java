public class ScoreContainer implements Comparable<ScoreContainer> {
    private String name;
    private Integer score;
    public ScoreContainer(String name, Integer score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        return this.score;
    }

    @Override
    public int compareTo(ScoreContainer o) {
        return (this.getScore().compareTo(o.getScore()));
    }

    @Override
    public String toString() {
        return getName() + "    " + getScore();
    }


}
