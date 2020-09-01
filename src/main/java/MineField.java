import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

public class MineField {

    private final int length;
    private final int width;
    private Collection<Cell> mines;

    public MineField(final int length, final int width) {
        if (length < 0) {
            throw new IllegalArgumentException("length can not be negative");
        }
        if (width < 0) {
            throw new IllegalArgumentException("width can not be negative");
        }
        this.length = length;
        this.width = width;
        this.mines = new ArrayList<>(length * width);
    }

    public String getHints() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append("\n");
            }
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(i, j);
                if (mines.contains(cell)) {
                    sb.append("*");
                } else {
                    long mineNeighbourCount = getNeighbourCells(cell).stream()
                            .filter(neighour -> mines.contains(neighour))
                            .count();
                    if (mineNeighbourCount > 0) {
                        sb.append(mineNeighbourCount);
                    } else {
                        sb.append(" ");
                    }
                }
            }
        }

        return sb.toString();
    }

    private Collection<Cell> getNeighbourCells(Cell cell) {
        List<Cell> neighbours = new ArrayList<>(8);
        neighbours.add(new Cell(cell.x-1, cell.y-1));
        neighbours.add(new Cell(cell.x-1, cell.y));
        neighbours.add(new Cell(cell.x-1, cell.y+1));
        neighbours.add(new Cell(cell.x, cell.y-1));
        neighbours.add(new Cell(cell.x, cell.y+1));
        neighbours.add(new Cell(cell.x+1, cell.y-1));
        neighbours.add(new Cell(cell.x+1, cell.y));
        neighbours.add(new Cell(cell.x+1, cell.y+1));
        return neighbours;
    }

    public void placeMine(final Cell cell) {
        this.mines.add(cell);
    }

    @Data
    public static class Cell {
        private final int x, y;
    }

}
