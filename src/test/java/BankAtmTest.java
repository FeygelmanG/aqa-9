import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BankAtmTest {

    //Добавить параметризацию и проверить остальные позитивные и негативные кейсы проверок карты и пароля
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldCheckPositiveTest(int count) {
        final boolean res = BankATM.checkCardAndPassword("6228123123", 888888, count);
        Assertions.assertTrue(res);
    }

    @ParameterizedTest
    @MethodSource("shouldCheckDataProvider")
    public void shouldCheckNegativeTest(String param1, int param2, int count) {
        final boolean res = BankATM.checkCardAndPassword(param1, param2, count);
        Assertions.assertFalse(res);
    }

    static Stream<Arguments> shouldCheckDataProvider() {
        return Stream.of(
                arguments("62281231", 888888, 1),
                arguments("6228123123", 888889, 1),
                arguments("6228123123", 1, 10)
        );
    }
    @Test
    public void shouldAuthPositiveTest() {
        final boolean res = BankATM.auth();
        Assertions.assertTrue(res);
    }


}
