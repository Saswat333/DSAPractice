import Array.LeftRightSum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeftRightSumTest {
    @Test
    public void successTestCase01(){
        int[] arr = {2,3,4,5,1,4,0,2,8};
        int expectedResult = 1;

        LeftRightSum obj = new LeftRightSum();
        int actualResult = obj.findSplitPoint(arr);

        assertSame(expectedResult, actualResult); // expected, actual
    }
}
