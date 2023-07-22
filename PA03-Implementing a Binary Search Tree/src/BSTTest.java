import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BSTTest {

    @Test
    void testEmptyConstructor() {
        // Create a new BST object using the empty constructor
        BST bst = new BST();

        // Assert that the root of the BST is null
        Assertions.assertNull(bst.root);
    }

    @Test
    void testInsert() {
        BST bst = new BST();
        bst.insert(100);
        bst.insert(50);
        bst.insert(150);
        bst.insert(25);
        bst.insert(125);
        bst.insert(180);

        // Check if elements are inserted correctly
        Assertions.assertTrue(bst.contains(100));
        Assertions.assertTrue(bst.contains(50));
        Assertions.assertTrue(bst.contains(150));
        Assertions.assertTrue(bst.contains(25));
        Assertions.assertTrue(bst.contains(125));
        Assertions.assertTrue(bst.contains(180));
    }

    @Test
    void testContains() {
        BST bst = new BST();
        bst.insert(100);
        bst.insert(50);
        bst.insert(150);
        bst.insert(25);
        bst.insert(125);
        bst.insert(180);

        // Check if contains method works as expected
        Assertions.assertTrue(bst.contains(100));
        Assertions.assertTrue(bst.contains(50));
        Assertions.assertTrue(bst.contains(150));
        Assertions.assertTrue(bst.contains(25));
        Assertions.assertTrue(bst.contains(125));
        Assertions.assertTrue(bst.contains(180));

        // Check for elements not present in the BST
        Assertions.assertFalse(bst.contains(10));
        Assertions.assertFalse(bst.contains(200));
    }

    @Test
    void testDelete() {
        BST bst = new BST();
        bst.insert(100);
        bst.insert(50);
        bst.insert(150);
        bst.insert(25);
        bst.insert(125);
        bst.insert(180);

        bst.delete(150);
        Assertions.assertFalse(bst.contains(150));

        bst.delete(25);
        Assertions.assertFalse(bst.contains(25));

        // Delete root node
        bst.delete(100);
        Assertions.assertFalse(bst.contains(100));
        Assertions.assertTrue(bst.contains(50));
        Assertions.assertTrue(bst.contains(125));
        Assertions.assertTrue(bst.contains(180));
    }

    @Test
    void testToString() {
        BST bst = new BST();
        bst.insert(100);
        bst.insert(50);
        bst.insert(150);
        bst.insert(25);
        bst.insert(125);
        bst.insert(180);

        String expected = "25, 50, 100, 125, 150, 180"; // Add commas between elements
        Assertions.assertEquals(expected, bst.toString());

        bst.delete(25);
        expected = "50, 100, 125, 150, 180"; // Add commas between elements
        Assertions.assertEquals(expected, bst.toString());

        bst.delete(100);
        expected = "50, 125, 150, 180"; // Add commas between elements
        Assertions.assertEquals(expected, bst.toString());
    }

    @Test
    void testRebalance() {
        BST bst = new BST();
        bst.insert(100);
        bst.insert(50);
        bst.insert(150);
        bst.insert(25);
        bst.insert(125);
        bst.insert(180);

        BST rebalancedBST = bst.rebalance();

        String expectedRebalanced = "25, 50, 100, 125, 150, 180";
        Assertions.assertEquals(expectedRebalanced, rebalancedBST.toString());
    }
    @Test
    void testPartition() {
        BST bst = new BST();
        bst.insert(100);
        bst.insert(50);
        bst.insert(150);
        bst.insert(25);
        bst.insert(125);
        bst.insert(180);

        List<Integer> partitionedValues = bst.partition(100);

        List<Integer> expectedPartitioned = Arrays.asList(100, 125, 150, 180);
        Assertions.assertEquals(expectedPartitioned, partitionedValues);

        partitionedValues = bst.partition(140);
        expectedPartitioned = Arrays.asList(150, 180);
        Assertions.assertEquals(expectedPartitioned, partitionedValues);
    }

}

