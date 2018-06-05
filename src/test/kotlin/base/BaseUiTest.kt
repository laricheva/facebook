package base

import org.junit.AfterClass
import org.junit.BeforeClass

open class BaseUiTest: BaseUiTestClass() {

    companion object {
        const val QUEST_DELAY = 0.3f
        @BeforeClass @JvmStatic fun setUpTest() {
        }
        @AfterClass @JvmStatic fun tearDownTest() {
        }
    }
}
