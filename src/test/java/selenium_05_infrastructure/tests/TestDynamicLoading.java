package selenium_05_infrastructure.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import selenium_05_infrastructure.pageobjects.DynamicLoading;


public class TestDynamicLoading extends Base {

    private DynamicLoading dynamicLoading;

    @Before
    public void setUp() {
        dynamicLoading = new DynamicLoading(driver);
    }

    @Test
    public void hiddenElementLoads() {
        dynamicLoading.loadExample("1");
        assertTrue("finish text didn't display after loading",
                dynamicLoading.isFinishTextDisplayed());
    }

    @Test
    public void elementAppears() {
        dynamicLoading.loadExample("2");
        assertTrue("finish text didn't render after loading",
                dynamicLoading.isFinishTextTextPresent());
    }
}
