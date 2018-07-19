import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yining on 7/17/18.
 */
public class linkedListTest {

    @Test
    public void testInsert() throws Exception {
        linkedList dLList = new linkedList();
        dLList.insert(1);
        dLList.insert(2);
        dLList.insert(3);
        dLList.insert(4);

    }
}