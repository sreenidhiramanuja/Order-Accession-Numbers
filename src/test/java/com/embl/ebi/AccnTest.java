package com.embl.ebi;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AccnTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AccnTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AccnTest.class );
    }

    /**
     * Test methods
     */
    public void testApp()
    {
		OrderAccnNumberImpl testAccn = new OrderAccnNumberImpl();
		List<String> parseList = testAccn.Parse("ABCD000115");
		List<String> verifyList = new ArrayList<String>();
		verifyList.add("ABCD");
		verifyList.add("000115");
		assertEquals(verifyList, parseList);
    }
}
