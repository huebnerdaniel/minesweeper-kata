import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MineFieldTest {

    @Test
    void mine_field_with_no_cells_has_no_hints() {
        MineField minefield = new MineField(0,0);
        assertThat(minefield.getHints()).isEqualTo("");
    }

    @Test
    void minefield_with_one_non_mine_cell() {
        MineField minefield = new MineField(1,1);
        assertThat(minefield.getHints()).isEqualTo(" ");
    }

    @Test
    void minefield_with_one_mine_cell() {
        MineField minefield = new MineField(1,1);
        minefield.placeMine(new MineField.Cell(0,0));
        assertThat(minefield.getHints()).isEqualTo("*");
    }

    @Test
    void big_minefield_with_no_mines_should_have_line_breaks() {
        MineField minefield = new MineField(4,5);
        assertThat(minefield.getHints()).isEqualTo("     \n     \n     \n     ");
    }

    @Test
    void two_cell_minefield_1() {
        MineField minefield = new MineField(1, 2);
        minefield.placeMine(new MineField.Cell(0,0));
        assertThat(minefield.getHints()).isEqualTo("*1");
    }

    @Test
    void two_cell_minefield_2() {
        MineField minefield = new MineField(1, 2);
        minefield.placeMine(new MineField.Cell(0,1));
        assertThat(minefield.getHints()).isEqualTo("1*");
    }

    @Test
    void _3_x_3_field_with_mine_in_the_middle() {
        MineField minefield = new MineField(3, 3);
        minefield.placeMine(new MineField.Cell(1,1));
        assertThat(minefield.getHints()).isEqualTo("111\n1*1\n111");
    }

    @Test
    void _3_x_3_field_with_two_mines() {
        MineField minefield = new MineField(3, 3);
        minefield.placeMine(new MineField.Cell(1,0));
        minefield.placeMine(new MineField.Cell(1,1));
        assertThat(minefield.getHints()).isEqualTo("221\n**1\n221");
    }

    @Test
    void minefield_can_not_have_negative_length() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new MineField(-1, 0);
        });
    }

    @Test
    void minefield_can_not_have_negative_width() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new MineField(1, -12);
        });
    }
}
