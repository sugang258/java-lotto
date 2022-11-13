package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.User;
import lotto.view.InputView;
import lotto.view.OutputView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 유효한_숫자_입력() {
        User user = new User();
        boolean result = user.isNumber("12345");
        assertThat(result).isEqualTo(true);
    }

    @DisplayName("숫자가 아닌 값을 입력하면 예외가 발생한다.")
    @Test
    void 유효하지않는_숫자_입력() {
        User user = new User();
        assertThatThrownBy(() -> {
            user.isNumber("hello");
        }).isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 유효한_단위_입력() {
        User user = new User();
        boolean result = user.unitError("3000");
        assertThat(result).isEqualTo(true);
    }

    @DisplayName("1000원 단위가 아닌 값을 입력하면 예외가 발생한다.")
    @Test
    void 유효하지않는_단위_입력() {
        User user = new User();
        assertThatThrownBy(() -> {
            user.unitError("3300");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 로또_갯수() {
        User user = new User();
        int count = user.lottoCount("5000");
        assertThat(count).isEqualTo(5);

    }

    @Test
    void 유효한_당첨번호_중복_입력() {
        Lotto lotto = new Lotto();
        List<Integer> num = List.of(1,2,3,4,5,6);
        boolean result = lotto.isEqualNumber(num);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void 유효하지않은_당첨번호_중복_입력() {
        Lotto lotto = new Lotto();
        List<Integer> num = List.of(1,2,3,4,5,5);
        assertThatThrownBy(() -> {
            lotto.isEqualNumber(num);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 유효한_당첨번호_범위_입력() {
        Lotto lotto = new Lotto();
        List<Integer> num = List.of(1,2,3,4,5,6);
        boolean result = lotto.isRangeNumber(num);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void 유효하지않은_당첨범호_범위_입력(){
        Lotto lotto = new Lotto();
        List<Integer> num = List.of(1,2,3,4,5,67);
        assertThatThrownBy(() -> {
            lotto.isRangeNumber(num);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 유효한_보너스번호_입력(){
        BonusNumber bonusNumber = new BonusNumber();
        boolean result = bonusNumber.isNumber("1");
        assertThat(result).isEqualTo(true);
    }

    @Test
    void 유효하지않은_보너스번호_입력(){
        BonusNumber bonusNumber = new BonusNumber();
        assertThatThrownBy(() -> {
            bonusNumber.isNumber("hello");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 유효하지않은_보너스번호_범위_입력(){
        BonusNumber bonusNumber = new BonusNumber();
        assertThatThrownBy(() -> {
            bonusNumber.RangeNumber("65");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 유효하지않은_보너스번호_중복_입력(){
        List<Integer> num = List.of(1,2,3,4,5,67);
        BonusNumber bonusNumber = new BonusNumber();
        assertThatThrownBy(() -> {
            bonusNumber.isEqualNumber("1",num);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
